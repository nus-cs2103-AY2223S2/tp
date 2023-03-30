package seedu.address.logic.parser;

import seedu.address.logic.commands.LoadCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.UiInputRequiredException;

/**
 * Parses input arguments and creates a new LoadCommand object
 */
public class LoadCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the LoadCommand
     * and returns a LoadCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws UiInputRequiredException if Ui input is required
     */
    public LoadCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new UiInputRequiredException("Ui input required!");
        }

        if (trimmedArgs.equals("!")) {
            return new LoadCommand(null);
        }

        return new LoadCommand(trimmedArgs);
    }
}
