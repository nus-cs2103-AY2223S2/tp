package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CONTENT_DESC_SPORTS;
import static seedu.address.logic.commands.CommandTestUtil.STATUS_DESC_SPORTS;
import static seedu.address.logic.commands.CommandTestUtil.SUBJECT_DESC_SPORTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTENT_SPORTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_SPORTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_SPORTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing subject prefix
        assertParseFailure(parser, VALID_TITLE_SPORTS + CONTENT_DESC_SPORTS + STATUS_DESC_SPORTS,
                expectedMessage);

        // missing content prefix
        assertParseFailure(parser, SUBJECT_DESC_SPORTS + VALID_CONTENT_SPORTS + STATUS_DESC_SPORTS,
                expectedMessage);

        // missing status prefix
        assertParseFailure(parser, SUBJECT_DESC_SPORTS + SUBJECT_DESC_SPORTS + VALID_STATUS_SPORTS,
                expectedMessage);

    }
}
