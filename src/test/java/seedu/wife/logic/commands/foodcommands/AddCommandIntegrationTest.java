package seedu.wife.logic.commands.foodcommands;

import static seedu.wife.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.wife.testutil.TypicalFood.getTypicalWife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;
import seedu.wife.model.food.Food;
import seedu.wife.testutil.FoodBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWife(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Food validFood = new FoodBuilder().build();

        Model expectedModel = new ModelManager(model.getWife(), new UserPrefs());
        expectedModel.addFood(validFood);

        assertCommandSuccess(new AddCommand(validFood), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validFood), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Food foodInList = model.getWife().getFoodList().get(0);
        assertCommandFailure(new AddCommand(foodInList), model, AddCommand.MESSAGE_DUPLICATE_FOOD);
    }

}
