package seedu.techtrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.techtrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.techtrack.commons.core.index.Index;

public class ViewCommandTest {

    @Test
    public void constructor_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        ViewCommand view1Based1 = new ViewCommand(Index.fromOneBased(1));
        assertThrows(NullPointerException.class, () -> view1Based1.execute(null));
    }

    @Test
    public void equals() {
        ViewCommand view1Based1 = new ViewCommand(Index.fromOneBased(1));
        ViewCommand view0Based0 = new ViewCommand(Index.fromZeroBased(0));
        ViewCommand view0Based1 = new ViewCommand(Index.fromZeroBased(1));

        // same object -> returns true
        assertTrue(view1Based1.equals(view1Based1));

        // same values -> returns true
        assertTrue(view1Based1.equals(view0Based0));

        // different types -> returns false
        assertFalse(view0Based1.equals(1));

        // null -> returns false
        assertFalse(view0Based1.equals(null));

        // different role -> returns false
        assertFalse(view0Based0.equals(view0Based1));
    }

}
