package com.financeme.account;

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
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void viewSeededPolicy() throws Exception {
        mockMvc.perform(get("/viewPolicy/1001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Rakshith"));
    }

    @Test
    void createUpdateAndDeleteAccount() throws Exception {
        String account = "{\"accountNo\":3001,\"customerName\":\"Test User\",\"policy\":\"Credit Card\",\"balance\":5000}";
        String update = "{\"accountNo\":3001,\"customerName\":\"Updated User\",\"policy\":\"Investment\",\"balance\":7000}";

        mockMvc.perform(post("/createAccount").contentType(MediaType.APPLICATION_JSON).content(account))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountNo").value(3001));
        mockMvc.perform(put("/updateAccount/3001").contentType(MediaType.APPLICATION_JSON).content(update))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Updated User"));
        mockMvc.perform(delete("/deletePolicy/3001"))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/viewPolicy/3001"))
                .andExpect(status().isNotFound());
    }
}
