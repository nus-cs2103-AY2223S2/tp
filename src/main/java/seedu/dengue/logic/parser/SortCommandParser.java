package seedu.dengue.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.dengue.logic.commands.SortCommand;
import seedu.dengue.logic.comparators.PersonAgeComparator;
import seedu.dengue.logic.comparators.PersonDateComparator;
import seedu.dengue.logic.comparators.PersonNameComparator;
import seedu.dengue.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim().toLowerCase();

        if (trimmedArgs.equals(PREFIX_NAME.getPrefix())) {
            return new SortCommand(new PersonNameComparator(), "NAME");
        } else if (trimmedArgs.equals(PREFIX_AGE.getPrefix())) {
            return new SortCommand(new PersonAgeComparator(), "AGE");
        } else if (trimmedArgs.equals(PREFIX_DATE.getPrefix())) {
            return new SortCommand(new PersonDateComparator(), "DATE");
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
