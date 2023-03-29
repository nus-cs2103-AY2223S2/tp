package seedu.address.model.recommender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.Model;
import seedu.address.model.location.Location;
import seedu.address.model.location.LocationRecommender;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.timingrecommender.TimingRecommender;
import seedu.address.model.time.HourBlock;
import seedu.address.model.time.TimePeriod;

/**
 * Recommends meetup times and locations.
 */
public class Recommender {

    private static final Logger logger = LogsCenter.getLogger(Recommender.class);
    private static final int RECOMMENDATION_LIMIT = 20;
    private final LocationRecommender lr;
    private final TimingRecommender sc;
    private final Model model;
    private Set<LocationTracker> locationTrackers;

    public Recommender(Model model) {
        this.model = model;
        lr = new LocationRecommender();
        sc = new TimingRecommender(model);
        locationTrackers = new HashSet<>();
    }

    /**
     * Returns a list of recommendations.
     */
    public List<Recommendation> recommend(Collection<ContactIndex> contactIndices, Collection<Location> destinations) {
        initialise(contactIndices, destinations);
        List<HourBlock> timingRecommendations = sc.giveLongestTimingRecommendations(RECOMMENDATION_LIMIT)
                .stream().map(TimePeriod::fragmentIntoHourBlocks)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        logger.info(String.format("%d timings recommended", timingRecommendations.size()));

        List<List<Location>> locationRecommendations = timingRecommendations.stream()
                .map(this::getLocationsFromHourBlock)
                .map(lr::recommend)
                .collect(Collectors.toList());

        List<Recommendation> recommendations = CollectionUtil
                .zip(locationRecommendations.stream(),
                        timingRecommendations.stream(),
                        this::recommendFromLocationsHourBlock)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<Recommendation> filteredRecommendations = filterRecommendations(recommendations);

        return filteredRecommendations.stream()
                .sorted().limit(RECOMMENDATION_LIMIT)
                .collect(Collectors.toList());
    }

    /**
     * Sets up the {@code LocationRecommender}, {@code TimingRecommender}
     * and {@code LocationTracker} for each person.
     */
    private void initialise(Collection<ContactIndex> contactIndices, Collection<Location> destinations) {
        lr.initialise(destinations);
        sc.initialise(contactIndices);
        locationTrackers = sc.getParticipants().stream()
                .map(LocationTracker::new)
                .collect(Collectors.toSet());
    }

    /**
     * Gets location from hour block.
     */
    private Set<Location> getLocationsFromHourBlock(HourBlock hourBlock) {
        return locationTrackers.stream()
                .map(lt -> lt.getLocation(hourBlock))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a list of recommendation based on a particular timing.
     */
    private List<Recommendation> recommendFromLocationsHourBlock(List<Location> locations, HourBlock hourBlock) {
        return locations.stream()
                .map(l -> new Recommendation(l, hourBlock))
                .collect(Collectors.toList());
    }

    /**
     * Filters out duplicate timings or locations.
     */
    private List<Recommendation> filterRecommendations(List<Recommendation> recommendations) {
        Set<TimePeriod> timePeriods = new HashSet<>();
        Set<Location> locations = new HashSet<>();

        List<Recommendation> filteredRecommendations = new ArrayList<>();

        for (Recommendation recommendation : recommendations) {
            TimePeriod timePeriod = recommendation.getTimePeriod();
            Location location = recommendation.getLocation();

            if (timePeriods.contains(timePeriod) || locations.contains(location)) {
                continue;
            }

            filteredRecommendations.add(recommendation);
            timePeriods.add(timePeriod);
            locations.add(location);
        }

        return filteredRecommendations;
    }
}
