package com.healthcare;

import com.healthcare.healthcare_info_system.controller.PatientController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientControllerUnitTest {

    @Test
    void testAddPatient() {
        PatientController controller = new PatientController();
        String response = controller.addPatient("Charlie");
        assertTrue(response.contains("Charlie"));
    }
}
