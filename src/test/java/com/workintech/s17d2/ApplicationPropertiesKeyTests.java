package com.workintech.s17d2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class ApplicationPropertiesKeyTests {

    @Autowired
    private Environment env;

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
}


