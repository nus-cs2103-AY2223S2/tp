package seedu.vms.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.parser.exceptions.ParseException;


public class ArgumentTokenizerTest {
    @Test
    public void tokenize_emptyArgsString_noValues() {
        String arg = "";
        ArgumentMultimap argMap = tokenizeSuccess(arg);
        assertPreambleEmpty(argMap);
    }


    @Test
    public void tokenize_noFlags_allTakenAsPreamble() {
        String arg = "   ruBb15h aNd m0re rubbish (o w o;)  ";
        ArgumentMultimap argMap = tokenizeSuccess(arg);
        assertPreamblePresent(argMap, arg.strip());
    }


    @Test
    public void tokenize_oneArgumentWithPreamble() {
        String arg = "   PREAMBLE!! --flag argument value ";
        ArgumentMultimap argMap = tokenizeSuccess(arg);
        assertPreamblePresent(argMap, "PREAMBLE!!");
        assertArgumentPresent(argMap, new Prefix("flag"), "argument value");
    }


    @Test
    public void tokenize_oneArgumentWithoutPreamble() {
        String arg = "--flag argument value ";
        ArgumentMultimap argMap = tokenizeSuccess(arg);
        assertPreambleEmpty(argMap);
        assertArgumentPresent(argMap, new Prefix("flag"), "argument value");
    }


    @Test
    public void tokenize_multipleArguments() {
        String arg = "--flag-1 value 1 --flag-2 value2 --flag-3";
        ArgumentMultimap argMap = tokenizeSuccess(arg);
        assertPreambleEmpty(argMap);
        assertArgumentPresent(argMap, new Prefix("flag-1"), "value 1");
        assertArgumentPresent(argMap, new Prefix("flag-2"), "value2");
        assertArgumentPresent(argMap, new Prefix("flag-3"), "");
        assertArgumentAbsent(argMap, new Prefix("flag-4"));
    }


    @Test
    public void tokenize_multipleArgumentsWithRepeats() {
        String arg = "--flag-1 value 1 --flag-1 value2 --flag-1";
        ArgumentMultimap argMap = tokenizeSuccess(arg);
        assertPreambleEmpty(argMap);
        assertArgumentPresent(argMap, new Prefix("flag-1"), "value 1", "value2", "");
        assertArgumentAbsent(argMap, new Prefix("flag-2"));
    }


    @Test
    public void tokenize_multipleArgumentsAlienSpacing() {
        String arg = "gomi--flag-1 value 1--    flag-1         value 2       --flag-1--flag-2";
        ArgumentMultimap argMap = tokenizeSuccess(arg);
        assertPreamblePresent(argMap, "gomi");
        assertArgumentPresent(argMap, new Prefix("flag-1"), "value 1", "value 2", "");
        assertArgumentPresent(argMap, new Prefix("flag-2"), "");
        assertArgumentAbsent(argMap, new Prefix("flag-3"));
    }


    @Test
    public void tokenize_blankFlag_exceptionThrown() {
        String arg = "gomi--flag-1 value 1--flag-1         value 2       --flag-1--";
        try {
            ArgumentTokenizer.tokenize(arg);
            fail("Exception not thrown");
        } catch (ParseException parseEx) {
            // expected exception thrown
        }
    }


    @Test
    public void equalsMethod() {
        Prefix aaa = new Prefix("aaa");

        assertEquals(aaa, aaa);
        assertEquals(aaa, new Prefix("aaa"));

        assertNotEquals(aaa, "aaa");
        assertNotEquals(aaa, new Prefix("aab"));
    }





    private void assertPreamblePresent(ArgumentMultimap argMultimap, String expectedPreamble) {
        assertEquals(expectedPreamble, argMultimap.getPreamble());
    }


    private void assertPreambleEmpty(ArgumentMultimap argMultimap) {
        assertTrue(argMultimap.getPreamble().isEmpty());
    }


    /**
     * Asserts all the arguments in {@code argMultimap} with {@code prefix} match the {@code expectedValues}
     * and only the last value is returned upon calling {@code ArgumentMultimap#getValue(Prefix)}.
     */
    private void assertArgumentPresent(ArgumentMultimap argMultimap, Prefix prefix, String... expectedValues) {

        // Verify the last value is returned
        assertEquals(expectedValues[expectedValues.length - 1], argMultimap.getValue(prefix).get());

        // Verify the number of values returned is as expected
        assertEquals(expectedValues.length, argMultimap.getAllValues(prefix).size());

        // Verify all values returned are as expected and in order
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], argMultimap.getAllValues(prefix).get(i));
        }
    }


    private void assertArgumentAbsent(ArgumentMultimap argMultimap, Prefix prefix) {
        assertFalse(argMultimap.getValue(prefix).isPresent());
    }


    private ArgumentMultimap tokenizeSuccess(String args) {
        ArgumentMultimap argMap = new ArgumentMultimap();
        try {
            argMap = ArgumentTokenizer.tokenize(args);
        } catch (Throwable ex) {
            fail(ex);
        }
        return argMap;
    }
}
