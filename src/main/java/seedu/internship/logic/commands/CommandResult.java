package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internship.model.event.UniqueEventList.EMPTY_UNIQUE_EVENTS_LIST;
import static seedu.internship.model.internship.Internship.EMPTY_INTERNSHIP;

import java.util.HashMap;
import java.util.List;
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

    /** Hash map of event to a list of events **/
    private HashMap<Event, List<Event>> hash = null;


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
        this(feedbackToUser, showHelp, exit, EMPTY_INTERNSHIP, EMPTY_UNIQUE_EVENTS_LIST);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code internship},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, Internship internship) {
        this(feedbackToUser, false, false, internship, EMPTY_UNIQUE_EVENTS_LIST);
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
        this(feedbackToUser, false, false, EMPTY_INTERNSHIP, EMPTY_UNIQUE_EVENTS_LIST);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value for Clash Function
     */
    public CommandResult(String feedbackToUser, HashMap<Event, List<Event>> hash) {
        this(feedbackToUser, false, false, EMPTY_INTERNSHIP, EMPTY_UNIQUE_EVENTS_LIST);
        this.hash = hash;
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

    public boolean isEmptyInternship() {
        return this.internship.equals(EMPTY_INTERNSHIP);
    }

    public boolean isEmptyEvents() {
        return this.events.equals(EMPTY_UNIQUE_EVENTS_LIST);
    }


    public ObservableList<Event> getEvents() {
        return events;
    }

    public HashMap<Event, List<Event>> getClashingEvents() {
        return hash;
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
                && internship.equals(otherCommandResult.internship)
                && events.equals(events);


    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, internship);
    }

}
