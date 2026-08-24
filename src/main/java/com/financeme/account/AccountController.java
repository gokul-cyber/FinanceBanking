package com.financeme.account;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AccountController {
    private final AccountRepository repository;

    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
                "application", "FinanceMe",
                "status", "running",
                "endpoints", List.of(
                        "POST /createAccount",
                        "PUT /updateAccount/{accountNo}",
                        "GET /viewPolicy/{accountNo}",
                        "DELETE /deletePolicy/{accountNo}",
                        "GET /accounts"));
    }

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello from CBS Bank";
    }

    @GetMapping("/createAccount")
    public Account createSampleAccount() {
        return repository.save(new Account(1010101010L, "Shubham", "Saving Account", 20000.0));
    }

    @PostMapping("/registerAccount")
    public Account registerAccount(@RequestBody Account account) {
        return repository.save(account);
    }

    @GetMapping("/getAccount/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountNumber) {
        return repository.findById(accountNumber)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(account));
    }

    @PutMapping("/updateAccount/{accountNo}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountNo, @RequestBody Account updated) {
        return repository.findById(accountNo)
                .map(account -> {
                    account.updateFrom(updated);
                    return ResponseEntity.ok(repository.save(account));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/viewPolicy/{accountNo}")
    public ResponseEntity<Account> viewPolicy(@PathVariable Long accountNo) {
        return repository.findById(accountNo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteAccount/{accountNo}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long accountNo) {
        if (!repository.existsById(accountNo)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(accountNo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/accounts")
    public List<Account> accounts() {
        return repository.findAll();
    }
}
