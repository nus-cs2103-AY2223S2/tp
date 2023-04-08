package seedu.socket.logic.parser;

import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.UnassignCommand;
import seedu.socket.model.person.Name;

public class UnassignCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.MESSAGE_USAGE);
    private UnassignCommandParser parser = new UnassignCommandParser();

    @Test
    public void parse_validArgs_returnsUnassignCommand() {
        UnassignCommand expectedUnassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, new Name(VALID_NAME_AMY));
        assertParseSuccess(parser, "1 " + PREFIX_NAME + VALID_NAME_AMY, expectedUnassignCommand);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a n/Amy Bee", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidName_throwsParseException() {
        assertParseFailure(parser, "1 n/", MESSAGE_INVALID_FORMAT);
    }
}
