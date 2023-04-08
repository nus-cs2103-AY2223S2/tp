package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.END_DATETIME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.END_DATETIME_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECURRENCE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RECURRENCE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.RECURRENCE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.START_DATETIME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.START_DATETIME_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRENCE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATETIME_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.event.Event;
import seedu.address.model.event.OneTimeEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;

class AddEventCommandParserTest {

    private final AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Event expectedEvent = new RecurringEvent(new Description(VALID_DESCRIPTION_1),
                new DateTime(VALID_START_DATETIME_1), new DateTime(VALID_END_DATETIME_1),
                new Recurrence(VALID_RECURRENCE_1), Set.of());

        // whitespace only preamble
        assertParseSuccess(parser, String.format("%s%s%s%s%s", PREAMBLE_WHITESPACE, DESCRIPTION_DESC_1,
                START_DATETIME_DESC_1, END_DATETIME_DESC_1, RECURRENCE_DESC_1), new AddEventCommand(expectedEvent));

        // last of multiple descriptions
        assertParseSuccess(parser, String.format("%s%s%s%s%s", DESCRIPTION_DESC_2, DESCRIPTION_DESC_1,
                START_DATETIME_DESC_1, END_DATETIME_DESC_1, RECURRENCE_DESC_1), new AddEventCommand(expectedEvent));

        // last of multiple start date times
        assertParseSuccess(parser, String.format("%s%s%s%s%s", DESCRIPTION_DESC_1, START_DATETIME_DESC_2,
                START_DATETIME_DESC_1, END_DATETIME_DESC_1, RECURRENCE_DESC_1), new AddEventCommand(expectedEvent));

        // last of multiple end date times
        assertParseSuccess(parser, String.format("%s%s%s%s%s", DESCRIPTION_DESC_1, END_DATETIME_DESC_2,
                START_DATETIME_DESC_1, END_DATETIME_DESC_1, RECURRENCE_DESC_1), new AddEventCommand(expectedEvent));

        // last of multiple recurrences
        assertParseSuccess(parser, String.format("%s%s%s%s%s", DESCRIPTION_DESC_1, RECURRENCE_DESC_2,
                START_DATETIME_DESC_1, END_DATETIME_DESC_1, RECURRENCE_DESC_1), new AddEventCommand(expectedEvent));
    }

    @Test
    void parse_optionalRecurrenceMissing_success() {
        Event expectedEvent = new OneTimeEvent(new Description(VALID_DESCRIPTION_1),
                new DateTime(VALID_START_DATETIME_1), new DateTime(VALID_END_DATETIME_1), Set.of());
        assertParseSuccess(parser, String.format("%s%s%s", DESCRIPTION_DESC_1,
                START_DATETIME_DESC_1, END_DATETIME_DESC_1), new AddEventCommand(expectedEvent));
    }

    @Test
    void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing description
        assertParseFailure(parser,
                String.format("%s%s%s", START_DATETIME_DESC_1, END_DATETIME_DESC_1, RECURRENCE_DESC_1),
                expectedMessage);
        // missing start date time
        assertParseFailure(parser,
                String.format("%s%s%s", DESCRIPTION_DESC_1, END_DATETIME_DESC_1, RECURRENCE_DESC_1),
                expectedMessage);
        // missing end date time
        assertParseFailure(parser,
                String.format("%s%s%s", START_DATETIME_DESC_1, DESCRIPTION_DESC_1, RECURRENCE_DESC_1),
                expectedMessage);
    }

    @Test
    void parse_invalidValue_failure() {
        assertParseFailure(parser, String.format("%s%s%s%s", INVALID_DESCRIPTION_DESC,
                START_DATETIME_DESC_1, END_DATETIME_DESC_1, RECURRENCE_DESC_1), Description.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, String.format("%s%s%s%s", DESCRIPTION_DESC_1,
                INVALID_START_DATETIME_DESC, END_DATETIME_DESC_1, RECURRENCE_DESC_1), DateTime.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, String.format("%s%s%s%s", DESCRIPTION_DESC_1,
                START_DATETIME_DESC_1, INVALID_END_DATETIME_DESC, RECURRENCE_DESC_1), DateTime.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, String.format("%s%s%s%s", DESCRIPTION_DESC_1,
                START_DATETIME_DESC_1, END_DATETIME_DESC_1, INVALID_RECURRENCE_DESC), Recurrence.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_startBeforeEnd_failure() {
        assertParseFailure(parser, "1 e/2023-01-01 0000 s/2023-01-01 0001",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
    }
}
