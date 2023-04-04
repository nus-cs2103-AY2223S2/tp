package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.stubs.ModelStub;
import seedu.address.logic.commands.edit.EditCategoryCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.category.Category;
import seedu.address.model.category.UserDefinedCategory;

public class EditCategoryTest {

    private ModelStub model = new ModelStub();
    private Category toDelete = new UserDefinedCategory("test", "test");
    private Category toDelete2 = new UserDefinedCategory("test2", "test2");
    @Test
    public void execute_validInput_success() throws CommandException {
        model.addCategory(toDelete);
        EditCategoryCommand editCategoryCommand = new EditCategoryCommand(Index.fromOneBased(1),
                "test2", "test2");
        editCategoryCommand.execute(model);
        ModelStub expectedModel = new ModelStub();
        expectedModel.addCategory(toDelete2);
        assertEquals(expectedModel.getFilteredCategoryList(), model.getFilteredCategoryList());
    }

    @Test
    public void execute_invalidInput_failure() throws CommandException {
        model.addCategory(toDelete);
        EditCategoryCommand editCategoryCommand = new EditCategoryCommand(Index.fromOneBased(2), "test", "test");
        assertThrows(CommandException.class, () -> editCategoryCommand.execute(model));
    }
}
