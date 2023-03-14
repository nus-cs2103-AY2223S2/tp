package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.PrescribeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Medication;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;

/**
 * Parses input arguments and creates a new PrescribeCommand object
 */
public class PrescribeCommandParser implements Parser<PrescribeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PrescribeCommand
     * and returns an PrescribeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PrescribeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEDICATION);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrescribeCommand.MESSAGE_USAGE));
        }

        Medication medication = new Medication(argMultimap.getValue(PREFIX_MEDICATION).orElse(""));

        return new PrescribeCommand(index, medication);
    }
}
