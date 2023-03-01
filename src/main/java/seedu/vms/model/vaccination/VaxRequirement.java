package seedu.vms.model.vaccination;

import java.util.HashSet;


/** Represents a vaccination requirement. */
public class VaxRequirement {
    private final boolean isExclusion;
    private final HashSet<String> grpSet;


    /**
     * Constructs a {@code VaxRequirement}.
     *
     * @param isExclusion - if the requirement is an exclusion.
     * @param grpSet - the set of groups this requirement will check for.
     * @throws IllegalArgumentException if requirements given is empty.
     */
    public VaxRequirement(boolean isExclusion, HashSet<String> grpSet) {
        if (grpSet.isEmpty()) {
            throw new IllegalArgumentException("Empty substitution list");
        }
        this.isExclusion = isExclusion;
        this.grpSet = new HashSet<>(grpSet);
    }


    /**
     * Checks if the given histories satisfies the set requirements.
     *
     * @param grpSet - the group set to check for.
     */
    public boolean check(HashSet<String> grpSet) {
        boolean hasReq = checkReq(grpSet);
        return (isExclusion) ? !hasReq : hasReq;
    }


    private boolean checkReq(HashSet<String> grpSet) {
        for (String grp : this.grpSet) {
            if (grpSet.contains(grp)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns a copy of this requirement. Changes to one will not affect
     * the other.
     *
     * @return a copy of this requirement.
     */
    public VaxRequirement copy() {
        return new VaxRequirement(isExclusion, grpSet);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof VaxRequirement)) {
            return false;
        }

        VaxRequirement casted = (VaxRequirement) other;
        return isExclusion == casted.isExclusion && grpSet.equals(casted.grpSet);
    }


    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + ((isExclusion) ? 1 : 0);
        result = 37 * result + grpSet.hashCode();

        return result;
    }
}
