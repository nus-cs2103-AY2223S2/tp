package seedu.address.model.recommender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.util.Pair;
import seedu.address.model.commitment.Commitment;
import seedu.address.model.location.Location;
import seedu.address.model.location.util.DistanceUtil;
import seedu.address.model.person.Person;
import seedu.address.model.scheduler.Timetable;
import seedu.address.model.scheduler.time.Day;
import seedu.address.model.scheduler.time.HourBlock;

public class LocationTracker {
    private static final Integer[] START_TIMINGS = Timetable.startTimings;
    private static final int EARLIEST_TIMING = START_TIMINGS[0];
    private static final int LATEST_TIMING = START_TIMINGS[START_TIMINGS.length - 1];
    private static final int NUMBER_OF_HOURS = START_TIMINGS.length;
    private final Person person;
    private final HashMap<Day, ArrayList<Optional<Location>>> locations;

    public LocationTracker(Person person) {
        this.person = person;
        locations = new HashMap<>();
        initialiseWithEmptyLocations();
        initialiseWithSchedule();
        fillUnknown();
    }

    private void initialiseWithEmptyLocations() {
        for (Day day : Day.values()) {
            locations.put(day, new ArrayList<>(Collections.nCopies(NUMBER_OF_HOURS, Optional.empty())));
        }
    }

    private void initialiseWithSchedule() {
        for (Map.Entry<Day, ArrayList<HourBlock>> entry : person.getTimetable().getSchedule().entrySet()) {
            ArrayList<Optional<Location>> dayLocations = locations.get(entry.getKey());

            entry.getValue().stream()
                    .filter(hourBlock -> hourBlock.getLesson().isPresent())
                    .forEach(hourBlock -> setHourBlock(dayLocations, hourBlock));
        };
    }

    private int getIndexFromHourBlock(HourBlock hourBlock) {
        return hourBlock.getStartTime().getHourOfDay() - EARLIEST_TIMING;
    }

    private void setHourBlock(ArrayList<Optional<Location>> dayLocations, HourBlock hourBlock) {
        int indexOfHourBlock = getIndexFromHourBlock(hourBlock);
        assert indexOfHourBlock >= 0 && indexOfHourBlock < NUMBER_OF_HOURS;

        dayLocations.set(indexOfHourBlock, getLocationFromHourBlock(hourBlock));
    }

    private Optional<Location> getLocationFromHourBlock(HourBlock hourBlock) {
        return hourBlock.getLesson().map(Commitment::getLocation);
    }

    public Optional<Location> getLocation(HourBlock hourBlock) {
        return getLocation(hourBlock.getSchoolDay(), hourBlock.getStartTime().getHourOfDay());
    }

    private Optional<Location> getLocation(Day day, int hour) {
        assert hour >= EARLIEST_TIMING && hour <= LATEST_TIMING;
        return locations.get(day).get(hour - EARLIEST_TIMING);
    }

    private void fillUnknown() {
        for (List<Optional<Location>> dayLocations : locations.values()) {
            List<Pair<Location, Integer>> knownLocationIndices = findKnownLocationIndices(dayLocations);
            fillUnknownWithKnown(knownLocationIndices, dayLocations);
        }
    }

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

    private void fillUnknownWithKnown(
            List<Pair<Location, Integer>> knownLocationIndices, List<Optional<Location>> dayLocations) {
        Location homeAddress = person.getAddress().getValue();
        Pair<Location, Integer> startPair = new Pair<>(homeAddress, -1);
        Pair<Location, Integer> endPair = new Pair<>(homeAddress, NUMBER_OF_HOURS);

        Pair<Location, Integer> currLocationIndex = startPair;
        for (Pair<Location, Integer> locationIndex : knownLocationIndices) {
            fillUnknownWithStartEnd(currLocationIndex, locationIndex, dayLocations);
            currLocationIndex = locationIndex;
        }
        fillUnknownWithStartEnd(currLocationIndex, endPair, dayLocations);
    }

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

    private void fillWithApproximateLocations(List<Optional<Location>> dayLocations, List<Location> fillLocations, int startIndex) {
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
