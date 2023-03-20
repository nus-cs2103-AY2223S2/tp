package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * Timetable information should be shown to the user.
     */
    private final boolean showTimetable;

    /**
     * Statistics information should be shown to the user.
     */
    private final boolean showStatistics;

    /**
     * Reminder list should be shown to the user.
     */
    private final boolean showReminderList;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */

    public CommandResult(String feedbackToUser, boolean showHelp, boolean showTimetable, boolean showReminderList,
                         boolean showStatistics, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showTimetable = showTimetable;
        this.showReminderList = showReminderList;
        this.showStatistics = showStatistics;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowTimetable() {
        return showTimetable;
    }


    public boolean isShowStatistics() {
        return showStatistics;
    }

    public boolean isShowReminderList() {
        return showReminderList;
    }

    public boolean isExit() {
        return exit;
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
                && showTimetable == otherCommandResult.showTimetable
                && showStatistics == otherCommandResult.showStatistics
                && showReminderList == otherCommandResult.showReminderList
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, showTimetable, showReminderList, showStatistics, exit);
    }

}
