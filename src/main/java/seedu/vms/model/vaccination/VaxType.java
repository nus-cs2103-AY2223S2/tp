package seedu.vms.model.vaccination;

import java.util.HashSet;
import java.util.List;


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

    private final String name;
    private final HashSet<String> groups;
    private final int minAge;
    private final int maxAge;
    private final int minSpacing;
    private final List<Requirement> historyReqs;
    private final List<Requirement> allergyReqs;


    /**
     * Constructs a {@code VaxType}.
     */
    public VaxType(String name, HashSet<String> groups,
                int minAge, int maxAge, int minSpacing,
                List<Requirement> historyReqs, List<Requirement> allergyReqs) {
        this.name = name;
        this.groups = groups;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minSpacing = minSpacing;
        this.historyReqs = historyReqs;
        this.allergyReqs = allergyReqs;
    }


    public String getName() {
        return name;
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
        return name;
    }
}
