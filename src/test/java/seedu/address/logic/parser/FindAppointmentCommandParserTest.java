package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.logic.commands.FindDetailsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.TimeInTimeslotPredicate;
import seedu.address.model.patient.DetailsContainKeywordsPredicate;

public class FindAppointmentCommandParserTest {

    private FindAppointmentCommandParser parser = new FindAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        try {
            // no leading and trailing whitespaces
            FindAppointmentCommand expectedFindCommand =
                new FindAppointmentCommand(new TimeInTimeslotPredicate("19032023 08:30"));
            assertParseSuccess(parser, "19032023 08:30", expectedFindCommand);
        } catch (ParseException pe) {
            fail();
        }
    }

}
