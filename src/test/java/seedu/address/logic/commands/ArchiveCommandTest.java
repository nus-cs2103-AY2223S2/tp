package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPetAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PET;
import static seedu.address.testutil.TypicalPets.getTypicalArchive;
import static seedu.address.testutil.TypicalPets.getTypicalPetPal;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Pet;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ArchiveCommand}.
 */
public class ArchiveCommandTest {
    private Model model = new ModelManager(getTypicalPetPal(), getTypicalArchive(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Pet petToArchive = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        ArchiveCommand archiveCommand = new ArchiveCommand(INDEX_FIRST_PET);

        String expectedMessage = String.format(ArchiveCommand.MESSAGE_ARCHIVE_PET_SUCCESS, petToArchive);

        ModelManager expectedModel = new ModelManager(model.getPetPal(), model.getPetPalArchive(), new UserPrefs());
        expectedModel.archivePet(petToArchive);

        assertCommandSuccess(archiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        ArchiveCommand archiveCommand = new ArchiveCommand(outOfBoundIndex);

        assertCommandFailure(archiveCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_success() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Pet petToArchive = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        ArchiveCommand archiveCommand = new ArchiveCommand(INDEX_FIRST_PET);

        String expectedMessage = String.format(ArchiveCommand.MESSAGE_ARCHIVE_PET_SUCCESS, petToArchive);
        Model expectedModel = new ModelManager(model.getPetPal(), model.getPetPalArchive(), new UserPrefs());

        expectedModel.archivePet(petToArchive);

        assertCommandSuccess(archiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Index outOfBoundIndex = INDEX_SECOND_PET;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPetPal().getPetList().size());

        ArchiveCommand archiveCommand = new ArchiveCommand(outOfBoundIndex);

        assertCommandFailure(archiveCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ArchiveCommand archiveFirstCommand = new ArchiveCommand(INDEX_FIRST_PET);
        ArchiveCommand archiveSecondCommand = new ArchiveCommand(INDEX_THIRD_PET);

        // same object -> returns true
        assertEquals(archiveFirstCommand, archiveSecondCommand);

        // same values -> returns true
        ArchiveCommand archiveFirstCommandCopy = new ArchiveCommand(INDEX_FIRST_PET);
        assertEquals(archiveFirstCommand, archiveFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, archiveFirstCommand);

        // null -> returns false
        assertNotEquals(null, archiveFirstCommand);

        // different PET -> returns false
        assertNotEquals(archiveFirstCommand, archiveSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPet(Model model) {
        model.updateFilteredPetList(p -> false);

        assertTrue(model.getFilteredPetList().isEmpty());
    }
}
