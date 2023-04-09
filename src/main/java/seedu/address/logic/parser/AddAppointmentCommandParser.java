package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_NAME;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.appointment.Appointment;
import seedu.address.model.client.appointment.AppointmentName;
import seedu.address.model.client.appointment.MeetupDate;

/**
 * Parses input arguments and creates a new {@code AddAppointmentCommand} object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddAppointmentCommand}
     * @return an {@code AddAppointmentCommand} object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_APPOINTMENT_NAME, PREFIX_APPOINTMENT_DATE);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            logger.info("Missing Index: " + args);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE), ive);
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_APPOINTMENT_NAME, PREFIX_APPOINTMENT_DATE)
                || argMultimap.getPreamble().isEmpty()) {
            logger.info("Missing parameters: " + args);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
        }
        AppointmentName appointmentName = ParserUtil.parseAppointmentName(
                argMultimap.getValue(PREFIX_APPOINTMENT_NAME).get());
        MeetupDate meetupDate = ParserUtil.parseMeetupDate(argMultimap.getValue(PREFIX_APPOINTMENT_DATE).get());
        Appointment appointment = new Appointment(appointmentName, meetupDate);
        logger.info("Parsed: " + args);
        return new AddAppointmentCommand(index, appointment);

    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
