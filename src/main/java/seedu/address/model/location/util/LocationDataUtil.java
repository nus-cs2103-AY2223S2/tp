package seedu.address.model.location.util;

import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.location.Location;

/**
 * Processes the location data stored in the txt files.
 * Different from {@code DistanceUtil} which handles computations instead.
 */
public class LocationDataUtil {
    /**
     * Keeping the locations as sets makes it easier to pass around.
     */
    public static final Set<Location> EAT_LOCATIONS = readLocationData("eat");
    public static final Set<Location> STUDY_LOCATIONS = readLocationData("study");
    public static final Set<Location> MEET_LOCATIONS =
            Stream.concat(EAT_LOCATIONS.stream(), STUDY_LOCATIONS.stream()).collect(Collectors.toSet());
    public static final Set<Location> LESSON_VENUES = readLocationData("venues");
    public static final Set<Location> STATIONS = readLocationData("stations");

    /**
     * If we need to, we can convert them to hash map.
     * The key will simply be the name of the location.
     */
    public static final Map<String, Location> STATIONS_HASH_MAP = getLocationHashMap(STATIONS);

    private static final Logger logger = LogsCenter.getLogger(LocationDataUtil.class);

    /**
     * Reads and parses the location data from a txt file.
     * @param fileName the name of the file in the data folder.
     * @return the parsed locations retrieved from the data file.
     */
    private static Set<Location> readLocationData(String fileName) {
        HashSet<Location> locationSet = new HashSet<>();
        String fullFileName = String.format("data/%s.txt", fileName);
        InputStreamReader file =
                new InputStreamReader(
                        LocationDataUtil.class.getClassLoader().getResourceAsStream(fullFileName));

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();

            // Tries to parse the line, and just ignores if it throws an error
            getLocationFromData(nextLine)
                    .ifPresentOrElse(locationSet::add, () -> logger.warning("Invalid information in: " + nextLine));
        }



        return locationSet;
    }

    /**
     * Tries to parse data into a location, and returns an {@code Optonal.empty()} if it fails.
     * @param locationData string containing location information in the format {@code name|lat|long}.
     * @return an {@code Optional<Location>} object which is empty if it cannot be parsed.
     */
    private static Optional<Location> getLocationFromData(String locationData) {
        List<String> locationDataList = Stream.of(locationData.split("\\|"))
                .map(String::trim)
                .limit(3) // name, lat, lon
                .collect(Collectors.toList());

        if (locationDataList.size() != 3
                || !Location.isValidLocation(locationDataList.get(1), locationDataList.get(2))) {
            return Optional.empty();
        }

        String name = locationDataList.get(0);

        double lat = Double.parseDouble(locationDataList.get(1));
        double lon = Double.parseDouble(locationDataList.get(2));

        return Optional.of(new Location(name, lat, lon));
    }

    /**
     * Converts the locations from a set to a hashmap, using the location name as the key.
     * @param locations set of locations to be converted.
     * @return a {@code Map} object from {@code String} to {@code Location}.
     */
    private static Map<String, Location> getLocationHashMap(Set<Location> locations) {
        return locations.stream()
                .collect(Collectors.toMap(location -> location.getName().toLowerCase(),
                        location -> location));
    }

}
