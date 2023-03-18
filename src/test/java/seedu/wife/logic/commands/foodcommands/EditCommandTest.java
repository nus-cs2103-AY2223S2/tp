package seedu.wife.logic.commands.foodcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.logic.commands.CommandTestUtil.DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_NAME_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.wife.logic.commands.CommandTestUtil.showFoodAtIndex;
import static seedu.wife.testutil.TypicalFood.getTypicalWife;
import static seedu.wife.testutil.TypicalIndexes.INDEX_FIRST_FOOD;
import static seedu.wife.testutil.TypicalIndexes.INDEX_SECOND_FOOD;

import org.junit.jupiter.api.Test;

import seedu.wife.commons.core.Messages;
import seedu.wife.commons.core.index.Index;
import seedu.wife.logic.commands.foodcommands.EditCommand.EditFoodDescriptor;
import seedu.wife.logic.commands.generalcommands.ClearCommand;
import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;
import seedu.wife.model.Wife;
import seedu.wife.model.food.Food;
import seedu.wife.testutil.EditFoodDescriptorBuilder;
import seedu.wife.testutil.FoodBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalWife(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Food editedFood = new FoodBuilder().build();
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder(editedFood).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FOOD, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new Wife(model.getWife()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), editedFood);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    /*
    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastFood = Index.fromOneBased(model.getFilteredFoodList().size());
        Food lastFood = model.getFilteredFoodList().get(indexLastFood.getZeroBased());

        FoodBuilder FoodInList = new FoodBuilder(lastFood);
        Food editedFood = FoodInList.withName(VALID_NAME_CHOCOLATE).withUnit(VALID_UNIT_CHOCOLATE)
                .withTags(VALID_TAG_CHOCOLATE).build();

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_NAME_CHOCOLATE)
                .withUnit(VALID_UNIT_CHOCOLATE).withTags(VALID_TAG_CHOCOLATE).build();
        EditCommand editCommand = new EditCommand(indexLastFood, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new Wife(model.getWife()), new UserPrefs());
        expectedModel.setFood(lastFood, editedFood);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
    */

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FOOD, new EditFoodDescriptor());
        Food editedFood = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new Wife(model.getWife()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);

        Food foodInFilteredList = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());
        Food editedFood = new FoodBuilder(foodInFilteredList).withName(VALID_NAME_CHOCOLATE).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FOOD,
                new EditFoodDescriptorBuilder().withName(VALID_NAME_CHOCOLATE).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FOOD_SUCCESS, editedFood);

        Model expectedModel = new ModelManager(new Wife(model.getWife()), new UserPrefs());
        expectedModel.setFood(model.getFilteredFoodList().get(0), editedFood);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateFoodUnfilteredList_failure() {
        Food firstFood = model.getFilteredFoodList().get(INDEX_FIRST_FOOD.getZeroBased());
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder(firstFood).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_FOOD, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FOOD);
    }

    @Test
    public void execute_duplicateFoodFilteredList_failure() {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);

        // edit Food in filtered list into a duplicate in address book
        Food foodInList = model.getWife().getFoodList().get(INDEX_SECOND_FOOD.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FOOD,
                new EditFoodDescriptorBuilder(foodInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FOOD);
    }

    @Test
    public void execute_invalidFoodIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFoodList().size() + 1);
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_NAME_CHOCOLATE).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidFoodIndexFilteredList_failure() {
        showFoodAtIndex(model, INDEX_FIRST_FOOD);
        Index outOfBoundIndex = INDEX_SECOND_FOOD;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWife().getFoodList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditFoodDescriptorBuilder().withName(VALID_NAME_CHOCOLATE).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_FOOD, DESC_MEIJI);

        // same values -> returns true
        EditFoodDescriptor copyDescriptor = new EditFoodDescriptor(DESC_MEIJI);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_FOOD, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_FOOD, DESC_MEIJI)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_FOOD, DESC_CHOCOLATE)));
    }

}
