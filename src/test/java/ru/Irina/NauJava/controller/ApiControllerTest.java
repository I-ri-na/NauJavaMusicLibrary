package ru.Irina.NauJava.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@SpringBootTest
@ActiveProfiles("test")
class ApiControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build());
    }

    @Test
    void getRegistration_ShouldReturn200() {
        given()
                .when()
                .get("/registration")
                .then()
                .statusCode(200);
    }

    @Test
    void getReport_ShouldReturn302() {
        given()
                .when()
                .get("/reports/1")
                .then()
                .statusCode(302);
    }

    @Test
    void startReportGeneration_ShouldReturn302() {

        given()
                .when()
                .post("/reports/generate")
                .then()
                .statusCode(302);
    }
}