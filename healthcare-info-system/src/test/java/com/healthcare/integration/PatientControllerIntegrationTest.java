package com.healthcare.integration;

import com.healthcare.healthcare_info_system.controller.PatientController;
import com.healthcare.HealthcareInfoSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    classes = HealthcareInfoSystemApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class PatientControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetPatients() {
        ResponseEntity<String> response = restTemplate.getForEntity("/patients", String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }
}
