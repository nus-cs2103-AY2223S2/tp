package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_VARIANT;

import seedu.dengue.logic.commands.SortCommand;
import seedu.dengue.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toLowerCase();

        if (trimmedArgs.equals(PREFIX_POSTAL.getPrefix())) {
            return new SortCommand("POSTAL");
        } else if (trimmedArgs.equals(PREFIX_AGE.getPrefix())) {
            return new SortCommand("AGE");
        } else if (trimmedArgs.equals(PREFIX_DATE.getPrefix())) {
            return new SortCommand("DATE");
        } else if (trimmedArgs.equals(PREFIX_VARIANT.getPrefix())) {
            return new SortCommand("VARIANT");
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
