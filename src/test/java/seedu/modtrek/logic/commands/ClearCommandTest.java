package seedu.modtrek.logic.commands;

import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;
import seedu.modtrek.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyDegreeProgression_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyDegreeProgression_success() {
        Model model = new ModelManager(getTypicalDegreeProgression(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalDegreeProgression(), new UserPrefs());
        expectedModel.setDegreeProgression(new DegreeProgression());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
