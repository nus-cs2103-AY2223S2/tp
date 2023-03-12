package seedu.loyaltylift.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;
import seedu.loyaltylift.model.customer.Points;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.*;
import static seedu.loyaltylift.logic.commands.SetPointsCommand.MESSAGE_ARGUMENTS;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.loyaltylift.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;

public class SetPointsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final Points points = new Points(100);

        assertCommandFailure(new SetPointsCommand(INDEX_FIRST_CUSTOMER, points), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_CUSTOMER.getOneBased(), points));
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
