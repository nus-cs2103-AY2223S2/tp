package seedu.address.model.crew;

import seedu.address.model.item.Identifiable;
import java.util.*;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Crew implements Identifiable {
    private final String name;
    private final String id;

    private final CrewRank rank;

    public Crew(String name, CrewRank rank) {
        this(UUID.randomUUID().toString(), name, rank);
    }

    public Crew(String name, String id, CrewRank rank) {
        requireAllNonNull(name, id, rank);
        this.name = name;
        this.id = id;
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
    public int hashCode() {
        return Objects.hash(name, id, rank);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Id: ")
                .append(getId())
                .append("; Rank: ")
                .append(getRank());
        return builder.toString();
    }
}
