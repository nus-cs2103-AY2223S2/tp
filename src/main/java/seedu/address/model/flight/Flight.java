package seedu.address.model.flight;

import java.util.List;
import java.util.UUID;

import seedu.address.model.item.Identifiable;


/**
 * Represents a flight object in wingman
 */
public class Flight implements Identifiable {
    private final String code;
    private final String id;

    //TODO: Add departure and arrival locations
    //TODO: Add exceptions to ensure departure and arrival locations are distinct

    /**
     * Creates a flight with a random UUID as its id
     *
     * @param code the code of the flight
     */
    public Flight(String code) {
        this.code = code;
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Creates a flight with a given id as its id
     *
     * @param id the id for the flight
     * @param code the code of the flight
     */
    public Flight(String id, String code) {
        this.code = code;
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public List<String> getDisplayList() {
        return List.of(
                "UUID: " + id,
                "Code: " + code
        );
    }

    @Override
    public String getId() {
        return this.id;
    }
}

