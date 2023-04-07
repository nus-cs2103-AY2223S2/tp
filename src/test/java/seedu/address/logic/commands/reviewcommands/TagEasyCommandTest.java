package seedu.address.logic.commands.reviewcommands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.reviewcommands.TagEasyCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for TagEasyCommand.
 */
public class TagEasyCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        model.selectDeck(INDEX_FIRST);
        model.reviewDeck(INDEX_FIRST,
                List.of(new Tag.TagName[]{Tag.TagName.EASY, Tag.TagName.MEDIUM, Tag.TagName.HARD}));
    }

    @Test
    public void execute_tagCardInReviewSuccess() {
        String expectedMessage = MESSAGE_SUCCESS;
        Model expectedModel = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        expectedModel.selectDeck(INDEX_FIRST);
        expectedModel.reviewDeck(INDEX_FIRST,
                List.of(new Tag.TagName[]{Tag.TagName.EASY, Tag.TagName.MEDIUM, Tag.TagName.HARD}));
        expectedModel.tagCurrentCardInReview(new Tag(Tag.TagName.EASY));
        assertCommandSuccess(new TagEasyCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        TagEasyCommand tagEasyCommand = new TagEasyCommand();

        // same object -> returns true
        assertTrue(tagEasyCommand.equals(tagEasyCommand));

        // different types -> returns false
        assertTrue(!tagEasyCommand.equals(1));

        // null -> returns false
        assertTrue(!tagEasyCommand.equals(null));
    }
}
