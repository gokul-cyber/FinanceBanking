package com.financeme.doctor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Doctor {
    @Id
    private Long doctorRegNo;
    private String doctorName;
    private String specialization;
    private String licenseno;
    private int experience;
    private String phone;
    private String email;

    protected Doctor() {
    }

    public Doctor(Long doctorRegNo, String doctorName, String specialization, String licenseno,
            int experience, String phone, String email) {
        this.doctorRegNo = doctorRegNo;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.licenseno = licenseno;
        this.experience = experience;
        this.phone = phone;
        this.email = email;
    }

    public Long getDoctorRegNo() { return doctorRegNo; }
    public String getDoctorName() { return doctorName; }
    public String getSpecialization() { return specialization; }
    public String getLicenseno() { return licenseno; }
    public int getExperience() { return experience; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void updateFrom(Doctor updated) {
        this.doctorName = updated.doctorName;
        this.specialization = updated.specialization;
        this.licenseno = updated.licenseno;
        this.experience = updated.experience;
        this.phone = updated.phone;
        this.email = updated.email;
    }
}
