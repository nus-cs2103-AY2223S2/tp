package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.COFFEE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.appointment.MeetupDate;

class AddAppointmentCommandParserTest {

    private final AddAppointmentCommandParser parser = new AddAppointmentCommandParser();
    @Test
    void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, "1 an/Coffee chat ad/01.01.2024",
                new AddAppointmentCommand(Index.fromOneBased(1), COFFEE));

        // multiple appointment names -> last name accepted
        assertParseSuccess(parser, "1 an/Testing an/Coffee chat ad/01.01.2024",
                new AddAppointmentCommand(Index.fromOneBased(1), COFFEE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);

        // missing appointment name
        assertParseFailure(parser, "1 ad/01.01.2024", expectedMessage);

        // missing appointment date
        assertParseFailure(parser, "1 an/Test goals", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // appointment date is set in the past
        assertParseFailure(parser, "1 an/Appointment ad/01.01.2023",
                MeetupDate.MESSAGE_PAST_DATE);

        // appointment date is in the wrong format
        assertParseFailure(parser, "1 an/Appointment ad/01-01-2023",
                MeetupDate.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertThrows(ParseException.class, () -> parser.parse("-1 an/Appointment ad/01.01.2024"));

    }
}
