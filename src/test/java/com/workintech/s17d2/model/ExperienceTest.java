package com.workintech.s17d2.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExperienceTest {

    @Test
    void testEnumValuesExist() {
        // Check that all expected enum values exist
        assertTrue(containsEnumValue("JUNIOR"), "JUNIOR should be a valid Experience enum value.");
        assertTrue(containsEnumValue("MID"), "MID should be a valid Experience enum value.");
        assertTrue(containsEnumValue("SENIOR"), "SENIOR should be a valid Experience enum value.");
    }

    private boolean containsEnumValue(String value) {
        for (Experience experience : Experience.values()) {
            if (experience.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}

