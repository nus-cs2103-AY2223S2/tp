package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.id.AppointmentId;
import seedu.address.model.id.Id;
import seedu.address.model.patient.Name;


/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TIMESLOT, PREFIX_DESCRIPTION, PREFIX_DOCTOR);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TIMESLOT, PREFIX_DESCRIPTION, PREFIX_DOCTOR)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        Name patientName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Timeslot timeslot = ParserUtil.parseTimeslot(argMultimap.getValue(PREFIX_TIMESLOT).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Doctor doctor = ParserUtil.parseDoctor(argMultimap.getValue(PREFIX_DOCTOR).get());
        AppointmentId appointmentId = new AppointmentId(Id.generateUniqueId());

        Appointment appointment = new Appointment(appointmentId, patientName, timeslot, description, doctor);

        return new AddAppointmentCommand(appointment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
