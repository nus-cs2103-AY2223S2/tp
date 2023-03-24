package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Command is undoable and can be saved to undo & redo history. */
    private final boolean undoable;

    /**
     * Command only ever produces one resultant Model state, given the initial Model state.
     * (If false, a snapshot of Model must be taken afterwards to safely redo this Command.)
     */
    private final boolean deterministic;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean undoable, boolean deterministic,
                         boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.undoable = undoable;
        this.deterministic = deterministic;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code undoable},
     * and {@code deterministic}, with other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean undoable, boolean deterministic) {
        this(feedbackToUser, undoable, deterministic, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isUndoable() {
        return undoable;
    }

    public boolean isDeterministic() {
        return deterministic;
    }

    @Override
    public String toString() {
        return feedbackToUser + "; showHelp: " + showHelp + "; exit: " + exit;
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
                && undoable == otherCommandResult.undoable
                && deterministic == otherCommandResult.deterministic
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, undoable, deterministic, showHelp, exit);
    }

}
