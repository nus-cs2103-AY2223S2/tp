package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.address.testutil.TypicalDoctors.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListDoctorCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListDoctorCommand(), model, ListDoctorCommand.getMessageSuccess(), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListDoctorCommand(), model, ListDoctorCommand.getMessageSuccess(), expectedModel);
    }

    @Test
    public void execute_getCommandUsageSuccessful() {
        String messageUsage = ListDoctorCommand.COMMAND_WORD + ": Lists all doctors in Docedex.\n"
                + "Example: " + ListDoctorCommand.COMMAND_WORD;
        assertEquals(messageUsage, ListDoctorCommand.getCommandUsage());
    }

    @Test
    public void execute_getMessageSuccessSuccessful() {
        String messageSuccess = "Listed all doctors";
        assertEquals(messageSuccess, ListDoctorCommand.getMessageSuccess());
    }
}
