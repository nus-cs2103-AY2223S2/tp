package seedu.address.logic.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Appointment;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    public static final String ENDTIME_BEFORE_STATRTIME = "endTime should be after startTime";
    public static final String DIFFERENT_START_AND_END_DATE = "startTime and endTime should be at the same date";

    public static final String STARTTIME_BEFORE_NOW = "startTime should not be before current time";

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    // command: makeApp {index} /from {time} /to {time}
    public AddAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        // a string with: index,startTime|endTime
        String parsedTime = ParserUtil.parseAddAppointmentCommand(trimmedArgs);
        int idxComma = parsedTime.indexOf(',');
        int idxSlash = parsedTime.indexOf('|');
        String indexStr = parsedTime.substring(0, idxComma);
        String startTimeStr = parsedTime.substring(idxComma + 1, idxSlash).trim();
        String endTimeStr = parsedTime.substring(idxSlash + 1).trim();
        Index index = ParserUtil.parseIndex(indexStr);
        LocalDateTime startTime = ParserUtil.parseTime(startTimeStr);
        LocalDateTime endTime = ParserUtil.parseTime(endTimeStr);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime nowTime = LocalDateTime.now();
        if (startTime.isBefore(nowTime)) {
            throw new ParseException(STARTTIME_BEFORE_NOW);
        }
        if (!startTime.format(formatter).equals(endTime.format(formatter))) {
            throw new ParseException(DIFFERENT_START_AND_END_DATE);
        }
        if (!endTime.isAfter(startTime)) {
            throw new ParseException(ENDTIME_BEFORE_STATRTIME);
        }
        Appointment newAppointment = new Appointment(startTime, endTime);
        return new AddAppointmentCommand(index, newAppointment);
    }
}
