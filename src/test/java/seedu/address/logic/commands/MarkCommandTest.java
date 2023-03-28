package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Mark;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkCommand}.
 */
public class MarkCommandTest {

    @Test
    public void execute_validIndexUnfilteredList_mark_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, new Mark("YES"));

        String expectedMessage = MarkCommand.MESSAGE_MARK_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.markPerson(INDEX_FIRST_PERSON, new Mark("no"));
        expectedModel.markPerson(INDEX_FIRST_PERSON, new Mark("YES"));

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredList_unmark_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.markPerson(INDEX_FIRST_PERSON, new Mark("YES"));
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, new Mark("no"));

        String expectedMessage = MarkCommand.MESSAGE_UNMARK_SUCCESS;
        expectedModel.markPerson(INDEX_FIRST_PERSON, new Mark("no"));

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredList_mark_failure_alrMark() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, new Mark("YES"));

        String expectedMessage = MarkCommand.MESSAGE_PERSON_ALR_MARKED;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.markPerson(INDEX_FIRST_PERSON, new Mark("YES"));
        expectedModel.markPerson(INDEX_FIRST_PERSON, new Mark("YES"));

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexUnfilteredList_mark_failure_alrUNmark() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, new Mark("No"));

        String expectedMessage = MarkCommand.MESSAGE_PERSON_ALR_UNMARKED;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.markPerson(INDEX_FIRST_PERSON, new Mark("no"));
        expectedModel.markPerson(INDEX_FIRST_PERSON, new Mark("no"));

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
