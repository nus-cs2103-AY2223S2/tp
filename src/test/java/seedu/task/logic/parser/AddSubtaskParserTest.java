package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.commands.CommandTestUtil.BLANK_DESCRIPTION_DESC;
import static seedu.task.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.task.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.task.logic.commands.CommandTestUtil.INDEX_1;
import static seedu.task.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.task.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.task.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.task.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.task.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.task.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.task.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.task.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.task.testutil.TypicalSubtasks.BOB_SUBTASK;

import org.junit.jupiter.api.Test;

import seedu.task.commons.core.index.Index;
import seedu.task.logic.commands.AddSubtaskCommand;
import seedu.task.model.task.Description;
import seedu.task.model.task.Name;
import seedu.task.model.task.Subtask;
import seedu.task.testutil.SubtaskBuilder;

public class AddSubtaskParserTest {
    private AddSubtaskParser parser = new AddSubtaskParser();

    @Test
    public void parseSimpleTask_allFieldsPresent_success() {
        Subtask expectedTask = new SubtaskBuilder(BOB_SUBTASK).build();
        Subtask expectedNoDescription = new SubtaskBuilder().withName(VALID_NAME_BOB).withDescription().build();

        // whitespace in preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_1 + NAME_DESC_BOB + DESCRIPTION_DESC_BOB,
            new AddSubtaskCommand(Index.fromOneBased(1), expectedTask));

        // no description
        assertParseSuccess(parser, INDEX_1 + NAME_DESC_BOB,
            new AddSubtaskCommand(Index.fromOneBased(1), expectedNoDescription));

        // multiple description - last description accepted
        assertParseSuccess(parser, INDEX_1 + NAME_DESC_BOB + DESCRIPTION_DESC_AMY + DESCRIPTION_DESC_BOB,
            new AddSubtaskCommand(Index.fromOneBased(1), expectedTask));


    }


    @Test
    public void parseSimpleTask_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSubtaskCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + DESCRIPTION_DESC_BOB,
            expectedMessage);

        // missing description prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_DESCRIPTION_BOB,
            expectedMessage);



        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_DESCRIPTION_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSubtaskCommand.MESSAGE_USAGE);
        // invalid name
        assertParseFailure(parser, INDEX_1 + INVALID_NAME_DESC + DESCRIPTION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, INDEX_1 + NAME_DESC_BOB + BLANK_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);


        // empty preamble
        assertParseFailure(parser, NAME_DESC_BOB + DESCRIPTION_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSubtaskCommand.MESSAGE_USAGE));

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INDEX_1 + INVALID_NAME_DESC + BLANK_DESCRIPTION_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }
}
