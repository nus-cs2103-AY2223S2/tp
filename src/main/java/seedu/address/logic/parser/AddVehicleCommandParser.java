package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BRAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATE_NUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.idgen.IdGenerator;
import seedu.address.logic.commands.AddVehicleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.service.Vehicle;
import seedu.address.model.service.VehicleType;

/**
 * Parses input arguments and creates new AddVehicleCommand object
 */
public class AddVehicleCommandParser implements Parser<AddVehicleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddVehicleCommand
     * and returns an AddVehicleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddVehicleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PLATE_NUM, PREFIX_BRAND, PREFIX_CUSTOMER_ID,
                        PREFIX_VEHICLE_TYPE, PREFIX_VEHICLE_COLOR);

        if (!arePrefixesPresent(argMultimap, PREFIX_PLATE_NUM, PREFIX_BRAND, PREFIX_CUSTOMER_ID, PREFIX_VEHICLE_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVehicleCommand.MESSAGE_USAGE));
        }

        String plateNumber = argMultimap.getValue(PREFIX_PLATE_NUM).get();
        String brand = argMultimap.getValue(PREFIX_BRAND).get();
        int customerId = Integer.parseInt(argMultimap.getValue(PREFIX_CUSTOMER_ID).get());
        String vehicleType = argMultimap.getValue(PREFIX_VEHICLE_TYPE).get();
        String vehicleColor = argMultimap.getValue(PREFIX_VEHICLE_COLOR).get();

        VehicleType type = ParserUtil.parseVehicleType(vehicleType);

        Vehicle vehicle = new Vehicle(IdGenerator.generateVehicleId(), customerId, plateNumber, vehicleColor, brand,
                type);

        return new AddVehicleCommand(vehicle);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
