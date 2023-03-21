package seedu.recipe.testutil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.recipe.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Title;

/**
 * A utility class to help with building EditRecipeDescriptor objects.
 */
public class EditRecipeDescriptorBuilder {

    private EditRecipeDescriptor descriptor;

    public EditRecipeDescriptorBuilder() {
        descriptor = new EditRecipeDescriptor();
    }

    public EditRecipeDescriptorBuilder(EditRecipeDescriptor descriptor) {
        this.descriptor = new EditRecipeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRecipeDescriptor} with fields containing {@code recipe}'s details
     */
    public EditRecipeDescriptorBuilder(Recipe recipe) {
        descriptor = new EditRecipeDescriptor();
        descriptor.setTitle(recipe.getTitle());
        descriptor.setDesc(recipe.getDesc());
        descriptor.setIngredients(recipe.getIngredients());
        descriptor.setSteps(recipe.getSteps());
    }

    /**
     * Sets the {@code Title} of the {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withDesc(String desc) {
        descriptor.setDesc(new Description(desc));
        return this;
    }

    /**
     * Parses the {@code ingredients} into a {@code Set<Ingredient>} and set it to the {@code EditRecipeDescriptor}
     * that we are building.
     */
    public EditRecipeDescriptorBuilder withIngredients(String... ingredients) {
        Set<Ingredient> ingredientSet = Stream.of(ingredients).map(Ingredient::new).collect(Collectors.toSet());
        descriptor.setIngredients(ingredientSet);
        return this;
    }

    /**
     * Parses the {@code steps} into a {@code List<Step>} and set it to the {@code EditRecipeDescriptor}
     * that we are building.
     */
    public EditRecipeDescriptorBuilder withSteps(String... steps) {
        List<Step> stepList = Stream.of(steps).map(Step::new).collect(Collectors.toList());
        descriptor.setSteps(stepList);
        return this;
    }

    public EditRecipeDescriptor build() {
        return descriptor;
    }
}
