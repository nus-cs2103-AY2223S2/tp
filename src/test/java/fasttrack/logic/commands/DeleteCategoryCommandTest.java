package fasttrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fasttrack.commons.core.index.Index;
import fasttrack.commons.stubs.ModelStub;
import fasttrack.logic.commands.delete.DeleteCategoryCommand;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.category.Category;
import fasttrack.model.category.UserDefinedCategory;

public class DeleteCategoryCommandTest {
    private ModelStub model = new ModelStub();
    private Category toDelete = new UserDefinedCategory("test", "test");
    private Category toDelete2 = new UserDefinedCategory("test2", "test2");

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        model.addCategory(toDelete);
        model.addCategory(toDelete2);
        DeleteCategoryCommand deleteCategoryCommand = new DeleteCategoryCommand(Index.fromOneBased(1));
        deleteCategoryCommand.execute(model);
        ModelStub expectedModel = new ModelStub();
        expectedModel.addCategory(toDelete2);
        assertEquals(expectedModel.getFilteredCategoryList(), model.getFilteredCategoryList());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() throws CommandException {
        model.addCategory(toDelete);
        model.addCategory(toDelete2);
        DeleteCategoryCommand deleteCategoryCommand = new DeleteCategoryCommand(Index.fromOneBased(3));
        // command should throw an exception
        assertThrows(CommandException.class, () -> deleteCategoryCommand.execute(model));
    }

}
