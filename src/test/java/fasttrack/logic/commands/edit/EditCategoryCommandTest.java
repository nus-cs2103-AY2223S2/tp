package fasttrack.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.category.Category;
import fasttrack.model.category.UserDefinedCategory;

public class EditCategoryCommandTest {

    private Model model = new ModelManager();
    private Category firstCat = new UserDefinedCategory("test", "test");
    private Category catToBe = new UserDefinedCategory("newCat", "test2");
    @Test
    public void execute_validInput_success() throws CommandException {
        model.addCategory(firstCat);
        EditCategoryCommand editCategoryCommand = new EditCategoryCommand(Index.fromOneBased(1),
               catToBe.getCategoryName(), catToBe.getSummary());
        editCategoryCommand.execute(model);
        Model expectedModel = new ModelManager();
        expectedModel.addCategory(catToBe);
        assertEquals(expectedModel.getFilteredCategoryList(), model.getFilteredCategoryList());
    }

    @Test
    public void execute_invalidInput_failure() throws CommandException {
        model.addCategory(firstCat);
        EditCategoryCommand editCategoryCommand = new EditCategoryCommand(Index.fromOneBased(2), "test", "test");
        assertThrows(CommandException.class, () -> editCategoryCommand.execute(model));
    }
}
