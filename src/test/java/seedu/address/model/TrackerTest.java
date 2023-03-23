package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalModules;

public class TrackerTest {

    private final Tracker tracker = new Tracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tracker.getModuleList());
    }

    @Test
    public void clear_nonEmptyModuleList_emptyModuleList() {
        Tracker tracker = TypicalModules.getTypicalTracker();
        tracker.clear();

        assertTrue(tracker.getModuleList().size() == 0);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTracker_replacesData() {
        Tracker newData = TypicalModules.getTypicalTracker();
        tracker.resetData(newData);
        assertEquals(newData, tracker);
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicaateModuleException() {
        Module originalModule = TypicalModules.getCs2040s();
        Module editedModule = new ModuleBuilder(originalModule).withTags("Easy").build();
        List<Module> modules = Arrays.asList(originalModule, editedModule);
        ReadOnlyTracker newData = new TrackerStub(modules);

        assertThrows(DuplicateModuleException.class, () -> tracker.resetData(newData));
    }

    @Test
    public void hasModuleReadOnlyModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tracker.hasModule((ReadOnlyModule) null));
    }

    @Test
    public void hasModuleModuleCode_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tracker.hasModule((ModuleCode) null));
    }

    @Test
    public void hasModule_moduleNotInTracker_returnsFalse() {
        assertFalse(tracker.hasModule(TypicalModules.getCs2040s()));
    }

    @Test
    public void hasModule_moduleWithSameCodeInTracker_returnsTrue() {
        Module originalModule = TypicalModules.getCs2040s();
        Module editedModule = new ModuleBuilder(originalModule)
                .withName("Algorithms and Data Structures").build();

        tracker.addModule(originalModule);

        assertTrue(tracker.hasModule(editedModule));
    }

    @Test
    public void removeModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tracker.removeModule(null));
    }

    @Test
    public void removeModule_moduleNotInTracker_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> tracker.removeModule(TypicalModules.getCs2040s()));
    }

    @Test
    public void removeModule_moduleInTracker_trackerDoesNotHaveModule() {
        Module module = TypicalModules.getCs2040s();

        tracker.addModule(module);
        tracker.removeModule(module);

        assertFalse(tracker.hasModule(module));
    }

    @Test
    public void addModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tracker.addModule(null));
    }

    @Test
    public void addModule_moduleNotInTracker_trackerHasModule() {
        Module module = TypicalModules.getCs2040s();

        tracker.addModule(module);

        assertTrue(tracker.hasModule(module));
    }

    @Test
    public void addModule_moduleInTracker_throwsDuplicateModuleException() {
        Module module = TypicalModules.getCs2040s();

        tracker.addModule(module);

        assertThrows(DuplicateModuleException.class, () -> tracker.addModule(module));
    }

    @Test
    public void setModule_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tracker.setModule(null, TypicalModules.getCs2040s()));
    }

    @Test
    public void setModule_nullEditedModule_throwsNullPointerException() {
        Module module = TypicalModules.getCs2040s();

        tracker.addModule(module);

        assertThrows(NullPointerException.class, () -> tracker.setModule(module, null));
    }

    @Test
    public void setModule_targetNotInTracker_throwsModuleNotFoundException() {
        Module module = TypicalModules.getCs2040s();

        assertThrows(ModuleNotFoundException.class, () -> tracker.setModule(module, module));
    }

    @Test
    public void setModule_editedModuleIsDuplicate_throwsDuplicateModuleException() {
        Module originalModule = TypicalModules.getCs2040s();
        Module editedModule = TypicalModules.getSt2334();

        tracker.addModule(originalModule);
        tracker.addModule(editedModule);

        assertThrows(DuplicateModuleException.class, () -> tracker.setModule(originalModule, editedModule));
    }

    @Test
    public void setModule_editedModuleIsTheSame_moduleNoChange() {
        Module module = TypicalModules.getCs2040s();

        tracker.addModule(module);
        tracker.setModule(module, module);

        assertTrue(tracker.hasModule(module));
    }

    @Test
    public void setModule_editedModuleHasNoChange_moduleNoChange() {
        Module module = TypicalModules.getCs2040s();
        Module moduleCopy = new ModuleBuilder(module).build();

        tracker.addModule(module);
        tracker.setModule(module, moduleCopy);

        assertTrue(tracker.hasModule(module));
    }

    @Test
    public void setModule_editedModuleHasSameCode_moduleReplaced() {
        Module originalModule = TypicalModules.getCs2040s();
        Module editedCs2040s = new ModuleBuilder(originalModule)
                .withName("Algorithms and Data Structures").build();

        tracker.addModule(originalModule);
        tracker.setModule(originalModule, editedCs2040s);

        assertTrue(tracker.getModule(originalModule.getCode()).equals(editedCs2040s));
    }

    @Test
    public void setModule_validTargetAndEditedModule_moduleReplaced() {
        Module originalModule = TypicalModules.getCs2040s();
        Module editedModule = TypicalModules.getSt2334();

        tracker.addModule(originalModule);
        tracker.setModule(originalModule, editedModule);

        assertFalse(tracker.hasModule(originalModule));
        assertTrue(tracker.hasModule(editedModule));
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tracker.getModuleList().remove(0));
    }

    @Test
    public void getModule_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tracker.getModule(null));
    }

    @Test
    public void getModule_noModuleWithCode_returnsNull() {
        assertEquals(tracker.getModule(TypicalModules.getCs2040s().getCode()), null);
    }

    @Test
    public void getModule_hasModuleWithCode_returnsModule() {
        Module module = TypicalModules.getCs2040s();

        tracker.addModule(module);

        assertEquals(tracker.getModule(module.getCode()), module);
    }

    @Test
    public void equals() {
        Tracker tracker = TypicalModules.getTypicalTracker();

        // same values -> returns true
        Tracker trackerCopy = TypicalModules.getTypicalTracker();

        assertTrue(tracker.equals(trackerCopy));

        // same object -> returns true
        assertTrue(tracker.equals(tracker));

        // null -> returns false
        assertFalse(tracker.equals(null));

        // different type -> returns false
        assertFalse(tracker.equals(5));

        //diferent module -> returns false
        assertFalse(tracker.equals(new Tracker()));
    }

    /**
     * A stub ReadOnlyTracker whose module list can violate interface constraints.
     */
    private static class TrackerStub implements ReadOnlyTracker {
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        TrackerStub(Collection<Module> modules) {
            this.modules.setAll(modules);
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ReadOnlyModule getModule(ModuleCode code) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(ReadOnlyModule module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(ModuleCode moduleCode) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
