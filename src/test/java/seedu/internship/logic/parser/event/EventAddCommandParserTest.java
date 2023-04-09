package seedu.internship.logic.parser.event;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.commands.CommandTestUtil.END_DESC_EM11;
import static seedu.internship.logic.commands.CommandTestUtil.EVENT_DESCRIPTION_DESC_EM11;
import static seedu.internship.logic.commands.CommandTestUtil.NAME_DESC_EM11;
import static seedu.internship.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.internship.logic.commands.CommandTestUtil.START_DESC_EM11;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internship.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internship.testutil.TypicalEvents.EM11;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.event.EventAddCommand;
import seedu.internship.model.event.Event;
import seedu.internship.testutil.EventBuilder;

public class EventAddCommandParserTest {
    private EventAddCommandParser parser = new EventAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(EM11).withInternship(null).build();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_EM11 + START_DESC_EM11 + END_DESC_EM11
                + EVENT_DESCRIPTION_DESC_EM11, new EventAddCommand(expectedEvent));
    }

    @Test
    public void parse_withoutStart_success() {
        Event expectedEvent = new EventBuilder(EM11).withInternship(null).build();
        Event expectedDeadline = new EventBuilder(EM11)
                .withInternship(null)
                .withStart(expectedEvent.getEnd().getNumericDateTimeString())
                .build();
        // whitespace only End
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_EM11 + END_DESC_EM11
                + EVENT_DESCRIPTION_DESC_EM11, new EventAddCommand(expectedDeadline));
    }

    @Test
    public void parse_withoutDescription_success() {
        Event expectedEvent = new EventBuilder(EM11).withInternship(null).build();
        Event expectedDeadline = new EventBuilder(EM11).withInternship(null).withDescription("").build();
        // whitespace only End
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NAME_DESC_EM11 + START_DESC_EM11 + END_DESC_EM11,
                new EventAddCommand(expectedDeadline));
    }

    @Test
    public void parse_withoutEnd_failure() {
        // whitespace without End
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_EM11 + START_DESC_EM11,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventAddCommand.MESSAGE_USAGE));
    }
}
