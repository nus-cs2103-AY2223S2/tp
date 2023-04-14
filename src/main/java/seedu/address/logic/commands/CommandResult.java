package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Light mode should be shown to the user.
     */
    private final boolean showLight;

    /**
     * Dark mode should be shown to the user.
     */
    private final boolean showDark;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * The application should show backups.
     */
    private final boolean showBackups;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showLight, boolean showDark) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showBackups = false;
        this.showDark = showDark;
        this.showLight = showLight;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields,
     * including showBackup.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showBackups) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showBackups = showBackups;
        this.showDark = false;
        this.showLight = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false);
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

    public boolean isShowBackups() {
        return showBackups;
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
            && showLight == otherCommandResult.showLight
            && showDark == otherCommandResult.showDark
            && showBackups == otherCommandResult.showBackups;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showLight, showDark);
    }

    public boolean isShowLight() {
        return showLight;
    }

    public boolean isShowDark() {
        return showDark;
    }
}
