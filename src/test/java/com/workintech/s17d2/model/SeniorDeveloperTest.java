package com.workintech.s17d2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SeniorDeveloperTest {

    @Test
    void testSeniorDeveloperExistenceAndInheritance() {

        SeniorDeveloper seniorDeveloper = new SeniorDeveloper(1, "Test Developer", 80000.0);
        assertTrue(seniorDeveloper instanceof Developer, "SeniorDeveloper should extend Developer.");
        assertEquals(Experience.SENIOR, seniorDeveloper.getExperience(), "Experience should be SENIOR.");
    }
}

