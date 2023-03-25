package arb.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import arb.model.ListType;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The application should enter link mode. */
    private final boolean enterLinkMode;

    /** What type of list should be shown to the user. */
    private final ListType listToBeShown;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean enterLinkMode,
            ListType listToBeShown) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.listToBeShown = listToBeShown;
        this.exit = exit;
        this.enterLinkMode = enterLinkMode;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and
     * {@code listToBeShown}, with all other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, ListType listToBeShown) {
        this(feedbackToUser, false, false, false, listToBeShown);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * {@code listToBeShown} and {@code enterLinkMode}, with all other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean enterLinkMode, ListType listToBeShown) {
        this(feedbackToUser, false, false, enterLinkMode, listToBeShown);
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

    public boolean shouldEnterLinkMode() {
        return enterLinkMode;
    }

    public ListType getListToBeShown() {
        return listToBeShown;
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
                && exit == otherCommandResult.exit
                && enterLinkMode == otherCommandResult.enterLinkMode
                && listToBeShown == otherCommandResult.listToBeShown;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, enterLinkMode, listToBeShown);
    }

}
