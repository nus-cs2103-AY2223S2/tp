package wingman.logic.toplevel.delete;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.Mokito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.item.Item;


/**
 * Test cases for {@code DeleteCommand}
 */
@ExtendWith(MockitoExtension.class)
public class DeleteCommandTest {
    @Test
    public void testExecute_doesNotThrow() throws CommandException {
        DeleteFunction<Item> deleteFunction = mock(DeleteFunction.class);
        GetManagerFunction<Item> getManagerFunction = mock(GetManagerFunction.class);
        ReadOnlyItemManager<Item> itemManager = mock(ReadOnlyItemManager.class);

        Item item = mock(Item.class);
        Model model = mock(Model.class);

        when(model.isIndexValid(1, itemManager)).thenReturn(true);
        when(getManagerFunction.get(model)).thenReturn(itemManager);

        DeleteCommand<Item> deleteCommand = new DeleteCommand<>(
                1,
                getManagerFunction,
                deleteFunction
        );
        Mokito.lenient().when(deleteFunction).delete(eq(model), eq(item));

        CommandResult result = deleteCommand.execute(model);
        verify(deleteFunction).delete(eq(model), eq(item));

        assertTrue(result.getFeedbackToUser().contains("Deleted"));
    }

    @Test
    public void testExecute_throwOutOfBoundsException() throws CommandException {
        DeleteFunction<Item> deleteFunction = mock(DeleteFunction.class);
        GetManagerFunction<Item> getManagerFunction = mock(GetManagerFunction.class);
        ReadOnlyItemManager<Item> itemManager = mock(ReadOnlyItemManager.class);

        Item item = mock(Item.class);
        Model model = mock(Model.class);

        when(model.isIndexValid(1, itemManager)).thenReturn(false);
        when(getManagerFunction.get(model)).thenReturn(itemManager);

        DeleteCommand<Item> deleteCommand = new DeleteCommand<>(
                1,
                getManagerFunction,
                deleteFunction
        );

        assertThrows(CommandException.class, () -> deleteCommand.execute(model));

        verify(getManagerFunction, times(1)).get(model);
    }
}
