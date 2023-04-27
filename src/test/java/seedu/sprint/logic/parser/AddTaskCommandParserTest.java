package seedu.sprint.logic.parser;

import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.DEADLINE_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.DESCRIPTION_DESC_ASSESSMENT;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.DESCRIPTION_DESC_INTERVIEW;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.INVALID_DATE_DEADLINE_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.INVALID_FORMAT_DEADLINE_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DEADLINE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION_INTERVIEW;
import static seedu.sprint.logic.parser.ApplicationCommandParserTestUtil.assertParseFailure;
import static seedu.sprint.logic.parser.ApplicationCommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.sprint.commons.core.index.Index;
import seedu.sprint.logic.commands.AddTaskCommand;
import seedu.sprint.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.sprint.model.task.Deadline;
import seedu.sprint.model.task.Description;
import seedu.sprint.testutil.EditApplicationDescriptorBuilder;


public class AddTaskCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DESCRIPTION, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DESCRIPTION_DESC_INTERVIEW + DEADLINE_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DESCRIPTION_DESC_INTERVIEW + DEADLINE_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid description
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC + DEADLINE_DESC,
                Description.MESSAGE_CONSTRAINTS);

        // invalid date (wrong format)
        assertParseFailure(parser, "1" + INVALID_FORMAT_DEADLINE_DESC + DESCRIPTION_DESC_ASSESSMENT,
                Deadline.MESSAGE_CONSTRAINTS);

        // invalid date (date does not exist)
        assertParseFailure(parser, "1" + INVALID_DATE_DEADLINE_DESC + DESCRIPTION_DESC_ASSESSMENT,
                Deadline.MESSAGE_CONSTRAINTS);

        // multiple invalid values but only first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC + INVALID_DATE_DEADLINE_DESC,
                Description.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_bothFieldsPresent_success() {
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withTask(VALID_DEADLINE, VALID_DESCRIPTION_INTERVIEW).build();
        // preamble with white space
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + "1" + DESCRIPTION_DESC_INTERVIEW
                + DEADLINE_DESC, new AddTaskCommand(Index.fromOneBased(1), descriptor));

        // normal command
        assertParseSuccess(parser, "1" + DESCRIPTION_DESC_INTERVIEW
                + DEADLINE_DESC, new AddTaskCommand(Index.fromOneBased(1), descriptor));
    }

    @Test
    public void parse_missingFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, "1" + VALID_DESCRIPTION + DEADLINE_DESC, expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, "1" + VALID_DEADLINE + DESCRIPTION_DESC_ASSESSMENT, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "1" + VALID_DEADLINE + VALID_DESCRIPTION, expectedMessage);
    }
}
