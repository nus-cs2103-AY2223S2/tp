package seedu.sprint.logic.parser;

import seedu.sprint.logic.commands.ApplicationCommand;
import seedu.sprint.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface ApplicationParser<T extends ApplicationCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format.
     */
    T parse(String userInput) throws ParseException;
}
