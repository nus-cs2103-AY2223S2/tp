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
                && hasChangedModelState == otherCommandResult.hasChangedModelState
                && (command == null || otherCommandResult.command == null
                ? command == otherCommandResult.command
                : command.getClass() == otherCommandResult.command.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, feedbackToUser, hasChangedModelState);
    }

    public Command getCommand() {
        return this.command;
    }

}
