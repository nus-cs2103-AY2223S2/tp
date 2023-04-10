package codoc.logic.commands;

import static codoc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static codoc.testutil.TypicalPersons.getTypicalCodoc;

import org.junit.jupiter.api.Test;

import codoc.model.Codoc;
import codoc.model.Model;
import codoc.model.ModelManager;
import codoc.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyCodoc_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyCodoc_success() {
        Model model = new ModelManager(getTypicalCodoc(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalCodoc(), new UserPrefs());
        expectedModel.setCodoc(new Codoc());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
