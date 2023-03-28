package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.order.Order;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListOrderCommand.
 */
public class ListOrderCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        ListOrderCommand listSortCreatedDateCommand = new ListOrderCommand(Order.SORT_CREATED_DATE);
        ListOrderCommand listSortStatusCommand = new ListOrderCommand(Order.SORT_STATUS);

        // same object -> returns true
        assertTrue(listSortCreatedDateCommand.equals(listSortCreatedDateCommand));

        // same comparator -> returns true
        ListOrderCommand listSortCreatedDateCommandCopy = new ListOrderCommand(Order.SORT_CREATED_DATE);
        assertTrue(listSortCreatedDateCommand.equals(listSortCreatedDateCommandCopy));

        // different types -> returns false
        assertFalse(listSortCreatedDateCommand.equals(1));

        // null -> returns false
        assertFalse(listSortCreatedDateCommand.equals(null));

        // different comparator -> returns false
        assertFalse(listSortCreatedDateCommand.equals(listSortStatusCommand));
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListOrderCommand(null), model, ListOrderCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showOrderAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListOrderCommand(null), model, ListOrderCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
