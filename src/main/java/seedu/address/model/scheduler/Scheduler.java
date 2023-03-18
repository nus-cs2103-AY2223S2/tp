package seedu.address.model.scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    public void initialise(List<ContactIndex> participantIndices) {
        // for each contact person, query person from model.
        // Each person's schedule would be constructed
        // and appended to the schedules
        addParticipants(participantIndices);
    }

    /**
     * Adds participants from the model by their ContactIndex.
     * @param participantIndices
     */
    private void addParticipants(List<ContactIndex> participantIndices) {
        IndexHandler indexHandler = new IndexHandler(model);
        // Fill participants into participants
        participantIndices.stream().map(indexHandler::getPersonByIndex)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(this.participants::add);
        // Append timetables for each participant
        // Wait for @kennycjy to handle his Module Tag + Lesson integration
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
    public Optional<TimePeriod> giveLongestTimingRecommendations() {
        List<TimePeriod> periods = new ArrayList<>();
        for (SchoolDay day : SchoolDay.values()) {
            List<TimeSlot> availableTimeSlots = TimeUtils.getFreeCommonIntervals(day, schedules);
            periods.addAll(TimeUtils.mergeTimeSlots(availableTimeSlots));
        }
        System.out.println(periods);
        System.out.println(periods.stream()
                .max(Comparator.comparing(TimePeriod::getHoursBetween)));
        return periods.stream()
                .max(Comparator.comparing(TimePeriod::getHoursBetween));
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
