package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.awt.GraphicsEnvironment;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CopyCommand.CopyInformationSelector;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.CopyInformationSelectorBuilder;

public class CopyCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        CopyInformationSelector informationSelector = new CopyInformationSelectorBuilder().selectAll().build();
        CopyCommand copyCommand = new CopyCommand(INDEX_FIRST_PERSON, informationSelector);

        if (GraphicsEnvironment.isHeadless()) {
            Person personToCopy = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
            String expectedMessage = CopyCommand.MESSAGE_NO_CLIPBOARD_FOUND
                    + copyCommand.getInformation(personToCopy, informationSelector);
            assertCommandSuccess(copyCommand, model, expectedMessage, expectedModel);
        } else {
            assertCommandSuccess(copyCommand, model, CopyCommand.MESSAGE_COPY_SUCCESS, expectedModel);
        }
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        CopyInformationSelector informationSelector = new CopyInformationSelectorBuilder().selectAddress()
                .selectRank().selectPlatoon().build();
        CopyCommand copyCommand = new CopyCommand(indexLastPerson, informationSelector);

        if (GraphicsEnvironment.isHeadless()) {
            Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
            String expectedMessage = CopyCommand.MESSAGE_NO_CLIPBOARD_FOUND
                    + copyCommand.getInformation(lastPerson, informationSelector);
            assertCommandSuccess(copyCommand, model, expectedMessage, expectedModel);
        } else {
            assertCommandSuccess(copyCommand, model, CopyCommand.MESSAGE_COPY_SUCCESS, expectedModel);
        }
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CopyInformationSelector informationSelector = new CopyInformationSelectorBuilder().build();
        CopyCommand copyCommand = new CopyCommand(outOfBoundIndex, informationSelector);
        assertCommandFailure(copyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        CopyCommand copyCommand = new CopyCommand(outOfBoundIndex, new CopyInformationSelectorBuilder().build());

        assertCommandFailure(copyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CopyInformationSelector informationSelector = new CopyInformationSelectorBuilder().build();
        CopyCommand copyFirstCommand = new CopyCommand(INDEX_FIRST_PERSON, informationSelector);
        CopyCommand copySecondCommand = new CopyCommand(INDEX_SECOND_PERSON, informationSelector);

        // same object -> returns true
        assertTrue(copyFirstCommand.equals(copyFirstCommand));

        // same values -> returns true
        CopyCommand copyFirstCommandCopy = new CopyCommand(INDEX_FIRST_PERSON, informationSelector);
        assertTrue(copyFirstCommand.equals(copyFirstCommandCopy));

        // different types -> returns false
        assertFalse(copyFirstCommand.equals(1));

        // null -> returns false
        assertFalse(copyFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(copyFirstCommand.equals(copySecondCommand));
    }
}
