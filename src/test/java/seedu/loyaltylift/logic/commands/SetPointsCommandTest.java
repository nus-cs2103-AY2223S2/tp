package seedu.loyaltylift.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.Points;
import seedu.loyaltylift.testutil.CustomerBuilder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.*;
import static seedu.loyaltylift.logic.commands.SetPointsCommand.MESSAGE_ARGUMENTS;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.loyaltylift.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;

public class SetPointsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetPointsCommand(null, new Points(0)));
    }

    @Test
    public void constructor_nullPoints_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetPointsCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void equals() {
        final SetPointsCommand standardCommand = new SetPointsCommand(INDEX_FIRST_CUSTOMER,
                new Points(VALID_POINTS_AMY));

        // same values -> returns true
        SetPointsCommand commandWithSameValues = new SetPointsCommand(INDEX_FIRST_CUSTOMER,
                new Points(VALID_POINTS_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new SetPointsCommand(INDEX_SECOND_CUSTOMER,
                new Points(VALID_POINTS_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new SetPointsCommand(INDEX_FIRST_CUSTOMER,
                new Points(VALID_POINTS_BOB))));
    }
}
