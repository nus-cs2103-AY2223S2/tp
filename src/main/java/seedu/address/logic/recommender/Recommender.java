package seedu.address.logic.recommender;

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
import seedu.address.logic.recommender.location.LocationRecommender;
import seedu.address.logic.recommender.location.LocationTracker;
import seedu.address.logic.recommender.timing.TimingRecommender;
import seedu.address.model.Model;
import seedu.address.model.location.Location;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.model.time.TimePeriod;

/**
 * Recommends meetup times and locations.
 */
public class Recommender {

    private static final Logger logger = LogsCenter.getLogger(Recommender.class);
    private static final int RECOMMENDATION_LIMIT = 20;
    private final LocationRecommender locationRecommender;
    private final TimingRecommender timingRecommender;
    private final Model model;
    private Set<LocationTracker> locationTrackers;

    /**
     * Constructs a {@code Recommender} object
     */
    public Recommender(Model model) {
        this.model = model;
        locationRecommender = new LocationRecommender();
        timingRecommender = new TimingRecommender(model);
        locationTrackers = new HashSet<>();
    }

    /**
     * Returns a list of recommendations.
     */
    public List<Recommendation> recommend(Collection<ContactIndex> contactIndices, Collection<Location> destinations) {
        logger.info(String.format("Persons to meet: %s", contactIndices.toString()));

        initialise(contactIndices, destinations);
        List<TimePeriod> timingRecommendations =
                timingRecommender.giveLongestTimingRecommendations(RECOMMENDATION_LIMIT);

        logger.info(String.format("%d timings recommended", timingRecommendations.size()));

        List<List<Location>> locationRecommendations = timingRecommendations.stream()
                .map(this::getLocationsFromTimePeriod)
                .map(locationRecommender::recommend)
                .collect(Collectors.toList());

        List<Recommendation> recommendations = CollectionUtil
                .zip(locationRecommendations.stream(),
                        timingRecommendations.stream(),
                        this::recommendFromLocationsTimePeriod)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<Recommendation> filteredRecommendations = filterRecommendations(recommendations);

        return filteredRecommendations.stream().limit(RECOMMENDATION_LIMIT).collect(Collectors.toList());
    }

    /**
     * Sets up the {@code LocationRecommender}, {@code TimingRecommender}
     * and {@code LocationTracker} for each person.
     */
    private void initialise(Collection<ContactIndex> contactIndices, Collection<Location> destinations) {
        locationRecommender.initialise(destinations);
        timingRecommender.initialise(contactIndices);
        locationTrackers = timingRecommender.getParticipants().stream()
                .map(LocationTracker::new)
                .collect(Collectors.toSet());

        logger.info(String.format("Location Trackers: %s", locationTrackers.stream()
                .map(LocationTracker::toString)
                .collect(Collectors.joining("\n"))));
    }

    /**
     * Gets location from hour block.
     */
    private Set<Location> getLocationsFromTimePeriod(TimePeriod timePeriod) {
        return locationTrackers.stream()
                .map(lt -> lt.getLocation(timePeriod))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a list of recommendation based on a particular timing.
     */
    private List<Recommendation> recommendFromLocationsTimePeriod(
            List<Location> locations, TimePeriod timePeriod) {
        return locations.stream()
                .map(l -> new Recommendation(l, timePeriod))
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
