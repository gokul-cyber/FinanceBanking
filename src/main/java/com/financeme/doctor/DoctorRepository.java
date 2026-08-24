package com.financeme.doctor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByDoctorNameContainingIgnoreCase(String doctorName);
}
