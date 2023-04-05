package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTaskCommand;

class DeleteTaskParserCommandTest {

    private DeleteTaskParserCommand parser = new DeleteTaskParserCommand();

    @Test
    public void parse_validArgs_returnsDeleteTaskCommand() {
        assertParseSuccess(parser, "1 1", new DeleteTaskCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteTaskCommand.MESSAGE_USAGE));
    }
}
