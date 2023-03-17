package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_POINTS_ADD;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.customer.Points;

public class AddPointsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPointsCommand(null,
                new Points.AddPoints(VALID_POINTS_ADD, Points.AddPoints.Modifier.PLUS)));
    }

    @Test
    public void constructor_nullAddPoints_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPointsCommand(Index.fromOneBased(1),
                null));
    }

    @Test
    public void equals() {
        final AddPointsCommand standardCommand = new AddPointsCommand(INDEX_FIRST_CUSTOMER,
                new Points.AddPoints(VALID_POINTS_ADD, Points.AddPoints.Modifier.PLUS));

        // same values -> returns true
        AddPointsCommand commandWithSameValues = new AddPointsCommand(INDEX_FIRST_CUSTOMER,
                new Points.AddPoints(VALID_POINTS_ADD, Points.AddPoints.Modifier.PLUS));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddPointsCommand(INDEX_SECOND_CUSTOMER,
                new Points.AddPoints(VALID_POINTS_ADD, Points.AddPoints.Modifier.PLUS))));

        // different AddPoints -> returns false
        assertFalse(standardCommand.equals(new AddPointsCommand(INDEX_FIRST_CUSTOMER,
                new Points.AddPoints(VALID_POINTS_ADD, Points.AddPoints.Modifier.MINUS))));
    }
}
