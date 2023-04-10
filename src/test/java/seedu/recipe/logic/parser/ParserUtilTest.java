package seedu.recipe.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_PORTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.recipe.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.logic.util.RecipeDescriptor;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.tag.Tag;

public class ParserUtilTest {

    private static final String INVALID_NAME = "L@sagna*";
    private static final String INVALID_PORTION = "2";
    private static final String INVALID_DURATION = "10";
    private static final String INVALID_TAG = "#chinese";
    private static final String INVALID_INGREDIENT = " ";
    private static final String INVALID_STEP = " ";

    private static final String VALID_NAME = "Lasagna";
    private static final String VALID_PORTION = "2 - 3 portions";
    private static final String VALID_DURATION = "1 hour";
    private static final String VALID_TAG_1 = "favourites";
    private static final String VALID_TAG_2 = "italian";
    private static final String VALID_INGREDIENT_1 = "-a 1 pound -n lasagna sheets";
    private static final String VALID_INGREDIENT_2 = "-a 1 teaspoon -n salt";
    private static final String VALID_STEP_1 =
        "Cook the lasagna noodles according to the package instructions. Drain and set aside.";
    private static final String VALID_STEP_2 =
        "Add the crushed tomatoes, tomato paste, basil, oregano, salt, and black pepper to the skillet. "
        + "Stir to combine and simmer for 10-15 minutes.";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_RECIPE, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_RECIPE, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePortion_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePortion((String) null));
    }

    @Test
    public void parsePortion_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePortion(INVALID_PORTION));
    }

    @Test
    public void parsePortion_validValueWithoutWhitespace_returnsPortion() throws Exception {
        RecipePortion expectedPortion = RecipePortion.of(VALID_PORTION);
        assertEquals(expectedPortion, ParserUtil.parsePortion(VALID_PORTION));
    }

    @Test
    public void parsePortion_validValueWithWhitespace_returnsTrimmedPortion() throws Exception {
        String portionWithWhitespace = WHITESPACE + VALID_PORTION + WHITESPACE;
        RecipePortion expectedPortion = RecipePortion.of(VALID_PORTION);
        assertEquals(expectedPortion, ParserUtil.parsePortion(portionWithWhitespace));
    }

    @Test
    public void parseDuration_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDuration((String) null));
    }

    @Test
    public void parseDuration_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDuration(INVALID_DURATION));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_2);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_2));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseIngredient_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIngredient(null));
    }

    @Test
    public void parseIngredient_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_INGREDIENT));
    }

    @Test
    public void parseIngredient_validValueWithoutWhitespace_returnsIngredient() throws Exception {
        HashMap<Ingredient, IngredientInformation> expectedIngredient = new IngredientBuilder(
            VALID_INGREDIENT_1).build();
        assertEquals(expectedIngredient, ParserUtil.parseIngredient(VALID_INGREDIENT_1));
    }

    @Test
    public void parseIngredient_validValueWithWhitespace_returnsTrimmedIngredient() throws Exception {
        String ingredientWithWhitespace = WHITESPACE + VALID_INGREDIENT_1 + WHITESPACE;
        HashMap<Ingredient, IngredientInformation> expectedIngredient = new IngredientBuilder(
            VALID_INGREDIENT_1).build();
        assertEquals(expectedIngredient, ParserUtil.parseIngredient(ingredientWithWhitespace));
    }

    @Test
    public void parseIngredients_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIngredients(null));
    }

    @Test
    public void parseIngredients_collectionWithInvalidIngredients_throwsParseException() {
        assertThrows(ParseException.class, () ->
            ParserUtil.parseIngredients(Arrays.asList(VALID_INGREDIENT_1, INVALID_INGREDIENT)));
    }

    @Test
    public void parseIngredients_emptyCollection_returnsEmptyList() throws Exception {
        assertTrue(ParserUtil.parseIngredients(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseIngredients_collectionWithValidIngredients_returnsIngredientsList() throws Exception {
        HashMap<Ingredient, IngredientInformation> actualIngredientList =
            ParserUtil.parseIngredients(Arrays.asList(VALID_INGREDIENT_1, VALID_INGREDIENT_2));

        HashMap<Ingredient, IngredientInformation> expectedIngredientList = new HashMap<>();
        HashMap<Ingredient, IngredientInformation> ingredient1 = new IngredientBuilder(VALID_INGREDIENT_1).build();
        HashMap<Ingredient, IngredientInformation> ingredient2 = new IngredientBuilder(VALID_INGREDIENT_2).build();
        expectedIngredientList.putAll(ingredient1);
        expectedIngredientList.putAll(ingredient2);

        assertEquals(expectedIngredientList, actualIngredientList);
    }

    @Test
    public void parseStep_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStep(null));
    }

    @Test
    public void parseStep_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStep(INVALID_STEP));
    }

    @Test
    public void parseStep_validValueWithoutWhitespace_returnsStep() throws Exception {
        Step expectedStep = new Step(VALID_STEP_1);
        assertEquals(expectedStep, ParserUtil.parseStep(VALID_STEP_1));
    }


    @Test
    public void parseSteps_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSteps(null));
    }

    @Test
    public void parseSteps_collectionWithInvalidSteps_throwsParseException() {
        assertThrows(ParseException.class, () ->
            ParserUtil.parseSteps(Arrays.asList(VALID_STEP_1, INVALID_STEP)));
    }

    @Test
    public void parseSteps_emptyCollection_returnsEmptyList() throws Exception {
        assertTrue(ParserUtil.parseSteps(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseSteps_collectionWithValidSteps_returnsStepsList() throws Exception {
        List<Step> actualStepList =
            ParserUtil.parseSteps(Arrays.asList(VALID_STEP_1, VALID_STEP_2));
        List<Step> expectedStepList =
            new ArrayList<Step>(Arrays.asList(new Step(VALID_STEP_1),
                new Step(VALID_STEP_2)));

        assertEquals(expectedStepList, actualStepList);
    }

    @Test
    public void parseToDescriptor_allFieldsPresent() {
        //Set up arguments
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(PREFIX_NAME, VALID_NAME);
        argumentMultimap.put(PREFIX_DURATION, VALID_DURATION);
        argumentMultimap.put(PREFIX_PORTION, VALID_PORTION);
        argumentMultimap.put(PREFIX_INGREDIENT, VALID_INGREDIENT_1);
        argumentMultimap.put(PREFIX_INGREDIENT, VALID_INGREDIENT_2);
        argumentMultimap.put(PREFIX_STEP, VALID_STEP_1);
        argumentMultimap.put(PREFIX_STEP, VALID_STEP_2);
        argumentMultimap.put(PREFIX_TAG, VALID_TAG_1);
        argumentMultimap.put(PREFIX_TAG, VALID_TAG_2);

        //Set up expected descriptor
        RecipeDescriptor expected = new RecipeDescriptor();
        expected.setName(new Name(VALID_NAME));
        expected.setPortion(RecipePortion.of(VALID_PORTION));
        expected.setDuration(RecipeDuration.of(VALID_DURATION));
        expected.setIngredients(
            List.of(new IngredientBuilder(VALID_INGREDIENT_1), new IngredientBuilder(VALID_INGREDIENT_2)));
        expected.setTags(Set.of(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));
        expected.setSteps(List.of(new Step(VALID_STEP_1), new Step(VALID_STEP_2)));

        try {
            assertEquals(expected, ParserUtil.parseToRecipeDescriptor(argumentMultimap));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }
}
