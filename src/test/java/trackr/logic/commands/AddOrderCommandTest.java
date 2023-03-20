package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.exceptions.CommandException;
import trackr.logic.commands.order.AddOrderCommand;
import trackr.model.ModelEnum;
import trackr.model.OrderList;
import trackr.model.ReadOnlyOrderList;
import trackr.model.item.Item;
import trackr.model.order.Order;
import trackr.testutil.OrderBuilder;
import trackr.testutil.TestUtil.ModelStub;

public class AddOrderCommandTest {

    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingOrderAdded modelStub = new ModelStubAcceptingOrderAdded();
        Order validTask = new OrderBuilder().build();

        CommandResult commandResult = new AddOrderCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddOrderCommand.MESSAGE_SUCCESS, ModelEnum.ORDER, validTask),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.ordersAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Order validTask = new OrderBuilder().build();
        AddOrderCommand addTaskCommand = new AddOrderCommand(validTask);
        ModelStub modelStub = new AddOrderCommandTest.ModelStubWithOrder(validTask);

        assertThrows(CommandException.class,
                String.format(AddOrderCommand.MESSAGE_DUPLICATE_ITEM, ModelEnum.ORDER,
                        ModelEnum.ORDER), () -> addTaskCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Order chocolateCookies = new OrderBuilder().withOrderName("Chocolate Cookies").build();
        Order cheeseCake = new OrderBuilder().withOrderName("Cheese cake").build();
        AddOrderCommand addChocolateCookiesCommand = new AddOrderCommand(chocolateCookies);
        AddOrderCommand addCheeseCakeCommand = new AddOrderCommand(cheeseCake);

        // same object -> returns true
        assertTrue(addChocolateCookiesCommand.equals(addChocolateCookiesCommand));

        // same values -> returns true
        AddOrderCommand addChocolateCookiesCopy = new AddOrderCommand(chocolateCookies);
        assertTrue(addChocolateCookiesCommand.equals(addChocolateCookiesCopy));

        // different types -> returns false
        assertFalse(addChocolateCookiesCommand.equals(1));

        // null -> returns false
        assertFalse(addChocolateCookiesCommand.equals(null));

        // different task -> returns false
        assertFalse(addChocolateCookiesCommand.equals(addCheeseCakeCommand));
    }

    /**
     * A Model stub that contains a single order.
     */
    private class ModelStubWithOrder extends ModelStub {
        private final Order order;

        ModelStubWithOrder(Order order) {
            requireNonNull(order);
            this.order = order;
        }

        @Override
        public <T extends Item> boolean hasItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            return this.order.isSameItem((Order) item);
        }
    }

    /**
     * A Model stub that always accept the order being added.
     */
    private class ModelStubAcceptingOrderAdded extends ModelStub {
        final ArrayList<Order> ordersAdded = new ArrayList<>();

        @Override
        public <T extends Item> boolean hasItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            return ordersAdded.stream().anyMatch(item::isSameItem);
        }

        @Override
        public <T extends Item> void addItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            ordersAdded.add((Order) item);
        }

        @Override
        public ReadOnlyOrderList getOrderList() {
            return new OrderList();
        }
    }
}
