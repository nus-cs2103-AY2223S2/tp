package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests {interaction with the Model} for {@code AddModuleCommand}.
 */
public class AddModuleCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalModules.getTypicalTracker(), new UserPrefs());
    }

    @Test
    public void execute_newModule_success() {
        Module validModule = TypicalModules.CS2107;

        Model expectedModel = new ModelManager(model.getTracker(), new UserPrefs());
        expectedModel.addModule(validModule);

        assertCommandSuccess(new AddModuleCommand(validModule), model,
                String.format(AddModuleCommand.MESSAGE_SUCCESS, validModule), expectedModel);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module moduleInList = (Module) model.getTracker().getModuleList().get(0);
        assertCommandFailure(new AddModuleCommand(moduleInList), model, AddModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

}
