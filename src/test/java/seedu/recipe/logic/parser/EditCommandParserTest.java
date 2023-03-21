package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.commands.CommandTestUtil.DESC_DESC_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.DESC_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.INGREDIENT_DESC_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.INGREDIENT_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_DESC_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_INGREDIENT_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_STEP_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.TITLE_DESC_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.STEP_DESC_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.STEP_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_DESC_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_DESC_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_INGREDIENTS_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_INGREDIENTS_SOUP;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_TITLE_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_STEPS_CORNDOGS;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_STEPS_SOUP;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_SECOND_RECIPE;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_THIRD_RECIPE;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.EditCommand;
import seedu.recipe.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.recipe.model.recipe.Title;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.testutil.EditRecipeDescriptorBuilder;

public class EditCommandParserTest {


    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE_CORNDOGS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_CORNDOGS, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_CORNDOGS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_STEP_DESC, Step.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_INGREDIENT_DESC, Ingredient.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_DESC_DESC, Description.MESSAGE_CONSTRAINTS); // invalid address

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_STEP_DESC + INGREDIENT_DESC_CORNDOGS, Step.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + STEP_DESC_SOUP + INVALID_STEP_DESC, Step.MESSAGE_CONSTRAINTS);


        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_INGREDIENT_DESC + VALID_DESC_CORNDOGS + VALID_STEPS_CORNDOGS,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_RECIPE;
        String userInput = targetIndex.getOneBased() + STEP_DESC_SOUP
                + INGREDIENT_DESC_CORNDOGS + DESC_DESC_CORNDOGS + TITLE_DESC_CORNDOGS;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withTitle(VALID_TITLE_CORNDOGS)
                .withSteps(VALID_STEPS_SOUP).withIngredients(VALID_INGREDIENTS_CORNDOGS).withDesc(VALID_DESC_CORNDOGS)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_RECIPE;
        String userInput = targetIndex.getOneBased() + STEP_DESC_SOUP + INGREDIENT_DESC_CORNDOGS;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withSteps(VALID_STEPS_SOUP)
                .withIngredients(VALID_INGREDIENTS_CORNDOGS).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = INDEX_THIRD_RECIPE;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_CORNDOGS;
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withTitle(VALID_TITLE_CORNDOGS).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // step
        userInput = targetIndex.getOneBased() + STEP_DESC_CORNDOGS;
        descriptor = new EditRecipeDescriptorBuilder().withSteps(VALID_STEPS_CORNDOGS).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // ingredient
        userInput = targetIndex.getOneBased() + INGREDIENT_DESC_CORNDOGS;
        descriptor = new EditRecipeDescriptorBuilder().withIngredients(VALID_INGREDIENTS_CORNDOGS).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESC_DESC_CORNDOGS;
        descriptor = new EditRecipeDescriptorBuilder().withDesc(VALID_DESC_CORNDOGS).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_RECIPE;
        String userInput = targetIndex.getOneBased() + STEP_DESC_CORNDOGS + DESC_DESC_CORNDOGS + INGREDIENT_DESC_CORNDOGS
            + STEP_DESC_CORNDOGS + DESC_DESC_CORNDOGS + INGREDIENT_DESC_CORNDOGS
                + STEP_DESC_SOUP + DESC_DESC_SOUP + INGREDIENT_DESC_SOUP;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withSteps(VALID_STEPS_SOUP)
                .withIngredients(VALID_INGREDIENTS_SOUP).withDesc(VALID_DESC_SOUP)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_RECIPE;
        String userInput = targetIndex.getOneBased() + INVALID_STEP_DESC + STEP_DESC_SOUP;
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withSteps(VALID_STEPS_SOUP).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INGREDIENT_DESC_SOUP + INVALID_STEP_DESC + DESC_DESC_SOUP
                + STEP_DESC_SOUP;
        descriptor = new EditRecipeDescriptorBuilder().withSteps(VALID_STEPS_SOUP).withIngredients(VALID_INGREDIENTS_SOUP)
                .withDesc(VALID_DESC_SOUP).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_RECIPE;
        String userInput = Integer.toString(targetIndex.getOneBased());

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
