package seedu.address.logic.commands.jobs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_JOBA;
import static seedu.address.logic.commands.CommandTestUtil.DESC_JOBB;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_JOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.jobs.EditDeliveryJobCommand.EditDeliveryJobDescriptor;
import seedu.address.logic.commands.person.ClearCommand;

public class EditDeliveryJobCommandTest {
    @Test
    public void equals() {
        final EditDeliveryJobCommand standardCommand = new EditDeliveryJobCommand(INDEX_FIRST_JOB, DESC_JOBA);

        // same values -> returns true
        EditDeliveryJobDescriptor copyDescriptor = new EditDeliveryJobDescriptor(DESC_JOBA);
        EditDeliveryJobCommand commandWithSameValues = new EditDeliveryJobCommand(INDEX_FIRST_JOB, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditDeliveryJobCommand(INDEX_SECOND_JOB, DESC_JOBA)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditDeliveryJobCommand(INDEX_FIRST_JOB, DESC_JOBB)));
    }
}
