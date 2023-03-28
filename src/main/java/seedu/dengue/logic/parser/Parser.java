package seedu.dengue.logic.parser;

import seedu.dengue.logic.commands.Command;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @throws seedu.dengue.logic.parser.exceptions.ParseException if {@code userInput} is not formatted
     */
    T parse(String userInput) throws seedu.dengue.logic.parser.exceptions.ParseException;
}
