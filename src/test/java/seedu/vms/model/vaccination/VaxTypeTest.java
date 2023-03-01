package seedu.vms.model.vaccination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.vms.model.vaccination.VaxType.Builder;


public class VaxTypeTest {
    private static final String TYPE_1_NAME = "HANYA";
    private static final HashSet<String> TYPE_1_GROUPS = new HashSet<>(List.of("HANYA"));
    private static final int TYPE_1_MIN_AGE = 5;
    private static final int TYPE_1_MAX_AGE = 35;
    private static final int TYPE_1_MIN_SPACING = 45;
    private static final List<VaxRequirement> TYPE_1_REQUIREMENTS = List.of(
            new VaxRequirement(true, TYPE_1_GROUPS));

    private static final String TYPE_2_NAME = "NEEEE";
    private static final HashSet<String> TYPE_2_GROUPS = new HashSet<>(List.of("NEEEE"));
    private static final int TYPE_2_MIN_AGE = 37;
    private static final int TYPE_2_MAX_AGE = 100;
    private static final int TYPE_2_MIN_SPACING = 365;
    private static final List<VaxRequirement> TYPE_2_REQUIREMENTS = List.of(
            new VaxRequirement(false, TYPE_1_GROUPS),
            new VaxRequirement(true, TYPE_2_GROUPS));


    @BeforeEach
    public void clearMap() {
        // clear is assumed to work.
        VaxType.clear();
    }


    @Test
    public void of_basicCreation_defaultVaxType() {
        VaxType vaxType = Builder.of(TYPE_1_NAME).build();
        assertVaxType(vaxType,
                TYPE_1_NAME,
                Builder.DEFAULT_GROUP_SET,
                Builder.DEFAULT_MIN_AGE,
                Builder.DEFAULT_MAX_AGE,
                Builder.DEFAULT_MIN_SPACING,
                Builder.DEFAULT_REQUIREMENTS);
    }


    @Test
    public void of_customCreation_createType() {
        VaxType vaxType = Builder.of(TYPE_1_NAME)
                .setGroups(TYPE_1_GROUPS)
                .setMinAge(TYPE_1_MIN_AGE)
                .setMaxAge(TYPE_1_MAX_AGE)
                .setMinSpacing(TYPE_1_MIN_SPACING)
                .setRequirements(TYPE_1_REQUIREMENTS)
                .build();
        assertVaxType(vaxType,
                TYPE_1_NAME,
                TYPE_1_GROUPS,
                TYPE_1_MIN_AGE,
                TYPE_1_MAX_AGE,
                TYPE_1_MIN_SPACING,
                TYPE_1_REQUIREMENTS);
    }


    @Test
    public void get_presentType_typeReturned() {
        Builder.of(TYPE_1_NAME).build();
        VaxType vaxType = VaxType.get(TYPE_1_NAME);
        assertVaxType(vaxType,
                TYPE_1_NAME,
                Builder.DEFAULT_GROUP_SET,
                Builder.DEFAULT_MIN_AGE,
                Builder.DEFAULT_MAX_AGE,
                Builder.DEFAULT_MIN_SPACING,
                Builder.DEFAULT_REQUIREMENTS);
    }


    @Test
    public void of_renaming_typeRenamed() {
        Builder.of(TYPE_1_NAME)
                .setGroups(TYPE_1_GROUPS)
                .setMinAge(TYPE_1_MIN_AGE)
                .setMaxAge(TYPE_1_MAX_AGE)
                .setMinSpacing(TYPE_1_MIN_SPACING)
                .setRequirements(TYPE_1_REQUIREMENTS)
                .build();
        VaxType vaxType = Builder.of(TYPE_1_NAME)
                .setName(TYPE_2_NAME)
                .build();
        assertVaxType(vaxType,
                TYPE_2_NAME,
                TYPE_1_GROUPS,
                TYPE_1_MIN_AGE,
                TYPE_1_MAX_AGE,
                TYPE_1_MIN_SPACING,
                TYPE_1_REQUIREMENTS);
        assertTrue(VaxType.get(TYPE_1_NAME) == null);
    }


    @Test
    public void of_editing_typeEdited() {
        Builder.of(TYPE_1_NAME)
                .setGroups(TYPE_1_GROUPS)
                .setMinAge(TYPE_1_MIN_AGE)
                .setMaxAge(TYPE_1_MAX_AGE)
                .setMinSpacing(TYPE_1_MIN_SPACING)
                .setRequirements(TYPE_1_REQUIREMENTS)
                .build();
        VaxType vaxType = Builder.of(TYPE_1_NAME)
                .setGroups(TYPE_2_GROUPS)
                .setMinAge(TYPE_2_MIN_AGE)
                .setMaxAge(TYPE_2_MAX_AGE)
                .setMinSpacing(TYPE_2_MIN_SPACING)
                .setRequirements(TYPE_2_REQUIREMENTS)
                .build();
        assertVaxType(vaxType,
                TYPE_1_NAME,
                TYPE_2_GROUPS,
                TYPE_2_MIN_AGE,
                TYPE_2_MAX_AGE,
                TYPE_2_MIN_SPACING,
                TYPE_2_REQUIREMENTS);
    }


    @Test
    public void immutabilityTest() {
        Builder.of(TYPE_1_NAME).build();
        VaxType vaxType = VaxType.get(TYPE_1_NAME);
        vaxType.getGroups().addAll(TYPE_1_GROUPS);
        vaxType.getRequirements().addAll(TYPE_1_REQUIREMENTS);
        assertVaxType(vaxType,
                TYPE_1_NAME,
                Builder.DEFAULT_GROUP_SET,
                Builder.DEFAULT_MIN_AGE,
                Builder.DEFAULT_MAX_AGE,
                Builder.DEFAULT_MIN_SPACING,
                Builder.DEFAULT_REQUIREMENTS);
    }





    private void assertVaxType(VaxType vaxType,
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
