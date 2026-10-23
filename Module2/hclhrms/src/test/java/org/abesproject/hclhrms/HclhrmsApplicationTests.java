package org.abesproject.hclhrms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HclhrmsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void employeeCrudEndpointsWork() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                "HRMS Employee API is running. Use /api/employees to manage employees."));

    mockMvc.perform(post("/api/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"empname":"Aayush","designation":"Developer",\
                "progStack":"Java","salary":60000}
                """))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.empid").value("1"));

    mockMvc.perform(get("/api/employees/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.empname").value("Aayush"));

    mockMvc.perform(put("/api/employees/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"empname":"Aayush Updated","designation":"Lead",\
                "progStack":"Java","salary":75000}
                """))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.empname").value("Aayush Updated"));

    mockMvc.perform(get("/api/employees"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].empid").value("1"));

    mockMvc.perform(delete("/api/employees/1"))
        .andExpect(status().isNoContent());

    mockMvc.perform(get("/api/employees/1"))
        .andExpect(status().isNotFound());
    }

}
