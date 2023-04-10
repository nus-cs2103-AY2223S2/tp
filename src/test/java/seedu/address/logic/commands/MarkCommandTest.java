package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPetAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static seedu.address.testutil.TypicalPets.getTypicalArchive;
import static seedu.address.testutil.TypicalPets.getTypicalPetPal;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkCommand}.
 */
public class MarkCommandTest {
    private final Model model = new ModelManager(getTypicalPetPal(), getTypicalArchive(), new UserPrefs());

    //@Test
    //public void execute_validIndexUnfilteredList_success() {
    //    Pet petToMark = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
    //    MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PET);

    //    String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PET_SUCCESS, petToMark);

    //    ModelManager expectedModel = new ModelManager(model.getPetPal(), model.getPetPalArchive(), new UserPrefs());
    //    expectedModel.markPet(petToMark);

    //    assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    //}

    @Test
    public void execute_validIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    //@Test
    //public void execute_invalidIndexFilteredList_success() {
    //    showPetAtIndex(model, INDEX_FIRST_PET);
    //
    //    Pet petToMark = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
    //    MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PET);

    //    String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PET_SUCCESS, petToMark);
    //   Model expectedModel = new ModelManager(model.getPetPal(), model.getPetPalArchive(), new UserPrefs());

    //    expectedModel.markPet(petToMark);

    //   assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    //}

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Index outOfBoundIndex = INDEX_SECOND_PET;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPetPal().getPetList().size());

        MarkCommand markCommand = new MarkCommand(outOfBoundIndex);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkCommand markFirstCommand = new MarkCommand(INDEX_FIRST_PET);
        MarkCommand markSecondCommand = new MarkCommand(INDEX_SECOND_PET);

        // same object -> returns true
        assertEquals(markFirstCommand, markFirstCommand);

        // same values -> returns true
        MarkCommand markFirstCommandCopy = new MarkCommand(INDEX_FIRST_PET);
        assertEquals(markFirstCommand, markFirstCommandCopy);


        // different types -> returns false
        assertNotEquals(1, markFirstCommand);

        // null -> returns false
        assertNotEquals(null, markFirstCommand);

        // different PET -> returns false
        assertNotEquals(markFirstCommand, markSecondCommand);
    }
}
