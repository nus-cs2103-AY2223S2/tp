package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.commands.CommandTestUtil.INDEX_1;
import static seedu.task.logic.commands.CommandTestUtil.INDEX_SUBTASK;
import static seedu.task.logic.commands.CommandTestUtil.INVALID_INDEX;
import static seedu.task.logic.commands.CommandTestUtil.INVALID_SUBTASK_INDEX;
import static seedu.task.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.task.logic.commands.CommandTestUtil.VALID_SUBTASK_INDEX;
import static seedu.task.logic.commands.CommandTestUtil.VALID_TASK_INDEX;
import static seedu.task.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.task.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.task.commons.core.index.Index;
import seedu.task.logic.commands.DeleteSubtaskCommand;


public class DeleteSubtaskParserTest {
    private DeleteSubtaskParser parser = new DeleteSubtaskParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // whitespace in preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_1 + INDEX_SUBTASK,
            new DeleteSubtaskCommand(Index.fromOneBased(1), Index.fromOneBased(1)));

    }


    @Test
    public void parseSimpleTask_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSubtaskCommand.MESSAGE_USAGE);

        // missing main task index
        assertParseFailure(parser, INDEX_SUBTASK,
            expectedMessage);

        // missing subtask index
        assertParseFailure(parser, INDEX_1,
            expectedMessage);

        //missing subtask index prefix
        assertParseFailure(parser, INDEX_1 + VALID_SUBTASK_INDEX,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TASK_INDEX + VALID_SUBTASK_INDEX,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSubtaskCommand.MESSAGE_USAGE);

        // invalid name
        assertParseFailure(parser, INVALID_INDEX + INDEX_SUBTASK, expectedMessage);

        // invalid description
        assertParseFailure(parser, INDEX_1 + INVALID_SUBTASK_INDEX, expectedMessage);


    }

}
