package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.commands.CommandTestUtil.*;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.recipe.testutil.TypicalRecipes.CORNDOGS;
import static seedu.recipe.testutil.TypicalRecipes.SOUP;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.commands.AddCommand;
import seedu.recipe.model.recipe.Title;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.testutil.RecipeBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Recipe expectedRecipe = new RecipeBuilder(SOUP).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_SOUP + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP
                + DESC_DESC_SOUP, new AddCommand(expectedRecipe));

        // multiple names - last name accepted
        assertParseSuccess(parser, TITLE_DESC_CORNDOGS + TITLE_DESC_SOUP + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP
                + DESC_DESC_SOUP, new AddCommand(expectedRecipe));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, TITLE_DESC_SOUP + DESC_DESC_CORNDOGS + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP
                + DESC_DESC_SOUP, new AddCommand(expectedRecipe));

        // multiple tags - all accepted
        Recipe expectedRecipeMultipleIngredients = new RecipeBuilder(SOUP).withIngredients(VALID_INGREDIENTS_SOUP, VALID_INGREDIENTS_CORNDOGS)
                .build();
        assertParseSuccess(parser, TITLE_DESC_SOUP + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP + INGREDIENT_DESC_CORNDOGS + STEP_DESC_SOUP
               , new AddCommand(expectedRecipeMultipleIngredients));

        Recipe expectedRecipeMultipleSteps = new RecipeBuilder(SOUP).withSteps(VALID_STEPS_SOUP, VALID_STEPS_CORNDOGS)
                .build();
        assertParseSuccess(parser, TITLE_DESC_SOUP + DESC_DESC_SOUP + STEP_DESC_SOUP + STEP_DESC_CORNDOGS + INGREDIENT_DESC_SOUP
                , new AddCommand(expectedRecipeMultipleSteps));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TITLE_SOUP + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP + DESC_DESC_SOUP,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, TITLE_DESC_SOUP + VALID_DESC_SOUP + INGREDIENT_DESC_SOUP + DESC_DESC_SOUP,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, TITLE_DESC_SOUP + DESC_DESC_SOUP + VALID_INGREDIENTS_SOUP + DESC_DESC_SOUP,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, TITLE_DESC_SOUP + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP + VALID_DESC_SOUP,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_SOUP + VALID_DESC_SOUP + VALID_INGREDIENTS_SOUP + VALID_DESC_SOUP,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP + DESC_DESC_SOUP
             , Title.MESSAGE_CONSTRAINTS);

        // invalid step
        assertParseFailure(parser, TITLE_DESC_SOUP + INVALID_STEP_DESC + INGREDIENT_DESC_SOUP + DESC_DESC_SOUP
              , Step.MESSAGE_CONSTRAINTS);

        // invalid ingredient
        assertParseFailure(parser, TITLE_DESC_SOUP + DESC_DESC_SOUP + INVALID_INGREDIENT_DESC + DESC_DESC_SOUP
          , Ingredient.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, TITLE_DESC_SOUP + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP + INVALID_DESC_DESC,
               Description.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP + INVALID_DESC_DESC,
                Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_SOUP + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP
                + DESC_DESC_SOUP,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
