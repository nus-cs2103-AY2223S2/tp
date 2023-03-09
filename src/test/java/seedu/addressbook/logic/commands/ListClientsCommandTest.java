package seedu.addressbook.logic.commands;

import static seedu.addressbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.addressbook.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.addressbook.testutil.TypicalClients.getTypicalFitBook;
import static seedu.addressbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.addressbook.model.FitBookModel;
import seedu.addressbook.model.FitBookModelManager;
import seedu.addressbook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the FitBookModel) and unit tests for ListCommand.
 */
public class ListClientsCommandTest {

    private FitBookModel model;
    private FitBookModel expectedFitBookModel;

    @BeforeEach
    public void setUp() {
        model = new FitBookModelManager(getTypicalFitBook(), new UserPrefs());
        expectedFitBookModel = new FitBookModelManager(model.getFitBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListClientsCommand(), model, ListClientsCommand.MESSAGE_SUCCESS, expectedFitBookModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showClientAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListClientsCommand(), model, ListClientsCommand.MESSAGE_SUCCESS, expectedFitBookModel);
    }
}
