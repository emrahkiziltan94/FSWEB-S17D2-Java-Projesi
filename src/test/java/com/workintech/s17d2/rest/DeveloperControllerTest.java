package com.workintech.s17d2.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.Experience;
import com.workintech.s17d2.tax.DeveloperTax;
import com.workintech.s17d2.utils.Constants;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.Experience;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DeveloperController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeveloperControllerTest {

    private DeveloperController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeveloperTax developerTax;

    @BeforeEach
    void setup() throws Exception {
        controller = new DeveloperController(new DeveloperTax());
        // Simulate @PostConstruct call if necessary. In reality, this is managed by Spring.
        controller.init();
        Developer developer = new Developer(1, "Initial Developer", 5000.0, Experience.JUNIOR);
        mockMvc.perform(post("/developers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(developer)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(1)
    void developersMapShouldNotBeNullAfterInitialization() {
        assertNotNull(controller.developers, "The developers map should be initialized (not null) after @PostConstruct");
    }

    @Test
    @Order(2)
    void testAddDeveloper() throws Exception {
        Developer newDeveloper = new Developer(2, "New Developer", 6000.0, Experience.MID);
        mockMvc.perform(post("/developers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newDeveloper)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    void testGetAllDevelopers() throws Exception {
        mockMvc.perform(get("/developers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    @Order(4)
    void testGetDeveloperById() throws Exception {
        mockMvc.perform(get("/developers/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void testUpdateDeveloper() throws Exception {
        Developer updatedDeveloper = new Developer(1, "Updated Developer", 7000.0, Experience.SENIOR);
        mockMvc.perform(put("/developers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDeveloper)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    void testDeleteDeveloper() throws Exception {
        mockMvc.perform(delete("/developers/{id}", 1))
                .andExpect(status().isOk());
    }
}
