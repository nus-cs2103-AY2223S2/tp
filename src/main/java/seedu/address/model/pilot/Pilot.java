package seedu.address.model.pilot;

import java.util.UUID;

import seedu.address.model.item.Identifiable;

/**
 * Represents a Pilot in the Wingman app.
 */
public class Pilot implements Identifiable {
    private final PilotRank rank;

    private final String id;

    /**
     * Creates a Pilot with the given name, phone, email and address and a
     * random {@code UUID} as the id.
     *
     * @param rank the rank of the pilot.
     */
    public Pilot(PilotRank rank) {
        this(UUID.randomUUID().toString(), rank);
    }

    /**
     * Creates a Pilot with the given id, name, phone, email and address.
     * Every field must be present and not null.
     *
     * @param id the id of the pilot.
     */
    public Pilot(String id, PilotRank rank) {
        this.id = id;
        this.rank = rank;
    }

    /**
     * Returns the rank of the pilot.
     *
     * @return the rank of the pilot.
     */
    public PilotRank getRank() {
        return rank;
    }


    @Override
    public String getId() {
        return id;
    }
}
