package seedu.sudohr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.sudohr.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalPersons.ALICE;
import static seedu.sudohr.testutil.TypicalPersons.AMY;
import static seedu.sudohr.testutil.TypicalPersons.BENSON;
import static seedu.sudohr.testutil.TypicalPersons.BOB;
import static seedu.sudohr.testutil.TypicalPersons.CARL;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.GuiSettings;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.NameContainsKeywordsPredicate;
import seedu.sudohr.model.employee.exceptions.DuplicateEmailException;
import seedu.sudohr.model.employee.exceptions.DuplicateEmployeeException;
import seedu.sudohr.model.employee.exceptions.DuplicatePhoneNumberException;
import seedu.sudohr.model.employee.exceptions.EmployeeNotFoundException;
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

    /** Tests adding of a person **/
    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addEmployee(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        modelManager.addEmployee(ALICE);
        assertThrows(DuplicateEmployeeException.class, () -> modelManager.addEmployee(ALICE));
    }

    @Test
    public void add_personWithSameId_throwsDuplicatePersonException() {
        modelManager.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertThrows(DuplicateEmployeeException.class, () -> modelManager.addEmployee(editedAlice));
    }

    @Test
    public void add_differentPersonWithSamePhoneNumber_throwsDuplicatePhoneNumberException() {
        modelManager.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertThrows(DuplicatePhoneNumberException.class, () -> modelManager.addEmployee(editedAlice));
    }

    @Test
    public void add_differentPersonWithSameEmail_throwsDuplicateEmailException() {
        modelManager.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertThrows(DuplicateEmailException.class, () -> modelManager.addEmployee(editedAlice));
    }

    // first error accounted is that of duplicate person
    @Test
    public void add_personWithSameIdEmailPhone_throwsDuplicatePersonException() {
        modelManager.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertThrows(DuplicateEmployeeException.class, () -> modelManager.addEmployee(editedAlice));
    }

    // first error accounted is that of duplicate phone
    @Test
    public void add_personWithSameEmailPhone_throwsDuplicatePhoneNumberException() {
        modelManager.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> modelManager.addEmployee(editedAlice));
    }


    /** Tests editing of a person **/
    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setEmployee(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setEmployee(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> modelManager.setEmployee(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        modelManager.addEmployee(ALICE);
        modelManager.setEmployee(ALICE, ALICE);
        ModelManager expectedModelManager = new ModelManager();
        expectedModelManager.addEmployee(ALICE);
        assertEquals(expectedModelManager, modelManager);
    }

    @Test
    public void setPerson_editedPersonAlreadyExists_throwsDuplicatePersonException() {
        modelManager.addEmployee(ALICE);
        modelManager.addEmployee(BOB);
        assertThrows(DuplicateEmployeeException.class, () -> modelManager.setEmployee(ALICE, BOB));
    }

    @Test
    public void setPerson_editedPersonChangeAllUnique_success() {
        modelManager.addEmployee(ALICE);
        modelManager.addEmployee(CARL);
        modelManager.setEmployee(ALICE, BOB);
        ModelManager expectedModelManager = new ModelManager();
        expectedModelManager.addEmployee(BOB);
        expectedModelManager.addEmployee(CARL);
        assertEquals(expectedModelManager, modelManager);
    }

    // edited employee kept same id (ie change every other field)
    @Test
    public void setPerson_editedPersonHasSameIdentityOnly_success() {
        modelManager.addEmployee(BOB);
        modelManager.addEmployee(CARL);
        Employee newBob = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        modelManager.setEmployee(BOB, newBob);

        ModelManager expectedModelManager = new ModelManager();
        expectedModelManager.addEmployee(newBob);
        expectedModelManager.addEmployee(CARL);
        assertEquals(expectedModelManager, modelManager);
    }

    // edited employee changed id only (ie no change to other fields)
    @Test
    public void setPerson_editedPersonChangeUniqueIdOnly_success() {
        modelManager.addEmployee(ALICE);
        modelManager.addEmployee(BOB);
        modelManager.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withId(VALID_ID_AMY)
                .build();
        modelManager.setEmployee(BOB, newBob);

        ModelManager expectedModelManager = new ModelManager();
        expectedModelManager.addEmployee(ALICE);
        expectedModelManager.addEmployee(newBob);
        expectedModelManager.addEmployee(CARL);

        assertEquals(expectedModelManager, modelManager);
    }

    // edited person actually made no change to its own id
    @Test
    public void setPerson_editedPersonNoChangeToId_success() {
        modelManager.addEmployee(BOB);
        modelManager.addEmployee(AMY);
        modelManager.addEmployee(ALICE);
        Employee newBob = new PersonBuilder(BOB).withId(VALID_ID_BOB)
                .build();
        modelManager.setEmployee(BOB, newBob);

        ModelManager expectedModelManager = new ModelManager();
        expectedModelManager.addEmployee(newBob);
        expectedModelManager.addEmployee(AMY);
        expectedModelManager.addEmployee(ALICE);

        assertEquals(expectedModelManager, modelManager);
    }

    // edited person changed to an id that already exists
    @Test
    public void setPerson_editedIdAlreadyExists_throwsDuplicatePersonException() {
        modelManager.addEmployee(ALICE);
        modelManager.addEmployee(BOB);
        Employee sameIdAsBob = new PersonBuilder().withId(VALID_ID_BOB)
                .build();
        assertThrows(DuplicateEmployeeException.class, () -> modelManager.setEmployee(ALICE, sameIdAsBob));
    }

    // TODO test with some fields changed, excluding id

    // TODO test with some fields changed, including id

    // edited person change to non-duplicated phone number
    @Test
    public void setPerson_editedPersonNewPhoneIsUnique_success() {
        modelManager.addEmployee(ALICE);
        modelManager.addEmployee(BOB);
        modelManager.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .build();
        modelManager.setEmployee(BOB, newBob);

        ModelManager expectedModelManager = new ModelManager();
        expectedModelManager.addEmployee(ALICE);
        expectedModelManager.addEmployee(newBob);
        expectedModelManager.addEmployee(CARL);

        assertEquals(expectedModelManager, modelManager);
    }

    // edited person made no change to phone number
    @Test
    public void setPerson_editedPersonPhoneNoChange_success() {
        modelManager.addEmployee(ALICE);
        modelManager.addEmployee(BOB);
        modelManager.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_BOB)
                .build();
        modelManager.setEmployee(BOB, newBob);

        ModelManager expectedModelManager = new ModelManager();
        expectedModelManager.addEmployee(ALICE);
        expectedModelManager.addEmployee(newBob);
        expectedModelManager.addEmployee(CARL);

        assertEquals(expectedModelManager, modelManager);
    }


    // edited person change to duplicated phone number shared with someone SudoHR
    @Test
    public void setPerson_editedPersonDuplicatedPhoneNumber_throwsDuplicatePhoneNumberException() {
        modelManager.addEmployee(AMY);
        modelManager.addEmployee(BOB);
        modelManager.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> modelManager.setEmployee(BOB, newBob));
    }

    // edited person changed to duplicated phone number and email shared with someone in SudoHR
    @Test
    public void setPerson_editedPersonChangeEmailPhone_throwsDuplicatePhoneNumberException() {
        modelManager.addEmployee(AMY);
        modelManager.addEmployee(BOB);
        modelManager.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> modelManager.setEmployee(BOB, newBob));
    }

    // TODO edited person changed some fields, including phone number


    // edited person change to non-duplicated email
    @Test
    public void setPerson_editedPersonNewEmailIsUnique_success() {
        modelManager.addEmployee(ALICE);
        modelManager.addEmployee(BOB);
        modelManager.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        modelManager.setEmployee(BOB, newBob);

        ModelManager expectedModelManager = new ModelManager();
        expectedModelManager.addEmployee(ALICE);
        expectedModelManager.addEmployee(newBob);
        expectedModelManager.addEmployee(CARL);

        assertEquals(expectedModelManager, modelManager);
    }

    // edited person made no change to email
    @Test
    public void setPerson_editedPersonEmailNoChange_success() {
        modelManager.addEmployee(ALICE);
        modelManager.addEmployee(BOB);
        modelManager.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        modelManager.setEmployee(BOB, newBob);

        ModelManager expectedModelManager = new ModelManager();
        expectedModelManager.addEmployee(ALICE);
        expectedModelManager.addEmployee(newBob);
        expectedModelManager.addEmployee(CARL);

        assertEquals(expectedModelManager, modelManager);
    }


    // edited person change to duplicated email as someone SudoHR
    @Test
    public void setPerson_editedPersonDuplicatedEmail_throwsDuplicateEmailException() {
        modelManager.addEmployee(AMY);
        modelManager.addEmployee(BOB);
        modelManager.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        assertThrows(DuplicateEmailException.class, () -> modelManager.setEmployee(BOB, newBob));
    }

    // TODO edited person changed some fields, including email

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
