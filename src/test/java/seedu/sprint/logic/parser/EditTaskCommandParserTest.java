package seedu.sprint.logic.parser;

import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.DEADLINE_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.DESCRIPTION_DESC_ASSESSMENT;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.DESCRIPTION_DESC_INTERVIEW;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.INVALID_DATE_DEADLINE_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.INVALID_FORMAT_DEADLINE_DESC;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DEADLINE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION;
import static seedu.sprint.logic.parser.ApplicationCommandParserTestUtil.assertParseFailure;
import static seedu.sprint.logic.parser.ApplicationCommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.sprint.commons.core.index.Index;
import seedu.sprint.logic.commands.EditTaskCommand;
import seedu.sprint.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.sprint.model.task.Deadline;
import seedu.sprint.model.task.Description;
import seedu.sprint.testutil.EditTaskDescriptorBuilder;

public class EditTaskCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);
    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DESCRIPTION, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

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
        assertParseFailure(parser, "1" + INVALID_DATE_DEADLINE_DESC + INVALID_DESCRIPTION_DESC,
                Deadline.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_bothFieldsPresent_success() {
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION)
                .withDeadline(VALID_DEADLINE)
                .build();
        EditTaskCommand command = new EditTaskCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, "1" + DESCRIPTION_DESC_ASSESSMENT + DEADLINE_DESC, command);
    }

    @Test
    public void parse_oneFieldPresent_success() {
        // description present
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION)
                .build();
        EditTaskCommand command = new EditTaskCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, "1" + DESCRIPTION_DESC_ASSESSMENT, command);

        // deadline present
        descriptor = new EditTaskDescriptorBuilder()
                .withDeadline(VALID_DEADLINE)
                .build();
        command = new EditTaskCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, "1" + DEADLINE_DESC, command);
    }
}
