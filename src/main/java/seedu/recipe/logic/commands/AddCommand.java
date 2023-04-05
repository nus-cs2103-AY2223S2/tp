package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Title;

/**
 * Adds a recipe to the recipe book.
 */
public class AddCommand extends Command {



    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a recipe to your recipe book. "
            + "\nFormat: " + PREFIX_TITLE + "TITLE " + PREFIX_DESCRIPTION + "DESCRIPTION " + PREFIX_INGREDIENT
            + "INGREDIENT... " + PREFIX_STEP + "STEP... [" + PREFIX_TAG + "TAG]...\n"
            + "Example: add t/Orange juice d/Yummy i/Orange, 1, piece, 0.50 s/Juice the orange";

    public static final String MESSAGE_SUCCESS = "New recipe added: %1$s";

    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in recipe book.";

    public static final String NO_TITLE_FAILURE = "A title needs to be included when adding a recipe. \n"
            + Title.MESSAGE_CONSTRAINTS + "\n"
            + "Use the prefix `" + PREFIX_TITLE + "`.";

    public static final String NO_DESC_FAILURE = "A description needs to be included when adding a recipe.\n"
            + Description.MESSAGE_CONSTRAINTS + "\n"
            + "Use the prefix `" + PREFIX_DESCRIPTION + "`.";

    public static final String NO_INGREDIENT_FAILURE = "An ingredient needs to be included when adding a "
            + "recipe.\n"
            + "Use the prefix `" + PREFIX_INGREDIENT + "`.\n"
            + Ingredient.INGREDIENT_FORMAT;

    public static final String NO_STEP_FAILURE = "A step needs to be included when adding a recipe.\n"
            + Step.MESSAGE_CONSTRAINTS + "\n"
            + "Use the prefix `" + PREFIX_STEP + "`.";

    private final Recipe toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Recipe}
     */
    public AddCommand(Recipe recipe) {
        requireNonNull(recipe);
        toAdd = recipe;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasRecipe(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.addRecipe(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getTitle()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
