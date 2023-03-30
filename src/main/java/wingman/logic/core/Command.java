package wingman.logic.core;

import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;

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
        int parsedIndex = Integer.parseInt(string);
        if (parsedIndex <= 0) {
            throw new CommandException(
                    "Input index must be positive.\n"
                            + "Please enter a one-based index, refer to the display list for index.");
        }

        return parsedIndex - 1;
    }
}
