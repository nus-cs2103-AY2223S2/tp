package fasttrack.logic.commands.list;

import static fasttrack.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.UserPrefs;
import fasttrack.testutil.TypicalCategories;

public class ListCategoriesCommandTest {
    private Model model = new ModelManager(TypicalCategories.getTypicalExpenseTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalCategories.getTypicalExpenseTracker(), new UserPrefs());

    @Test
    public void execute() {
        String expectedMessage = ListCategoryCommand.MESSAGE_SUCCESS;
        ListCategoryCommand command = new ListCategoryCommand();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
