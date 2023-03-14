package seedu.vms.logic.parser.appointment;

import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VAX_GROUPS;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.commands.appointment.AddCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.ArgumentTokenizer;
import seedu.vms.logic.parser.Parser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.Prefix;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;
import seedu.vms.model.appointment.Appointment;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        if (!arePrefixesPresent(argMultimap, PREFIX_PATIENT, PREFIX_STARTTIME, PREFIX_ENDTIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Index patientId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PATIENT).get());
        LocalDateTime startTime = ParserUtil.parseDate(argMultimap.getValue(PREFIX_STARTTIME).get());
        LocalDateTime endTime = ParserUtil.parseDate(argMultimap.getValue(PREFIX_ENDTIME).get());
        GroupName vaccine = ParserUtil.parseGroupName(argMultimap.getValue(PREFIX_VAX_GROUPS).get());

        Appointment appointment = new Appointment(patientId, startTime, endTime, vaccine);

        return new AddCommand(appointment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
