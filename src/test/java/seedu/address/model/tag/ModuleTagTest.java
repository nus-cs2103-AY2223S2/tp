package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.timetable.util.TypicalLesson.CS1010J_WED_2PM_2HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2103T_THU_1PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2103T_WED_2PM_1HR;
import static seedu.address.model.timetable.util.TypicalLesson.CS2105_THU_4PM_2HR;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ModuleTagTest {
    private static final String MODULE_TAG_STRING = "CS2103T";
    private static final ModuleTag MODULE_TAG = new ModuleTag(MODULE_TAG_STRING);

    /*
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleTag(null));
    }
     */

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleTag(invalidTagName));
    }

    @Test
    public void isValidTagName_validTagName_true() {
        assertTrue(ModuleTag.isValidTagName("CS2101")); // standard module code
        assertTrue(ModuleTag.isValidTagName("CS2103T")); // module code with suffix
        assertTrue(ModuleTag.isValidTagName("DSA1101")); // long module code
        assertTrue(ModuleTag.isValidTagName("ACC2101GTX")); // module code with long prefix and suffix
    }

    @Test
    public void isValidTagName_invalidTagName_false() {
        assertFalse(ModuleTag.isValidTagName("CS50")); // short module number
        assertFalse(ModuleTag.isValidTagName("C2100")); // short module prefix
        assertFalse(ModuleTag.isValidTagName("DSAIS2100")); // long module prefix
        assertFalse(ModuleTag.isValidTagName("DSA2100GTFO")); // long module suffix
        assertFalse(ModuleTag.isValidTagName("CS 2100")); // space in between
        assertFalse(ModuleTag.isValidTagName("CS2103/T")); // non-alphanumeric character
        assertFalse(ModuleTag.isValidTagName("23CSIT")); // numbers and letters are swapped
        assertFalse(ModuleTag.isValidTagName("2100CS")); // wrong order of module details
    }

    @Test
    public void isValidTagName_validTagValidTarget_true() {
        assertTrue(MODULE_TAG.isValidTagName("CS2103T", "CS2100"));
    }

    @Test
    public void equals_sameObjectBasic_true() {
        assertEquals(MODULE_TAG, MODULE_TAG);
    }

    @Test
    public void equals_sameObjectNonBasic_true() {
        ModuleTag nonBasicModuleTag =
                new ModuleTag("CS2103T", CS2103T_WED_2PM_1HR, CS2103T_THU_1PM_1HR);

        assertEquals(nonBasicModuleTag, nonBasicModuleTag);
    }

    @Test
    public void equals_sameValuesBasic_true() {
        ModuleTag otherModuleTag = new ModuleTag("CS2103T");
        assertEquals(MODULE_TAG, otherModuleTag);
    }

    @Test
    public void equals_sameValuesNonBasic_true() {
        ModuleTag nonBasicModuleTag =
                new ModuleTag("CS2103T", CS2103T_WED_2PM_1HR, CS2103T_THU_1PM_1HR);

        ModuleTag otherNonBasicModuleTag =
                new ModuleTag("CS2103T", CS2103T_WED_2PM_1HR, CS2103T_THU_1PM_1HR);

        assertEquals(nonBasicModuleTag, otherNonBasicModuleTag);
    }

    @Test
    public void equals_oneBasicOneNonBasic_false() {
        ModuleTag nonBasicModuleTag =
                new ModuleTag("CS2103T", CS2103T_WED_2PM_1HR, CS2103T_THU_1PM_1HR);

        assertNotEquals(nonBasicModuleTag, MODULE_TAG);
    }

    @Test
    public void hashCode_sameTag_success() {
        assertEquals(new ModuleTag(MODULE_TAG_STRING).hashCode(), MODULE_TAG.hashCode());
    }

    @Test
    public void toString_validTag_success() {
        assertTrue(MODULE_TAG.toString().contains(String.format("%s", MODULE_TAG_STRING)));
    }

    @Test
    public void addLesson_null_throwsNullPointerException() {
        ModuleTag moduleTag = new ModuleTag(MODULE_TAG_STRING);
        assertThrows(NullPointerException.class, ()
                -> moduleTag.addLesson(null));
    }

    @Test
    public void addLessons_null_throwsNullPointerException() {
        ModuleTag moduleTag = new ModuleTag(MODULE_TAG_STRING);
        assertThrows(NullPointerException.class, ()
                -> moduleTag.addLessons(null));
    }

    @Test
    public void addLesson_validLesson_success() {
        ModuleTag moduleTag = new ModuleTag(MODULE_TAG_STRING);
        moduleTag.addLesson(CS1010J_WED_2PM_2HR);

        assertTrue(moduleTag.getImmutableLessons().contains(CS1010J_WED_2PM_2HR));
    }

    @Test
    public void addLessons_validLessons_success() {
        ModuleTag moduleTag = new ModuleTag(MODULE_TAG_STRING);
        moduleTag.addLessons(List.of(CS2105_THU_4PM_2HR, CS1010J_WED_2PM_2HR));

        assertTrue(moduleTag.containsLessons(List.of(CS2105_THU_4PM_2HR)));
        assertTrue(moduleTag.containsLessons(List.of(CS1010J_WED_2PM_2HR)));
    }

    @Test
    public void copy_validValues_success() {
        ModuleTag moduleTag = new ModuleTag(MODULE_TAG_STRING);
        moduleTag.addLesson(CS2105_THU_4PM_2HR);

        assertTrue(moduleTag.containsLessons(List.of(CS2105_THU_4PM_2HR)));
        assertFalse(moduleTag.containsLessons(List.of(CS1010J_WED_2PM_2HR)));

        ModuleTag copiedModuleTag = moduleTag.copy();
        copiedModuleTag.addLesson(CS1010J_WED_2PM_2HR);

        assertTrue(copiedModuleTag.containsLessons(
                List.of(CS2105_THU_4PM_2HR, CS1010J_WED_2PM_2HR)));
        assertFalse(moduleTag.containsLessons(
                List.of(CS2105_THU_4PM_2HR, CS1010J_WED_2PM_2HR)));
        assertTrue(moduleTag.containsLessons(List.of(CS2105_THU_4PM_2HR)));
        assertFalse(moduleTag.containsLessons(List.of(CS1010J_WED_2PM_2HR)));
    }
}
