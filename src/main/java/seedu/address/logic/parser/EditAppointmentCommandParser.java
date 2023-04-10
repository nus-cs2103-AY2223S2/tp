
package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAppointment object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    public static final String MESSAGE_MISSING_DATE_OR_TIME = "Both Date and Time must be specified when attempting to"
            + "edit either";

    /**
     * Parses the given {@code String} of arguments in the context of the EditAppointment
     * and returns an EditAppointment object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INTERNAL_ID, PREFIX_CUSTOMER_ID, PREFIX_DATE, PREFIX_TIME);

        // If no appt id present, don't know what to edit, throw error.
        if (!argMultimap.getValue(PREFIX_INTERNAL_ID).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE));
        }

        int appointmentId = ParserUtil.parseInt(argMultimap.getValue(PREFIX_INTERNAL_ID).get());
        Optional<Integer> customerId = Optional.empty();
        if (argMultimap.getValue(PREFIX_CUSTOMER_ID).isPresent()) {
            customerId = Optional.of(ParserUtil.parseInt(argMultimap.getValue(PREFIX_CUSTOMER_ID).get()));
        } // Invalid customer Id handled by EditAppointmentCommand

        Optional<LocalDateTime> dateTime = Optional.empty();
        // If either is present, enforce that both must be present.
        if (argMultimap.getValue(PREFIX_DATE).isPresent() || argMultimap.getValue(PREFIX_TIME).isPresent()) {
            if (arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_TIME)) {
                LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
                LocalTime time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
                dateTime = Optional.of(date.atTime(time));
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_DATE_OR_TIME,
                                EditAppointmentCommand.MESSAGE_USAGE));
            }
        }
        return new EditAppointmentCommand(appointmentId, customerId, dateTime);

        // Leaving this commented for reference if in future EditAppointmentCommandParser also handles setStaffIds
        // Set<Integer> staffIds
        //        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editAppointmentDescriptor::setTags);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
