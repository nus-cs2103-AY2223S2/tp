package wingman.model.pilot;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import wingman.model.item.Item;
import wingman.model.location.PilotLocationType;

/**
 * Represents a Pilot in the Wingman app.
 */
public class Pilot implements Item {

    /**
     * The shape of the link between pilot and flight
     */
    public static final Map<FlightPilotType, Integer> SHAPE =
            Map.of(
                    FlightPilotType.PILOT_FLYING, 1,
                    FlightPilotType.PILOT_MONITORING, 1
            );

    /**
     * The shape of the link between pilot and location.
     * Here the number is an arbitrarily large number that
     * allows a location to link to infinite number of pilots,
     * in the ideal case.
     */
    public static final Map<PilotLocationType, Integer> SHAPE_FOR_LOCATION =
            Map.of(
                    PilotLocationType.LOCATION_USING, 1000000
            );

    private static final String GENDER_STRING = "Gender";

    private static final String AGE_STRING = "Age";

    private static final String RANK_STRING = "Rank";

    private static final String FLIGHT_HR_STRING = "Flight Hours";

    private final String name;

    private final int age;

    private final Gender gender;

    private final int flightHour;

    private final PilotRank rank;

    private final String id;
    private boolean isAvailable;

    /**
     * Creates a pilot with a random UUID as its id.
     *
     * @param name       the name of the pilot.
     * @param age        the age of the pilot.
     * @param gender     the gender of the pilot.
     * @param rank       the rank of the pilot.
     * @param flightHour the flight hour of the pilot.
     */
    public Pilot(
            String name,
            int age,
            Gender gender,
            PilotRank rank,
            int flightHour
    ) {
        this(UUID.randomUUID().toString(), name, age, gender, rank, flightHour);
        this.isAvailable = true;
    }

    /**
     * Creates a pilot.
     *
     * @param id         the id of the pilot.
     * @param name       the name of the pilot.
     * @param age        the age of the pilot.
     * @param gender     the gender of the pilot.
     * @param rank       the rank of the pilot.
     * @param flightHour the flight hour of the pilot.
     */
    public Pilot(
            String id,
            String name,
            int age,
            Gender gender,
            PilotRank rank,
            int flightHour
    ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.rank = rank;
        this.flightHour = flightHour;
        this.isAvailable = true;
    }

    /**
     * Returns the name of the pilot.
     *
     * @return the name of the pilot.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rank of the pilot.
     *
     * @return the rank of the pilot.
     */
    public PilotRank getRank() {
        return rank;
    }

    /**
     * Returns the age of the pilot.
     *
     * @return the age of the pilot.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the gender of the pilot.
     *
     * @return the gender of the pilot.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets the flight hour of the pilot.
     *
     * @return the flight hour of the pilot.
     */
    public int getFlightHour() {
        return flightHour;
    }

    /**
     * Returns the availability of the pilot.
     *
     * @return the availability of the pilot.
     */
    public boolean isAvailable() {
        return this.isAvailable;
    }

    /**
     * Sets the availability of the pilot to unavailable.
     */
    public void setUnavailable() {
        this.isAvailable = false;
    }

    /**
     * Sets the availability of the pilot to available.
     */
    public void setAvailable() {
        this.isAvailable = true;
    }

    /**
     * Returns a String corresponding to the availability of the pilot.
     *
     * @return the availability of the pilot as a String
     */
    public String getAvailabilityString() {
        return (this.isAvailable)
                       ? "Available"
                       : "Unavailable";
    }

    @Override
    public List<String> getDisplayList() {
        return List.of(
                String.format("%s", name),
                String.format("%s: %s", RANK_STRING, rank),
                String.format("%s: %s", AGE_STRING, age),
                String.format("%s: %s", GENDER_STRING, gender),
                String.format("%s: %s", FLIGHT_HR_STRING, flightHour)
        );
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s",
                rank,
                name
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Pilot)) {
            return false;
        }

        Pilot other = (Pilot) obj;

        return other.getName().equals(getName());
    }
}
