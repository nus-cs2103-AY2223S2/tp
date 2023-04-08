package fasttrack.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import fasttrack.ui.ScreenType;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The screen to display upon execution of the command. */
    private final ScreenType screenType;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, ScreenType screenType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.screenType = screenType;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, ScreenType screenType) {
        this(feedbackToUser, false, false, screenType);
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

    public ScreenType getScreenType() {
        return screenType;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommandResult // instanceof handles nulls
                && feedbackToUser.equals(((CommandResult) other).feedbackToUser)
                && showHelp == ((CommandResult) other).showHelp
                && exit == ((CommandResult) other).exit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    @Override
    public String toString() {
        return "CommandResult{"
                + "feedbackToUser='" + feedbackToUser + '\''
                + ", showHelp=" + showHelp
                + ", exit=" + exit
                + ", screenType=" + screenType
                + '}';
    }

}
