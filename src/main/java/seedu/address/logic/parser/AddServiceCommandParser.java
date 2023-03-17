package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_ID;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddServiceCommand;
import seedu.address.logic.idgen.IdGenerator;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.service.Service;
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

        if (!arePrefixesPresent(argMultimap, PREFIX_VEHICLE_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddServiceCommand.MESSAGE_USAGE));
        }

        int vehicleId = ParserUtil.parseInt(argMultimap.getValue(PREFIX_VEHICLE_ID).get());
        String serviceDesc = argMultimap.getAllValues(PREFIX_SERVICE_DESCRIPTION).stream().findFirst().orElse("");
        Optional<String> rawServiceDuration = argMultimap.getAllValues(PREFIX_SERVICE_DURATION).stream().findFirst();
        Optional<String> rawServiceStatus = argMultimap.getAllValues(PREFIX_SERVICE_STATUS).stream().findFirst();

        int serviceDuration = ParserUtil.parseInt(rawServiceDuration.orElse("7"));
        ServiceStatus serviceStatus = ParserUtil.parseServiceStatus(rawServiceStatus.orElse("to repair"));

        Service service = new Service(IdGenerator.generateServiceId(), vehicleId, serviceDuration, serviceDesc,
                serviceStatus);
        return new AddServiceCommand(service);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
