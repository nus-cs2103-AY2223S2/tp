package seedu.sudohr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.*;
import static seedu.sudohr.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalPersons.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.GuiSettings;
import seedu.sudohr.model.employee.NameContainsKeywordsPredicate;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.testutil.AddressBookBuilder;
import seedu.sudohr.testutil.PersonBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new SudoHr(), new SudoHr(modelManager.getSudoHr()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setSudoHrFilePath(Paths.get("sudohr/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setSudoHrFilePath(Paths.get("new/sudohr/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSudoHrFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("sudohr/book/file/path");
        modelManager.setSudoHrFilePath(path);
        assertEquals(path, modelManager.getSudoHrFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEmployee(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasEmployee(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addEmployee(ALICE);
        assertTrue(modelManager.hasEmployee(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdInAddressBook_returnsTrue() {
        modelManager.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertTrue(modelManager.hasEmployee(editedAlice));
    }

    @Test
    public void hasClashingEmail_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClashingEmail(null));
    }

    @Test
    public void hasClashingEmail_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasClashingEmail(ALICE));
    }

    @Test
    public void hasClashingEmail_personWithSameIdInAddressBook_returnsFalse() {
        modelManager.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertFalse(modelManager.hasClashingEmail(editedAlice));
    }

    @Test
    public void hasClashingEmail_personWithDifferentIdInAddressBook_returnsTrue() {
        modelManager.addEmployee(ALICE);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertTrue(modelManager.hasClashingEmail(editedAlice));
    }

    @Test
    public void hasClashingEmail_personWithSameEmailOnlyInAddressBook_returnsTrue() {
        modelManager.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .build();
        assertTrue(modelManager.hasClashingEmail(editedAlice));
    }

    @Test
    public void hasClashingPhoneNumber_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClashingPhoneNumber(null));
    }

    @Test
    public void hasClashingPhoneNumber_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasClashingPhoneNumber(ALICE));
    }

    @Test
    public void hasClashingPhoneNumber_personWithSameIdInAddressBook_returnsFalse() {
        modelManager.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertFalse(modelManager.hasClashingPhoneNumber(editedAlice));
    }

    @Test
    public void hasClashingPhoneNumber_personWithDifferentIdInAddressBook_returnsTrue() {
        modelManager.addEmployee(ALICE);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertTrue(modelManager.hasClashingPhoneNumber(editedAlice));
    }

    @Test
    public void hasClashingPhoneNumber_personWithSamePhoneNumberOnlyInAddressBook_returnsTrue() {
        modelManager.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .build();
        assertTrue(modelManager.hasClashingPhoneNumber(editedAlice));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEmployeeList().remove(0));
    }

    @Test
    public void equals() {
        SudoHr sudoHr = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        SudoHr differentSudoHr = new SudoHr();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(sudoHr, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(sudoHr, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different sudoHr -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentSudoHr, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredEmployeeList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(sudoHr, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setSudoHrFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(sudoHr, differentUserPrefs)));
    }
}
