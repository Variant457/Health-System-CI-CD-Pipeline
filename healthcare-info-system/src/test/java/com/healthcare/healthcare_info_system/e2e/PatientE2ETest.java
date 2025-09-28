package com.healthcare.healthcare_info_system.e2e;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientE2ETest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        // Set RestAssured to use the random port Spring Boot chose
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void testAddAndRetrievePatient() {
        // Add patient
        given()
            .contentType("application/json")
            .body("David")
        .when()
            .post("/patients")
        .then()
            .statusCode(200)
            .body(containsString("David"));

        // Get patients
        when()
            .get("/patients")
        .then()
            .statusCode(200)
            .body(containsString("David"));
    }
}

