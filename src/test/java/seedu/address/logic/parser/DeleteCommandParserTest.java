package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Name;

public class DeleteCommandParserTest {
    private final String CHARACTER = "char";
    private final String NAME_STRING = "TEST";
    private final Name DEFAULT_NAME = new Name(NAME_STRING);
    // Creates a Character classification
    private final Classification DEFAULT_CLASSIFICATION = new Classification(CHARACTER);
    private final DeleteCommandParser parser = new DeleteCommandParser();

    /**
     * Tests if the normal input of "char TEST" creates a valid command
     */
    @Test
    public void parse_normalInput_success() {
        final DeleteCommand normalCommand = new DeleteCommand(DEFAULT_NAME, DEFAULT_CLASSIFICATION);
        String normalInput = CHARACTER + " " + NAME_STRING;
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
        String whiteSpaceInput = CHARACTER + " " + whiteSpaceString;
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
        String multipleSpaceInput = CHARACTER + " " + multipleSpaceString;
        assertParseSuccess(parser, multipleSpaceInput , multipleSpaceCommand);
    }

    /**
     * Tests if parser rejects inputs with invalid command formats
     */
    @Test
    public void parse_invalidCommandFormat_fail() {
        final String invalidInputWithNoName = CHARACTER;
        final String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);
        assertParseFailure(parser, invalidInputWithNoName, expectedErrorMessage);
    }
}
