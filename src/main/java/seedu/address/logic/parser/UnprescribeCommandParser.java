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

import seedu.address.logic.commands.UnprescribeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Nric;
import seedu.address.model.prescription.Medication;

/**
 * Parses input arguments and creates a new PrescribeCommand object
 */
public class UnprescribeCommandParser implements Parser<UnprescribeCommand> {

    //@@author Jeffry Lum-reused
    //Reused from https://nus-cs2103-ay2223s2.github.io/tp/tutorials/AddRemark.html
    /**
     * Parses the given {@code String} of arguments in the context of the UnprescribeCommand
     * and returns an UnprescribeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnprescribeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_MEDICATION, PREFIX_ADDRESS, PREFIX_BOOKING,
                        PREFIX_COST, PREFIX_DR_NRIC, PREFIX_EMAIL, PREFIX_NAME, PREFIX_PHONE, PREFIX_TAG);

        if (!argMultimap.getValue(PREFIX_NRIC).isPresent() || !argMultimap.getValue(PREFIX_MEDICATION).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnprescribeCommand.MESSAGE_USAGE));
        }

        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        Medication medication = ParserUtil.parseMedication(argMultimap.getValue(PREFIX_MEDICATION).get());

        return new UnprescribeCommand(nric, medication);
    }
    //@@author Jeffry Lum
}
