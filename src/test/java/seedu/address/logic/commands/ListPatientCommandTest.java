package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsOnlyAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListPatientCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPatientsOnlyAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPatientCommand(), model, ListPatientCommand.getMessageSuccess(), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListPatientCommand(), model, ListPatientCommand.getMessageSuccess(), expectedModel);
    }

    @Test
    public void execute_getCommandUsageSuccessful() {
        String messageUsage = ListPatientCommand.COMMAND_WORD + ": Lists all patients in Docedex.\n"
                + "Example: " + ListPatientCommand.COMMAND_WORD;

        assertEquals(messageUsage, ListPatientCommand.getCommandUsage());
    }

    @Test
    public void execute_getMessageSuccessSuccessful() {
        String messageSuccess = "Listed all patients";

        assertEquals(messageSuccess, ListPatientCommand.getMessageSuccess());
    }
}
