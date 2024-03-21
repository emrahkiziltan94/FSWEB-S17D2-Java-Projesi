package com.workintech.s17d2.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeveloperTest {

    // developer class creation test
    @Test
    void testDeveloperCreation() {

        int expectedId = 1;
        String expectedName = "John Doe";
        double expectedSalary = 1000.0;
        Experience expectedExperience = Experience.JUNIOR;
        // Arrange
        Developer developer = new Developer(expectedId, expectedName, expectedSalary, expectedExperience);

        // Act
        int actualId = developer.getId();
        String actualName = developer.getName();
        double actualSalary = developer.getSalary();
        Experience actualExperience = developer.getExperience();

        // Assert
        assertEquals(expectedId, actualId, "The ID should match the expected value.");
        assertEquals(expectedName, actualName, "The name should match the expected value.");
        assertEquals(expectedSalary, actualSalary, "The salary should match the expected value.");
        assertEquals(expectedExperience, actualExperience, "The experience should match the expected value.");


    }
}
