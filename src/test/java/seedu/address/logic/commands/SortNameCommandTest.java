package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class SortNameCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }



    @Test
    public void execute_listIsSortedName() {

        expectedModel.sortPersonList("name");
        assertCommandSuccess(new SortNameCommand(), model, SortNameCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsSortedNameNotOther() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        new SortNameCommand().execute(model);
        assertFalse(expectedModel.getFilteredPersonList() == model.getFilteredPersonList());
    }
}
