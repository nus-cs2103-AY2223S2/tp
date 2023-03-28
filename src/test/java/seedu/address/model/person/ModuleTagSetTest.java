package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_2;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.ModuleTag;
import seedu.address.testutil.TypicalUser;

public class ModuleTagSetTest {
    private static final ModuleTag MODULE_TAG_1 = new ModuleTag(VALID_MODULE_1);
    private static final ModuleTag MODULE_TAG_2 = new ModuleTag(VALID_MODULE_2);

    @Test
    public void add() {
        ModuleTagSet moduleTagSet = new ModuleTagSet();
        assertEquals(0, moduleTagSet.size());

        moduleTagSet.add(MODULE_TAG_1);
        assertEquals(1, moduleTagSet.size());
    }

    @Test
    public void remove() {
        ModuleTagSet moduleTagSet = new ModuleTagSet();
        moduleTagSet.add(MODULE_TAG_1);
        assertEquals(1, moduleTagSet.size());

        moduleTagSet.remove(MODULE_TAG_1);
        assertEquals(0, moduleTagSet.size());
    }

    @Test
    public void toString_validModuleTagSet_success() {
        ModuleTagSet moduleTagSet = new ModuleTagSet();
        moduleTagSet.add(MODULE_TAG_1);
        moduleTagSet.add(MODULE_TAG_2);

        assertTrue(moduleTagSet.toString().contains(MODULE_TAG_1.toString()));
        assertTrue(moduleTagSet.toString().contains(MODULE_TAG_2.toString()));
    }

    @Test
    public void getImmutableModules_tryEdit_throwsUnsupportedOperationException() {
        ModuleTagSet moduleTagSet = new ModuleTagSet();
        Set<ModuleTag> immutableSet = moduleTagSet.getImmutableModules();

        assertThrows(UnsupportedOperationException.class, () -> immutableSet.add(MODULE_TAG_1));
    }

    @Test
    public void getImmutableCommonModules_tryEdit_throwsUnsupportedOperationException() {
        ModuleTagSet moduleTagSet = new ModuleTagSet();
        Set<ModuleTag> immutableSet = moduleTagSet.getImmutableCommonModules();

        assertThrows(UnsupportedOperationException.class, () -> immutableSet.add(MODULE_TAG_1));
    }

    @Test
    public void compareTo() {
        ModuleTagSet moduleTagSet = new ModuleTagSet();
        moduleTagSet.add(MODULE_TAG_2); // GEA1000 not in LINUS
        moduleTagSet.setCommonModules(TypicalUser.LINUS.getImmutableModuleTags());

        ModuleTagSet commonModuleTagSet = new ModuleTagSet();
        commonModuleTagSet.add(MODULE_TAG_1); // CS2100 is in LINUS
        commonModuleTagSet.setCommonModules(TypicalUser.LINUS.getImmutableModuleTags());

        assertEquals(-1, moduleTagSet.compareTo(commonModuleTagSet));
        assertEquals(1, commonModuleTagSet.compareTo(moduleTagSet));

        assertEquals(0, moduleTagSet.getNumberOfCommonModules());
        assertEquals(1, commonModuleTagSet.getNumberOfCommonModules());

        assertEquals(0, moduleTagSet.getImmutableCommonModules().size());
    }
}
