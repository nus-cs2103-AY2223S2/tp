package seedu.vms.model.vaccination;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


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


    /**
     * Creates a {@code VaxType}.
     *
     * <p>This method should be used for creating or replacing a type only. To
     * get a type, use {@link #get(String)}.
     *
     * @param name - the name of the vaccination type.
     * @param groups - the groups the vaccination is classified under.
     * @param minAge - the minimum age requirement to take the vaccination.
     * @param maxAge - the maximum age requirement to take the vaccination.
     * @param minSpacing - the minimum time spacing this vaccination can be
     *      taken from the last in days.
     * @param requirements - the list of requirements.
     * @return the created vaccination type.
     */
    public static VaxType of(String name, HashSet<String> groups,
                int minAge, int maxAge, int minSpacing,
                List<VaxRequirement> requirements) {
        VaxType vaxType = new VaxType(name, groups,
                minAge, maxAge, minSpacing,
                requirements) {};
        TYPE_MAP.put(name, vaxType);
        return vaxType;
    }


    /**
     * Creates a {@code VaxType} that has no groups.
     *
     * <p>This method should be used for creating or replacing a type only. To
     * get a type, use {@link #get(String)}.
     *
     * @param name - the name of the vaccination type.
     * @param minAge - the minimum age requirement to take the vaccination.
     * @param maxAge - the maximum age requirement to take the vaccination.
     * @param minSpacing - the minimum time spacing this vaccination can be
     *      taken from the last in days.
     * @param requirements - the list of requirements.
     * @return the created vaccination type.
     */
    public static VaxType of(String name,
                int minAge, int maxAge, int minSpacing,
                List<VaxRequirement> requirements) {
        return VaxType.of(name, new HashSet<>(), minAge, maxAge, minSpacing, requirements);
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
        return groups;
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
        return requirements;
    }


    @Override
    public String toString() {
        return name;
    }
}
