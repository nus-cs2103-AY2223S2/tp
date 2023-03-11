package seedu.address.model.location;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.CollectionUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocationUtil {

    private static final Logger logger = LogsCenter.getLogger(LocationUtil.class);
    public static final HashMap<String, Location> EAT_LOCATIONS = readLocationData("eat");
    public static final HashMap<String, Location> STUDY_LOCATIONS = readLocationData("study");
    public static final HashMap<String, Location> MEET_LOCATIONS = CollectionUtil.combineHashMaps(EAT_LOCATIONS, STUDY_LOCATIONS);
    public static final HashMap<String, Location> LESSON_VENUES = readLocationData("venues");
    public static final HashMap<String, Location> ADDRESSES = readLocationData("address");

    private static HashMap<String, Location> readLocationData(String fileName) {
        HashMap<String, Location> locationHashMap = new HashMap<>();
        String fullFileName = String.format("src/main/java/seedu/address/model/location/data/%s.txt", fileName);
        File file = new File(fullFileName);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                getLocationFromData(nextLine, locationHashMap);
            }
        } catch (FileNotFoundException fnfe) {
            logger.info("Location Data not found: " + fnfe.getMessage());
        }

        return locationHashMap;
    }

    private static void getLocationFromData(String locationData, HashMap<String, Location> locationHashMap) {
        List<String> locationDataList = Stream.of(locationData.split("\\|"))
                .map(String::trim)
                .limit(3) // name, lat, lon
                .collect(Collectors.toList());

        if (locationDataList.size() != 3) {
            return;
        }

        String name = locationDataList.get(0);
        double lat = Double.parseDouble(locationDataList.get(1));
        double lon = Double.parseDouble(locationDataList.get(2));

        locationHashMap.put(name, new Location(name, lat, lon));
    }

}
