package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_CUMULATIVE_POINTS_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_CUMULATIVE_POINTS_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_POINTS_AMY;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_POINTS_BOB;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.customer.Points;

public class SetPointsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetPointsCommand(null,
                new Points(0, 0)));
    }

    @Test
    public void constructor_nullPoints_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetPointsCommand(Index.fromOneBased(1),
                null));
    }

    @Test
    public void equals() {
        final SetPointsCommand standardCommand = new SetPointsCommand(INDEX_FIRST_CUSTOMER,
                new Points(VALID_POINTS_AMY, VALID_CUMULATIVE_POINTS_AMY));

        // same values -> returns true
        SetPointsCommand commandWithSameValues = new SetPointsCommand(INDEX_FIRST_CUSTOMER,
                new Points(VALID_POINTS_AMY, VALID_CUMULATIVE_POINTS_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new SetPointsCommand(INDEX_SECOND_CUSTOMER,
                new Points(VALID_POINTS_AMY, VALID_CUMULATIVE_POINTS_AMY))));

        // different Points -> returns false
        assertFalse(standardCommand.equals(new SetPointsCommand(INDEX_FIRST_CUSTOMER,
                new Points(VALID_POINTS_BOB, VALID_CUMULATIVE_POINTS_BOB))));
    }
}
