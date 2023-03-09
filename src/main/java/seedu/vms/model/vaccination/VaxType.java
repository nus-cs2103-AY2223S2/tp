package seedu.vms.model.vaccination;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import seedu.vms.commons.util.AppUtil;
import seedu.vms.model.GroupName;


/**
 * Represents a vaccination type.
 */
public class VaxType {
    public static final String MESSAGE_AGE_CONSTRAINTS =
            "Minimum age must be lesser than or equals to maximum age";
    public static final String MESSAGE_SPACING_CONSTRAINTS =
            "Spacing must be a positive integer";

    public static final HashSet<GroupName> DEFAULT_GROUP_SET = new HashSet<>();
    public static final int DEFAULT_MIN_AGE = Integer.MIN_VALUE;
    public static final int DEFAULT_MAX_AGE = Integer.MAX_VALUE;
    public static final int DEFAULT_MIN_SPACING = Integer.MAX_VALUE;
    public static final List<Requirement> DEFAULT_HISTORY_REQS = List.of();
    public static final List<Requirement> DEFAULT_ALLERGY_REQS = List.of();

    private final GroupName name;
    private final HashSet<GroupName> groups;
    private final int minAge;
    private final int maxAge;
    private final int minSpacing;
    private final List<Requirement> historyReqs;
    private final List<Requirement> allergyReqs;


    /**
     * Constructs a {@code VaxType}.
     *
     * @throws IllegalArgumentException if {@code minAge > maxAge} or
     *      {@code minSpacing < 0}.
     */
    public VaxType(GroupName name, HashSet<GroupName> groups,
                int minAge, int maxAge, int minSpacing,
                List<Requirement> allergyReqs, List<Requirement> historyReqs) {
        AppUtil.checkArgument(isValidRange(minAge, maxAge), MESSAGE_AGE_CONSTRAINTS);
        AppUtil.checkArgument(isValidSpacing(minSpacing), MESSAGE_SPACING_CONSTRAINTS);
        this.name = name;
        this.groups = groups;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minSpacing = minSpacing;
        this.allergyReqs = allergyReqs;
        this.historyReqs = historyReqs;
    }


    public static boolean isValidRange(int minAge, int maxAge) {
        return maxAge >= minAge;
    }


    public static boolean isValidSpacing(int minSpacing) {
        return minSpacing >= 0;
    }


    public String getName() {
        return name.getName();
    }


    public GroupName getGroupName() {
        return name;
    }


    public HashSet<GroupName> getGroups() {
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
