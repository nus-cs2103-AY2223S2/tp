package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_ID;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddServiceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.service.ServiceStatus;

/**
 * Parses input arguments and creates new AddServiceCommandParser object
 */
public class AddServiceCommandParser implements Parser<AddServiceCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddVehicleCommand
     * and returns an AddVehicleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddServiceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_VEHICLE_ID, PREFIX_SERVICE_DURATION, PREFIX_SERVICE_STATUS,
                PREFIX_SERVICE_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_VEHICLE_ID, PREFIX_SERVICE_DESCRIPTION)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddServiceCommand.MESSAGE_USAGE));
        }

        int vehicleId = ParserUtil.parseInt(argMultimap.getValue(PREFIX_VEHICLE_ID).get());
        String serviceDesc = argMultimap.getValue(PREFIX_SERVICE_DESCRIPTION).get();
        Optional<String> rawServiceDuration = argMultimap.getValue(PREFIX_SERVICE_DURATION);
        Optional<String> rawServiceStatus = argMultimap.getValue(PREFIX_SERVICE_STATUS);

        Optional<LocalDate> estimatedFinishDate = rawServiceDuration.flatMap(s -> {
            try {
                int d = ParserUtil.parseInt(s);
                return Optional.of(LocalDate.now().plusDays(d));
            } catch (ParseException e) {
                return Optional.empty();
            }
        });

        if (estimatedFinishDate.isEmpty() && rawServiceDuration.isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddServiceCommand.MESSAGE_USAGE));
        }

        Optional<ServiceStatus> serviceStatus = rawServiceStatus.flatMap(s -> {
            try {
                ServiceStatus status = ParserUtil.parseServiceStatus(s);
                return Optional.of(status);
            } catch (ParseException e) {
                return Optional.empty();
            }
        });

        if (serviceStatus.isEmpty() && rawServiceStatus.isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddServiceCommand.MESSAGE_USAGE));
        }

        return new AddServiceCommand(vehicleId, Optional.empty(),
            serviceDesc, estimatedFinishDate, serviceStatus);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
