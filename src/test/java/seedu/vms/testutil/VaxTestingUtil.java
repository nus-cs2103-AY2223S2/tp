package seedu.vms.testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;

import seedu.vms.model.Age;
import seedu.vms.model.GroupName;
import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.VaxType;


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
     * @param historyReqs - the expected history requirements.
     * @param ingredients - the expected ingredients.
     */
    public static void assertVaxType(VaxType vaxType,
            GroupName name, HashSet<GroupName> groups, Age minAge, Age maxAge,
            List<Requirement> historyReqs, HashSet<GroupName> ingredients) {
        assertEquals(name, vaxType.getGroupName(), "Name");
        assertEquals(groups, vaxType.getGroups(), "Groups");
        assertEquals(minAge, vaxType.getMinAge(), "Min age");
        assertEquals(maxAge, vaxType.getMaxAge(), "Max age");
        assertEquals(historyReqs, vaxType.getHistoryReqs(), "History requirements");
        assertEquals(ingredients, vaxType.getIngredients(), "Ingredients");
    }
}
