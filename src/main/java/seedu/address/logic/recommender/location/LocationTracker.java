package seedu.address.logic.recommender.location;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.util.Pair;
import seedu.address.model.commitment.Commitment;
import seedu.address.model.location.Location;
import seedu.address.model.location.util.DistanceUtil;
import seedu.address.model.person.Person;
import seedu.address.model.time.Day;
import seedu.address.model.time.HourBlock;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.timetable.Timetable;

/**
 * Follows a person around and can predict a person's location given a timing.
 */
public class LocationTracker {
    private static final Integer[] START_TIMINGS = Timetable.START_TIMINGS;
    private static final int EARLIEST_TIMING = START_TIMINGS[0];
    private static final int LATEST_TIMING = START_TIMINGS[START_TIMINGS.length - 1];
    private static final int NUMBER_OF_HOURS = START_TIMINGS.length;
    private final Person person;
    private final HashMap<Day, ArrayList<Optional<Location>>> locations;

    /**
     * Constructor for a {@code LocationTracker} object.
     */
    public LocationTracker(Person person) {
        this.person = person;
        locations = new HashMap<>();
        initialiseWithEmptyLocations();
        initialiseWithSchedule();
        fillUnknown();
    }

    /**
     * Creates an empty locations list.
     */
    private void initialiseWithEmptyLocations() {
        for (Day day : Day.values()) {
            locations.put(day, new ArrayList<>(Collections.nCopies(NUMBER_OF_HOURS, Optional.empty())));
        }
    }

    /**
     * Uses the person schedule to populate the locations list.
     */
    private void initialiseWithSchedule() {
        for (Map.Entry<Day, ArrayList<HourBlock>> entry : person.getTimetable().getSchedule().entrySet()) {
            ArrayList<Optional<Location>> dayLocations = locations.get(entry.getKey());

            entry.getValue().stream()
                    .filter(hourBlock -> hourBlock.getCommitment().isPresent())
                    .forEach(hourBlock -> setHourBlock(dayLocations, hourBlock));
        };
    }

    /**
     * Converts an hour block into a more manageable index.
     */
    private int getIndexFromHourBlock(HourBlock hourBlock) {
        return hourBlock.getStartTime().getHourOfDay() - EARLIEST_TIMING;
    }

    /**
     * Gets the location from the hour block, and fills in the Location list.
     */
    private void setHourBlock(ArrayList<Optional<Location>> dayLocations, HourBlock hourBlock) {
        int indexOfHourBlock = getIndexFromHourBlock(hourBlock);
        assert indexOfHourBlock >= 0 && indexOfHourBlock < NUMBER_OF_HOURS;

        dayLocations.set(indexOfHourBlock, getLocationFromHourBlock(hourBlock));
    }

    /**
     * Gets the location from the hour block.
     */
    private Optional<Location> getLocationFromHourBlock(HourBlock hourBlock) {
        return hourBlock.getCommitment().map(Commitment::getLocation);
    }

    /**
     * Gets the location of the person given a timing, consisting of the day as well.
     */
    public Optional<Location> getLocation(HourBlock hourBlock) {
        return getLocation(hourBlock.getSchoolDay(), hourBlock.getStartTime().getHourOfDay());
    }

    /**
     * Gets the location of the person given a day and hour.
     */
    private Optional<Location> getLocation(Day day, int hour) {
        assert hour >= EARLIEST_TIMING && hour <= LATEST_TIMING;
        return locations.get(day).get(hour - EARLIEST_TIMING);
    }

    /**
     * Gets the average location of a person within a time period.
     */
    public Optional<Location> getLocation(TimePeriod timePeriod) {
        requireNonNull(timePeriod);
        List<Location> locations =
                timePeriod.fragmentIntoHourBlocks()
                        .stream().map(this::getLocation)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList());

        return Optional.of(DistanceUtil.getMidpoint(locations));
    }

    /**
     * Fills the unknown locations based on the last known and next known locations.
     */
    private void fillUnknown() {
        Location homeAddress = person.getAddress().getValue();
        Pair<Location, Integer> startPair = new Pair<>(homeAddress, -1);
        Pair<Location, Integer> endPair = new Pair<>(homeAddress, NUMBER_OF_HOURS);

        for (List<Optional<Location>> dayLocations : locations.values()) {
            List<Pair<Location, Integer>> knownLocationIndices = new ArrayList<>();
            knownLocationIndices.add(startPair);
            knownLocationIndices.addAll(findKnownLocationIndices(dayLocations));
            knownLocationIndices.add(endPair);
            fillUnknownWithKnown(knownLocationIndices, dayLocations);
        }
    }

    /**
     * Finds the indices of times when we know the person's location.
     */
    private List<Pair<Location, Integer>> findKnownLocationIndices(List<Optional<Location>> dayLocations) {
        List<Integer> knownIndices = IntStream.range(0, dayLocations.size() - 1)
                .filter(i -> dayLocations.get(i).isPresent())
                .boxed().collect(Collectors.toList());
        List<Pair<Location, Integer>> knownLocationIndices = new ArrayList<>();

        for (int index : knownIndices) {
            assert dayLocations.get(index).isPresent();
            knownLocationIndices.add(new Pair<>(dayLocations.get(index).get(), index));
        }

        return knownLocationIndices;
    }

    /**
     * Looks for the last known and next known locations.
     * Uses these to fill up the unknown locations.
     */
    private void fillUnknownWithKnown(
            List<Pair<Location, Integer>> knownLocationIndices, List<Optional<Location>> dayLocations) {

        Pair<Location, Integer> currLocationIndex = knownLocationIndices.get(0);
        for (Pair<Location, Integer> locationIndex : knownLocationIndices) {
            fillUnknownWithStartEnd(currLocationIndex, locationIndex, dayLocations);
            currLocationIndex = locationIndex;
        }
    }

    /**
     * Fills a range of unknown locations with known ones.
     */
    private void fillUnknownWithStartEnd(
            Pair<Location, Integer> startLocationIndex,
            Pair<Location, Integer> endLocationIndex,
            List<Optional<Location>> dayLocations) {
        int startIndex = startLocationIndex.getValue();
        int endIndex = endLocationIndex.getValue();
        Location startLocation = startLocationIndex.getKey();
        Location endLocation = endLocationIndex.getKey();

        // number of unknown hours between the two known locations
        int n = endIndex - startIndex - 1;

        // we don't do anything if there are no unknown hours in between
        if (n < 1) {
            return;
        }

        List<Location> fillLocations = DistanceUtil.getApproximateLocations(startLocation, endLocation, n);
        fillWithApproximateLocations(dayLocations, fillLocations, startIndex);
    }

    /**
     * Fills a range of empty locations with known ones.
     */
    private void fillWithApproximateLocations(
            List<Optional<Location>> dayLocations,
            List<Location> fillLocations,
            int startIndex) {
        for (int i = 0; i < fillLocations.size(); i++) {
            int indexToFill = i + startIndex + 1;
            assert dayLocations.get(indexToFill).isEmpty();

            dayLocations.set(indexToFill, Optional.of(fillLocations.get(i)));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Day day : Day.values()) {
            sb.append(locations.get(day).stream()
                    .map(lc -> lc.orElse(Location.NUS))
                    .map(Location::toString)
                    .collect(Collectors.joining(", ")));
            sb.append("\n");
        }
        return sb.toString();
    }
}
