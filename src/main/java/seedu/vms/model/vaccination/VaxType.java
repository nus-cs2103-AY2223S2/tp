package seedu.vms.model.vaccination;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


/**
 * Represents a vaccination type.
 */
public abstract class VaxType {
    private static final HashMap<String, VaxType> TYPE_MAP = new HashMap<>();

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


    private VaxType(String name, HashSet<String> groups,
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


    private static VaxType of(String name, HashSet<String> groups,
                int minAge, int maxAge, int minSpacing,
                List<Requirement> historyReqs, List<Requirement> allergyReqs) {
        VaxType vaxType = new VaxType(name, groups,
                minAge, maxAge, minSpacing,
                historyReqs, allergyReqs) {};
        TYPE_MAP.put(name, vaxType);
        return vaxType;
    }


    public static VaxType remove(String name) {
        return TYPE_MAP.remove(name);
    }


    /** Clears all stored vaccination type. */
    public static void clear() {
        TYPE_MAP.clear();
    }

    /**
     * Returns the vaccination type mapped to the specified name. If there are
     * no mappings to the name, {@code null} is returned.
     *
     * @param name - the name of the type to retrieve.
     * @return the type mapped to the specified name.
     */
    public static VaxType get(String name) {
        return TYPE_MAP.get(name);
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





    /**
     * A {@code VaxType} builder to provide support for creating, renaming and
     * editing of {@code VaxType}.
     */
    public static class Builder {
        public static final HashSet<String> DEFAULT_GROUP_SET = new HashSet<>();
        public static final int DEFAULT_MIN_AGE = Integer.MIN_VALUE;
        public static final int DEFAULT_MAX_AGE = Integer.MAX_VALUE;
        public static final int DEFAULT_MIN_SPACING = Integer.MAX_VALUE;
        public static final List<Requirement> DEFAULT_HISTORY_REQS = List.of();
        public static final List<Requirement> DEFAULT_ALLERGY_REQS = List.of();

        private final Optional<String> originalName;
        private final String name;
        private final HashSet<String> groups;
        private final int minAge;
        private final int maxAge;
        private final int minSpacing;
        private final List<Requirement> historyReqs;
        private final List<Requirement> allergyReqs;


        private Builder(Optional<String> originalName, String name, HashSet<String> groups,
                    int minAge, int maxAge, int minSpacing,
                    List<Requirement> historyReqs, List<Requirement> allergyReqs) {
            this.originalName = originalName;
            this.name = name;
            this.groups = new HashSet<>(groups);
            this.minAge = minAge;
            this.maxAge = maxAge;
            this.minSpacing = minSpacing;
            this.historyReqs = Requirement.copy(historyReqs);
            this.allergyReqs = Requirement.copy(allergyReqs);
        }


        /**
         * Creates a builder that is set to build a vaccination type of the
         * given name.
         *
         * <p>If the type already exists, the attributes of that existing type
         * will be loaded into this builder. Else, the default attributes will
         * be used.
         *
         * @param name - the name of the vaccination type to build.
         * @return a {@code VaxType.Builder}.
         */
        public static Builder of(String name) {
            Optional<String> originalName = Optional.empty();
            HashSet<String> groups = DEFAULT_GROUP_SET;
            int minAge = DEFAULT_MIN_AGE;
            int maxAge = DEFAULT_MAX_AGE;
            int minSpacing = DEFAULT_MIN_SPACING;
            List<Requirement> historyReqs = DEFAULT_HISTORY_REQS;
            List<Requirement> allergyReqs = DEFAULT_ALLERGY_REQS;

            VaxType vaxType = VaxType.get(name);
            if (vaxType != null) {
                originalName = Optional.ofNullable(vaxType.getName());
                groups = vaxType.getGroups();
                minAge = vaxType.getMinAge();
                maxAge = vaxType.getMaxAge();
                minSpacing = vaxType.getMinSpacing();
                historyReqs = vaxType.getHistoryReqs();
                allergyReqs = vaxType.getAllergyReqs();
            }

            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, historyReqs, allergyReqs);
        }


        public Builder setName(String name) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, historyReqs, allergyReqs);
        }


        public Builder setGroups(String ... grps) {
            HashSet<String> groups = new HashSet<>(List.of(grps));
            return setGroups(groups);
        }


        public Builder setGroups(HashSet<String> groups) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, historyReqs, allergyReqs);
        }


        public Builder setMinAge(int minAge) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, historyReqs, allergyReqs);
        }


        public Builder setMaxAge(int maxAge) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, historyReqs, allergyReqs);
        }


        public Builder setMinSpacing(int minSpacing) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, historyReqs, allergyReqs);
        }


        public Builder setHistoryReq(Requirement ... reqs) {
            List<Requirement> historyReqs = List.of(reqs);
            return setHistoryReqs(historyReqs);
        }


        public Builder setHistoryReqs(List<Requirement> historyReqs) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, historyReqs, allergyReqs);
        }


        public Builder setAllergyReqs(Requirement ... reqs) {
            List<Requirement> allergyReqs = List.of(reqs);
            return setAllergyReqs(allergyReqs);
        }


        public Builder setAllergyReqs(List<Requirement> allergyReqs) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, historyReqs, allergyReqs);
        }


        /**
         * Builds the set vaccination type.
         *
         * @return the built vaccination type.
         */
        public VaxType build() {
            originalName.ifPresent(VaxType::remove);
            return VaxType.of(name, groups, minAge, maxAge, minSpacing, historyReqs, allergyReqs);
        }
    }
}
