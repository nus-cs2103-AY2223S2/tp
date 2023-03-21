package seedu.recipe.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;

import java.util.*;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Title;
import seedu.recipe.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_DESCRIPTION = "This is some !@% invalid description";
    private static final String INVALID_INGREDIENT = "#clam";
    private static final String INVALID_STEP = "step 1 & 2";
    private static final String INVALID_TITLE = "~~Mala Xiangguo~~";


    private static final String VALID_DESCRIPTION = "A yummy recipe for your stomach";
    private static final String VALID_INGREDIENT1 = "salt";
    private static final String VALID_INGREDIENT2 = "pepper";
    private static final String VALID_STEP1 = "step 1";
    private static final String VALID_STEP2 = "step 2";
    private static final String VALID_TITLE = "Gelato";


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
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedName = new Description(VALID_DESCRIPTION);
        assertEquals(expectedName, ParserUtil.parseDescription(descriptionWithWhitespace));
    }

    @Test
    public void parseIngredient_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIngredientHelper((String) null));
    }

    @Test
    public void parseIngredient_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIngredientHelper(INVALID_INGREDIENT));
    }

    @Test
    public void parseIngredient_validValueWithoutWhitespace_returnsIngredients() throws Exception {
        Ingredient expectedIngredient = new Ingredient(VALID_INGREDIENT1);
        assertEquals(expectedIngredient, ParserUtil.parseIngredientHelper(VALID_INGREDIENT1));
    }

    @Test
    public void parseIngredient_validValueWithWhitespace_returnsTrimmedIngredient() throws Exception {
        String ingredientWithWhitespace = WHITESPACE + VALID_INGREDIENT1 + WHITESPACE;
        Ingredient expectedIngredient = new Ingredient(ingredientWithWhitespace);
        assertEquals(expectedIngredient, ParserUtil.parseIngredientHelper(ingredientWithWhitespace));
    }

    @Test
    public void parseSteps_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStepHelper((String) null));
    }

    @Test
    public void parseSteps_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStepHelper(INVALID_STEP));
    }

    @Test
    public void parseSteps_validValueWithoutWhitespace_returnsSteps() throws Exception {
        Ingredient expectedStep = new Ingredient(VALID_STEP2);
        assertEquals(expectedStep, ParserUtil.parseIngredientHelper(VALID_STEP2));
    }

    @Test
    public void parseSteps_validValueWithWhitespace_returnsTrimmedSteps() throws Exception {
        String stepWithWhitespace = WHITESPACE + VALID_STEP1 + WHITESPACE;
        Ingredient expectedStep = new Ingredient(stepWithWhitespace);
        assertEquals(expectedStep, ParserUtil.parseIngredientHelper(stepWithWhitespace));
    }

    @Test
    public void parseTitle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle((String) null));
    }

    @Test
    public void parseTitle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithoutWhitespace_returnsTitle() throws Exception {
        Title expectedTitle = new Title(VALID_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(VALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithWhitespace_returnsTrimmedTitle() throws Exception {
        String titleWithWhitespace = WHITESPACE + VALID_TITLE + WHITESPACE;
        Title expectedTitle = new Title(VALID_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(titleWithWhitespace));
    }
}