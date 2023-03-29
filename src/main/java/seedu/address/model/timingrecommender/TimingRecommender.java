package seedu.address.model.timingrecommender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.time.Day;
import seedu.address.model.time.HourBlock;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.util.TimeUtil;
import seedu.address.model.timetable.Timetable;

/**
 * Represents an automatic timingrecommender.
 */

public class TimingRecommender {

    private static final Logger logger = LogsCenter.getLogger(TimingRecommender.class);
    private List<Timetable> schedules;
    private Model model;
    private List<Person> participants;

    /**
     * Constructs a timingrecommender.
     * @param model
     */
    public TimingRecommender(Model model) {
        this.model = model;
        this.schedules = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    /**
     * Initialises the timingrecommender with all the participants.
     * @param participantIndices
     */
    public TimingRecommender initialise(Collection<ContactIndex> participantIndices) {
        // for each contact person, query person from model.
        // Each person's schedule would be constructed
        // and appended to the schedules
        logger.info(String.format("Attempting to query %d indices from Model in TimingRecommender",
            participantIndices.size()));
        addParticipants(participantIndices);
        return this;
    }

    /**
     * Adds participants from the model by their ContactIndex.
     */
    private void addParticipants(Collection<ContactIndex> participantIndices) {
        IndexHandler indexHandler = new IndexHandler(model);
        // Fill participants into participants
        participantIndices.stream().map(indexHandler::getPersonByIndex)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(this.participants::add);

        this.participants.stream()
                .map(Person::getTimetable)
                .forEach(this.schedules::add);
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
        for (Day day : Day.values()) {
            List<HourBlock> availableHourBlocks = TimeUtil.getFreeCommonIntervals(day, schedules);
            periods.addAll(TimeUtil.mergeTimeSlots(availableHourBlocks));
        }
        logger.info(String.format("%d possible timings", periods.size()));
        return periods;
    }

    /**
     * Get all Time Periods that everyone is free on that school day.
     */
    public List<TimePeriod> getAllTimings(Day schoolDay) {
        List<HourBlock> availableHourBlocks = TimeUtil.getFreeCommonIntervals(schoolDay, schedules);
        return new ArrayList<>(TimeUtil.mergeTimeSlots(availableHourBlocks));
    }

    /**
     * Get all Hour Blocks that everyone is free.
     */
    public List<HourBlock> getAllHourBlocks() {
        List<HourBlock> blocks = new ArrayList<>();
        for (Day day : Day.values()) {
            List<HourBlock> availableHourBlocks = TimeUtil.getFreeCommonIntervals(day, schedules);
            blocks.addAll(availableHourBlocks);
        }
        return blocks;
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
