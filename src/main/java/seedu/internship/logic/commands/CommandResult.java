package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internship.model.event.UniqueEventList.EMPTY_UNIQUE_EVENTS_LIST;
import static seedu.internship.model.internship.Internship.EMPTY_INTERNSHIP;
import static seedu.internship.model.internship.Statistics.EMPTY_STATISTICS;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.internship.model.internship.Statistics;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final ResultType resultType;

    /** Instance of internship to be viewed **/
    private final Internship internship;

    /** Lists of Events to be viewed **/
    private final ObservableList<Event> events;

    /** Statistical data of current application progress **/
    private final Statistics statistics;

    /** Hash map of event to a list of events **/
    private HashMap<Event, List<Event>> hash = null;

    /**
     * Constructs a {@code CommandResult} with all specified fields.
     */
    public CommandResult(String feedbackToUser, ResultType resultType, Internship internship,
                         ObservableList<Event> events, Statistics statistics) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.resultType = resultType;
        this.internship = internship;
        this.events = events;
        this.statistics = statistics;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, ResultType.NO_CHANGE, EMPTY_INTERNSHIP, EMPTY_UNIQUE_EVENTS_LIST, EMPTY_STATISTICS);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code resultType},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, ResultType resultType) {
        this(feedbackToUser, resultType, EMPTY_INTERNSHIP, EMPTY_UNIQUE_EVENTS_LIST, EMPTY_STATISTICS);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code resultType}
     * and {@code statistics}, and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, ResultType resultType, Statistics statistics) {
        this(feedbackToUser, resultType, EMPTY_INTERNSHIP, EMPTY_UNIQUE_EVENTS_LIST, statistics);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code internship},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, ResultType resultType, Internship internship) {
        this(feedbackToUser, resultType, internship, EMPTY_UNIQUE_EVENTS_LIST, EMPTY_STATISTICS);
    }


    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code showHome} and
     * {@code events}, and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, ResultType resultType, ObservableList<Event> events) {
        this(feedbackToUser, resultType, EMPTY_INTERNSHIP, events, EMPTY_STATISTICS);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code showHome},
     * {@code internship} and {@code events}, and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, ResultType resultType, Internship internship,
                         ObservableList<Event> events) {
        this(feedbackToUser, resultType, internship, events, EMPTY_STATISTICS);
    }
    
    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value for Clash Function
     */
    public CommandResult(String feedbackToUser, ResultType resultType, HashMap<Event, List<Event>> hash) {
        this(feedbackToUser, resultType, EMPTY_INTERNSHIP, EMPTY_UNIQUE_EVENTS_LIST, EMPTY_STATISTICS);
        this.hash = hash;
    }


    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public ResultType getResultType() {
        return resultType;
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

    public Statistics getStatistics() {
        return statistics;
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
                && resultType.equals(otherCommandResult.resultType)
                && internship.equals(otherCommandResult.internship)
                && events.equals(otherCommandResult.events)
                && statistics.equals(otherCommandResult.statistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, resultType, internship, events, statistics);
    }

}
