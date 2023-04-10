package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DR_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.AppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Booking;
import seedu.address.model.person.Nric;

/**
 * Parses input arguments and creates a new AppointmentCommand object
 */
public class AppointmentCommandParser implements Parser<AppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AppointmentCommand
     * and returns an AppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_BOOKING, PREFIX_DR_NRIC, PREFIX_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_COST, PREFIX_MEDICATION, PREFIX_TAG, PREFIX_ADDRESS);

        parseInputs(argMultimap);

        Nric patientNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        Booking booking = ParserUtil.parseBooking(argMultimap.getValue(PREFIX_BOOKING).get());
        Nric drNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_DR_NRIC).get());

        Appointment appointment = new Appointment(patientNric, booking, drNric);

        return new AppointmentCommand(appointment);
    }

    private void parseInputs(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC, PREFIX_BOOKING, PREFIX_DR_NRIC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AppointmentCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
