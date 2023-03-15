package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CODE_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CREDIT_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_GRADE_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_SEMYEAR_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_CS1101S;
import static seedu.modtrek.testutil.Assert.assertThrows;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.MA2002;

import org.junit.jupiter.api.Test;

import seedu.modtrek.testutil.ModuleBuilder;

public class ModuleTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Module person = new ModuleBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(CS1101S.isSameModule(CS1101S));

        // null -> returns false
        assertFalse(CS1101S.isSameModule(null));

        // same name, all other attributes different -> returns true
        Module editedCS1101s = new ModuleBuilder(CS1101S).withCredit(VALID_CREDIT_MA2002)
                .withSemYear(VALID_SEMYEAR_MA2002)
                .withGrade(VALID_GRADE_MA2002).withTags(VALID_TAG_CS1101S).build();
        assertTrue(CS1101S.isSameModule(editedCS1101s));

        // different name, all other attributes same -> returns false
        editedCS1101s = new ModuleBuilder(CS1101S).withCode(VALID_CODE_MA2002).build();
        assertFalse(CS1101S.isSameModule(editedCS1101s));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module aliceCopy = new ModuleBuilder(CS1101S).build();
        assertTrue(CS1101S.equals(aliceCopy));

        // same object -> returns true
        assertTrue(CS1101S.equals(CS1101S));

        // null -> returns false
        assertFalse(CS1101S.equals(null));

        // different type -> returns false
        assertFalse(CS1101S.equals(5));

        // different person -> returns false
        assertFalse(CS1101S.equals(MA2002));

        // different name -> returns false
        Module editedCS1101s = new ModuleBuilder(CS1101S).withCode(VALID_CODE_MA2002).build();
        assertFalse(CS1101S.equals(editedCS1101s));
    }

}
