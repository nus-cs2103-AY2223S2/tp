package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_PORTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.logic.util.RecipeDescriptor;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Recipe;

/**
 * Adds a recipe to the recipe book via a text command.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a recipe to the recipe book. "
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + "[" + PREFIX_DURATION + "DURATION] "
        + "[" + PREFIX_PORTION + "PORTION] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "[" + PREFIX_INGREDIENT + "INGREDIENT]...\n"
        + "[" + PREFIX_STEP + "STEP]...\n"

        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "Cacio e Pepe Pasta "
        + PREFIX_PORTION + "1 - 2 portions "
        + PREFIX_DURATION + "15 minutes "
        + PREFIX_TAG + "Italian "
        + PREFIX_INGREDIENT + "2 eggs "
        + PREFIX_INGREDIENT + "parmesan cheese "
        + PREFIX_INGREDIENT + "125g spaghetti noodles "
        + PREFIX_STEP + "Heat a pot with water until it boils "
        + PREFIX_STEP
        + "Place the spaghetti in the pot and leave for 5 minutes or until al "
        + "dente"
        + PREFIX_STEP + "In a bowl, combine the parmesan and the egg yolks";

    public static final String MESSAGE_SUCCESS = "New recipe added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the recipe book";

    private final RecipeDescriptor toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Recipe}
     */
    public AddCommand(RecipeDescriptor recipe) {
        requireNonNull(recipe);
        toAdd = recipe;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Recipe recipeToAdd = toAdd.toRecipe();

        if (model.hasRecipe(recipeToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.addRecipe(recipeToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, recipeToAdd.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
