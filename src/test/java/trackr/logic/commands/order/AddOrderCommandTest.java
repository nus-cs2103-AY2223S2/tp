package trackr.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalMenuItems.CHOCOLATE_COOKIE_M;
import static trackr.testutil.TypicalMenuItems.CUPCAKE_M;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trackr.logic.commands.AddItemCommand;
import trackr.logic.commands.CommandResult;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.ModelEnum;
import trackr.model.OrderList;
import trackr.model.ReadOnlyOrderList;
import trackr.model.item.Item;
import trackr.model.menu.MenuItem;
import trackr.model.order.Order;
import trackr.testutil.OrderBuilder;
import trackr.testutil.TestUtil.ModelStub;

public class AddOrderCommandTest {

    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null));
    }

    @Test
    public void execute_orderAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingOrderAdded modelStub = new ModelStubAcceptingOrderAdded();
        Order validOrder = new OrderBuilder().build();

        CommandResult commandResult = new AddOrderCommand(validOrder).execute(modelStub);

        assertEquals(String.format(AddItemCommand.MESSAGE_SUCCESS, ModelEnum.ORDER, ModelEnum.ORDER),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOrder), modelStub.ordersAdded);
    }

    @Test
    public void execute_duplicateOrder_throwsCommandException() {
        Order validOrder = new OrderBuilder().build();
        AddOrderCommand addOrderCommand = new AddOrderCommand(validOrder);
        ModelStub modelStub = new AddOrderCommandTest.ModelStubWithOrder(validOrder);

        assertThrows(CommandException.class,
                String.format(AddItemCommand.MESSAGE_DUPLICATE_ITEM, ModelEnum.ORDER,
                        ModelEnum.ORDER), () -> addOrderCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Order chocolateCookies = new OrderBuilder().withOrderItem(CHOCOLATE_COOKIE_M).build();
        Order cupcake = new OrderBuilder().withOrderItem(CUPCAKE_M).build();
        AddOrderCommand addChocolateCookiesCommand = new AddOrderCommand(chocolateCookies);
        AddOrderCommand addCupcakeCommand = new AddOrderCommand(cupcake);

        // same object -> returns true
        assertTrue(addChocolateCookiesCommand.equals(addChocolateCookiesCommand));

        // same values -> returns true
        AddOrderCommand addChocolateCookiesCopy = new AddOrderCommand(chocolateCookies);
        assertTrue(addChocolateCookiesCommand.equals(addChocolateCookiesCopy));

        // different types -> returns false
        assertFalse(addChocolateCookiesCommand.equals(1));

        // null -> returns false
        assertFalse(addChocolateCookiesCommand.equals(null));

        // different order -> returns false
        assertFalse(addChocolateCookiesCommand.equals(addCupcakeCommand));
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
        public ObservableList<MenuItem> getFilteredMenu() {
            ObservableList<MenuItem> sampleMenu = FXCollections.observableArrayList();
            sampleMenu.add(CHOCOLATE_COOKIE_M);
            return sampleMenu;
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
        public ObservableList<MenuItem> getFilteredMenu() {
            ObservableList<MenuItem> sampleMenu = FXCollections.observableArrayList();
            sampleMenu.add(CHOCOLATE_COOKIE_M);
            return sampleMenu;
        }

        @Override
        public ReadOnlyOrderList getOrderList() {
            return new OrderList();
        }
    }
}
