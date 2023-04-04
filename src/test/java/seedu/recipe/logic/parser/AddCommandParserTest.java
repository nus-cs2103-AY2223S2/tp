package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.commands.CommandTestUtil.DESC_DESC_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.DESC_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.INGREDIENT_DESC_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.INGREDIENT_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_DESC_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_INGREDIENT_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_STEP_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.recipe.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.recipe.logic.commands.CommandTestUtil.STEP_DESC_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.STEP_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.TAG_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.TITLE_DESC_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.TITLE_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_INGREDIENTS_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_INGREDIENTS_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_STEPS_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_STEPS_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_TITLE_SOUP;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.recipe.testutil.TypicalRecipes.SOUP;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.commands.AddCommand;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Title;
import seedu.recipe.testutil.RecipeBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Recipe expectedRecipe = new RecipeBuilder(SOUP).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_SOUP + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP
                + STEP_DESC_SOUP + TAG_DESC_SOUP, new AddCommand(expectedRecipe));


        // multiple names - last name accepted
        assertParseSuccess(parser, TITLE_DESC_CORNDOGS + TITLE_DESC_SOUP + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP
                + STEP_DESC_SOUP + TAG_DESC_SOUP, new AddCommand(expectedRecipe));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, TITLE_DESC_SOUP + DESC_DESC_CORNDOGS + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP
                + STEP_DESC_SOUP + TAG_DESC_SOUP, new AddCommand(expectedRecipe));

        // multiple tags - all accepted
        Recipe expectedRecipeManyIngredients = new RecipeBuilder(SOUP)
                .withIngredients(VALID_INGREDIENTS_CORNDOGS, VALID_INGREDIENTS_SOUP)
                    .build();

        assertParseSuccess(parser, TITLE_DESC_SOUP + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP
                        + INGREDIENT_DESC_CORNDOGS + STEP_DESC_SOUP + TAG_DESC_SOUP,
                new AddCommand(expectedRecipeManyIngredients));

        Recipe expectedRecipeManySteps = new RecipeBuilder(SOUP).withSteps(VALID_STEPS_SOUP,
                        VALID_STEPS_CORNDOGS)
                .build();
        assertParseSuccess(parser, TITLE_DESC_SOUP + DESC_DESC_SOUP + STEP_DESC_SOUP
                        + STEP_DESC_CORNDOGS + INGREDIENT_DESC_SOUP + TAG_DESC_SOUP,
                new AddCommand(expectedRecipeManySteps));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {

        // missing title prefix, add Soup d/Instant soup i/water, 3, cup, 0 s/step 2
        String expectedMissingTitleMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE);
        assertParseFailure(parser, VALID_TITLE_SOUP + DESC_DESC_SOUP
                        + INGREDIENT_DESC_SOUP + STEP_DESC_SOUP,
                expectedMissingTitleMessage);

        // missing desc prefix add t/SoupInstant soup i/water, 3, cup, 0 s/step 2
        String expectedMissingDescMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.NO_DESC_FAILURE);
        assertParseFailure(parser,
                TITLE_DESC_SOUP + VALID_DESC_SOUP + INGREDIENT_DESC_SOUP
                        + STEP_DESC_SOUP, expectedMissingDescMessage);

        // missing ingredient prefix
        String expectedMissingIngredientMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.NO_INGREDIENT_FAILURE);
        assertParseFailure(parser, TITLE_DESC_SOUP + DESC_DESC_SOUP + VALID_INGREDIENTS_SOUP + STEP_DESC_SOUP,
                expectedMissingIngredientMessage);

        // missing step prefix, t/Soup d/Instant soup i/water, 3, cup, 0Instant soup
        String expectedMissingStepMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.NO_STEP_FAILURE);
        assertParseFailure(parser, TITLE_DESC_SOUP + DESC_DESC_SOUP
                        + INGREDIENT_DESC_SOUP + VALID_DESC_SOUP,
                expectedMissingStepMessage);

        // all prefixes, missing SoupInstant soupwater, 3, cup, 0Instant soup
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
        assertParseFailure(parser, VALID_TITLE_SOUP + VALID_DESC_SOUP + VALID_INGREDIENTS_SOUP
                + VALID_DESC_SOUP, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP + STEP_DESC_SOUP
                + TAG_DESC_SOUP,
                Title.MESSAGE_CONSTRAINTS);

        // invalid desc
        assertParseFailure(parser, TITLE_DESC_SOUP + INVALID_DESC_DESC + INGREDIENT_DESC_SOUP + STEP_DESC_SOUP
                + TAG_DESC_SOUP,
                Description.MESSAGE_CONSTRAINTS);

        // invalid ingredient
        assertParseFailure(parser, TITLE_DESC_SOUP + DESC_DESC_SOUP + INVALID_INGREDIENT_DESC
                + STEP_DESC_SOUP + TAG_DESC_SOUP, Ingredient.INGREDIENT_WRONG_ARGUMENTS_MESSAGE_CONSTRAINTS);

        // invalid step
        assertParseFailure(parser, TITLE_DESC_SOUP + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP + INVALID_STEP_DESC
                + TAG_DESC_SOUP, Step.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP + INVALID_STEP_DESC
                + TAG_DESC_SOUP, Title.MESSAGE_CONSTRAINTS);


        // non-empty preamble, NonEmptyPreamble t/Soup d/Instant Soup i/water, 3, cup, 0 s/step 2
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_SOUP + DESC_DESC_SOUP
                        + INGREDIENT_DESC_SOUP + STEP_DESC_SOUP + TAG_DESC_SOUP,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

    }
}
