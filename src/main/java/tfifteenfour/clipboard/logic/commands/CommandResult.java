package tfifteenfour.clipboard.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final Command command;

    private final String feedbackToUser;

    /** The roster state has been modified */
    private final boolean hasChangedModelState;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(Command command, String feedbackToUser, boolean hasChangedModelState) {
        this.command = command;
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.hasChangedModelState = hasChangedModelState;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(Command command, String feedbackToUser) {
        this(command, feedbackToUser, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isStateModified() {
        return hasChangedModelState;
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

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && command == otherCommandResult.command;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, command);
    }

    public Command getCommand() {
        return this.command;
    }

}
