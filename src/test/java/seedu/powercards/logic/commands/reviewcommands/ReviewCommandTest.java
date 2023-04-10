package seedu.powercards.logic.commands.reviewcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIFTH;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;
import seedu.powercards.model.tag.Tag.TagName;

public class ReviewCommandTest {

    private Model model;
    private ReviewCommand reviewCommand;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        reviewCommand = new ReviewCommand(INDEX_SECOND, Arrays.asList(TagName.MEDIUM));
    }

    @Test
    public void execute_reviewCommand_success() throws Exception {
        CommandResult result = reviewCommand.execute(model);

        // The expected message should contain the review deck name and the difficulty string
        String expectedMessage = String.format(ReviewCommand.MESSAGE_SUCCESS,
                model.getReviewDeckName(), "MEDIUM difficulty");
        assertEquals(expectedMessage, result.getFeedbackToUser());

        reviewCommand = new ReviewCommand(INDEX_SECOND, Arrays.asList());
        assertEquals(String.format(ReviewCommand.MESSAGE_SUCCESS,
                model.getReviewDeckName(), "ALL difficulties"),
                reviewCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_reviewCommandDeckEmpty_throwCommandException() {
        Model emptyModel = new ModelManager();
        assertThrows(CommandException.class, () -> reviewCommand.execute(emptyModel));
    }

    @Test
    public void execute_reviewCommandDeckIndexOutOfBounds_throwCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredDeckList().size() + 1);
        ReviewCommand reviewCommand = new ReviewCommand(invalidIndex, Arrays.asList(TagName.EASY, TagName.MEDIUM));
        assertThrows(CommandException.class, () -> reviewCommand.execute(model));
    }

    @Test
    public void execute_reviewCommandNoCardsWithDifficulty_throwCommandException() {
        ReviewCommand reviewCommand = new ReviewCommand(INDEX_FIFTH, Arrays.asList(TagName.HARD));
        assertThrows(CommandException.class, () -> reviewCommand.execute(model));
    }

    @Test
    public void equals() {
        ReviewCommand reviewCommandCopy = new ReviewCommand(INDEX_SECOND, Arrays.asList(TagName.UNTAGGED));
        ReviewCommand reviewCommandDifferentIndex = new ReviewCommand(INDEX_FIRST, Arrays.asList(TagName.HARD));
        ReviewCommand reviewCommandDifferentDifficulties = new ReviewCommand(INDEX_SECOND,
                Arrays.asList(TagName.MEDIUM));

        // same object -> returns true
        assertEquals(reviewCommand, reviewCommand);

        // same values -> returns true
        assertEquals(reviewCommand, reviewCommandCopy);

        // different types -> returns false
        assertNotEquals(reviewCommand, new Object());

        // null -> returns false
        assertNotEquals(reviewCommand, null);

        // different index -> returns false
        assertNotEquals(reviewCommand, reviewCommandDifferentIndex);

    }
}
