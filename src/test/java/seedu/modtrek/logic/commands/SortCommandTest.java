package seedu.modtrek.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;
import seedu.modtrek.model.UserPrefs;

public class SortCommandTest {

    private Model model = new ModelManager(getTypicalDegreeProgression(), new UserPrefs());

    @Test
    public void sortSuccess() {
        assertTrue(model.getSort().equals("SEMYEAR"));
        try {
            new SortCommand("grade").execute(model);
            assertTrue(model.getSort().equals("GRADE"));
        } catch (Exception e) {
            assertFalse(true);
        }
        try {
            new SortCommand("credit").execute(model);
            assertTrue(model.getSort().equals("CREDIT"));
        } catch (Exception e) {
            assertFalse(true);
        }
        try {
            new SortCommand("semyear").execute(model);
            assertTrue(model.getSort().equals("SEMYEAR"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    public void equals() {
        SortCommand sortFirstCommand = new SortCommand("grade");
        SortCommand sortSecondCommand = new SortCommand("credit");

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand("grade");
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different module -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }
}
