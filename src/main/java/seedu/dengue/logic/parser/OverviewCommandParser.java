package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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
        String trimmedArgs = args.trim().toLowerCase();

        switch (trimmedArgs) {
        case "location":
            // Fallthrough
        case "p":
            // Fallthrough
        case "p/":
            // Fallthrough
        case "postal":
            return new OverviewCommand(new PostalOverview(), "POSTAL");

        case "a":
            // Fallthrough
        case "a/":
            // Fallthrough
        case "age":
            return new OverviewCommand(new AgeOverview(), "AGE");

        case "v":
            // Fallthrough
        case "v/":
            // Fallthrough
        case "variant":
            return new OverviewCommand(new VariantOverview(), "VARIANT");

        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, OverviewCommand.MESSAGE_USAGE));
        }
    }

}
