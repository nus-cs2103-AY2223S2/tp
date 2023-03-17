package seedu.recipe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.logic.commands.CommandTestUtil.DESC_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_STEPS_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_INGREDIENTS_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_TITLE_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_DESC_SOUP;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.recipe.testutil.EditRecipeDescriptorBuilder;

public class EditRecipeDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditRecipeDescriptor descriptorWithSameValues = new EditRecipeDescriptor(DESC_CORNDOGS);
        assertTrue(DESC_CORNDOGS.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CORNDOGS.equals(DESC_CORNDOGS));

        // null -> returns false
        assertFalse(DESC_CORNDOGS.equals(null));

        // different types -> returns false
        assertFalse(DESC_CORNDOGS.equals(5));

        assertFalse(DESC_CORNDOGS.equals(DESC_SOUP));


        // different name -> returns false
        EditRecipeDescriptor editedAmy = new EditRecipeDescriptorBuilder(DESC_CORNDOGS).withTitle(VALID_TITLE_SOUP).build();
        assertFalse(DESC_CORNDOGS.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditRecipeDescriptorBuilder(DESC_CORNDOGS).withDesc(VALID_DESC_SOUP).build();
        assertFalse(DESC_CORNDOGS.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditRecipeDescriptorBuilder(DESC_CORNDOGS).withIngredients(VALID_INGREDIENTS_SOUP).build();
        assertFalse(DESC_CORNDOGS.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditRecipeDescriptorBuilder(DESC_CORNDOGS).withSteps(VALID_STEPS_SOUP).build();
        assertFalse(DESC_CORNDOGS.equals(editedAmy));

    }
}
