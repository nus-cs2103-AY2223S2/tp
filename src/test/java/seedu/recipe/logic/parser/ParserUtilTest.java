package seedu.recipe.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

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
    private static final String INVALID_INGREDIENT = "salt & pepper";
    private static final String INVALID_STEP = "Mix the cookie batter in the %^&, Do this next";
    private static final String INVALID_TITLE = "~~Mala Xiangguo~~";
//    private static final String INVALID_TAG = "#friend";

    private static final String VALID_DESCRIPTION = "A yummy recipe for your stomach";
    private static final String VALID_INGREDIENT = "salt, pepper";
    private static final String VALID_STEP = "Mix the butter in the batter, then add sugar";
    private static final String VALID_TITLE = "Gelato";
//    private static final String VALID_TAG_1 = "friend";
//    private static final String VALID_TAG_2 = "neighbour";

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
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
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
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIngredients((String) null));
    }

    @Test
    public void parseIngredient_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIngredients(INVALID_INGREDIENT));
    }

    @Test
    public void parseIngredient_validValueWithoutWhitespace_returnsIngredients() throws Exception {
        String[] tempIng = VALID_INGREDIENT.split(",");
        ArrayList<Ingredient> actualIng = ParserUtil.parseIngredients(VALID_INGREDIENT);
        for (int i = 0; i < tempIng.length; i++) {
            Ingredient expectedIngredient = new Ingredient(tempIng[i]);
            assertEquals(expectedIngredient, actualIng.get(i));
        }
    }

    @Test
    public void parseIngredient_validValueWithWhitespace_returnsTrimmedIngredient() throws Exception {
        String[] tempIng = VALID_INGREDIENT.split(",");
        ArrayList<Ingredient> actualIng = ParserUtil.parseIngredients(WHITESPACE+ VALID_INGREDIENT + WHITESPACE);
        for (int i = 0; i < tempIng.length; i++) {
            Ingredient expectedIngredient = new Ingredient(tempIng[i]);
            assertEquals(expectedIngredient, actualIng.get(i));
        }
    }

    @Test
    public void parseSteps_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSteps((String) null));
    }

    @Test
    public void parseSteps_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSteps(INVALID_STEP));
    }

    @Test
    public void parseSteps_validValueWithoutWhitespace_returnsSteps() throws Exception {
        String[] tempStep = VALID_STEP.split(",");
        ArrayList<Step> actualStep = ParserUtil.parseSteps(VALID_STEP);
        for (int i = 0; i < tempStep.length; i++) {
            Step expectedStep = new Step(tempStep[i]);
            assertEquals(expectedStep, actualStep.get(i));
        }
    }

    @Test
    public void parseSteps_validValueWithWhitespace_returnsTrimmedSteps() throws Exception {
        String[] tempStep = VALID_STEP.split(",");
        ArrayList<Step> actualStep = ParserUtil.parseSteps(WHITESPACE + VALID_STEP + WHITESPACE);
        for (int i = 0; i < tempStep.length; i++) {
            Step expectedStep = new Step(tempStep[i]);
            assertEquals(expectedStep, actualStep.get(i));
        }
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
//
//    @Test
//    public void parseTag_null_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
//    }
//
//    @Test
//    public void parseTag_invalidValue_throwsParseException() {
//        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
//    }
//
//    @Test
//    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
//        Tag expectedTag = new Tag(VALID_TAG_1);
//        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
//    }
//
//    @Test
//    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
//        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
//        Tag expectedTag = new Tag(VALID_TAG_1);
//        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
//    }
//
//    @Test
//    public void parseTags_null_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
//    }
//
//    @Test
//    public void parseTags_collectionWithInvalidTags_throwsParseException() {
//        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
//    }
//
//    @Test
//    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
//        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
//    }
//
//    @Test
//    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
//        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
//        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));
//
//        assertEquals(expectedTagSet, actualTagSet);
//    }
}
