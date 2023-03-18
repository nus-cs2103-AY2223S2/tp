package seedu.recipe.logic.commands;

import seedu.recipe.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.model.Model.PREDICATE_SHOW_ALL_RECIPE;

/**
 * Lists all persons in the recipe book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all recipes";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPE);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
