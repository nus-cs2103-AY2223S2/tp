package seedu.address.model.recommender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import seedu.address.model.location.Location;
import seedu.address.model.person.Person;
import seedu.address.model.scheduler.time.Day;
import seedu.address.model.scheduler.time.HourBlock;

public class LocationTracker {
    private final Person person;
    private final HashMap<Day, List<Optional<Location>>> locations;

    public LocationTracker(Person person) {
        this.person = person;
        locations = new HashMap<>();
        initialiseWithEmptyLocations();
        initialiseWithAddress();
        initialiseWithSchedule();
    }

    private void initialiseWithEmptyLocations() {
        for (Day day : Day.values()) {
            locations.put(day, Collections.nCopies(23, Optional.empty()));
        }
    }

    private void initialiseWithAddress() {
        for (Day day : Day.values()) {
            locations.get(day).set(7, Optional.of(person.getAddress().getValue()));
            locations.get(day).set(22, Optional.of(person.getAddress().getValue()));
        }
    }

    private void initialiseWithSchedule() {
        for (Map.Entry<Day, ArrayList<HourBlock>> entry : person.getTimetable().getSchedule().entrySet()) {
            List<Optional<Location>> locationMap = locations.get(entry.getKey());
            entry.getValue().stream()
                    .filter(hourBlock -> hourBlock.getLesson().isPresent())
                    .forEach(hourBlock -> setHourBlock(locationMap, hourBlock));
        };
    }

    private Optional<Location> getOptionalLocationFromHourBlock(HourBlock hourBlock) {
        assert hourBlock.getLesson().isPresent();
        return Optional.of(hourBlock.getLesson().get().getLocation());
    }

    private void setHourBlock(List<Optional<Location>> locationMap, HourBlock hourBlock) {
        locationMap.set(hourBlock.getStartTime().getHourOfDay(),
                getOptionalLocationFromHourBlock(hourBlock));
    }

    private Optional<Location> getLocation(Day day, int hour) {
        return locations.get(day).get(hour);
    }

    private Optional<Location> getLocation(Day day, HourBlock hourBlock) {
        return getLocation(day, hourBlock.getStartTime().getHourOfDay());
    }

    private void fillEmptyBlocksWithApproximateLocations() {
        
    }

    private void fillEmptyBlocksWithApproximateLocations(List<Optional<Location>> locations) {


    }
}
