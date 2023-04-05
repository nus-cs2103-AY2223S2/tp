package seedu.address.logic.commands;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_SORTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        SortCommand firstSortCommand = new SortCommand("name");
        SortCommand secondSortCommand = new SortCommand("gender");

        // same object -> returns true
        assertTrue(firstSortCommand.equals(firstSortCommand));

        // same values -> returns true
        SortCommand firstSortCommandCopy = new SortCommand("name");
        assertTrue(firstSortCommand.equals(firstSortCommandCopy));

        // different types -> returns false
        assertFalse(firstSortCommand.equals(1));

        // null -> returns false
        assertFalse(firstSortCommand.equals(null));

        // different person -> returns false
        assertFalse(firstSortCommand.equals(secondSortCommand));
    }

    @Test
    public void execute_invalidSortAttribute() {
        String expectedMessage = "Attribute does not exist!";
        SortCommand command = new SortCommand("surname");
        assertEquals(command.execute(model), new CommandResult(expectedMessage));
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_correctSortAttribute_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_SORTED_OVERVIEW, "name");
        SortCommand command = new SortCommand("name");
        expectedModel.sortPersonList("name");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
