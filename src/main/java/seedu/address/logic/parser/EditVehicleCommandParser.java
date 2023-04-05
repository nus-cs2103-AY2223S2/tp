package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BRAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATE_NUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VEHICLE_TYPE;

import seedu.address.logic.commands.EditVehicleCommand;
import seedu.address.logic.commands.EditVehicleCommand.EditVehicleDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditVehicleCommand object
 */
public class EditVehicleCommandParser implements Parser<EditVehicleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditVehicleCommand
     * and returns an EditVehicleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditVehicleCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INTERNAL_ID, PREFIX_PLATE_NUM,
                PREFIX_BRAND, PREFIX_CUSTOMER_ID, PREFIX_VEHICLE_TYPE, PREFIX_VEHICLE_COLOR);
        // If no id present, don't know what to edit, throw error.
        if (!argMultimap.getValue(PREFIX_INTERNAL_ID).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditVehicleCommand.MESSAGE_USAGE));
        }

        EditVehicleDescriptor editVehicleDescriptor = new EditVehicleDescriptor();

        editVehicleDescriptor.setId(ParserUtil.parseInt(argMultimap.getValue(PREFIX_INTERNAL_ID).get()));

        if (argMultimap.getValue(PREFIX_PLATE_NUM).isPresent()) {
            editVehicleDescriptor.setPlateNumber(ParserUtil.parseString(argMultimap.getValue(PREFIX_PLATE_NUM).get()));
        }
        if (argMultimap.getValue(PREFIX_BRAND).isPresent()) {
            editVehicleDescriptor.setBrand(ParserUtil.parseString(argMultimap.getValue(PREFIX_BRAND).get()));
        }
        if (argMultimap.getValue(PREFIX_CUSTOMER_ID).isPresent()) {
            editVehicleDescriptor.setOwnerId(Integer.parseInt(argMultimap.getValue(PREFIX_CUSTOMER_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_VEHICLE_TYPE).isPresent()) {
            editVehicleDescriptor.setType(ParserUtil.parseVehicleType(argMultimap.getValue(PREFIX_VEHICLE_TYPE).get()));
        }

        if (argMultimap.getValue(PREFIX_VEHICLE_COLOR).isPresent()) {
            editVehicleDescriptor.setColor(ParserUtil.parseString(argMultimap.getValue(PREFIX_VEHICLE_COLOR).get()));
        }

        if (!editVehicleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditVehicleCommand.MESSAGE_NOT_EDITED);
        }

        return new EditVehicleCommand(editVehicleDescriptor);
    }

}
