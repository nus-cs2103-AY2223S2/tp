package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_POINTS_ADD;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_POINTS_SUBTRACT;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;

public class AddPointsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPointsCommand(null, VALID_POINTS_ADD));
    }

    @Test
    public void constructor_nullAddPoints_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPointsCommand(Index.fromOneBased(1),
                null));
    }

    @Test
    public void equals() {
        final AddPointsCommand standardCommand = new AddPointsCommand(INDEX_FIRST, VALID_POINTS_ADD);

        // same values -> returns true
        AddPointsCommand commandWithSameValues = new AddPointsCommand(INDEX_FIRST, VALID_POINTS_ADD);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddPointsCommand(INDEX_SECOND, VALID_POINTS_ADD)));

        // different AddPoints -> returns false
        assertFalse(standardCommand.equals(new AddPointsCommand(INDEX_FIRST, VALID_POINTS_SUBTRACT)));
    }
}
