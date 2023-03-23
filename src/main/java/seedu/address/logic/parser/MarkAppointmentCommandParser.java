package seedu.address.logic.parser;

import seedu.address.logic.commands.MarkAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MarkAppointmentCommandParser implements Parser<MarkAppointmentCommand> {

    public MarkAppointmentCommand parse(String args) throws ParseException {
        return new MarkAppointmentCommand();
    }
}
