package seedu.vms.model.vaccination;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Represents a vaccination type.
 *
 * <p>Types created through either {@link #of(String, HashSet, int, int, int, List)}
 * or {@link #of(String, int, int, int, List)} methods are stored and can be
 * retrieved through the {@link #get(String)} method. During creation of a
 * type, if the name of the type already exists, the vaccination type mapping
 * to that name is replaced with the newly created type.
 */
public abstract class VaxType {
    private static final HashMap<String, VaxType> TYPE_MAP = new HashMap<>();

    private final String name;
    private final HashSet<String> groups;
    private final int minAge;
    private final int maxAge;
    private final int minSpacing;
    private final List<VaxRequirement> requirements;


    private VaxType(String name, HashSet<String> groups,
                int minAge, int maxAge, int minSpacing,
                List<VaxRequirement> requirements) {
        this.name = name;
        this.groups = groups;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minSpacing = minSpacing;
        this.requirements = requirements;
    }


    private static VaxType of(String name, HashSet<String> groups,
                int minAge, int maxAge, int minSpacing,
                List<VaxRequirement> requirements) {
        VaxType vaxType = new VaxType(name, groups,
                minAge, maxAge, minSpacing,
                requirements) {};
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


    public List<VaxRequirement> getRequirements() {
        return requirements.stream()
                .map(req -> req.copy())
                .collect(Collectors.toList());
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
        public static final List<VaxRequirement> DEFAULT_REQUIREMENTS = List.of();

        private final Optional<String> originalName;
        private final String name;
        private final HashSet<String> groups;
        private final int minAge;
        private final int maxAge;
        private final int minSpacing;
        private final List<VaxRequirement> requirements;


        private Builder(Optional<String> originalName, String name, HashSet<String> groups,
                    int minAge, int maxAge, int minSpacing, List<VaxRequirement> requirements) {
            this.originalName = originalName;
            this.name = name;
            this.groups = new HashSet<>(groups);
            this.minAge = minAge;
            this.maxAge = maxAge;
            this.minSpacing = minSpacing;
            this.requirements = requirements.stream()
                    .map(req -> req.copy())
                    .collect(Collectors.toList());
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
            List<VaxRequirement> requirements = DEFAULT_REQUIREMENTS;

            VaxType vaxType = VaxType.get(name);
            if (vaxType != null) {
                originalName = Optional.ofNullable(vaxType.getName());
                groups = vaxType.getGroups();
                minAge = vaxType.getMinAge();
                maxAge = vaxType.getMaxAge();
                minSpacing = vaxType.getMinSpacing();
                requirements = vaxType.getRequirements();
            }

            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, requirements);
        }


        public Builder setName(String name) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, requirements);
        }


        public Builder setGroups(String ... grps) {
            HashSet<String> groups = new HashSet<>(List.of(grps));
            return setGroups(groups);
        }


        public Builder setGroups(HashSet<String> groups) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, requirements);
        }


        public Builder setMinAge(int minAge) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, requirements);
        }


        public Builder setMaxAge(int maxAge) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, requirements);
        }


        public Builder setMinSpacing(int minSpacing) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, requirements);
        }


        public Builder setRequirements(VaxRequirement ... reqs) {
            List<VaxRequirement> requirements = List.of(reqs);
            return setRequirements(requirements);
        }


        public Builder setRequirements(List<VaxRequirement> requirements) {
            return new Builder(originalName, name, groups, minAge, maxAge, minSpacing, requirements);
        }


        /**
         * Builds the set vaccination type.
         *
         * @return the built vaccination type.
         */
        public VaxType build() {
            originalName.ifPresent(VaxType::remove);
            return VaxType.of(name, groups, minAge, maxAge, minSpacing, requirements);
        }
    }
}
