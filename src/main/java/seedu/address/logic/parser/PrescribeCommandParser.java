package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
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

import seedu.address.logic.commands.PrescribeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Nric;
import seedu.address.model.prescription.Cost;
import seedu.address.model.prescription.Medication;
import seedu.address.model.prescription.Prescription;

/**
 * Parses input arguments and creates a new PrescribeCommand object
 */
public class PrescribeCommandParser implements Parser<PrescribeCommand> {

    //@@author Jeffry Lum-reused
    //Reused from https://nus-cs2103-ay2223s2.github.io/tp/tutorials/AddRemark.html
    /**
     * Parses the given {@code String} of arguments in the context of the PrescribeCommand
     * and returns an PrescribeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PrescribeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_MEDICATION, PREFIX_COST, PREFIX_DR_NRIC,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_BOOKING, PREFIX_TAG, PREFIX_ADDRESS);

        if (!argMultimap.getValue(PREFIX_NRIC).isPresent() || !argMultimap.getValue(PREFIX_MEDICATION).isPresent()
                || !argMultimap.getValue(PREFIX_COST).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrescribeCommand.MESSAGE_USAGE));
        }

        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        Medication medication = ParserUtil.parseMedication(argMultimap.getValue(PREFIX_MEDICATION).get());
        Cost cost = ParserUtil.parseCost(argMultimap.getValue(PREFIX_COST).get());
        Prescription prescription = new Prescription(medication, cost);

        return new PrescribeCommand(nric, prescription);
    }
    //@@author Jeffry Lum
}
