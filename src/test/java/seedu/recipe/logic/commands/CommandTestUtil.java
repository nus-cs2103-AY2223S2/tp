package seedu.recipe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.recipe.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.NameContainsKeywordsPredicate;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.testutil.EditRecipeDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TITLE_CORNDOGS = "Corndogs";
    public static final String VALID_TITLE_SOUP = "Soup";
    public static final String VALID_DESC_CORNDOGS = "Homemade corndogs";
    public static final String VALID_DESC_SOUP = "Instant soup";

    public static final String VALID_STEPS_CORNDOGS = "step 1;";
    public static final String VALID_STEPS_SOUP = "step 2";
    public static final String VALID_INGREDIENTS_CORNDOGS = "flour";
    public static final String VALID_INGREDIENTS_SOUP = "water";


    public static final String TITLE_DESC_CORNDOGS = " " + PREFIX_TITLE + VALID_TITLE_CORNDOGS;
    public static final String DESC_DESC_CORNDOGS = " " + PREFIX_DESCRIPTION + VALID_DESC_CORNDOGS;
    public static final String STEP_DESC_CORNDOGS = " " + PREFIX_STEP + VALID_STEPS_CORNDOGS;
    public static final String INGREDIENT_DESC_CORNDOGS = " " + PREFIX_INGREDIENT + VALID_INGREDIENTS_CORNDOGS;

    public static final String TITLE_DESC_SOUP = " " + PREFIX_TITLE + VALID_TITLE_SOUP;
    public static final String DESC_DESC_SOUP = " " + PREFIX_DESCRIPTION + VALID_DESC_SOUP;
    public static final String STEP_DESC_SOUP = " " + PREFIX_STEP + VALID_STEPS_SOUP;
    public static final String INGREDIENT_DESC_SOUP = " " + PREFIX_INGREDIENT + VALID_INGREDIENTS_SOUP;


    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "Sushi&"; // '&' not allowed in names
    public static final String INVALID_DESC_DESC = " " + PREFIX_DESCRIPTION + "Hooray!"; // 'a' not allowed in phones
    public static final String INVALID_STEP_DESC = " " + PREFIX_STEP + "boil water & flour"; // missing '@' symbol
    public static final String INVALID_INGREDIENT_DESC = " " + PREFIX_INGREDIENT; // empty string not allowed for addresses


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditRecipeDescriptor DESC_CORNDOGS;
    public static final EditCommand.EditRecipeDescriptor DESC_SOUP;



    static {
        DESC_CORNDOGS = new EditRecipeDescriptorBuilder().withTitle(VALID_TITLE_CORNDOGS)
                .withDesc(VALID_DESC_CORNDOGS).withSteps(VALID_STEPS_CORNDOGS).withIngredients(VALID_INGREDIENTS_CORNDOGS)
                .build();
        DESC_SOUP = new EditRecipeDescriptorBuilder().withTitle(VALID_TITLE_SOUP)
                .withDesc(VALID_DESC_SOUP).withSteps(VALID_STEPS_SOUP).withIngredients(VALID_INGREDIENTS_SOUP)
                .build();

    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        RecipeBook expectedRecipeBook = new RecipeBook(actualModel.getRecipeBook());
        List<Recipe> expectedFilteredList = new ArrayList<>(actualModel.getFilteredRecipeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedRecipeBook, actualModel.getRecipeBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredRecipeList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showRecipeAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredRecipeList().size());

        Recipe recipe = model.getFilteredRecipeList().get(targetIndex.getZeroBased());
        final String[] splitName = recipe.getTitle().toString().split("\\s+");
        model.updateFilteredRecipeList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredRecipeList().size());
    }

}
