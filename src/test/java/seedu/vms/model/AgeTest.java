package seedu.vms.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AgeTest {
    private static final Age TEST = Age.MAX_AGE;
    private static final Age EQS = new Age(Age.MAX_VALUE);
    private static final Age OFF_LIMIT = new Age(Age.MAX_VALUE + 1);
    private static final Age LESS = new Age(Age.MAX_VALUE - 1);


    @Test
    public void getValue() {
        assertEquals(Age.MIN_VALUE, Age.MIN_AGE.getValue());
        assertEquals(Age.MAX_VALUE, Age.MAX_AGE.getValue());
        assertEquals(Age.MAX_VALUE, OFF_LIMIT.getValue());
    }


    @Test
    public void compareToTest() {
        assertTrue(TEST.compareTo(EQS) == 0);
        assertTrue(TEST.compareTo(LESS) > 0);
        assertTrue(LESS.compareTo(TEST) < 0);
        assertTrue(TEST.compareTo(OFF_LIMIT) == 0);
    }


    @Test
    public void toStringTest() {
        assertEquals(String.valueOf(Age.MIN_VALUE + 1), new Age(Age.MIN_VALUE + 1).toString());
        assertEquals(String.valueOf(Age.MAX_VALUE - 1), new Age(Age.MAX_VALUE - 1).toString());
        assertEquals(Age.STRING_MIN, new Age(Age.MIN_VALUE).toString());
        assertEquals(Age.STRING_MAX, new Age(Age.MAX_VALUE).toString());
        assertEquals(Age.STRING_MAX, OFF_LIMIT.toString());
    }


    @Test
    public void equalsTest() {
        assertTrue(TEST.equals(TEST));
        assertTrue(TEST.equals(EQS));
        assertTrue(TEST.equals(OFF_LIMIT));
        assertFalse(TEST.equals(LESS));
        assertFalse(TEST.equals(5));
    }


    @Test
    public void hashCodeTest() {
        HashSet<Age> ageSet = new HashSet<>(List.of(TEST, EQS, OFF_LIMIT, LESS));
        assertTrue(ageSet.size() == 2);
        assertTrue(ageSet.contains(OFF_LIMIT));
    }


    @Test
    public void isValid() {
        assertFalse(Age.isValid(Age.MIN_VALUE - 1));
        assertTrue(Age.isValid(Age.MAX_VALUE + 1));
    }
}
