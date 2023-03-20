package tfifteenfour.clipboard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tfifteenfour.clipboard.testutil.TypicalStudents.getTypicalRoster;

import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.ModelManager;
import tfifteenfour.clipboard.model.UserPrefs;
import tfifteenfour.clipboard.model.student.StudentTakingModulePredicate;

public class ModuleCommandTest {
    private Model model = new ModelManager(getTypicalRoster(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalRoster(), new UserPrefs());

    @Test
    public void equals() {
        StudentTakingModulePredicate firstPredicate =
                new StudentTakingModulePredicate("CS2103T");
        StudentTakingModulePredicate secondPredicate =
                new StudentTakingModulePredicate("CS2106");

        ModuleCommand firstModuleCommand = new ModuleCommand(firstPredicate);
        ModuleCommand secondModuleCommand = new ModuleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstModuleCommand.equals(firstModuleCommand));

        // same values -> returns true
        ModuleCommand firstModuleCommandCopy = new ModuleCommand(firstPredicate);
        assertTrue(firstModuleCommand.equals(firstModuleCommandCopy));

        // different types -> returns false
        assertFalse(firstModuleCommand.equals(1));

        // null -> returns false
        assertFalse(firstModuleCommand.equals(null));

        // different module -> returns false
        assertFalse(firstModuleCommand.equals(secondModuleCommand));
    }

}
