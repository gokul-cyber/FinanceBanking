package com.financeme.doctor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void searchSeededDoctor() throws Exception {
        mockMvc.perform(get("/searchDoctor/Aarav"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].doctorName").value("Aarav Mehta"));
    }

    @Test
    void registerUpdateAndDeleteDoctor() throws Exception {
        String doctor = "{\"doctorRegNo\":3001,\"doctorName\":\"Maya Chen\",\"specialization\":\"Oncology\",\"licenseno\":\"MED-ON-3001\",\"experience\":8,\"phone\":\"2125550301\",\"email\":\"maya.chen@medicure.example\"}";
        String update = "{\"doctorRegNo\":3001,\"doctorName\":\"Maya Chen\",\"specialization\":\"Surgical Oncology\",\"licenseno\":\"MED-ON-3001\",\"experience\":9,\"phone\":\"2125550301\",\"email\":\"maya.chen@medicure.example\"}";

        mockMvc.perform(post("/registerDoctor").contentType(MediaType.APPLICATION_JSON).content(doctor))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.doctorName").value("Maya Chen"));
        mockMvc.perform(put("/updateDoctor/3001").contentType(MediaType.APPLICATION_JSON).content(update))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.specialization").value("Surgical Oncology"));
        mockMvc.perform(delete("/deletePolicy/3001"))
                .andExpect(status().isNoContent());
        mockMvc.perform(put("/updateDoctor/9999").contentType(MediaType.APPLICATION_JSON).content(update))
                .andExpect(status().isNotFound());
        mockMvc.perform(delete("/deletePolicy/9999"))
                .andExpect(status().isNotFound());
    }
}
