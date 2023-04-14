package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2106_TUT;
import static seedu.address.testutil.TypicalModules.getTypicalModuleTracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.testutil.ModuleBuilder;

public class ModuleTrackerTest {

    private final ModuleTracker moduleTracker = new ModuleTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), moduleTracker.getModuleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyModuleTracker_replacesData() {
        ModuleTracker newData = getTypicalModuleTracker();
        moduleTracker.resetData(newData);
        assertEquals(newData, moduleTracker);
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two modules with the same identity fields
        Module editedAlice = new ModuleBuilder(CS2106_TUT).build();
        List<Module> newModules = Arrays.asList(CS2106_TUT, editedAlice);
        ModuleTrackerStub newData = new ModuleTrackerStub(newModules);

        assertThrows(DuplicateModuleException.class, () -> moduleTracker.resetData(newData));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTracker.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInModuleTracker_returnsFalse() {
        assertFalse(moduleTracker.hasModule(CS2106_TUT));
    }

    @Test
    public void hasModule_moduleInModuleTracker_returnsTrue() {
        moduleTracker.addModule(CS2106_TUT);
        assertTrue(moduleTracker.hasModule(CS2106_TUT));
    }

    @Test
    public void hasModule_moduleWithSameIdentityFieldsInModuleTracker_returnsTrue() {
        moduleTracker.addModule(CS2106_TUT);
        Module editedAlice = new ModuleBuilder(CS2106_TUT).build();
        assertTrue(moduleTracker.hasModule(editedAlice));
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> moduleTracker.getModuleList().remove(0));
    }

    /**
     * A stub ReadOnlyModuleTracker whose modules list can violate interface constraints.
     */
    private static class ModuleTrackerStub implements ReadOnlyModuleTracker {
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        ModuleTrackerStub(Collection<Module> modules) {
            this.modules.setAll(modules);
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }
    }

}
