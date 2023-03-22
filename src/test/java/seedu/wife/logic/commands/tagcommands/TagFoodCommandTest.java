package seedu.wife.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_DAIRY;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.wife.testutil.TypicalIndex.INDEX_FIRST_FOOD;
import static seedu.wife.testutil.TypicalIndex.INDEX_THIRD_FOOD;
import static seedu.wife.testutil.TypicalWife.getTypicalWifeWithoutFoodTag;

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
//import seedu.wife.testutil.TypicalTags;

/**
 * A class to test the TagFoodCommandTest.
 */
public class TagFoodCommandTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "%s successfully tagged with %s";
    private static final String EXPECTED_ERROR_DUPLICATE_TAG = "You have already tagged %s with %s.";
    private static final String EXPECTED_ERROR_TAG_NOT_FOUND = "There is no %s Tag!";
    private static final String EXPECTED_ERROR_FOOD_NOT_FOUND = "The food item index provided is invalid";

    @Test
    public void execute_tagFood_success() {
        final Model model = new ModelManager(getTypicalWifeWithoutFoodTag(), new UserPrefs());

        // Creating a copy of first Food of model and adding a dairy tag
        Food editedFood = new FoodBuilder(model.getFilteredFoodList().get(0))
                .withTags(VALID_TAG_DAIRY)
                .build();
        Tag tag = new TagBuilder()
                .withTagName(VALID_TAG_DAIRY)
                .build();

        // Creating an expected model for comparison
        Model expectedModel = new ModelManager(new Wife(model.getWife()), new UserPrefs());

        // Setting expected model's first Food to the tagged first Food
        expectedModel.createTag(tag);
        expectedModel.setFood(expectedModel.getFilteredFoodList().get(0), editedFood);

        // tag food on original model
        model.createTag(tag);
        TagFoodCommand tagFoodCommand = new TagFoodCommand(tag.getTagName(), INDEX_FIRST_FOOD);

        String expectedMessage = String.format(EXPECTED_SUCCESS_MESSAGE, editedFood.getName(), tag.getTagName());
        assertCommandSuccess(tagFoodCommand, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredFoodList().get(0).getTags().contains(tag));
    }

    @Test
    public void execute_tagFoodWithoutExistingTagInModel_throwsCommandException() {
        final Model model = new ModelManager(getTypicalWifeWithoutFoodTag(), new UserPrefs());
        Tag tag = new TagBuilder().withTagName(VALID_TAG_DAIRY).build();
        TagFoodCommand tagFoodCommand = new TagFoodCommand(tag.getTagName(), INDEX_FIRST_FOOD);

        String expectedMessage = String.format(EXPECTED_ERROR_TAG_NOT_FOUND, tag.getTagName());
        assertCommandFailure(tagFoodCommand, model, expectedMessage);
    }

    @Test
    public void execute_foodIndexNotFound_throwsCommandException() {
        final Model model = new ModelManager(getTypicalWifeWithoutFoodTag(), new UserPrefs());
        Tag tag = new TagBuilder().withTagName(VALID_TAG_DAIRY).build();
        model.createTag(tag);
        TagFoodCommand tagFoodCommand = new TagFoodCommand(tag.getTagName(), INDEX_THIRD_FOOD);

        assertCommandFailure(tagFoodCommand, model, EXPECTED_ERROR_FOOD_NOT_FOUND);
    }

    @Test
    public void execute_duplicateTagInFood_throwsCommandException() {
        final Model model = new ModelManager(getTypicalWifeWithoutFoodTag(), new UserPrefs());
        Tag tag = new TagBuilder().withTagName(VALID_TAG_DAIRY).build();
        model.createTag(tag);

        Food food = model.getFilteredFoodList().get(0);
        Set<Tag> tagSet = food.getCurrentTags();
        tagSet.add(tag);
        model.setFood(food, food.createNewFoodWithNewTags(food, tagSet));

        TagFoodCommand tagFoodCommand = new TagFoodCommand(tag.getTagName(), INDEX_FIRST_FOOD);

        String expectedMessage = String.format(EXPECTED_ERROR_DUPLICATE_TAG, food.getName(), tag.getTagName());
        assertCommandFailure(tagFoodCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Tag dairyTag = TypicalTag.DAIRY_TAG;
        Tag chocolateTag = TypicalTag.CHOCOLATE_TAG;

        TagFoodCommand tagFoodWithDairyCommand = new TagFoodCommand(dairyTag.getTagName(), INDEX_FIRST_FOOD);
        TagFoodCommand tagFoodWithChocolateCommand = new TagFoodCommand(chocolateTag.getTagName(), INDEX_FIRST_FOOD);

        // same object -> returns true
        assertEquals(tagFoodWithDairyCommand, tagFoodWithDairyCommand);

        // same tags and same food -> returns true
        Tag dairyTagCopy = new TagBuilder().withTagName(VALID_TAG_DAIRY).build();
        TagFoodCommand tagFoodWithDairyCommandCopy = new TagFoodCommand(dairyTagCopy.getTagName(), INDEX_FIRST_FOOD);
        assertEquals(tagFoodWithDairyCommand, tagFoodWithDairyCommandCopy);

        // different commands -> returns false
        assertNotEquals(tagFoodWithDairyCommand, new ExitCommand());

        // null -> returns false
        assertNotEquals(tagFoodWithDairyCommand, null);

        // different tag command -> returns false
        assertNotEquals(tagFoodWithDairyCommand, tagFoodWithChocolateCommand);
    }
}
