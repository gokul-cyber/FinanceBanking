package com.financeme.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Account {
    @Id
    @JsonAlias("accountNumber")
    private Long accountNo;
    @JsonAlias("accountName")
    private String customerName;
    @JsonAlias("accountType")
    private String policy;
    @JsonAlias("accountBalance")
    private double balance;

    protected Account() {
    }

    public Account(Long accountNo, String customerName, String policy, double balance) {
        this.accountNo = accountNo;
        this.customerName = customerName;
        this.policy = policy;
        this.balance = balance;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    @JsonProperty("accountNumber")
    public Long getAccountNumber() {
        return accountNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    @JsonProperty("accountName")
    public String getAccountName() {
        return customerName;
    }

    public String getPolicy() {
        return policy;
    }

    @JsonProperty("accountType")
    public String getAccountType() {
        return policy;
    }

    public double getBalance() {
        return balance;
    }

    @JsonProperty("accountBalance")
    public double getAccountBalance() {
        return balance;
    }

    public void updateFrom(Account updated) {
        this.customerName = updated.customerName;
        this.policy = updated.policy;
        this.balance = updated.balance;
    }
}
