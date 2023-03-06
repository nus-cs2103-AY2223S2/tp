package seedu.address.model.crew;

import seedu.address.model.item.Identifiable;

import java.util.List;
import java.util.UUID;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Crew in the Wingman app.
 */
public class Crew implements Identifiable {

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
        requireAllNonNull(id, name, rank);
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
        return List.of("ID: " + id,
                "Name: " + name,
                "Rank: " + rank);
    }
}
