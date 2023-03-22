package seedu.address.logic.parser;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.SearchAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand>{

    public static final String ENDTIME_BEFORE_STATRTIME = "endTime can not be before startTime";

    // command: makeApp /from {time} /to {time}
    public AddAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        // a string with: startTime|endTime
        String parsedTime = ParserUtil.parseAddAppointmentCommand(trimmedArgs);
        int idxSlash = parsedTime.indexOf('|');
        String startTimeStr = parsedTime.substring(0, idxSlash).trim();
        String endTimeStr = parsedTime.substring(idxSlash + 1).trim();
        LocalDateTime startTime = ParserUtil.parseTime(startTimeStr);
        LocalDateTime endTime = ParserUtil.parseTime(endTimeStr);
        if (endTime.isBefore(startTime)) {
            throw new ParseException(ENDTIME_BEFORE_STATRTIME);
        }
        return new AddAppointmentCommand(new Appointment(startTime, endTime));
    }
}
