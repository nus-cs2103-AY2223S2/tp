package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalTuteeManagingSystem;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.logic.commands.FilterCommand.FilterTuteeDescription;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
class FilterCommandTest {

    @Test
    public void equals() {
        FilterTuteeDescription firstFilterTuteeDescription = new FilterCommand.FilterTuteeDescription();
        firstFilterTuteeDescription.setSubjectToFilter("math");

        FilterTuteeDescription secondFilterTuteeDescription = new FilterCommand.FilterTuteeDescription();
        secondFilterTuteeDescription.setSubjectToFilter("english");

        FilterCommand filterFirstCommand = new FilterCommand(firstFilterTuteeDescription);
        FilterCommand filterSecondCommand = new FilterCommand(secondFilterTuteeDescription);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstFilterTuteeDescription);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different tutee -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }
}
