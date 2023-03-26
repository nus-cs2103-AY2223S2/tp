package seedu.recipe.logic.commands;

import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.ui.CommandBox.CommandExecutor;
import seedu.recipe.ui.RecipeForm;

/**
 * Opens a new RecipeForm instance.
 */
public class AddFormCommand extends Command {

    public static final String COMMAND_WORD = "addf";
    public static final String MESSAGE_SUCCESS = "Opened a new RecipeForm instance.";
    private final CommandExecutor commandExecutor;

    public AddFormCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Override
    public CommandResult execute(Model model) {
        // Implement the logic for opening a new RecipeForm instance here
        RecipeForm recipeForm = new RecipeForm(null, -1,  commandExecutor);
        recipeForm.display();

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
