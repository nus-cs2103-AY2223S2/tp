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
import seedu.address.model.tank.TankName;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;
import seedu.address.testutil.EditFishDescriptorBuilder;
import seedu.address.testutil.FishBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model;

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(),
                getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        Fish editedFish = new FishBuilder().build();
        String expectedMessage = String.format(FishEditCommand.MESSAGE_EDIT_FISH_SUCCESS, editedFish);
        //In edit command, a new fish with new tank is created. Since edited fish also belongs in tank index 1,
        // hard coded 1 here
        editedFish.setTank(new Tank(new TankName("1"), new AddressBook(), new UniqueIndividualReadingLevels()));
        EditFishDescriptor descriptor = new EditFishDescriptorBuilder(editedFish).build();
        FishEditCommand editCommand = new FishEditCommand(INDEX_FIRST_FISH, descriptor);

        expectedModel.setFish(expectedModel.getFilteredFishList().get(0), editedFish);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(),
                getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        Index indexLastFish = Index.fromOneBased(expectedModel.getFilteredFishList().size());
        Fish lastFish = expectedModel.getFilteredFishList().get(indexLastFish.getZeroBased());

        FishBuilder fishInList = new FishBuilder(lastFish);
        Fish editedFish = fishInList.withName(VALID_NAME_BOB).withLastFedDate(VALID_LAST_FED_DATE_BOB)
                .withSpecies(VALID_SPECIES_BOB).withTank(TYPICAL_TANK_2_STRING).withTags(VALID_TAG_HUSBAND).build();

        String expectedMessage = String.format(FishEditCommand.MESSAGE_EDIT_FISH_SUCCESS, editedFish);
        //In edit command, a new fish with new tank is created. Since new fish is in tank 2, hard coded 2 here
        editedFish.setTank(new Tank(new TankName("2"), new AddressBook(), new UniqueIndividualReadingLevels()));
        EditFishDescriptor descriptor = new EditFishDescriptorBuilder(editedFish).build();
        FishEditCommand editCommand = new FishEditCommand(indexLastFish, descriptor);
        expectedModel.setFish(lastFish, editedFish);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(),
                getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        Fish editedFish = new FishBuilder(expectedModel.getFilteredFishList().get(INDEX_FIRST_FISH.getZeroBased()))
                .build();
        String expectedMessage = String.format(FishEditCommand.MESSAGE_EDIT_FISH_SUCCESS, editedFish);
        //In edit command, a new fish with new tank is created. Since edited fish also belongs in tank index 1,
        // hard coded 1 here
        editedFish.setTank(new Tank(new TankName("1"), new AddressBook(), new UniqueIndividualReadingLevels()));
        EditFishDescriptor descriptor = new EditFishDescriptorBuilder(editedFish).build();
        FishEditCommand editCommand = new FishEditCommand(INDEX_FIRST_FISH, descriptor);
        expectedModel.setFish(expectedModel.getFilteredFishList().get(INDEX_FIRST_FISH.getZeroBased()), editedFish);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(),
                getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());

        showFishAtIndex(model, INDEX_FIRST_FISH);
        Fish fishInFilteredList = expectedModel.getFilteredFishList().get(INDEX_FIRST_FISH.getZeroBased());
        Fish editedFish = new FishBuilder(fishInFilteredList).withName(VALID_NAME_BOB).build();
        String expectedMessage = String.format(FishEditCommand.MESSAGE_EDIT_FISH_SUCCESS, editedFish);
        //In edit command, a new fish with new tank is created. Since edited fish also belongs in tank index 1,
        // hard coded 1 here
        editedFish.setTank(new Tank(new TankName("1"), new AddressBook(), new UniqueIndividualReadingLevels()));
        FishEditCommand editCommand = new FishEditCommand(INDEX_FIRST_FISH,
                new EditFishDescriptorBuilder(editedFish).build());
        expectedModel.setFish(expectedModel.getFilteredFishList().get(0), editedFish);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateFishUnfilteredList_failure() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        Fish firstFish = model.getFilteredFishList().get(INDEX_FIRST_FISH.getZeroBased());
        firstFish.setTank(new Tank(new TankName("1"), new AddressBook(), new UniqueIndividualReadingLevels()));
        EditFishDescriptor descriptor = new EditFishDescriptorBuilder(firstFish).build();
        FishEditCommand editCommand = new FishEditCommand(INDEX_SECOND_FISH, descriptor);

        assertCommandFailure(editCommand, model, FishEditCommand.MESSAGE_DUPLICATE_FISH);
    }

    @Test
    public void execute_duplicateFishFilteredList_failure() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        showFishAtIndex(model, INDEX_FIRST_FISH);

        // edit fish in filtered list into a duplicate in address book
        Fish fishInList = model.getAddressBook().getFishList().get(INDEX_SECOND_FISH.getZeroBased());
        // user inputs tank attribute will be a number for edit commands
        fishInList.setTank(new Tank(new TankName("1"), new AddressBook(), new UniqueIndividualReadingLevels()));
        FishEditCommand editCommand = new FishEditCommand(INDEX_FIRST_FISH,
                new EditFishDescriptorBuilder(fishInList).build());

        assertCommandFailure(editCommand, model, FishEditCommand.MESSAGE_DUPLICATE_FISH);
    }

    @Test
    public void execute_invalidFishIndexUnfilteredList_failure() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFishList().size() + 1);
        //The fish parameter of edit commands have tank attribute as an index.
        EditFishDescriptor descriptor = new EditFishDescriptorBuilder().withName(VALID_NAME_BOB).withTank("1").build();
        FishEditCommand editCommand = new FishEditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FISH_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidFishIndexFilteredList_failure() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        showFishAtIndex(model, INDEX_FIRST_FISH);
        Index outOfBoundIndex = INDEX_SECOND_FISH;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getFishList().size());

        //The fish parameter of edit commands have tank attribute as an index.
        FishEditCommand editCommand = new FishEditCommand(outOfBoundIndex,
                new EditFishDescriptorBuilder().withName(VALID_NAME_BOB).withTank("1").build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FISH_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
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
