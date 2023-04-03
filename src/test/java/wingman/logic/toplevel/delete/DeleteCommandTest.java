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

import org.mockito.Mock;
import org.mockito.Mockito;

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

    @Mock
    DeleteFunction<Item> deleteFunction;

    @Mock
    GetManagerFunction<Item> getManagerFunction;

    @Mock
    ReadOnlyItemManager<Item> itemManager;

    @Mock
    Item item;

    @Mock
    Model model;

    @Test
    public void testExecute_doesNotThrow() throws CommandException {
        when(model.isIndexValid(1, itemManager)).thenReturn(true);
        when(getManagerFunction.get(model)).thenReturn(itemManager);
        when(itemManager.getItem(0)).thenReturn(item);
        DeleteCommand<Item> deleteCommand = new DeleteCommand<>(
                1,
                getManagerFunction,
                deleteFunction
        );

        CommandResult result = deleteCommand.execute(model);
        verify(deleteFunction).delete(eq(model), eq(item));
        assertTrue(result.getFeedbackToUser().contains("Deleted"));
    }

    @Test
    public void testExecute_throwOutOfBoundsException() throws CommandException {
        when(model.isIndexValid(1, itemManager)).thenReturn(false);
        when(getManagerFunction.get(model)).thenReturn(itemManager);

        DeleteCommand<Item> deleteCommand = new DeleteCommand<>(
                1,
                getManagerFunction,
                deleteFunction
        );

        assertThrows(
                CommandException.class,
                () -> deleteCommand.execute(model)
        );

        verify(getManagerFunction, times(1)).get(model);
    }
}
