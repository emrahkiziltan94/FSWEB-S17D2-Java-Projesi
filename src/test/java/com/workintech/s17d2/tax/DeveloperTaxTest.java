package com.workintech.s17d2.tax;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeveloperTaxTest {

    private final DeveloperTax developerTax = new DeveloperTax();

    @Test
    void testGetSimpleTaxRate() {
        Double expectedSimpleTaxRate = 15d; // Update this value if the tax rate changes
        assertEquals(expectedSimpleTaxRate, developerTax.getSimpleTaxRate(), "The simple tax rate should be correct.");
    }

    @Test
    void testGetMiddleTaxRate() {
        Double expectedMiddleTaxRate = 25d; // Update this value if the tax rate changes
        assertEquals(expectedMiddleTaxRate, developerTax.getMiddleTaxRate(), "The middle tax rate should be correct.");
    }

    @Test
    void testGetUpperTaxRate() {
        Double expectedUpperTaxRate = 35d; // Update this value if the tax rate changes
        assertEquals(expectedUpperTaxRate, developerTax.getUpperTaxRate(), "The upper tax rate should be correct.");
    }
}
