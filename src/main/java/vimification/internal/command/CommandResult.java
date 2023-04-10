package vimification.internal.command;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private boolean shouldRefreshUi;


    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = Objects.requireNonNull(feedbackToUser);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and configure
     * the result to refresh the Ui upon success.
     */
    public CommandResult(String feedbackToUser, boolean shouldRefreshUi) {
        this.feedbackToUser = Objects.requireNonNull(feedbackToUser);
        this.shouldRefreshUi = shouldRefreshUi;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean getShouldRefreshUi() {
        return shouldRefreshUi;
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
        return feedbackToUser.equals(otherCommandResult.feedbackToUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser);
    }

}
