package seedu.medinfo.logic.commands;

import static seedu.medinfo.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.medinfo.model.Model;
import seedu.medinfo.model.ModelManager;

public class ClearCommandTest {

    @Test
    public void execute_emptyMedInfo_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    //    @Test
    //    public void execute_nonEmptyMedInfo_success() {
    //        Model model = new ModelManager(getTypicalMedInfo(), new UserPrefs());
    //        Model expectedModel = new ModelManager(getTypicalMedInfo(), new UserPrefs());
    //        expectedModel.setMedInfo(new MedInfo());
    //
    //        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    //    }

}
