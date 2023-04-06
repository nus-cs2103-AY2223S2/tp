package fasttrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fasttrack.commons.stubs.ModelStub;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.category.UserDefinedCategory;

public class AddCategoryCommandTest {
    private UserDefinedCategory toAdd = new UserDefinedCategory("test", "test");
    private UserDefinedCategory toAdd2 = new UserDefinedCategory("test2", "test2");
    private ModelStub model = new ModelStub();

    @Test
    public void addCategoryCommandTest() throws CommandException {
        requireNonNull(model);
        model.addCategory(toAdd);
        model.addCategory(toAdd2);
        assertEquals(model.getFilteredCategoryList().get(0), toAdd);
        assertEquals(model.getFilteredCategoryList().get(1), toAdd2);
    }

    @Test
    public void addSameCategoryCommandTest() throws CommandException {
        requireNonNull(model);
        model.addCategory(toAdd);
        assertThrows(CommandException.class, () -> model.addCategory(toAdd));

    }
}
