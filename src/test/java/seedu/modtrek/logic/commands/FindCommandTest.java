package seedu.modtrek.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.TypicalModules.CS2100;
import static seedu.modtrek.testutil.TypicalModules.ST2334;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;
import seedu.modtrek.model.UserPrefs;
import seedu.modtrek.model.module.ModuleCodePredicate;

class FindCommandTest {
    private Model model = new ModelManager(getTypicalDegreeProgression(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalDegreeProgression(), new UserPrefs());

    @Test
    public void equals() {
        ModuleCodePredicate firstPredicate =
                new ModuleCodePredicate(CS2100.getCode().toString(), "", "", "", new HashSet<>());
        ModuleCodePredicate secondPredicate =
                new ModuleCodePredicate(ST2334.getCode().toString(), "", "", "", new HashSet<>());

        FindCommand findFirstCommand = new FindCommand(firstPredicate, new ArrayList<>());
        FindCommand findSecondCommand = new FindCommand(secondPredicate, new ArrayList<>());

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, new ArrayList<>());
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

}
