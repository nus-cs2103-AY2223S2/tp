package seedu.modtrek.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    private final Model model = new ModelManager(getTypicalDegreeProgression(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalDegreeProgression(), new UserPrefs());

    @Test
    public void equals() {
        ModuleCodePredicate firstPredicate =
                new ModuleCodePredicate(true, CS2100.getCode().toString(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        ModuleCodePredicate secondPredicate =
                new ModuleCodePredicate(true, ST2334.getCode().toString(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());

        FindCommand findFirstCommand = new FindCommand(firstPredicate, new ArrayList<>());
        FindCommand findSecondCommand = new FindCommand(secondPredicate, new ArrayList<>());

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, new ArrayList<>());
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different person -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

}
