package seedu.quickcontacts.logic.parser;
import static java.util.Objects.requireNonNull;
import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.quickcontacts.logic.commands.AutocompleteResult;
import seedu.quickcontacts.logic.commands.HelpCommand;
import seedu.quickcontacts.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new HelpCommand object.
 */
public class HelpCommandParser implements Parser<HelpCommand> {
    @Override
    public HelpCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        if (userInput.isEmpty()) {
            return new HelpCommand();
        }
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.split("\\s+").length != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        return new HelpCommand(trimmedArgs);
    }

    @Override
    public AutocompleteResult getAutocompleteSuggestion(String input) {
        return new AutocompleteResult();
    }
}
