package vimification.internal.command;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private boolean refreshUi;

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and configure
     * the result to refresh the Ui upon success.
     */
    public CommandResult(String feedbackToUser, boolean refreshUi) {
        this.feedbackToUser = Objects.requireNonNull(feedbackToUser);
        this.refreshUi = refreshUi;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean shouldRefreshUi() {
        return refreshUi;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CommandResult)) {
            return false;
        }
        CommandResult otherResult = (CommandResult) other;
        return feedbackToUser.equals(otherResult.feedbackToUser)
                && refreshUi == otherResult.refreshUi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, refreshUi);
    }

    @Override
    public String toString() {
        return "CommandResult [feedbackToUser=" + feedbackToUser + ", refreshUi=" + refreshUi + "]";
    }
}
