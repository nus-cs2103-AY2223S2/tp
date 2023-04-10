package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ProgrammingLanguageTest {
    @Test
    public void equals_sameObject_true() {
        ProgrammingLanguage pl = new ProgrammingLanguage("Java");
        assertTrue(pl.equals(pl));
    }

    @Test
    public void equals_sameValues_true() {
        ProgrammingLanguage pl1 = new ProgrammingLanguage("Java");
        ProgrammingLanguage pl2 = new ProgrammingLanguage("Java");
        assertTrue(pl1.equals(pl2));
    }

    @Test
    public void equals_differentValues_false() {
        ProgrammingLanguage pl1 = new ProgrammingLanguage("Java");
        ProgrammingLanguage pl2 = new ProgrammingLanguage("Python");
        assertFalse(pl1.equals(pl2));
    }

    @Test
    public void equals_null_false() {
        ProgrammingLanguage pl = new ProgrammingLanguage("Java");
        assertFalse(pl.equals(null));
    }

    @Test
    public void equals_differentClass_false() {
        ProgrammingLanguage pl = new ProgrammingLanguage("Java");
        assertFalse(pl.equals("Java"));
    }

}
