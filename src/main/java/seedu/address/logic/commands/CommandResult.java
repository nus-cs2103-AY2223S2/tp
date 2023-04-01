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
     * Timetable information of specific date should be shown to the user.
     */
    private boolean showTimetableDate;

    /**
     * List of unscheduled jobs should be shown to user.
     */
    private final boolean showUnschedule;

    /**
     * List of completed jobs should be shown to user.
     */
    private final boolean showComplete;

    /**
     * Statistics information should be shown to the user.
     */
    private final boolean showStatistics;

    /**
     * Reminder list should be shown to the user.
     */
    private final boolean showReminderList;

    /**
     * Address book should be shown to the user.
     */
    private final boolean showAddressBook;

    /**
     * Main window should be shown and focused to the user.
     */
    private final boolean showJobList;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */

    public CommandResult(String feedbackToUser, boolean showHelp, boolean showTimetable,
                         boolean showUnschedule, boolean showComplete, boolean showReminderList,
                         boolean showStatistics, boolean showAddressBook,
                         boolean showJobList, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showTimetable = showTimetable;
        this.showTimetableDate = false;
        this.showUnschedule = showUnschedule;
        this.showComplete = showComplete;
        this.showReminderList = showReminderList;
        this.showStatistics = showStatistics;
        this.showAddressBook = showAddressBook;
        this.showJobList = showJobList;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */

    public CommandResult(String feedbackToUser, boolean showHelp, boolean showTimetable,
                         boolean showUnschedule, boolean showComplete, boolean showReminderList,
                         boolean showStatistics, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showTimetable = showTimetable;
        this.showTimetableDate = false;
        this.showUnschedule = showUnschedule;
        this.showComplete = showComplete;
        this.showReminderList = showReminderList;
        this.showStatistics = showStatistics;
        this.showAddressBook = false;
        this.showJobList = false;
        this.exit = exit;
    }

    /**
     * Simplified constructor for CommandResult
     * which does not show Unscheduled job window
     * @param feedbackToUser
     * @param showHelp
     * @param showTimetable
     * @param showReminderList
     * @param showStatistics
     * @param exit
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean showTimetable,
                         boolean showReminderList,
                         boolean showStatistics, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showTimetable = showTimetable;
        this.showTimetableDate = false;
        this.showUnschedule = false;
        this.showComplete = false;
        this.showReminderList = showReminderList;
        this.showStatistics = showStatistics;
        this.showAddressBook = false;
        this.showJobList = false;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false);
    }

    /**
     * Returns feedback to user
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Checks if help window is shown
     */
    public boolean isShowHelp() {
        return showHelp;
    }

    /**
     * Checks if timetable window for any date is shown
     */
    public boolean isShowTimetable() {
        return showTimetable;
    }

    /**
     * Checks if timetable window for specific date is shown
     */
    public boolean isShowTimetableDate() {
        return showTimetableDate;
    }

    /**
     * Checks if unscheduled job window is shown
     */
    public boolean isShowUnschedule() {
        return showUnschedule;
    }

    /**
     * Checks if compelted job window is shown
     */
    public boolean isShowComplete() {
        return showComplete;
    }


    /**
     * Checks if stats window is shown
     */
    public boolean isShowStatistics() {
        return showStatistics;
    }

    /**
     * Checks if reminder window is shown
     */
    public boolean isShowReminderList() {
        return showReminderList;
    }

    /**
     * Checks if address window is shown
     */
    public boolean isShowAddressBook() {
        return showAddressBook;
    }

    /**
     * Checks if main window is required to focus
     */
    public boolean isShowJobList() {
        return showJobList;
    }

    /**
     * Checks if main window is exited
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Sets status of show timetable for specific date
     * @param isShowTimetableDate
     */
    public void setShowTimetableDate(boolean isShowTimetableDate) {
        this.showTimetableDate = isShowTimetableDate;
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
