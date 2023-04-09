package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_NO_FACULTY;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_SUCCESS;
import static seedu.address.storage.ChsContacts.CHS_CONTACTS;
import static seedu.address.storage.SocContacts.SOC_CONTACTS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ImportCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void executeWrongInput_failure() {
        ImportCommand importCommand = new ImportCommand("abcde");
        String expectedMessage = String.format(MESSAGE_NO_FACULTY);
        assertCommandFailure(importCommand, model, expectedMessage);
    }

    @Test
    public void executeSOCInput_success() {
        ImportCommand importCommand = new ImportCommand("soc");
        String expectedMessage = String.format(MESSAGE_SUCCESS) + "\nBen Leong\nSteven Halim";
        for (Person person : SOC_CONTACTS) {
            expectedModel.addPerson(person);
        }
        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeCHSInput_success() {
        ImportCommand importCommand = new ImportCommand("chs");
        String expectedMessage = String.format(MESSAGE_SUCCESS) + "\nChng Shu Sin\nChew Fook Tim";
        for (Person person : CHS_CONTACTS) {
            expectedModel.addPerson(person);
        }
        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ImportCommand importCommandSOC = new ImportCommand("soc");
        ImportCommand importCommandCHS = new ImportCommand("chs");
        AddCommand addCommand = new AddCommand(SOC_CONTACTS.get(0));

        // same object -> returns true
        assertTrue(importCommandSOC.equals(importCommandSOC));
        assertTrue(importCommandCHS.equals(importCommandCHS));

        // different person -> returns false
        assertFalse(importCommandCHS.equals(addCommand));
    }
}
