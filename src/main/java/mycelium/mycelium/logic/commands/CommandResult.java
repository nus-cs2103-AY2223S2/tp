package mycelium.mycelium.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import mycelium.mycelium.logic.uiaction.DoNothingAction;
import mycelium.mycelium.logic.uiaction.UiAction;
import mycelium.mycelium.ui.MainWindow;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;
    private UiAction action;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     *
     * @param feedbackToUser feedback displayed to the user. Must not be null.
     * @param action the action to be executed by the UI
     */
    public CommandResult(String feedbackToUser, UiAction action) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.action = requireNonNull(action);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, new DoNothingAction());
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public void executeUiAction(MainWindow mainWindow) {
        action.execute(mainWindow);
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
            && action.equals(otherCommandResult.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, action);
    }

}
