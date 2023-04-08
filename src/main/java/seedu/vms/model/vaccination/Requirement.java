package seedu.vms.model.vaccination;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import seedu.vms.commons.util.AppUtil;
import seedu.vms.model.GroupName;


/** Represents a vaccination requirement. */
public class Requirement {
    public static final String MESSAGE_CONSTRAINTS =
            "Requirement set cannot be empty and must have no more than 30 elements";

    public static final int LIMIT = 30;

    private final RequirementType reqType;
    private final HashSet<GroupName> reqSet;


    /**
     * Constructs a {@code VaxRequirement}.
     *
     * @param reqType - the requirement type.
     * @param reqSet - the requirement set.
     * @throws NullPointerException if {@code reqType} is {@code null}.
     * @throws IllegalArgumentException if {@code reqSet} is empty.
     */
    public Requirement(RequirementType reqType, HashSet<GroupName> reqSet) {
        Objects.requireNonNull(reqType);
        Objects.requireNonNull(reqSet);
        AppUtil.checkArgument(isValidReqSet(reqSet), MESSAGE_CONSTRAINTS);
        this.reqType = reqType;
        this.reqSet = new HashSet<>(reqSet);
    }


    public static boolean isValidReqSet(HashSet<GroupName> reqSet) {
        return !reqSet.isEmpty() && reqSet.size() <= LIMIT;
    }


    /**
     * Checks if the given set satisfies the requirements.
     *
     * @param grpSet - the group set to check for.
     */
    public boolean check(HashSet<GroupName> grpSet) {
        return reqType.check(reqSet, grpSet);
    }


    public RequirementType getReqType() {
        return reqType;
    }


    public HashSet<GroupName> getReqSet() {
        return new HashSet<>(reqSet);
    }


    /**
     * Returns a copy of this requirement. Changes to one will not affect
     * the other.
     *
     * @return a copy of this requirement.
     */
    public Requirement copy() {
        return new Requirement(reqType, reqSet);
    }


    /**
     * Returns a copy of the given list of {@code VaxRequirements}. Returned
     * list is immutable and changes to the original will not be reflected in
     * the copy.
     *
     * @param reqs - the list of requirements to copy.
     * @return a copy of the specified list.
     * @throws NullPointerException if list given is {@code null}.
     */
    public static List<Requirement> copy(List<Requirement> reqs) {
        return reqs.stream()
                .map(req -> req.copy())
                .collect(Collectors.toList());
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Requirement)) {
            return false;
        }

        Requirement casted = (Requirement) other;
        return reqType == casted.reqType && reqSet.equals(casted.reqSet);
    }


    @Override
    public int hashCode() {
        return Objects.hash(reqType, reqSet);
    }





    /** Represents the type of a vaccination requirement. */
    public static enum RequirementType {
        /** Ensures all elements in the required set are present. */
        ALL(RequirementType::checkAll),
        /** Ensures that at least one element in the required set is present. */
        ANY(RequirementType::checkAny),
        /** Ensures that no elements in the required set are present. */
        NONE(RequirementType::checkNone);


        private final BiPredicate<HashSet<GroupName>, HashSet<GroupName>> checker;


        private RequirementType(BiPredicate<HashSet<GroupName>, HashSet<GroupName>> checker) {
            this.checker = checker;
        }


        private boolean check(HashSet<GroupName> reqSet, HashSet<GroupName> checkingSet) {
            return checker.test(reqSet, checkingSet);
        }


        private static boolean checkAll(HashSet<GroupName> reqSet, HashSet<GroupName> checkingSet) {
            for (GroupName grp : reqSet) {
                if (!checkingSet.contains(grp)) {
                    return false;
                }
            }
            return true;
        }


        private static boolean checkAny(HashSet<GroupName> reqSet, HashSet<GroupName> checkingSet) {
            for (GroupName grp : reqSet) {
                if (checkingSet.contains(grp)) {
                    return true;
                }
            }
            // reqSet is assumed to always NOT be empty
            return false;
        }


        private static boolean checkNone(HashSet<GroupName> reqSet, HashSet<GroupName> checkingSet) {
            return !checkAny(reqSet, checkingSet);
        }
    }
}
