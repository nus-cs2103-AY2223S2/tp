package seedu.vms.model.vaccination;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import seedu.vms.commons.util.AppUtil;
import seedu.vms.model.Age;
import seedu.vms.model.GroupName;


/**
 * Represents a vaccination type.
 */
public class VaxType implements Comparable<VaxType> {
    public static final int LIMIT_GROUPS = 10;
    public static final int LIMIT_INGREDIENTS = 100;
    public static final int LIMIT_HISTORY_REQ = 10;

    public static final String MESSAGE_GROUPS_CONSTRAINTS =
            String.format("Only a maximum of %d groups are allowed", LIMIT_GROUPS);
    public static final String MESSAGE_AGE_CONSTRAINTS =
            "Minimum age must be lesser than or equals to maximum age";
    public static final String MESSAGE_INGREDIENTS_CONSTRAINTS =
            String.format("Only a maximum of %d ingredients are allowed", LIMIT_INGREDIENTS);
    public static final String MESSAGE_HISTORY_REQ_CONSTRAINTS =
            String.format("Only a maximum of %d history requirements are allowed", LIMIT_HISTORY_REQ);

    public static final HashSet<GroupName> DEFAULT_GROUP_SET = new HashSet<>();
    public static final Age DEFAULT_MIN_AGE = Age.MIN_AGE;
    public static final Age DEFAULT_MAX_AGE = Age.MAX_AGE;
    public static final HashSet<GroupName> DEFAULT_INGREDIENTS = new HashSet<>();
    public static final List<Requirement> DEFAULT_HISTORY_REQS = List.of();

    private final GroupName name;
    private final HashSet<GroupName> groups;
    private final Age minAge;
    private final Age maxAge;
    private final HashSet<GroupName> ingredients;
    private final List<Requirement> historyReqs;


    /**
     * Constructs a {@code VaxType}.
     *
     * @throws IllegalArgumentException if {@code minAge > maxAge} or
     *      {@code minSpacing < 0}.
     */
    public VaxType(GroupName name, HashSet<GroupName> groups,
                Age minAge, Age maxAge,
                HashSet<GroupName> ingredients, List<Requirement> historyReqs) {
        AppUtil.checkArgument(AppUtil.isWithinLimit(groups, LIMIT_GROUPS), MESSAGE_GROUPS_CONSTRAINTS);
        AppUtil.checkArgument(isValidRange(minAge, maxAge), MESSAGE_AGE_CONSTRAINTS);
        AppUtil.checkArgument(AppUtil.isWithinLimit(ingredients, LIMIT_INGREDIENTS), MESSAGE_INGREDIENTS_CONSTRAINTS);
        AppUtil.checkArgument(AppUtil.isWithinLimit(historyReqs, LIMIT_HISTORY_REQ), MESSAGE_HISTORY_REQ_CONSTRAINTS);

        this.name = name;
        this.groups = groups;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.ingredients = ingredients;
        this.historyReqs = historyReqs;
    }

    public static boolean isValidRange(Age minAge, Age maxAge) {
        return maxAge.compareTo(minAge) >= 0;
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


    public Age getMinAge() {
        return minAge;
    }


    public Age getMaxAge() {
        return maxAge;
    }


    public List<Requirement> getHistoryReqs() {
        return Requirement.copy(historyReqs);
    }


    public HashSet<GroupName> getIngredients() {
        return new HashSet<>(ingredients);
    }


    @Override
    public int compareTo(VaxType other) {
        return name.compareTo(other.name);
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
                && minAge.equals(casted.minAge) && maxAge.equals(casted.maxAge)
                && ingredients.equals(casted.ingredients)
                && historyReqs.equals(casted.historyReqs);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, groups, minAge, maxAge, ingredients, historyReqs);
    }
}
