package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;


public class VaxTestingUtil {
    public static void assertVaxType(VaxType vaxType,
            String name, HashSet<String> groups, int minAge, int maxAge, int minSpacing,
            List<VaxRequirement> requirements) {
        assertEquals(name, vaxType.getName(), "Name");
        assertEquals(groups, vaxType.getGroups(), "Groups");
        assertEquals(minAge, vaxType.getMinAge(), "Min age");
        assertEquals(maxAge, vaxType.getMaxAge(), "Max age");
        assertEquals(minSpacing, vaxType.getMinSpacing(), "Min spacing");
        assertEquals(requirements, vaxType.getRequirements(), "Requirements");
    }
}
