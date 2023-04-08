package seedu.wife.logic.commands.foodcommands;

import static seedu.wife.logic.commands.CommandTestUtil.INVALID_QUANTITY_DECREASE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_QUANTITY_DECREASE;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.wife.testutil.TypicalWife.getTypicalWife;

import org.junit.jupiter.api.Test;

import seedu.wife.commons.core.index.Index;
import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;
import seedu.wife.model.Wife;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.Quantity;
import seedu.wife.testutil.DecreaseFoodDescriptorBuilder;
import seedu.wife.testutil.FoodBuilder;

public class DecreaseCommandTest {
    private Model model = new ModelManager(getTypicalWife(), new UserPrefs());
    @Test
    public void execute_quantitySpecifiedUnfilteredList_success() {
        Index indexLastFood = Index.fromOneBased(model.getFilteredFoodList().size());
        Food lastFood = model.getFilteredFoodList().get(indexLastFood.getZeroBased());

        FoodBuilder foodInList = new FoodBuilder(lastFood);
        Integer newQuantity = lastFood.getQuantity().getValue() - Integer.parseInt(VALID_QUANTITY_DECREASE);
        String newQuantityString = newQuantity.toString();
        Food decreasedFood = foodInList.withQuantity(newQuantityString).build();

        DecreaseCommand.DecreaseFoodDescriptor descriptor = new DecreaseFoodDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_DECREASE)
                .build();
        DecreaseCommand decreaseCommand = new DecreaseCommand(indexLastFood, descriptor);

        String expectedMessage = String.format(DecreaseCommand.MESSAGE_DECREASE_FOOD_SUCCESS, decreasedFood) + " by "
                + descriptor.getQuantity();
        Model expectedModel = new ModelManager(new Wife(model.getWife()), new UserPrefs());
        expectedModel.setFood(lastFood, decreasedFood);

        assertCommandSuccess(decreaseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_quantityUnspecifiedUnfilteredList_success() {
        Index indexLastFood = Index.fromOneBased(model.getFilteredFoodList().size());
        Food lastFood = model.getFilteredFoodList().get(indexLastFood.getZeroBased());

        FoodBuilder foodInList = new FoodBuilder(lastFood);
        Integer newQuantity = lastFood.getQuantity().getValue() - 1;
        String newQuantityString = newQuantity.toString();
        Food decreasedFood = foodInList.withQuantity(newQuantityString).build();

        Model expectedModel = new ModelManager(new Wife(model.getWife()), new UserPrefs());
        expectedModel.setFood(lastFood, decreasedFood);
        DecreaseCommand.DecreaseFoodDescriptor descriptor = new DecreaseCommand.DecreaseFoodDescriptor();
        DecreaseCommand decreaseCommand = new DecreaseCommand(indexLastFood, descriptor);
        String expectedMessage = String.format(DecreaseCommand.MESSAGE_DECREASE_FOOD_SUCCESS, decreasedFood) + " by "
                + descriptor.getQuantity();
        assertCommandSuccess(decreaseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_quantityExcessUnfilteredList_failure() {
        Index indexLastFood = Index.fromOneBased(model.getFilteredFoodList().size());
        Food lastFood = model.getFilteredFoodList().get(indexLastFood.getZeroBased());

        FoodBuilder foodInList = new FoodBuilder(lastFood);
        DecreaseCommand.DecreaseFoodDescriptor descriptor = new DecreaseFoodDescriptorBuilder()
                .withQuantity(INVALID_QUANTITY_DECREASE)
                .build();
        DecreaseCommand decreaseCommand = new DecreaseCommand(indexLastFood, descriptor);
        String failureMessage = Quantity.DECREASE_CONSTRAINTS;
        assertCommandFailure(decreaseCommand, model, failureMessage);
    }

}
