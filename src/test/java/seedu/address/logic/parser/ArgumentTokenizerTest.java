package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ArgumentTokenizerTest {

    private final Prefix unknownPrefix = new Prefix("/u");
    private final Prefix slashA = new Prefix("/a");
    private final Prefix slashB = new Prefix("/b");
    private final Prefix slashC = new Prefix("/c");

    private final String preambleValue = "SomePreambleString";
    private final String altPreambleValue = "Different Preamble String";
    private final String slashAValue = "slashA value";
    private final String altSlashAValue = "another slashA value";
    private final String slashBValue = "slashB-Value";
    private final String slashCValue = "111";
    private final String unknownValue = "some value";


    @Test
    public void tokenize_emptyArgsString_noValues() {
        String argsString = "  ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, slashA);

        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, slashA);
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

    @Test
    public void tokenize_noPrefix_allTakenAsPreamble() {
        String argsString = "  some random string tag with leading and trailing spaces ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString);

        // Same string expected as preamble, but leading/trailing spaces should be trimmed
        assertPreamblePresent(argMultimap, argsString.trim());
    }

    @Test
    public void tokenize_oneSpecifiedPrefix() {
        // Preamble present
        String argsString = "  " + preambleValue + " " + slashA.getPrefix() + " " + slashAValue;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, slashA);
        assertPreamblePresent(argMultimap, preambleValue);
        assertArgumentPresent(argMultimap, slashA, slashAValue);

        // No preamble
        argsString = " " + slashA.getPrefix() + "   " + slashAValue + " ";
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashA);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, slashA, slashAValue);

        // Argument has no value
        argsString = " " + preambleValue + " " + slashA.getPrefix();
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashA);
        assertPreamblePresent(argMultimap, preambleValue);
        assertArgumentPresent(argMultimap, slashA, "");
    }

    @Test
    public void tokenize_oneUnspecifiedPrefix() {
        // Preamble present
        String argsString = "  " + preambleValue + " " + slashA.getPrefix() + " " + slashAValue;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString);
        assertPreamblePresent(argMultimap, preambleValue);
        assertArgumentAbsent(argMultimap, slashA);

        // No preamble
        argsString = " " + slashA.getPrefix() + "   " + slashAValue + " ";
        argMultimap = ArgumentTokenizer.tokenize(argsString);
        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, slashA);

        // Argument has no value
        argsString = " " + preambleValue + " " + slashA.getPrefix();
        argMultimap = ArgumentTokenizer.tokenize(argsString);
        assertPreamblePresent(argMultimap, preambleValue);
        assertArgumentAbsent(argMultimap, slashA);
    }

    @Test
    public void tokenize_multiplePrefixAllSpecified() {
        // Only two arguments are present, both have value
        String argsString = String.join(" ",
                preambleValue, slashB.getPrefix(), slashBValue, slashA.getPrefix(), slashAValue);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, slashA, slashB, slashC);
        assertPreamblePresent(argMultimap, preambleValue);
        assertArgumentPresent(argMultimap, slashA, slashAValue);
        assertArgumentPresent(argMultimap, slashB, slashBValue);
        assertArgumentAbsent(argMultimap, slashC);

        // Only two arguments are present, first argument has no value
        argsString = String.join(" ", preambleValue, slashB.getPrefix(), slashA.getPrefix(), slashAValue);
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashA, slashB, slashC);
        assertPreamblePresent(argMultimap, preambleValue);
        assertArgumentPresent(argMultimap, slashA, slashAValue);
        assertArgumentPresent(argMultimap, slashB, "");
        assertArgumentAbsent(argMultimap, slashC);

        // Only two arguments are present, last argument has no value
        argsString = String.join(" ", preambleValue, slashB.getPrefix(), slashBValue, slashA.getPrefix());
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashA, slashB, slashC);
        assertPreamblePresent(argMultimap, preambleValue);
        assertArgumentPresent(argMultimap, slashA, "");
        assertArgumentPresent(argMultimap, slashB, slashBValue);
        assertArgumentAbsent(argMultimap, slashC);

        // All three arguments are present, all with value
        argsString = String.join(" ",
                altPreambleValue, slashC.getPrefix(), slashCValue, slashB.getPrefix(), slashBValue,
                slashA.getPrefix(), slashAValue);
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashA, slashB, slashC);
        assertPreamblePresent(argMultimap, altPreambleValue);
        assertArgumentPresent(argMultimap, slashA, slashAValue);
        assertArgumentPresent(argMultimap, slashB, slashBValue);
        assertArgumentPresent(argMultimap, slashC, slashCValue);

        /* Also covers: Reusing of the tokenizer multiple times */

        // Reuse tokenizer on an empty string to ensure ArgumentMultimap is correctly reset
        // (i.e. no stale values from the previous tokenizing remain)
        argsString = "";
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashA, slashB, slashC);
        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, slashA);
    }

    @Test
    public void tokenize_multiplePrefixSomeUnspecified() {
        // Unspecified prefix appears after a specified prefix
        String argsString = String.join(" ",
                preambleValue, slashC.getPrefix(), slashCValue, unknownPrefix.getPrefix(), unknownValue,
                slashB.getPrefix(), slashBValue, slashA.getPrefix(), slashAValue);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, slashA, slashB, slashC);
        assertPreamblePresent(argMultimap, preambleValue);
        assertArgumentPresent(argMultimap, slashA, slashAValue);
        assertArgumentPresent(argMultimap, slashB, slashBValue);
        assertArgumentPresent(argMultimap, slashC, slashCValue);
        assertArgumentAbsent(argMultimap, unknownPrefix);

        // Unspecified prefix appears immediately after preamble
        argsString = String.join(" ",
                preambleValue, unknownPrefix.getPrefix(), unknownValue, slashC.getPrefix(), slashCValue,
                slashB.getPrefix(), slashBValue, slashA.getPrefix(), slashAValue);
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashA, slashB, slashC);
        assertPreamblePresent(argMultimap, preambleValue);
        assertArgumentPresent(argMultimap, slashA, slashAValue);
        assertArgumentPresent(argMultimap, slashB, slashBValue);
        assertArgumentPresent(argMultimap, slashC, slashCValue);
        assertArgumentAbsent(argMultimap, unknownPrefix);
    }

    @Test
    public void tokenize_multipleArgumentsWithRepeats() {
        // Two arguments repeated, some have empty values
        String argsString = String.join(" ",
                preambleValue, slashA.getPrefix(), slashAValue, slashB.getPrefix(), slashB.getPrefix(),
                slashA.getPrefix(), altSlashAValue, slashC.getPrefix(), slashCValue, slashA.getPrefix());
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, slashA, slashB, slashC);
        assertPreamblePresent(argMultimap, preambleValue);
        assertArgumentPresent(argMultimap, slashA, slashAValue, altSlashAValue, "");
        assertArgumentPresent(argMultimap, slashB, "", "");
        assertArgumentPresent(argMultimap, slashC, slashCValue);
    }

    @Test
    public void tokenize_multipleArgumentsJoined() {
        // No whitespace before prefix
        String argsString = "SomePreambleString/a slashA joined/b joined /b not joined/c joined";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, slashA, slashB, slashC);
        assertPreamblePresent(argMultimap, "SomePreambleString/a slashA joined/b joined");
        assertArgumentAbsent(argMultimap, slashA);
        assertArgumentPresent(argMultimap, slashB, "not joined/c joined");
        assertArgumentAbsent(argMultimap, slashC);

        // No whitespace after prefix and not end of string
        argsString = "SomePreambleString /aaSlash joined /bjoined /b not joined /cjoined";
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashA, slashB, slashC);
        assertPreamblePresent(argMultimap, "SomePreambleString");
        assertArgumentAbsent(argMultimap, slashA);
        assertArgumentPresent(argMultimap, slashB, "not joined");
        assertArgumentAbsent(argMultimap, slashC);
    }

}
