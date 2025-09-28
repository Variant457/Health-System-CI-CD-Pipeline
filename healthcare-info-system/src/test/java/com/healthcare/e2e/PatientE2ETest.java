package com.healthcare.e2e;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PatientE2ETest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
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
