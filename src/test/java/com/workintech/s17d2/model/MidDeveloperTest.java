package com.workintech.s17d2.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MidDeveloperTest {

    @Test
    void testMidDeveloperExistenceAndInheritance() {

        MidDeveloper midDeveloper = new MidDeveloper(1, "Test Developer", 60000.0);
        assertTrue(midDeveloper instanceof Developer, "MidDeveloper should extend Developer.");
        assertEquals(Experience.MID, midDeveloper.getExperience(), "Experience should be MID.");
    }
}

