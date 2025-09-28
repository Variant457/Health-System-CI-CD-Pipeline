package com.healthcare.healthcare_info_system.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private List<String> patients = new ArrayList<>(List.of("Alice", "Bob"));

    @GetMapping
    public List<String> getPatients() {
        return patients;
    }

    @PostMapping
    public String addPatient(@RequestBody String name) {
        patients.add(name);
        return "Patient " + name + " added.";
    }
}
