package seedu.modtrek.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_GRADE_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_CS1101S;
import static seedu.modtrek.testutil.Assert.assertThrows;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.exceptions.DuplicateModuleException;
import seedu.modtrek.testutil.ModuleBuilder;

public class DegreeProgressionTest {

    private final DegreeProgression degreeProgression = new DegreeProgression();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), degreeProgression.getModuleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> degreeProgression.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyDegreeProgression_replacesData() {
        DegreeProgression newData = getTypicalDegreeProgression();
        degreeProgression.resetData(newData);
        assertEquals(newData, degreeProgression);
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two modules with the same identity fields
        Module editedAlice = new ModuleBuilder(CS1101S).withGrade(VALID_GRADE_MA2002).withTags(VALID_TAG_CS1101S)
                .build();
        List<Module> newModules = Arrays.asList(CS1101S, editedAlice);
        DegreeProgressionStub newData = new DegreeProgressionStub(newModules);

        assertThrows(DuplicateModuleException.class, () -> degreeProgression.resetData(newData));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> degreeProgression.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInDegreeProgression_returnsFalse() {
        assertFalse(degreeProgression.hasModule(CS1101S));
    }

    @Test
    public void hasModule_moduleInDegreeProgression_returnsTrue() {
        degreeProgression.addModule(CS1101S);
        assertTrue(degreeProgression.hasModule(CS1101S));
    }

    @Test
    public void hasModule_moduleWithSameIdentityFieldsInDegreeProgression_returnsTrue() {
        degreeProgression.addModule(CS1101S);
        Module editedAlice = new ModuleBuilder(CS1101S).withGrade(VALID_GRADE_MA2002).withTags(VALID_TAG_CS1101S)
                .build();
        assertTrue(degreeProgression.hasModule(editedAlice));
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> degreeProgression.getModuleList().remove(0));
    }

    /**
     * A stub ReadOnlyDegreeProgression whose modules list can violate interface constraints.
     */
    private static class DegreeProgressionStub implements ReadOnlyDegreeProgression {
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        DegreeProgressionStub(Collection<Module> modules) {
            this.modules.setAll(modules);
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public DegreeProgressionData getProgressionData() {
            return null;
        }
    }

}
