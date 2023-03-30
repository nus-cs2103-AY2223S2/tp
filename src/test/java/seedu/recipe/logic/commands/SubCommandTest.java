package seedu.recipe.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.recipe.model.Model;
import seedu.recipe.model.ModelManager;
import seedu.recipe.model.UserPrefs;
import seedu.recipe.model.recipe.ingredient.Ingredient;

import static seedu.recipe.commons.core.Messages.MESSAGE_NO_STORED_SUBS;
import static seedu.recipe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.recipe.testutil.TypicalRecipes.getTypicalRecipeBook;

public class SubCommandTest {

    private Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());

    @Test
    public void execute_validIngredient_noSubsFound() {
        String expectedMessage = MESSAGE_NO_STORED_SUBS;
        Ingredient ingredient = Ingredient.of("Fish");
        SubCommand command = new SubCommand(ingredient);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandFailure(command, model, expectedCommandResult);
    }
}
