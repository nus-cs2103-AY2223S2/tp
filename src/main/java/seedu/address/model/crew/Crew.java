package seedu.address.model.crew;

import seedu.address.model.item.Identifiable;
import java.util.*;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Crew implements Identifiable {
    private final String id;
    private final String name;
    private final CrewRank rank;

    public Crew(String name, CrewRank rank) {
        this(UUID.randomUUID().toString(), name, rank);
    }

    public Crew(String id, String name, CrewRank rank) {
        requireAllNonNull(id, name, rank);
        this.id = id;
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public CrewRank getRank() {
        return rank;
    }

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
