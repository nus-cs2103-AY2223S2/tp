package seedu.address.model.crew;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import seedu.address.model.item.Item;

/**
 * Represents a Crew in the Wingman app.
 */
public class Crew implements Item {

    /**
     * The shape of the link between a flight and its crew
     */
    public static final Map<FlightCrewType, Integer> SHAPE =
            Map.of(FlightCrewType.CABIN_SERVICE_DIRECTOR, 1,
                    FlightCrewType.SENIOR_FLIGHT_ATTENDANT, 1,
                    FlightCrewType.FLIGHT_ATTENDANT, 12,
                    FlightCrewType.TRAINEE, 4
            );
    private static final String ID_STRING = "ID";
    private static final String NAME_STRING = "Name";
    private static final String RANK_STRING = "Rank";
    private final String id;
    private final String name;
    private final CrewRank rank;

    /**
     * Creates a crew with a random UUID as its id.
     *
     * @param name the name of the crew.
     * @param rank the rank of the crew.
     */
    public Crew(String name, CrewRank rank) {
        this(UUID.randomUUID().toString(), name, rank);
    }

    /**
     * Creates a crew.
     *
     * @param id the id of the crew.
     * @param name the name of the crew.
     * @param rank the rank of the crew.
     */
    public Crew(String id, String name, CrewRank rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
    }

    /**
     * Returns the name of the crew.
     *
     * @return the name of the crew.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rank of the crew.
     *
     * @return the rank of the crew.
     */
    public CrewRank getRank() {
        return rank;
    }

    /**
     * Returns the id of the crew.
     *
     * @return the id of the crew.
     */
    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<String> getDisplayList() {
        return List.of(
                String.format("%s: %s", ID_STRING, id),
                String.format("%s: %s", NAME_STRING, name),
                String.format("%s: %s", RANK_STRING, rank));
    }

    @Override
    public String toString() {
        return String.format("%s %s", rank, name);
    }
}
