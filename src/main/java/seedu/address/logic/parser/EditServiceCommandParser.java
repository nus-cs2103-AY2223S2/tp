package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_ID;

import java.time.LocalDate;
import java.util.Optional;

import seedu.address.logic.commands.EditServiceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.service.ServiceStatus;

/**
 * Parses input arguments and creates a new EditService object
 */
public class EditServiceCommandParser implements Parser<EditServiceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditService
     * and returns an EditService object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditServiceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_INTERNAL_ID, PREFIX_VEHICLE_ID, PREFIX_SERVICE_START, PREFIX_SERVICE_END,
                PREFIX_SERVICE_STATUS, PREFIX_SERVICE_DESCRIPTION);


        // If no id present, don't know what to edit, throw error.
        if (argMultimap.getValue(PREFIX_INTERNAL_ID).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditServiceCommand.MESSAGE_USAGE));
        }

        int serviceId = ParserUtil.parseInt(argMultimap.getValue(PREFIX_INTERNAL_ID).get());
        Optional<Integer> vehicleId = Optional.empty();
        Optional<LocalDate> startDate = Optional.empty();
        Optional<LocalDate> endDate = Optional.empty();
        Optional<String> description = Optional.empty();
        Optional<ServiceStatus> status = Optional.empty();


        if (argMultimap.getValue(PREFIX_VEHICLE_ID).isPresent()) {
            vehicleId = Optional.of(ParserUtil.parseInt(argMultimap.getValue(PREFIX_VEHICLE_ID).get()));
        }

        // start
        if (argMultimap.getValue(PREFIX_SERVICE_START).isPresent()) {
            startDate = Optional.of(ParserUtil.parseDate(argMultimap.getValue(PREFIX_SERVICE_START).get()));
        }

        if (argMultimap.getValue(PREFIX_SERVICE_END).isPresent()) {
            endDate = Optional.of(ParserUtil.parseDate(argMultimap.getValue(PREFIX_SERVICE_END).get()));
        }

        if (argMultimap.getValue(PREFIX_SERVICE_STATUS).isPresent()) {
            status = Optional.of(ParserUtil.parseServiceStatus(argMultimap.getValue(PREFIX_SERVICE_STATUS).get()));
        }

        if (argMultimap.getValue(PREFIX_SERVICE_DESCRIPTION).isPresent()) {
            description = Optional.of(argMultimap.getValue(PREFIX_SERVICE_DESCRIPTION).get());
        }
        return new EditServiceCommand(serviceId, vehicleId, startDate, endDate, status, description);
    }

}
