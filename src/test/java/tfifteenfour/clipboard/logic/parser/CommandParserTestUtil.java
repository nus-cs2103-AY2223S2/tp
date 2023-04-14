package tfifteenfour.clipboard.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import tfifteenfour.clipboard.logic.commands.Command;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser<? extends Command> parser, String userInput,
                                          Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            System.out.println("Expected: " + expectedCommand);
            System.out.println("Actual: " + command);
            assertEquals(expectedCommand, command);
        } catch (ParseException | CommandException pe) {
            throw new IllegalArgumentException("Invalid userInput." + pe.getMessage());
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(Parser<? extends Command> parser,
                                          String userInput, String expectedMessage) {
        try {
            parser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException | CommandException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
