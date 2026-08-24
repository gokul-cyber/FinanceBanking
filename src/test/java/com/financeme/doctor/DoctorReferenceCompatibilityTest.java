package com.financeme.doctor;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class DoctorReferenceCompatibilityTest {
    @Test
    public void exposesExpectedDoctorProperties() {
        Doctor doctor = new Doctor(1L, "Test Doctor", "Cardiology", "LIC-1", 10, "2125550000", "doctor@medicure.example");

        assertEquals(doctor.getDoctorRegNo().longValue(), 1L);
        assertEquals(doctor.getDoctorName(), "Test Doctor");
        assertEquals(doctor.getSpecialization(), "Cardiology");
        assertEquals(doctor.getLicenseno(), "LIC-1");
        assertEquals(doctor.getExperience(), 10);
        assertEquals(doctor.getPhone(), "2125550000");
        assertEquals(doctor.getEmail(), "doctor@medicure.example");
    }
}
