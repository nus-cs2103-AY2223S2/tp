package seedu.address.logic.core;

import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public interface Command {
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    CommandResult execute(Model model) throws CommandException;

    /**
     * Parses a one-based index string to zero-based index integer.
     *
     * @param string a string of an index that starts from one
     * @return a zero-based index integer
     */
    static int parseIntegerToZeroBasedIndex(String string) throws CommandException {
        int parsedIndex;
        try {
            parsedIndex = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new CommandException(
                    "NumberFormatException raised. Please input a valid one-based index. "
                            + "You may refer to the display list for index. "
            );
        }

        if (parsedIndex <= 0) {
            throw new CommandException(
                    "Input index must be positive. Please enter a one-based index. "
                            + "You may refer to the display list for index. "
            );
        }

        return parsedIndex - 1;
    }
}
