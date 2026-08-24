package com.financeme.doctor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {
    private final DoctorRepository repository;

    public DoctorController(DoctorRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/registerDoctor")
    public ResponseEntity<Doctor> registerDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(doctor));
    }

    @PutMapping("/updateDoctor/{doctorRegNo}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long doctorRegNo, @RequestBody Doctor updated) {
        return repository.findById(doctorRegNo)
                .map(doctor -> {
                    doctor.updateFrom(updated);
                    return ResponseEntity.ok(repository.save(doctor));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchDoctor/{doctorName}")
    public List<Doctor> searchDoctor(@PathVariable String doctorName) {
        return repository.findByDoctorNameContainingIgnoreCase(doctorName);
    }

    @DeleteMapping("/deletePolicy/{doctorRegNo}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long doctorRegNo) {
        if (!repository.existsById(doctorRegNo)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(doctorRegNo);
        return ResponseEntity.noContent().build();
    }
}
