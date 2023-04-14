package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class FavoriteCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person favoritedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        favoritedPerson.setIsFavorite(true);

        FavoriteCommand favoriteCommand = new FavoriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(FavoriteCommand.MESSAGE_FAVORITE_SUCCESS, favoritedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), favoritedPerson);

        assertCommandSuccess(favoriteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        FavoriteCommand favoriteCommand = new FavoriteCommand(outOfBoundIndex);

        assertCommandFailure(favoriteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person favoritedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        favoritedPerson.setIsFavorite(true);

        FavoriteCommand favoriteCommand = new FavoriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(FavoriteCommand.MESSAGE_FAVORITE_SUCCESS, favoritedPerson);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPerson(model.getFilteredPersonList().get(0), favoritedPerson);

        assertCommandSuccess(favoriteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        FavoriteCommand favoriteCommand = new FavoriteCommand(outOfBoundIndex);

        assertCommandFailure(favoriteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        FavoriteCommand favoriteFirstCommand = new FavoriteCommand(INDEX_FIRST_PERSON);
        FavoriteCommand favoriteSecondCommand = new FavoriteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(favoriteFirstCommand, favoriteFirstCommand);

        // same values -> returns true
        FavoriteCommand favoriteFirstCommandCopy = new FavoriteCommand(INDEX_FIRST_PERSON);
        assertEquals(favoriteFirstCommand, favoriteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, favoriteFirstCommand);

        // null -> returns false
        assertNotEquals(null, favoriteFirstCommand);

        // different person -> returns false
        assertNotEquals(favoriteFirstCommand, favoriteSecondCommand);
    }

}
