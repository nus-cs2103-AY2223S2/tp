package seedu.address.model.scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.logic.parser.IndexHandler;
import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.timetable.SchoolDay;
import seedu.address.model.timetable.TimePeriod;
import seedu.address.model.timetable.TimeSlot;
import seedu.address.model.timetable.Timetable;

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
     * Recommends the longest common timing that is available.
     */
    public Optional<TimePeriod> giveTimingRecommendations() {
        List<TimePeriod> periods = new ArrayList<>();
        for (SchoolDay day : SchoolDay.values()) {
            periods.addAll(getFreeCommonIntervals(day));
        }
        return periods.stream()
                .max(Comparator.comparing(TimePeriod::getHoursBetween));
    }

    /**
     * Gets a list of free intervals for which a participant is free for the specific school day.
     */
    private List<TimePeriod> getFreeCommonIntervals(SchoolDay schoolDay) {
        List<ArrayList<TimeSlot>> daySchedules = schedules.stream()
                .map(timetable -> timetable.getSchedule().get(schoolDay))
                .collect(Collectors.toList());
        if (daySchedules.isEmpty()) {
            return new ArrayList<>();
        }
        List<TimeSlot> commonFreeTimeSlots = new ArrayList<>();
        int numberOfTimeSlots = daySchedules.get(0).size();
        for (int i = 0; i < numberOfTimeSlots; i++) {
            boolean isFreeForAll = true;
            for (int j = 0; j < daySchedules.size(); i++) {
                isFreeForAll &= daySchedules.get(j).get(i).isFree();
            }
            if (isFreeForAll) {
                TimeSlot sampleSlot = daySchedules.get(daySchedules.size() - 1).get(i);
                commonFreeTimeSlots.add(new TimeSlot(sampleSlot));
            }
        }
        return mergeTimeSlots(commonFreeTimeSlots);
    }

    /**
     * Reduces the number of TimePeriods by merging consecutive time slots.
     */
    private List<TimePeriod> mergeTimeSlots(List<TimeSlot> timeSlots) {
        if (timeSlots.isEmpty()) {
            return new ArrayList<>();
        }
        TimePeriod block = null;
        List<TimePeriod> timePeriods = new ArrayList<>();
        for (int i = 0; i < timeSlots.size(); i++) {
            TimeSlot timeSlot = timeSlots.get(0);
            if (i == 0) {
                block = timeSlot;
            } else if (timeSlot.isConsecutiveWith(block)) {
                block.merge(timeSlot);
            } else {
                timePeriods.add(block);
                block = timeSlot;
            }
        }
        if (block != null) {
            timePeriods.add(block);
        }
        return timePeriods;
    }
}
