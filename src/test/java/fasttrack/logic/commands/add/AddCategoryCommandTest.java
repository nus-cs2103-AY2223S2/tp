package fasttrack.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.category.UserDefinedCategory;

public class AddCategoryCommandTest {
    private UserDefinedCategory toAdd = new UserDefinedCategory("test", "test");
    private UserDefinedCategory toAdd2 = new UserDefinedCategory("test2", "test2");
    private Model model = new ModelManager();
    private AddCategoryCommand addCategoryCommand = new AddCategoryCommand(toAdd);
    private AddCategoryCommand addCategoryCommand2 = new AddCategoryCommand(toAdd2);

    @Test
    public void addCategoryCommandTest() throws CommandException {
        requireNonNull(model);
        addCategoryCommand.execute(model);
        addCategoryCommand2.execute(model);
        assertEquals(model.getFilteredCategoryList().get(0), toAdd);
        assertEquals(model.getFilteredCategoryList().get(1), toAdd2);
    }

    @Test
    public void addSameCategoryCommandTest() throws CommandException {
        requireNonNull(model);
        addCategoryCommand.execute(model);
        assertThrows(CommandException.class, () -> addCategoryCommand.execute(model));
    }

    @Test
    public void addCategoryCommandNullTest() {
        assertThrows(NullPointerException.class, () -> new AddCategoryCommand(null));
    }
}
