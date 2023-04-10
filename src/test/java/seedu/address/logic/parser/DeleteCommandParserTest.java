package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Name;

public class DeleteCommandParserTest {
    private static final String CHARACTER_COMMAND = "char";
    // Creates a Character classification
    private static final Classification DEFAULT_CLASSIFICATION = new Classification(CHARACTER_COMMAND);
    private static final DeleteCommandParser parser = new DeleteCommandParser();

    /**
     * Tests if the normal input of "char TEST" creates a valid command
     */
    @Test
    public void parse_normalInput_success() {
        final String normalNameString = "TEST";
        final Name normalName = new Name(normalNameString);
        final DeleteCommand normalCommand = new DeleteCommand(normalName, DEFAULT_CLASSIFICATION);
        final String normalInput = CHARACTER_COMMAND + " " + normalNameString;

        assertParseSuccess(parser, normalInput , normalCommand);
    }

    /**
     * Tests if the parser trims name inputs with leading and trailing whitespace
     */
    @Test
    public void parse_whiteSpaces_success() {
        final String whiteSpaceString = "   WHITESPACE  ";
        final Name whiteSpaceName = new Name("WHITESPACE");
        final DeleteCommand whiteSpaceCommand = new DeleteCommand(whiteSpaceName, DEFAULT_CLASSIFICATION);
        final String whiteSpaceInput = CHARACTER_COMMAND + " " + whiteSpaceString;

        assertParseSuccess(parser, whiteSpaceInput , whiteSpaceCommand);
    }

    /**
     * Tests if the parser trims name inputs with multiple spaces
     */
    @Test
    public void parse_multipleSpaces_success() {
        final String multipleSpaceString = "MANY     SPACES";
        final Name multipleSpaceName = new Name("MANY     SPACES");
        final DeleteCommand multipleSpaceCommand = new DeleteCommand(multipleSpaceName, DEFAULT_CLASSIFICATION);
        final String multipleSpaceInput = CHARACTER_COMMAND + " " + multipleSpaceString;

        assertParseSuccess(parser, multipleSpaceInput , multipleSpaceCommand);
    }

    /**
     * Tests if parser rejects inputs with invalid command formats
     */
    @Test
    public void parse_invalidCommandFormat_fail() {
        final String invalidInputWithNoName = CHARACTER_COMMAND;
        final String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);

        assertParseFailure(parser, invalidInputWithNoName, expectedErrorMessage);
    }
}
