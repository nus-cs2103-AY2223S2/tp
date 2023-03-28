package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.logic.parser.AddCommandParser;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.logic.util.RecipeDescriptor;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.ui.AddRecipeForm;


/**
 * Opens a new RecipeForm instance.
 */
public class AddFormCommand extends Command {

    public static final String COMMAND_WORD = "addf";
    public static final String MESSAGE_SUCCESS = "Successfully added recipe ";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the recipe book";

    public AddFormCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder stringBuilder = new StringBuilder();
        AddRecipeForm recipeForm = new AddRecipeForm(stringBuilder);
        recipeForm.display();
        String commandString = stringBuilder.toString();
        try {
            RecipeDescriptor toAdd = AddCommandParser.parseToAddCommand(commandString);
            Recipe recipeToAdd = toAdd.toRecipe();
            if (model.hasRecipe(recipeToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
            }
            model.addRecipe(recipeToAdd);
            return new CommandResult(MESSAGE_SUCCESS + recipeToAdd.getName() + " .");
        } catch (ParseException e) {
            return null;
        }
    }
}
