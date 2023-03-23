package seedu.address.model.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.logic.parser.IndexHandler;
import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.timetable.Timetable;
import seedu.address.model.timetable.time.SchoolDay;
import seedu.address.model.timetable.time.TimePeriod;
import seedu.address.model.timetable.time.TimeSlot;
import seedu.address.model.timetable.time.util.TimeUtils;

/**
 * Represents an automatic scheduler.
 */
public class Scheduler {
    private List<Timetable> schedules;
    private Model model;
    private List<Person> participants;

    /**
     * Constructs a scheduler.
     * @param model
     */
    public Scheduler(Model model) {
        this.model = model;
        this.schedules = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    /**
     * Initialises the scheduler with all the participants.
     * @param participantIndices
     */
    public Scheduler initialise(Collection<ContactIndex> participantIndices) {
        // for each contact person, query person from model.
        // Each person's schedule would be constructed
        // and appended to the schedules
        addParticipants(participantIndices);
        return this;
    }

    /**
     * Adds participants from the model by their ContactIndex.
     * @param participantIndices
     */
    private void addParticipants(Collection<ContactIndex> participantIndices) {
        IndexHandler indexHandler = new IndexHandler(model);
        // Fill participants into participants
        participantIndices.stream().map(indexHandler::getPersonByIndex)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(this.participants::add);
        // Append timetables for each participant
        // Wait for @kennycjy to handle his Module Tag + Lesson integration
        // planned implementation:
        // for each participant : (1) build (2) add their timetable
        // to be handled in a separate method.
    }

    /**
     * Adds a timetable to collate all schedules.
     */
    public void addTimetable(Timetable timetable) {
        this.schedules.add(timetable);
    }

    /**
     * Recommends the longest common timing that is available.
     */
    public Optional<TimePeriod> giveLongestTimingRecommendation() {
        List<TimePeriod> periods = getAllTimings();
        return periods.stream()
                .max(Comparator.comparing(TimePeriod::getHoursBetween));
    }

    /**
     * Recommends a specified limit number of common available timings.
     * @param limit number of recommendations.
     */
    public List<TimePeriod> giveLongestTimingRecommendations(int limit) {
        List<TimePeriod> periods = getAllTimings();
        return periods.stream()
            .sorted(Comparator.comparing(TimePeriod::getHoursBetween).reversed())
            .limit(limit)
            .collect(Collectors.toList());
    }

    /**
     * Get all Time Periods that everyone is free.
     */
    public List<TimePeriod> getAllTimings() {
        List<TimePeriod> periods = new ArrayList<>();
        for (SchoolDay day : SchoolDay.values()) {
            List<TimeSlot> availableTimeSlots = TimeUtils.getFreeCommonIntervals(day, schedules);
            periods.addAll(TimeUtils.mergeTimeSlots(availableTimeSlots));
        }
        return periods;
    }

    /**
     * Get all Time Periods that everyone is free on that school day.
     */
    public List<TimePeriod> getAllTimings(SchoolDay schoolDay) {
        List<TimeSlot> availableTimeSlots = TimeUtils.getFreeCommonIntervals(schoolDay, schedules);
        return new ArrayList<>(TimeUtils.mergeTimeSlots(availableTimeSlots));
    }

    public List<Timetable> getSchedules() {
        return schedules;
    }

    public Model getModel() {
        return model;
    }

    public List<Person> getParticipants() {
        return participants;
    }
}
