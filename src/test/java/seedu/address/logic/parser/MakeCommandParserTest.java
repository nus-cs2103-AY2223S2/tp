package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MakeCommand;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Character.CharacterBuilder;
import seedu.address.model.entity.Name;

public class MakeCommandParserTest {
    private static final String CHARACTER_COMMAND = "char";
    private static final MakeCommandParser parser = new MakeCommandParser();

    /**
     * Tests if the normal input of "char TEST" creates a valid command
     */
    @Test
    public void parse_normalCharInput_success() {
        final String normalNameString = "TEST";
        final Name normalName = new Name(normalNameString);
        final Character normalChar = new CharacterBuilder(normalName).build();
        final MakeCommand normalCommand = new MakeCommand(normalChar);
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
        final Character whiteSpaceChar = new CharacterBuilder(whiteSpaceName).build();
        final MakeCommand whiteSpaceCommand = new MakeCommand(whiteSpaceChar);
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
        final Character multipleSpaceChar = new CharacterBuilder(multipleSpaceName).build();
        final MakeCommand multipleSpaceCommand = new MakeCommand(multipleSpaceChar);
        final String multipleSpaceInput = CHARACTER_COMMAND + " " + multipleSpaceString;
        assertParseSuccess(parser, multipleSpaceInput , multipleSpaceCommand);
    }

    /**
     * Tests if parser rejects inputs with invalid command formats
     */
    @Test
    public void parse_invalidCommandFormat_fail() {
        final String invalidInputWithNoName = CHARACTER_COMMAND;
        final String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeCommand.MESSAGE_USAGE);
        assertParseFailure(parser, invalidInputWithNoName, expectedErrorMessage);
    }
}
