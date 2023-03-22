package tfifteenfour.clipboard.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final Command command;

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The roster state has been modified */
    private final boolean hasChangedModelState;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(Command command, String feedbackToUser, boolean showHelp, boolean hasChangedModelState) {
        this.command = command;
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.hasChangedModelState = hasChangedModelState;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(Command command, String feedbackToUser) {
        this(command, feedbackToUser, false, false);
    }

    public CommandResult(Command command, String feedbackToUser, boolean hasChangedModelState) {
        this(command, feedbackToUser, false, hasChangedModelState);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
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
                && showHelp == otherCommandResult.showHelp
                && command == otherCommandResult.command;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, command);
    }

}
