package seedu.address.model.pilot;

import java.util.List;
import java.util.UUID;

import seedu.address.model.item.Identifiable;

/**
 * Represents a Pilot in the Wingman app.
 */
public class Pilot implements Identifiable {
    private final String name;

    private final int age;

    private final Gender gender;

    private final int flightHour;

    private final PilotRank rank;

    private final String id;

    /**
     * Creates a pilot with a random UUID as its id.
     *
     * @param name       the name of the pilot.
     * @param age        the age of the pilot.
     * @param gender     the gender of the pilot.
     * @param rank       the rank of the pilot.
     * @param flightHour the flight hour of the pilot.
     */
    public Pilot(String name, int age, Gender gender, PilotRank rank, int flightHour) {
        this(UUID.randomUUID().toString(), name, age, gender, rank, flightHour);
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
    public Pilot(String id, String name, int age, Gender gender, PilotRank rank, int flightHour) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.rank = rank;
        this.flightHour = flightHour;
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

    @Override
    public List<String> getDisplayList() {
        return List.of(
            "UUID: " + id,
            "Name: " + name,
            "Gender: " + gender,
            "Age: " + age,
            "Rank: " + rank,
            "Flight Hour: " + flightHour
        );
    }

    @Override
    public String getId() {
        return id;
    }
}
