package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QualificationTest {
    @Test
    public void equals() {
        Qualification qualification1 = new Qualification("Java");
        Qualification qualification2 = new Qualification("Java");
        Qualification qualification3 = new Qualification("C++");

        // reflexivity
        assertTrue(qualification1.equals(qualification1));

        // symmetry
        assertTrue(qualification1.equals(qualification2));
        assertTrue(qualification2.equals(qualification1));

        // transitivity
        assertTrue(qualification1.equals(qualification2));
        assertFalse(qualification2.equals(qualification3));
        assertFalse(qualification1.equals(qualification3));

        // inequality
        assertFalse(qualification1.equals(null));
        assertFalse(qualification1.equals("Java"));
        assertFalse(qualification1.equals(qualification3));
    }
}

