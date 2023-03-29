package seedu.wife.logic.commands.foodcommands;

import static seedu.wife.logic.commands.CommandTestUtil.VALID_QUANTITY_INCREASE;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.wife.testutil.TypicalWife.getTypicalWife;

import org.junit.jupiter.api.Test;

import seedu.wife.commons.core.index.Index;
import seedu.wife.logic.commands.foodcommands.IncreaseCommand.IncreaseFoodDescriptor;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.logic.parser.foodcommandparser.IncreaseCommandParser;
import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;
import seedu.wife.model.Wife;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.Quantity;
import seedu.wife.testutil.FoodBuilder;
import seedu.wife.testutil.IncreaseFoodDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code IncreaseCommand}.
 */
public class IncreaseCommandTest {

    private Model model = new ModelManager(getTypicalWife(), new UserPrefs());

    @Test
    public void execute_quantitySpecifiedUnfilteredList_success() {
        Index indexLastFood = Index.fromOneBased(model.getFilteredFoodList().size());
        Food lastFood = model.getFilteredFoodList().get(indexLastFood.getZeroBased());

        FoodBuilder foodInList = new FoodBuilder(lastFood);
        Integer newQuantity = lastFood.getQuantity().getValue() + Integer.parseInt(VALID_QUANTITY_INCREASE);
        String newQuantityString = newQuantity.toString();
        Food increasedFood = foodInList.withQuantity(newQuantityString).build();

        IncreaseFoodDescriptor descriptor = new IncreaseFoodDescriptorBuilder().withQuantity(VALID_QUANTITY_INCREASE)
                .build();
        IncreaseCommand increaseCommand = new IncreaseCommand(indexLastFood, descriptor);

        String expectedMessage = String.format(IncreaseCommand.MESSAGE_INCREASE_FOOD_SUCCESS, increasedFood) + " by "
                + descriptor.getQuantity();
        Model expectedModel = new ModelManager(new Wife(model.getWife()), new UserPrefs());
        expectedModel.setFood(lastFood, increasedFood);

        assertCommandSuccess(increaseCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_quantityUnspecifiedUnfilteredList_success() {
        Index indexLastFood = Index.fromOneBased(model.getFilteredFoodList().size());
        Food lastFood = model.getFilteredFoodList().get(indexLastFood.getZeroBased());

        FoodBuilder foodInList = new FoodBuilder(lastFood);
        Integer newQuantity = lastFood.getQuantity().getValue() + 1;
        String newQuantityString = newQuantity.toString();
        Food increasedFood = foodInList.withQuantity(newQuantityString).build();

        Model expectedModel = new ModelManager(new Wife(model.getWife()), new UserPrefs());
        expectedModel.setFood(lastFood, increasedFood);
        IncreaseCommandParser increaseCommandParser = new IncreaseCommandParser();

        try {
            IncreaseCommand increaseCommand = increaseCommandParser.parse(" 2");
            IncreaseFoodDescriptor descriptor = increaseCommand.increaseFoodDescriptor;
            String expectedMessage = String.format(IncreaseCommand.MESSAGE_INCREASE_FOOD_SUCCESS, increasedFood)
                    + " by "
                    + descriptor.getQuantity();
            assertCommandSuccess(increaseCommand, model, expectedMessage, expectedModel);
        } catch (ParseException ignored) {
            assert false;
        }
    }

    @Test
    public void execute_negativeQuantitySpecifiedUnfilteredList_failure() {
        String failureMessage = Quantity.MESSAGE_CONSTRAINTS;
        IncreaseCommandParser increaseCommandParser = new IncreaseCommandParser();
        try {
            IncreaseCommand increaseCommand = increaseCommandParser.parse(" 2 q/0");
            assertCommandFailure(increaseCommand, model, failureMessage);
        } catch (ParseException ignored) {
            assert true;
        }

    }
}
