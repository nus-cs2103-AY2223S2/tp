package seedu.wife.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_DAIRY;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_NEW;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_TEST;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_USED;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.wife.testutil.TypicalIndex.INDEX_FIRST_FOOD;
import static seedu.wife.testutil.TypicalIndex.INDEX_THIRD_FOOD;
import static seedu.wife.testutil.TypicalWife.getTypicalWifeWithoutFoodTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.generalcommands.ExitCommand;
import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;
import seedu.wife.model.Wife;
import seedu.wife.model.food.Food;
import seedu.wife.model.tag.Tag;
import seedu.wife.testutil.FoodBuilder;
import seedu.wife.testutil.TagBuilder;
import seedu.wife.testutil.TypicalTag;

/**
 * A class to test the TagFoodCommandTest.
 */
public class TagFoodCommandTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "%s successfully tagged with %s";
    private static final String EXPECTED_ERROR_DUPLICATE_TAG = "You have already tagged %s with %s.";
    private static final String EXPECTED_ERROR_TAG_NOT_FOUND = "There is no %s Tag!";
    private static final String EXPECTED_ERROR_FOOD_NOT_FOUND = "The food item index provided is invalid";
    private static final String EXPECTED_ERROR_MAXIMUM_TAG = "You have reached the maximum tag limit for %s.";
    private final Model model = new ModelManager(getTypicalWifeWithoutFoodTag(), new UserPrefs());
    private final Tag tagToUse = new TagBuilder().withTagName(VALID_TAG_DAIRY).build();
    @Test
    public void execute_tagFood_success() {
        Food foodToTag = new FoodBuilder(model.getFilteredFoodList().get(0))
                .withTags(VALID_TAG_DAIRY)
                .build();

        Model expectedModel = new ModelManager(new Wife(model.getWife()), new UserPrefs());
        expectedModel.createTag(tagToUse);
        expectedModel.setFood(expectedModel.getFilteredFoodList().get(0), foodToTag);

        model.createTag(tagToUse);
        TagFoodCommand tagFoodCommand = new TagFoodCommand(tagToUse.getTagName(), INDEX_FIRST_FOOD);

        String expectedMessage = String.format(EXPECTED_SUCCESS_MESSAGE, foodToTag.getName(), tagToUse.getTagName());

        assertCommandSuccess(tagFoodCommand, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredFoodList().get(0).getTags().contains(tagToUse));
    }

    @Test
    public void execute_tagFoodWithoutExistingTag_throwsCommandException() {
        TagFoodCommand tagFoodCommand = new TagFoodCommand(tagToUse.getTagName(), INDEX_FIRST_FOOD);

        String expectedMessage = String.format(EXPECTED_ERROR_TAG_NOT_FOUND, tagToUse.getTagName());
        assertCommandFailure(tagFoodCommand, model, expectedMessage);
    }

    @Test
    public void execute_foodIndexNotFound_throwsCommandException() {
        model.createTag(tagToUse);
        TagFoodCommand tagFoodCommand = new TagFoodCommand(tagToUse.getTagName(), INDEX_THIRD_FOOD);

        assertCommandFailure(tagFoodCommand, model, EXPECTED_ERROR_FOOD_NOT_FOUND);
    }

    @Test
    public void execute_maxTagInFood_throwsCommandException() {
        ArrayList<Tag> testTags = new ArrayList<>();
        ArrayList<String> validTags = new ArrayList<>(Arrays.asList(VALID_TAG_CHOCOLATE,
                VALID_TAG_DAIRY, VALID_TAG_NEW, VALID_TAG_USED));

        for (String validTag : validTags) {
            testTags.add(new TagBuilder().withTagName(validTag).build());
        }

        Food food = model.getFilteredFoodList().get(0);
        Set<Tag> tagSet = food.getCurrentTags();

        for (Tag testTag : testTags) {
            model.createTag(testTag);
            tagSet.add(testTag);
        };

        Tag lastTag = new TagBuilder().withTagName(VALID_TAG_TEST).build();
        model.createTag(lastTag);
        model.setFood(food, food.createNewFoodWithNewTags(food, tagSet));

        TagFoodCommand tagFoodCommand = new TagFoodCommand(lastTag.getTagName(), INDEX_FIRST_FOOD);

        String expectedMessage = String.format(EXPECTED_ERROR_MAXIMUM_TAG, food.getName());
        assertCommandFailure(tagFoodCommand, model, expectedMessage);
    }

    @Test
    public void execute_duplicateTagInFood_throwsCommandException() {
        model.createTag(tagToUse);

        Food food = model.getFilteredFoodList().get(0);
        Set<Tag> tagSet = food.getCurrentTags();
        tagSet.add(tagToUse);
        model.setFood(food, food.createNewFoodWithNewTags(food, tagSet));

        TagFoodCommand tagFoodCommand = new TagFoodCommand(tagToUse.getTagName(), INDEX_FIRST_FOOD);

        String expectedMessage = String.format(EXPECTED_ERROR_DUPLICATE_TAG, food.getName(), tagToUse.getTagName());
        assertCommandFailure(tagFoodCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Tag dairyTag = TypicalTag.DAIRY_TAG;
        Tag dairyTagCopy = new TagBuilder().withTagName(VALID_TAG_DAIRY).build();
        Tag chocolateTag = TypicalTag.CHOCOLATE_TAG;

        TagFoodCommand tagFoodWithDairyCommand = new TagFoodCommand(dairyTag.getTagName(), INDEX_FIRST_FOOD);
        TagFoodCommand tagFoodWithDairyCommandCopy = new TagFoodCommand(dairyTagCopy.getTagName(), INDEX_FIRST_FOOD);
        TagFoodCommand tagFoodWithChocolateCommand = new TagFoodCommand(chocolateTag.getTagName(), INDEX_FIRST_FOOD);

        // same command object -> returns true
        assertEquals(tagFoodWithDairyCommand, tagFoodWithDairyCommand);

        // same tags and same food -> returns true
        assertEquals(tagFoodWithDairyCommand, tagFoodWithDairyCommandCopy);

        // different commands -> returns false
        assertNotEquals(tagFoodWithDairyCommand, new ExitCommand());

        // null -> returns false
        assertNotEquals(tagFoodWithDairyCommand, null);

        // different tag command -> returns false
        assertNotEquals(tagFoodWithDairyCommand, tagFoodWithChocolateCommand);
    }
}
