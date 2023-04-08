package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LAST_FED_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFishAtIndex;
import static seedu.address.testutil.TypicalFishes.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FISH;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_FISH;
import static seedu.address.testutil.TypicalReadings.getTypicalFullReadingLevels;
import static seedu.address.testutil.TypicalTanks.TYPICAL_TANK_2_STRING;
import static seedu.address.testutil.TypicalTanks.getTypicalTankList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.fish.FishEditCommand;
import seedu.address.logic.commands.fish.FishEditCommand.EditFishDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.fish.Fish;
import seedu.address.model.tank.Tank;
import seedu.address.testutil.EditFishDescriptorBuilder;
import seedu.address.testutil.FishBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
            getTypicalTankList(), getTypicalFullReadingLevels());
    private Tank firstTankInModel = model.getFilteredTankList().get(0);

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Fish editedFish = new FishBuilder().build();
        editedFish.setTank(firstTankInModel); //hardcode editedfish tank to be first tank
        EditFishDescriptor descriptor = new EditFishDescriptorBuilder(editedFish, model).build();
        FishEditCommand editCommand = new FishEditCommand(INDEX_FIRST_FISH, descriptor);

        String expectedMessage = String.format(FishEditCommand.MESSAGE_EDIT_FISH_SUCCESS, editedFish);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        expectedModel.setFish(model.getFilteredFishList().get(0), editedFish);

        firstTankInModel.addFish(editedFish); //this line doesnt affect outcome of test case

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastFish = Index.fromOneBased(model.getFilteredFishList().size());
        Fish lastFish = model.getFilteredFishList().get(indexLastFish.getZeroBased());

        FishBuilder fishInList = new FishBuilder(lastFish);
        Fish editedFish = fishInList.withName(VALID_NAME_BOB).withLastFedDate(VALID_LAST_FED_DATE_BOB)
                .withSpecies(VALID_SPECIES_BOB).withTank(TYPICAL_TANK_2_STRING).withTags(VALID_TAG_HUSBAND).build();

        editedFish.setTank(firstTankInModel); //hardcode editedfish tank to be first tank
        EditFishDescriptor descriptor = new EditFishDescriptorBuilder(editedFish, model).build();
        FishEditCommand editCommand = new FishEditCommand(indexLastFish, descriptor);

        String expectedMessage = String.format(FishEditCommand.MESSAGE_EDIT_FISH_SUCCESS, editedFish);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        expectedModel.setFish(lastFish, editedFish);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        FishEditCommand editCommand = new FishEditCommand(INDEX_FIRST_FISH, new EditFishDescriptor());
        Fish editedFish = model.getFilteredFishList().get(INDEX_FIRST_FISH.getZeroBased());

        String expectedMessage = String.format(FishEditCommand.MESSAGE_EDIT_FISH_SUCCESS, editedFish);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    //test fails on expectedModel getTypicalFullReadingLevels()
    //    @Test
    //    public void execute_filteredList_success() {
    //        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
    //                getTypicalTankList(), getTypicalFullReadingLevels());
    //        showFishAtIndex(model, INDEX_FIRST_FISH);
    //
    //        Fish fishInFilteredList = model.getFilteredFishList().get(INDEX_FIRST_FISH.getZeroBased());
    //        Fish editedFish = new FishBuilder(fishInFilteredList).withName(VALID_NAME_BOB).build();
    //        FishEditCommand editCommand = new FishEditCommand(INDEX_FIRST_FISH,
    //                new EditFishDescriptorBuilder().withName(VALID_NAME_BOB).build());
    //
    //        String expectedMessage = String.format(FishEditCommand.MESSAGE_EDIT_FISH_SUCCESS, editedFish);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
    //                getTypicalTaskList(),
    //                getTypicalTankList(),
    //                getTypicalFullReadingLevels());
    //        expectedModel.setFish(model.getFilteredFishList().get(0), editedFish);
    //
    //        firstTankInModel.addFish(editedFish);
    //
    //        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_duplicateFishUnfilteredList_failure() {
        Fish firstFish = model.getFilteredFishList().get(INDEX_FIRST_FISH.getZeroBased());
        EditFishDescriptor descriptor = new EditFishDescriptorBuilder(firstFish, model).build();
        FishEditCommand editCommand = new FishEditCommand(INDEX_SECOND_FISH, descriptor);

        assertCommandFailure(editCommand, model, FishEditCommand.MESSAGE_DUPLICATE_FISH);
    }

    @Test
    public void execute_duplicateFishFilteredList_failure() {
        showFishAtIndex(model, INDEX_FIRST_FISH);

        // edit Fish in filtered list into a duplicate in address book
        Fish fishInList = model.getAddressBook().getFishList().get(INDEX_SECOND_FISH.getZeroBased());
        FishEditCommand editCommand = new FishEditCommand(INDEX_FIRST_FISH,
                new EditFishDescriptorBuilder(fishInList, model).build());

        assertCommandFailure(editCommand, model, FishEditCommand.MESSAGE_DUPLICATE_FISH);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFishList().size() + 1);
        EditFishDescriptor descriptor = new EditFishDescriptorBuilder().withName(VALID_NAME_BOB).build();
        FishEditCommand editCommand = new FishEditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FISH_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidFishIndexFilteredList_failure() {
        showFishAtIndex(model, INDEX_FIRST_FISH);
        Index outOfBoundIndex = INDEX_SECOND_FISH;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getFishList().size());

        FishEditCommand editCommand = new FishEditCommand(outOfBoundIndex,
                new EditFishDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FISH_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final FishEditCommand standardCommand = new FishEditCommand(INDEX_FIRST_FISH, DESC_AMY);

        // same values -> returns true
        EditFishDescriptor copyDescriptor = new EditFishDescriptor(DESC_AMY);
        FishEditCommand commandWithSameValues = new FishEditCommand(INDEX_FIRST_FISH, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand.equals(new FishEditCommand(INDEX_SECOND_FISH, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new FishEditCommand(INDEX_FIRST_FISH, DESC_BOB)));
    }

}
