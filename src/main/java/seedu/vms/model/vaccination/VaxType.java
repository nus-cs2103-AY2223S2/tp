package seedu.vms.model.vaccination;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;


/**
 * Represents a vaccination type.
 */
public class VaxType {
    public static final HashSet<String> DEFAULT_GROUP_SET = new HashSet<>();
    public static final int DEFAULT_MIN_AGE = Integer.MIN_VALUE;
    public static final int DEFAULT_MAX_AGE = Integer.MAX_VALUE;
    public static final int DEFAULT_MIN_SPACING = Integer.MAX_VALUE;
    public static final List<Requirement> DEFAULT_HISTORY_REQS = List.of();
    public static final List<Requirement> DEFAULT_ALLERGY_REQS = List.of();

    private final VaxName name;
    private final HashSet<String> groups;
    private final int minAge;
    private final int maxAge;
    private final int minSpacing;
    private final List<Requirement> historyReqs;
    private final List<Requirement> allergyReqs;


    /**
     * Constructs a {@code VaxType}.
     */
    public VaxType(VaxName name, HashSet<String> groups,
                int minAge, int maxAge, int minSpacing,
                List<Requirement> allergyReqs, List<Requirement> historyReqs) {
        this.name = name;
        this.groups = groups;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minSpacing = minSpacing;
        this.allergyReqs = allergyReqs;
        this.historyReqs = historyReqs;
    }


    /**
     * Constructs a {@code VaxType}. The given name is converted to a
     * {@code VaxName}.
     */
    public VaxType(String name, HashSet<String> groups,
            int minAge, int maxAge, int minSpacing,
            List<Requirement> allergyReqs, List<Requirement> historyReqs) {
        this(new VaxName(name), groups, minAge, maxAge, minSpacing, allergyReqs, historyReqs);
    }


    public String getName() {
        return name.toString();
    }


    public HashSet<String> getGroups() {
        return new HashSet<>(groups);
    }


    public int getMinAge() {
        return minAge;
    }


    public int getMaxAge() {
        return maxAge;
    }


    public int getMinSpacing() {
        return minSpacing;
    }


    public List<Requirement> getHistoryReqs() {
        return Requirement.copy(historyReqs);
    }


    public List<Requirement> getAllergyReqs() {
        return Requirement.copy(allergyReqs);
    }


    @Override
    public String toString() {
        return name.toString();
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof VaxType)) {
            return false;
        }

        VaxType casted = (VaxType) other;
        return name.equals(casted.name) && groups.equals(casted.groups)
                && minAge == casted.minAge && maxAge == casted.maxAge
                && minSpacing == casted.minSpacing
                && allergyReqs.equals(casted.allergyReqs)
                && historyReqs.equals(casted.historyReqs);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, groups, minAge, maxAge, minSpacing, allergyReqs, historyReqs);
    }
}
