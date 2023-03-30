package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.dengue.logic.parser.CliSyntax.PREFIX_VARIANT;

import seedu.dengue.logic.commands.OverviewCommand;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.overview.AgeOverview;
import seedu.dengue.model.overview.PostalOverview;
import seedu.dengue.model.overview.VariantOverview;

/**
 * Parses input arguments and creates a new OverviewCommand object
 */
public class OverviewCommandParser implements Parser<OverviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the OverviewCommand
     * and returns a OverviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public OverviewCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.equals(PREFIX_POSTAL.getPrefix())) {
            return new OverviewCommand(new PostalOverview(), "POSTAL");
        } else if (trimmedArgs.equals(PREFIX_AGE.getPrefix())) {
            return new OverviewCommand(new AgeOverview(), "AGE");
        } else if (trimmedArgs.equals(PREFIX_VARIANT.getPrefix())) {
            return new OverviewCommand(new VariantOverview(), "VARIANT");
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, OverviewCommand.MESSAGE_USAGE));
        }
    }
}
