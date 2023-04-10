package seedu.techtrack.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 * Refactoring to generic class inspired by
 * https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/master/src/main/java/seedu/foodrem/logic/commands/
 * CommandResult.java (link truncated to pass checkstyle)
 */
public class CommandResult<T> {

    private final T output;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Replaces result display with more information on command */
    private final boolean view;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(T output, boolean showHelp, boolean exit, boolean view) {
        this.output = requireNonNull(output);
        this.showHelp = showHelp;
        this.exit = exit;
        this.view = view;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(T output, boolean showHelp, boolean exit) {
        this(output, showHelp, exit, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(T output) {
        this(output, false, false, false);
    }

    public T getOutput() {
        return output;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult<?> otherCommandResult = (CommandResult<?>) other;
        return output.equals(otherCommandResult.output)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(output, showHelp, exit);
    }

}
