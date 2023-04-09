package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCORE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteScoreCommand;

public class DeleteScoreCommandParserTest {

    private DeleteScoreCommandParser parser = new DeleteScoreCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteScoreCommand() {
        assertParseSuccess(parser, "1 1", new DeleteScoreCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_SCORE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteScoreCommand.MESSAGE_USAGE));
    }
}
