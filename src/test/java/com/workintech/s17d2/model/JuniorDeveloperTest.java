package com.workintech.s17d2.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JuniorDeveloperTest {

    @Test
    void testJuniorDeveloperExistenceAndInheritance() {
        
        JuniorDeveloper juniorDeveloper = new JuniorDeveloper(1, "Test Developer", 50000.0);
        assertTrue(juniorDeveloper instanceof Developer, "JuniorDeveloper should extend Developer.");
        assertEquals(Experience.JUNIOR, juniorDeveloper.getExperience(), "Experience should be JUNIOR.");
    }
}
