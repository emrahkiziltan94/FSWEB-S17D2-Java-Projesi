package com.workintech.s17d2;

import com.workintech.s17d2.model.*;
import com.workintech.s17d2.tax.DeveloperTax;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    @DisplayName("Test Developer Creation")
    void testDeveloperCreation() {

        int expectedId = 1;
        String expectedName = "John Doe";
        double expectedSalary = 1000.0;
        Experience expectedExperience = Experience.JUNIOR;
        
        Developer developer = new Developer(expectedId, expectedName, expectedSalary, expectedExperience);

        
        int actualId = developer.getId();
        String actualName = developer.getName();
        double actualSalary = developer.getSalary();
        Experience actualExperience = developer.getExperience();

        
        assertEquals(expectedId, actualId, "The ID should match the expected value.");
        assertEquals(expectedName, actualName, "The name should match the expected value.");
        assertEquals(expectedSalary, actualSalary, "The salary should match the expected value.");
        assertEquals(expectedExperience, actualExperience, "The experience should match the expected value.");


    }

    @Test
    @DisplayName("Test Experience Enum Values")
    void testEnumValuesExist() {
        
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

    @Test
    @DisplayName("Test JuniorDeveloper Existence and Inheritance")
    void testJuniorDeveloperExistenceAndInheritance() {

        JuniorDeveloper juniorDeveloper = new JuniorDeveloper(1, "Test Developer", 50000.0);
        assertTrue(juniorDeveloper instanceof Developer, "JuniorDeveloper should extend Developer.");
        assertEquals(Experience.JUNIOR, juniorDeveloper.getExperience(), "Experience should be JUNIOR.");
    }
    @Test
    @DisplayName("Test MidDeveloper Existence and Inheritance")
    void testMidDeveloperExistenceAndInheritance() {

        MidDeveloper midDeveloper = new MidDeveloper(1, "Test Developer", 60000.0);
        assertTrue(midDeveloper instanceof Developer, "MidDeveloper should extend Developer.");
        assertEquals(Experience.MID, midDeveloper.getExperience(), "Experience should be MID.");
    }

    @Test
    @DisplayName("Test SeniorDeveloper Existence and Inheritance")
    void testSeniorDeveloperExistenceAndInheritance() {

        SeniorDeveloper seniorDeveloper = new SeniorDeveloper(1, "Test Developer", 80000.0);
        assertTrue(seniorDeveloper instanceof Developer, "SeniorDeveloper should extend Developer.");
        assertEquals(Experience.SENIOR, seniorDeveloper.getExperience(), "Experience should be SENIOR.");
    }
    
    
    
    /*-------------------DeveloperTaxTest-------------------*/


    private final DeveloperTax developerTax = new DeveloperTax();

    @Test
    @DisplayName("Test Get Simple Tax Rate")
    void testGetSimpleTaxRate() {
        Double expectedSimpleTaxRate = 15d; 
        assertEquals(expectedSimpleTaxRate, developerTax.getSimpleTaxRate(), "The simple tax rate should be correct.");
    }

    @Test
    @DisplayName("Test Get Middle Tax Rate")
    void testGetMiddleTaxRate() {
        Double expectedMiddleTaxRate = 25d; 
        assertEquals(expectedMiddleTaxRate, developerTax.getMiddleTaxRate(), "The middle tax rate should be correct.");
    }

    @Test
    @DisplayName("Test Get Upper Tax Rate")
    void testGetUpperTaxRate() {
        Double expectedUpperTaxRate = 35d; 
        assertEquals(expectedUpperTaxRate, developerTax.getUpperTaxRate(), "The upper tax rate should be correct.");
    }
}
