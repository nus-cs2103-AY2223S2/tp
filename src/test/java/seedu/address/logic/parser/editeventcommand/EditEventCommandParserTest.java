package seedu.address.logic.parser.editeventcommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.END_DATETIME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.END_DATETIME_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECURRENCE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.RECURRENCE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.RECURRENCE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.START_DATETIME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.START_DATETIME_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATETIME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRENCE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATETIME_2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;

class EditEventCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditEventCommand.MESSAGE_USAGE);

    private final EditEventCommandParser parser = new EditEventCommandParser();

    @Test
    void parse_missingParts_failure() {
        // no index
        assertParseFailure(parser, DESCRIPTION_DESC_2, MESSAGE_INVALID_FORMAT);

        // no field
        assertParseFailure(parser, "1", EditEventCommand.MESSAGE_NOT_EDITED);

        // neither
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, String.format("-1 %s", DESCRIPTION_DESC_2), MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, String.format("0 %s", DESCRIPTION_DESC_2), MESSAGE_INVALID_FORMAT);

        // invalid arguments in preamble
        assertParseFailure(parser, String.format("-1 hello %s", DESCRIPTION_DESC_2), MESSAGE_INVALID_FORMAT);

        // invalid prefix in preamble
        assertParseFailure(parser, String.format("-1 i/hello %s", DESCRIPTION_DESC_2), MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_invalidValue_failure() {
        assertParseFailure(parser, String.format("1 %s", INVALID_DESCRIPTION_DESC), Description.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, String.format("1 %s", INVALID_START_DATETIME_DESC), DateTime.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, String.format("1 %s", INVALID_END_DATETIME_DESC), DateTime.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, String.format("1 %s", INVALID_RECURRENCE_DESC), Recurrence.MESSAGE_CONSTRAINTS);

        // multiple invalid values with only the first captured
        assertParseFailure(parser, String.format("1 %s%s", INVALID_START_DATETIME_DESC, INVALID_RECURRENCE_DESC),
                DateTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_allFields_success() {
        Index index = Index.fromZeroBased(0);
        EventDescriptor descriptor = new EventDescriptor();
        descriptor.setIndex(index);
        descriptor.setDescription(new Description(VALID_DESCRIPTION_2));
        descriptor.setStartDateTime(new DateTime(VALID_START_DATETIME_2));
        descriptor.setEndDateTime(new DateTime(VALID_END_DATETIME_2));
        descriptor.setRecurrence(new Recurrence(VALID_RECURRENCE_2));

        String input = String.format("%d%s%s%s%s", index.getOneBased(), DESCRIPTION_DESC_2,
                START_DATETIME_DESC_2, END_DATETIME_DESC_2, RECURRENCE_DESC_2);

        assertParseSuccess(parser, input, new EditEventCommand(descriptor));
    }

    @Test
    void parse_someFields_success() {
        Index index = Index.fromZeroBased(0);
        EventDescriptor descriptor = new EventDescriptor();
        descriptor.setIndex(index);
        descriptor.setDescription(new Description(VALID_DESCRIPTION_2));
        descriptor.setRecurrence(new Recurrence(VALID_RECURRENCE_2));

        String input = String.format("%d%s%s", index.getOneBased(), DESCRIPTION_DESC_2,
                RECURRENCE_DESC_2);

        assertParseSuccess(parser, input, new EditEventCommand(descriptor));
    }

    @Test
    void parse_oneField_success() {
        Index index = Index.fromZeroBased(0);
        EventDescriptor descriptor = new EventDescriptor();
        descriptor.setIndex(index);
        descriptor.setDescription(new Description(VALID_DESCRIPTION_2));
        String input = String.format("%d%s", index.getOneBased(), DESCRIPTION_DESC_2);
        assertParseSuccess(parser, input, new EditEventCommand(descriptor));

        descriptor = new EventDescriptor();
        descriptor.setIndex(index);
        descriptor.setStartDateTime(new DateTime(VALID_START_DATETIME_2));
        input = String.format("%d%s", index.getOneBased(), START_DATETIME_DESC_2);
        assertParseSuccess(parser, input, new EditEventCommand(descriptor));

        descriptor = new EventDescriptor();
        descriptor.setIndex(index);
        descriptor.setEndDateTime(new DateTime(VALID_END_DATETIME_2));
        input = String.format("%d%s", index.getOneBased(), END_DATETIME_DESC_2);
        assertParseSuccess(parser, input, new EditEventCommand(descriptor));

        descriptor = new EventDescriptor();
        descriptor.setIndex(index);
        descriptor.setRecurrence(new Recurrence(VALID_RECURRENCE_2));
        input = String.format("%d%s", index.getOneBased(), RECURRENCE_DESC_2);
        assertParseSuccess(parser, input, new EditEventCommand(descriptor));
    }

    @Test
    void parse_duplicateFields_acceptsLast() {
        Index index = Index.fromZeroBased(0);
        EventDescriptor descriptor = new EventDescriptor();
        descriptor.setIndex(index);
        descriptor.setDescription(new Description(VALID_DESCRIPTION_2));
        descriptor.setStartDateTime(new DateTime(VALID_START_DATETIME_2));
        descriptor.setEndDateTime(new DateTime(VALID_END_DATETIME_2));
        descriptor.setRecurrence(new Recurrence(VALID_RECURRENCE_2));

        String input = String.format("%d%s%s%s%s%s%s%s%s", index.getOneBased(), DESCRIPTION_DESC_1, DESCRIPTION_DESC_2,
                START_DATETIME_DESC_1, START_DATETIME_DESC_2, END_DATETIME_DESC_1, END_DATETIME_DESC_2,
                RECURRENCE_DESC_1, RECURRENCE_DESC_2);

        assertParseSuccess(parser, input, new EditEventCommand(descriptor));
    }
}
