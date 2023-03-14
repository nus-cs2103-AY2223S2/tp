package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.JobTitle;

public class ClearByCommandTest {
    @Test
    public void equals() {
        ClearByCommand clearByFirstCommand = new ClearByCommand(new CompanyName("Company 1"));
        ClearByCommand clearBySecondCommand = new ClearByCommand(new JobTitle("Developer"));
        ClearByCommand clearByThirdCommand = new ClearByCommand(InternshipStatus.PENDING);

        // same object -> returns true
        assertTrue(clearByFirstCommand.equals(clearByFirstCommand));
        assertTrue(clearByThirdCommand.equals(clearByThirdCommand));

        // same values -> returns true
        ClearByCommand clearByFirstCommandCopy = new ClearByCommand(new CompanyName("Company 1"));
        assertTrue(clearByFirstCommand.equals(clearByFirstCommandCopy));

        // different types -> returns false
        assertFalse(clearByFirstCommand.equals(1));

        // null -> returns false
        assertFalse(clearByFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(clearByFirstCommand.equals(clearBySecondCommand));
    }
}
