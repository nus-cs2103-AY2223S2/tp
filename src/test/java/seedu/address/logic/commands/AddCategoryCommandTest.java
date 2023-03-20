package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.stubs.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.category.UserDefinedCategory;

public class AddCategoryCommandTest {
    UserDefinedCategory toAdd = new UserDefinedCategory("test", "test");
    UserDefinedCategory toAdd2 = new UserDefinedCategory("test2", "test2");
    ModelStub model = new ModelStub();
    
    @Test
    public void addCategoryCommandTest() throws CommandException {
        requireNonNull(model);
        model.addCategory(toAdd);
        model.addCategory(toAdd2);
    }

    @Test 
    public void addSameCategoryCommandTest() throws CommandException {
        requireNonNull(model);
        model.addCategory(toAdd);
        assertThrows(CommandException.class, () -> model.addCategory(toAdd));

    }
}
