package seedu.wife.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_DAIRY;
import static seedu.wife.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.wife.testutil.TypicalIndex.INDEX_FIRST_FOOD;
import static seedu.wife.testutil.TypicalIndex.INDEX_THIRD_FOOD;
import static seedu.wife.testutil.TypicalWife.getTypicalWifeWithoutFoodTag;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.generalcommands.ExitCommand;
import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;
import seedu.wife.model.tag.Tag;
import seedu.wife.testutil.TagBuilder;
import seedu.wife.testutil.TypicalTag;

public class UntagFoodCommandTest {
    private static final String EXPECTED_SUCCESS_MESSAGE = "%s successfully untagged from %s";
    private static final String EXPECTED_ERROR_TAG_NOT_FOUND = "There is no %s Tag!";
    private static final String EXPECTED_ERROR_FOOD_NOT_FOUND = "The food item index provided is invalid";

    @Test
    public void execute_untagFoodWithoutExistingTag_throwsCommandException() {
        final Model model = new ModelManager(getTypicalWifeWithoutFoodTag(), new UserPrefs());
        Tag dairyTag = new TagBuilder().withTagName(VALID_TAG_DAIRY).build();
        UntagFoodCommand untagFoodCommand = new UntagFoodCommand(dairyTag.getTagName(), INDEX_FIRST_FOOD);

        //tag does not exist in model
        String expectedMessage = String.format(EXPECTED_ERROR_TAG_NOT_FOUND, dairyTag.getTagName());
        assertCommandFailure(untagFoodCommand, model, expectedMessage);

        //tag name is not currently tagged to item(does not exist in food item tag set)
        //but tag does exist in model
        model.createTag(dairyTag);
        String expectedMessageToo = String.format(EXPECTED_ERROR_TAG_NOT_FOUND, dairyTag.getTagName());
        assertCommandFailure(untagFoodCommand, model, expectedMessageToo);
    }

    @Test
    public void execute_foodIndexNotFound_throwsCommandException() {
        final Model model = new ModelManager(getTypicalWifeWithoutFoodTag(), new UserPrefs());
        Tag tag = new TagBuilder().withTagName(VALID_TAG_DAIRY).build();
        model.createTag(tag);
        UntagFoodCommand untagFoodCommand = new UntagFoodCommand(tag.getTagName(), INDEX_THIRD_FOOD);

        assertCommandFailure(untagFoodCommand, model, EXPECTED_ERROR_FOOD_NOT_FOUND);
    }

    @Test
    public void equals() {
        Tag dairyTag = TypicalTag.DAIRY_TAG;
        Tag chocolateTag = TypicalTag.CHOCOLATE_TAG;

        UntagFoodCommand untagFoodDairy = new UntagFoodCommand(dairyTag.getTagName(), INDEX_FIRST_FOOD);
        UntagFoodCommand untagFoodChocolate = new UntagFoodCommand(chocolateTag.getTagName(),
            INDEX_FIRST_FOOD);

        // same object -> returns true
        assertEquals(untagFoodDairy, untagFoodDairy);

        // same tags and same food -> returns true
        Tag dairyTagCopy = new TagBuilder().withTagName(VALID_TAG_DAIRY).build();
        UntagFoodCommand untagFoodDairyCopy = new UntagFoodCommand(dairyTagCopy.getTagName(),
            INDEX_FIRST_FOOD);
        assertEquals(untagFoodDairy, untagFoodDairyCopy);

        // different commands -> returns false
        assertNotEquals(untagFoodDairy, new ExitCommand());

        // null -> returns false
        assertNotEquals(untagFoodDairy, null);

        // different tag command -> returns false
        assertNotEquals(untagFoodDairy, untagFoodChocolate);
    }
}
