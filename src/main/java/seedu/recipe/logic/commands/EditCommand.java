package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.recipe.model.Model.PREDICATE_SHOW_ALL_RECIPES;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.commons.core.index.Index;
import seedu.recipe.commons.util.CollectionUtil;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.*;

/**
 * Edits the details of an existing recipe in the recipe book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the recipe identified "
            + "by the index number used in the displayed recipe list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "EMAIL] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_INGREDIENT + "INGREDIENT] "
            + "[" + PREFIX_STEP + "STEP]...";
            /*
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "... "
            + PREFIX_INGREDIENT + "... "
            + PREFIX_STEP + "...";
             */

    public static final String MESSAGE_EDIT_RECIPE_SUCCESS = "Edited Recipe: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the recipe book.";

    private final Index index;
    private final EditRecipeDescriptor editRecipeDescriptor;

    /**
     * @param index of the recipe in the filtered recipe list to edit
     * @param editRecipeDescriptor details to edit the recipe with
     */
    public EditCommand(Index index, EditRecipeDescriptor editRecipeDescriptor) {
        requireNonNull(index);
        requireNonNull(editRecipeDescriptor);

        this.index = index;
        this.editRecipeDescriptor = new EditRecipeDescriptor(editRecipeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToEdit = lastShownList.get(index.getZeroBased());
        Recipe editedRecipe = createEditedRecipe(recipeToEdit, editRecipeDescriptor);

        if (!recipeToEdit.isSameRecipe(editedRecipe) && model.hasRecipe(editedRecipe)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.setRecipe(recipeToEdit, editedRecipe);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);
        return new CommandResult(String.format(MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe));
    }

    /**
     * Creates and returns a {@code Recipe} with the details of {@code recipeToEdit}
     * edited with {@code editRecipeDescriptor}.
     */
    private static Recipe createEditedRecipe(Recipe recipeToEdit, EditRecipeDescriptor editRecipeDescriptor) {
        assert recipeToEdit != null;

        Title updatedTitle = editRecipeDescriptor.getTitle().orElse(recipeToEdit.getTitle());
        Description updatedDesc = editRecipeDescriptor.getDesc().orElse(recipeToEdit.getDesc());
        Set<Ingredient> updatedIngredients = editRecipeDescriptor.getIngredients()
                .orElse(recipeToEdit.getIngredients());
        Set<Step> updatedSteps = editRecipeDescriptor.getSteps().orElse(recipeToEdit.getSteps());

        return new Recipe(updatedTitle, updatedDesc, updatedIngredients, updatedSteps);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editRecipeDescriptor.equals(e.editRecipeDescriptor);
    }

    /**
     * Stores the details to edit the recipe with. Each non-empty field value will replace the
     * corresponding field value of the recipe.
     */
    public static class EditRecipeDescriptor {
        private Title title;
        private Description desc;
        private Set<Ingredient> ingredients;
        private Set<Step> steps;

        public EditRecipeDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditRecipeDescriptor(EditRecipeDescriptor toCopy) {
            setTitle(toCopy.title);
            setDesc(toCopy.desc);
            setIngredients(toCopy.ingredients);
            setSteps(toCopy.steps);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, desc, ingredients, steps);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDesc(Description desc) {
            this.desc = desc;
        }

        public Optional<Description> getDesc() {
            return Optional.ofNullable(desc);
        }

        public void setIngredients(Set<Ingredient> ingredients) {
            this.ingredients = ingredients;
        }

        public Optional<Set<Ingredient>> getIngredients() {
            return (ingredients != null) ? Optional.of(Collections.unmodifiableSet(ingredients)) : Optional.empty();
        }

        public void setSteps(Set<Step> steps) {
            this.steps = steps;
        }

        public Optional<Set<Step>> getSteps() {
            return (steps != null) ? Optional.of(Collections.unmodifiableSet(steps)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditRecipeDescriptor)) {
                return false;
            }

            // state check
            EditRecipeDescriptor e = (EditRecipeDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDesc().equals(e.getDesc())
                    && getIngredients().equals(e.getIngredients())
                    && getSteps().equals(e.getSteps());
        }
    }
}
