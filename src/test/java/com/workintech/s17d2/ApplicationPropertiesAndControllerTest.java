package com.workintech.s17d2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.Experience;
import com.workintech.s17d2.rest.DeveloperController;
import com.workintech.s17d2.tax.DeveloperTax;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = {ApplicationPropertiesAndControllerTest.class, DeveloperController.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationPropertiesAndControllerTest {



    @Autowired
    private Environment env;
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
    @DisplayName("application properties istenilenler eklendi mi?")
    void serverPortIsSetTo8585() {

        String serverPort = env.getProperty("server.port");
        assertThat(serverPort).isEqualTo("8585");

        String contextPath = env.getProperty("server.servlet.context-path");
        assertNotNull(contextPath);
        assertThat(contextPath).isEqualTo("/workintech");

        //management.info.env.enabled
        String managementInfoEnvEnabled = env.getProperty("management.info.env.enabled");
        assertNotNull(managementInfoEnvEnabled);
        assertThat(managementInfoEnvEnabled).isEqualTo("true");


        //management.endpoints.web.exposure.include
        String managementEndpointsWebExposureInclude = env.getProperty("management.endpoints.web.exposure.include");
        assertNotNull(managementEndpointsWebExposureInclude);
        assertThat(managementEndpointsWebExposureInclude).isEqualTo("info,health,mappings");


        String infoAppName= env.getProperty("info.app.name");
        assertNotNull(infoAppName);
        //info.app.description
        String infoAppDescription= env.getProperty("info.app.description");
        assertNotNull(infoAppDescription);

        //info.app.version
        String infoAppVersion= env.getProperty("info.app.version");
        assertNotNull(infoAppVersion);

    }

    @Test
    @DisplayName("DeveloperController:DeveloperMapCheck")
    @Order(1)
    void developersMapShouldNotBeNullAfterInitialization() {
        assertNotNull(controller.developers, "The developers map should be initialized (not null) after @PostConstruct");
    }

    @Test
    @DisplayName("DeveloperController:AddDeveloper")
    @Order(2)
    void testAddDeveloper() throws Exception {
        Developer newDeveloper = new Developer(2, "New Developer", 6000.0, Experience.MID);
        mockMvc.perform(post("/developers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newDeveloper)))
                .andExpect(status().isCreated());
    }

    @Test
   @DisplayName("DeveloperController:GetAllDevelopers")
    @Order(3)
    void testGetAllDevelopers() throws Exception {
        mockMvc.perform(get("/developers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    @DisplayName("DeveloperController:GetDeveloperById")
    @Order(4)
    void testGetDeveloperById() throws Exception {
        mockMvc.perform(get("/developers/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DeveloperController:UpdateDeveloper")
    @Order(5)
    void testUpdateDeveloper() throws Exception {
        Developer updatedDeveloper = new Developer(1, "Updated Developer", 7000.0, Experience.SENIOR);
        mockMvc.perform(put("/developers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDeveloper)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DeveloperController:DeleteDeveloper")
    @Order(6)
    void testDeleteDeveloper() throws Exception {
        mockMvc.perform(delete("/developers/{id}", 1))
                .andExpect(status().isOk());
    }
}


