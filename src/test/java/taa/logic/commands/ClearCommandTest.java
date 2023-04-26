package taa.logic.commands;

import org.junit.jupiter.api.Test;

import taa.model.Model;
import taa.model.ModelManager;
import taa.model.UserPrefs;
import taa.storage.TaaData;
import taa.testutil.TypicalPersons;

public class ClearCommandTest {

    @Test
    public void execute_emptyTaaData_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTaaData_success() {
        Model model = new ModelManager(new TaaData(TypicalPersons.getTypicalTaaData()), new UserPrefs());
        Model expectedModel = new ModelManager(new TaaData(TypicalPersons.getTypicalTaaData()), new UserPrefs());
        expectedModel.setTaaData(new TaaData());

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
