package seedu.vms.logic.parser.appointment;

import static seedu.vms.commons.core.Messages.MESSAGE_MISSING_PARAMETER_FORMAT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.vms.commons.core.Retriever;
import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.commands.appointment.AddCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.Prefix;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.model.vaccination.VaxType;

// @@author nusE0726844
/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements CommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddCommand parse(ArgumentMultimap argsMap) throws ParseException {
        if (!arePrefixesPresent(argsMap, PREFIX_PATIENT, PREFIX_STARTTIME, PREFIX_ENDTIME, PREFIX_VACCINATION)) {
            throw new ParseException(String.format(MESSAGE_MISSING_PARAMETER_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Index patientId = ParserUtil.parseIndex(argsMap.getValue(PREFIX_PATIENT).get());
        LocalDateTime startTime = ParserUtil.parseDate(argsMap.getValue(PREFIX_STARTTIME).get());
        LocalDateTime endTime = ParserUtil.parseDate(argsMap.getValue(PREFIX_ENDTIME).get());
        Retriever<String, VaxType> vaxTypeRetriever = ParserUtil
                .parseVaxRetriever(argsMap.getValue(PREFIX_VACCINATION).get());

        if (Appointment.isInvalidAppointmentTime(startTime)) {
            throw new ParseException(Appointment.MESSAGE_START_TIME_CONSTRAINTS);
        }

        if (!Appointment.isValidDuration(startTime, endTime)) {
            throw new ParseException(Appointment.MESSAGE_DURATION_CONSTRAINTS);
        }
        return new AddCommand(patientId, vaxTypeRetriever, startTime, endTime);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
