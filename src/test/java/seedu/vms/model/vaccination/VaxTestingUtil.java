package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;


/** Testing utility class for vaccinations. */
public class VaxTestingUtil {
    /**
     * Asserts if the given vaccination type is of the expected state.
     *
     * @param vaxType - the {@code VaxType} to check.
     * @param name - the expected name.
     * @param groups - the expected groups.
     * @param minAge - the expected min age.
     * @param maxAge - the expected max age.
     * @param minSpacing - the expected min spacing.
     * @param historyReqs - the expected history requirements.
     * @param allergyReqs - the expected allergy requirements.
     */
    public static void assertVaxType(VaxType vaxType,
            String name, HashSet<String> groups, int minAge, int maxAge, int minSpacing,
            List<VaxRequirement> historyReqs, List<VaxRequirement> allergyReqs) {
        assertEquals(name, vaxType.getName(), "Name");
        assertEquals(groups, vaxType.getGroups(), "Groups");
        assertEquals(minAge, vaxType.getMinAge(), "Min age");
        assertEquals(maxAge, vaxType.getMaxAge(), "Max age");
        assertEquals(minSpacing, vaxType.getMinSpacing(), "Min spacing");
        assertEquals(historyReqs, vaxType.getHistoryReqs(), "History requirements");
        assertEquals(allergyReqs, vaxType.getAllergyReqs(), "Allergy requirements");
    }
}
