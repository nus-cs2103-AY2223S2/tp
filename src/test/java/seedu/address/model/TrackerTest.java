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
        Module editedCs2040s = new ModuleBuilder(TypicalModules.CS2040S).withTags("Easy").build();
        List<Module> modules = Arrays.asList(TypicalModules.CS2040S, editedCs2040s);
        ReadOnlyTracker newData = new TrackerStub(modules);

        assertThrows(DuplicateModuleException.class, () -> tracker.resetData(newData));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tracker.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInTracker_returnsFalse() {
        assertFalse(tracker.hasModule(TypicalModules.CS2040S));
    }

    @Test
    public void hasModule_moduleWithSameCodeInTracker_returnsTrue() {
        tracker.addModule(TypicalModules.CS2040S);
        Module editedCs2040s = new ModuleBuilder(TypicalModules.CS2040S)
                .withName("Algorithms & Data Structures").build();

        assertTrue(tracker.hasModule(editedCs2040s));
    }

    @Test
    public void removeModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tracker.removeModule(null));
    }

    @Test
    public void removeModule_moduleNotInTracker_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> tracker.removeModule(TypicalModules.CS2040S));
    }

    @Test
    public void removeModule_moduleInTracker_trackerDoesNotHaveModule() {
        tracker.addModule(TypicalModules.CS2040S);
        tracker.removeModule(TypicalModules.CS2040S);

        assertFalse(tracker.hasModule(TypicalModules.CS2040S));
    }

    @Test
    public void addModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tracker.addModule(null));
    }

    @Test
    public void addModule_moduleNotInTracker_trackerHasModule() {
        tracker.addModule(TypicalModules.CS2040S);

        assertTrue(tracker.hasModule(TypicalModules.CS2040S));
    }

    @Test
    public void addModule_moduleInTracker_throwsDuplicateModuleException() {
        tracker.addModule(TypicalModules.CS2040S);

        assertThrows(DuplicateModuleException.class, () -> tracker.addModule(TypicalModules.CS2040S));
    }

    @Test
    public void setModule_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tracker.setModule(null, TypicalModules.CS2040S));
    }

    @Test
    public void setModule_nullEditedModule_throwsNullPointerException() {
        tracker.addModule(TypicalModules.CS2040S);

        assertThrows(NullPointerException.class, () -> tracker.setModule(TypicalModules.CS2040S, null));
    }

    @Test
    public void setModule_targetNotInTracker_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () ->
                tracker.setModule(TypicalModules.CS2040S, TypicalModules.CS2040S));
    }

    @Test
    public void setModule_editedModuleIsDuplicate_throwsDuplicateModuleException() {
        tracker.addModule(TypicalModules.CS2040S);
        tracker.addModule(TypicalModules.ST2334);

        assertThrows(DuplicateModuleException.class, () ->
                tracker.setModule(TypicalModules.CS2040S, TypicalModules.ST2334));
    }

    @Test
    public void setModule_editedModuleHasNoChange_moduleNoChange() {
        tracker.addModule(TypicalModules.CS2040S);

        tracker.setModule(TypicalModules.CS2040S, TypicalModules.CS2040S);

        assertTrue(tracker.hasModule(TypicalModules.CS2040S));
    }

    @Test
    public void setModule_editedModuleHasSameCode_moduleReplaced() {
        tracker.addModule(TypicalModules.CS2040S);

        Module editedCs2040s = new ModuleBuilder(TypicalModules.CS2040S)
                .withName("Algorithms & Data Structures").build();


        tracker.setModule(TypicalModules.CS2040S, editedCs2040s);

        assertTrue(tracker.getModule(TypicalModules.CS2040S.getCode()).equals(editedCs2040s));
    }

    @Test
    public void setModule_validTargetAndEditedModule_moduleReplaced() {
        tracker.addModule(TypicalModules.CS2040S);

        tracker.setModule(TypicalModules.CS2040S, TypicalModules.ST2334);

        assertFalse(tracker.hasModule(TypicalModules.CS2040S));
        assertTrue(tracker.hasModule(TypicalModules.ST2334));
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
        assertEquals(tracker.getModule(TypicalModules.CS2040S.getCode()), null);
    }

    @Test
    public void getModule_hasModuleWithCode_returnsModule() {
        tracker.addModule(TypicalModules.CS2040S);

        assertEquals(tracker.getModule(TypicalModules.CS2040S.getCode()), TypicalModules.CS2040S);
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
    }
}
