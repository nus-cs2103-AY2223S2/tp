package seedu.address.logic.parser.jobs;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.jobs.CompleteDeliveryJobCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class CompleteDeliveryJobCommandParser implements Parser<CompleteDeliveryJobCommand> {

    private Boolean setDelivered;

    /**
     * Constructs a CompleteDeliveryJobCommandParser.
     *
     * @param setDelivered
     */
    public CompleteDeliveryJobCommandParser(Boolean setDelivered) {
        this.setDelivered = setDelivered;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CompleteDeliveryJobCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_JOB_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_JOB_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompleteDeliveryJobCommand.MESSAGE_USAGE));
        }

        return new CompleteDeliveryJobCommand(argMultimap.getValue(PREFIX_JOB_ID).get(), setDelivered);
    }

}
