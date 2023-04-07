package seedu.address.logic.commands;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    protected static String getParameterUsage(List<Prefix> argumentPrefixes) {
        return "Parameters:\n" + argumentPrefixes.stream()
                .map(Prefix::toPlaceholderString)
                .collect(Collectors.joining(" "));
    }

    protected static String getExampleUsage(String commandWord, List<Prefix> argumentPrefixes) {
        return "Example:\n" + commandWord + " " + argumentPrefixes.stream()
                .map(Prefix::toExampleString)
                .filter(Predicate.not(String::isEmpty))
                .collect(Collectors.joining(" "));
    }
}
