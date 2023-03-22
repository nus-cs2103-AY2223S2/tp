package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Instance of internship to be viewed **/
    private final Internship internship;

    /** Lists of Events to be viewed **/
    private final ObservableList<Event> events;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, Internship internship,
                         ObservableList<Event> events) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.internship = internship;
        this.events = events;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, null, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code internship},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Internship internship) {
        this(feedbackToUser, false, false, internship, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code internship},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Internship internship, ObservableList<Event> events) {
        this(feedbackToUser, false, false, internship, events);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
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

    public Internship getInternship() {
        return internship;
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

        if (this.internship != null && otherCommandResult.internship != null) {
            return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                    && showHelp == otherCommandResult.showHelp
                    && exit == otherCommandResult.exit
                    && internship.equals(otherCommandResult.internship);
        } else if (this.internship == null && otherCommandResult.internship == null) {
            return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                    && showHelp == otherCommandResult.showHelp
                    && exit == otherCommandResult.exit;
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, internship);
    }

}
