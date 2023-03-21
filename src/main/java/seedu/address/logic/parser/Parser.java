package seedu.address.logic.parser;

import seedu.address.logic.commands.AutocompleteResult;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;

    /**
     * Parses {@code input} to retrieve available suggestions based
     * on the command it matches.
     */
    AutocompleteResult getAutocompleteSuggestion(String input);
}
