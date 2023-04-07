package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalMenuItems.CHOCOLATE_COOKIE_M;
import static trackr.testutil.TypicalMenuItems.CUPCAKE_M;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES_O;
import static trackr.testutil.TypicalSuppliers.ALICE;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trackr.logic.commands.supplier.AddSupplierCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Menu;
import trackr.model.ModelEnum;
import trackr.model.OrderList;
import trackr.model.ReadOnlyMenu;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.SupplierList;
import trackr.model.TaskList;
import trackr.model.item.Item;
import trackr.model.menu.MenuItem;
import trackr.testutil.TestUtil.ModelStub;

public class UploadCsvCommandTest {

    private ModelStubWithItems model;

    @Test
    public void constructor_nullCommand_throwsNullException() {
        assertThrows(NullPointerException.class, () -> new UploadCsvCommand(null));
    }

    @Test
    public void execute_uploadCsv_addsItemSuccess() throws Exception {
        model = new ModelStubWithItems();
        List<String> csvInput = SampleCommands.withOneCommand(SampleCommands.VALID_ADD_S);
        CommandResult commandResult = new UploadCsvCommand(csvInput).execute(model);

        assertEquals(UploadCsvCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(ALICE), model.itemsAdded);
    }

    @Test
    public void execute_uploadCsv_addsTaskSuccess() throws Exception {
        model = new ModelStubWithItems();
        List<String> csvInput = SampleCommands.withOneCommand(SampleCommands.VALID_ADD_T);
        CommandResult commandResult = new UploadCsvCommand(csvInput).execute(model);

        assertEquals(UploadCsvCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(SORT_INVENTORY_N), model.itemsAdded);
    }

    @Test
    public void execute_uploadCsv_addsMenuSuccess() throws Exception {
        model = new ModelStubWithItems();
        List<String> csvInput = SampleCommands.withOneCommand(SampleCommands.VALID_ADD_M);
        CommandResult commandResult = new UploadCsvCommand(csvInput).execute(model);

        assertEquals(UploadCsvCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(CUPCAKE_M), model.itemsAdded);
    }

    @Test
    public void execute_uploadCsv_addsOrderSuccess() throws Exception {
        model = new ModelStubWithItems();
        List<String> csvInput = SampleCommands.withOneCommand(SampleCommands.VALID_ADD_O);
        CommandResult commandResult = new UploadCsvCommand(csvInput).execute(model);

        assertEquals(UploadCsvCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(CHOCOLATE_COOKIES_O), model.itemsAdded);
    }

    @Test
    public void execute_uploadCsv_addsMultipleItemSuccess() throws Exception {
        model = new ModelStubWithItems();
        List<String> csvInput = SampleCommands.withMultipleValidCommand(SampleCommands.VALID_ADD_T,
                                                                        SampleCommands.VALID_ADD_S);
        CommandResult commandResult = new UploadCsvCommand(csvInput).execute(model);

        assertEquals(UploadCsvCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(SORT_INVENTORY_N, ALICE), model.itemsAdded);
    }

    @Test
    public void execute_uploadInvalidFormatCsv_throwsParseException() {
        model = new ModelStubWithItems();
        List<String> csvInput = SampleCommands.withOneCommand(SampleCommands.INVALID);

        Command command = new UploadCsvCommand(csvInput);

        assertThrows(ParseException.class,
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddSupplierCommand.MESSAGE_USAGE), () -> command.execute(model));
    }

    /**
     * A Model stub that always accept Orders, Suppliers, Task and MenuItems being added.
     */
    private class ModelStubWithItems extends ModelStub {
        public final ArrayList<Item> itemsAdded = new ArrayList<>();

        @Override
        public <T extends Item> boolean hasItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            return itemsAdded.stream().anyMatch(item::isSameItem);
        }

        @Override
        public <T extends Item> void addItem(T item, ModelEnum modelEnum) {
            requireNonNull(item);
            itemsAdded.add((T) item);
        }

        @Override
        public ObservableList<MenuItem> getFilteredMenu() {
            ObservableList<MenuItem> sampleMenu = FXCollections.observableArrayList();
            sampleMenu.add(CHOCOLATE_COOKIE_M);
            return sampleMenu;
        }

        @Override
        public ReadOnlySupplierList getSupplierList() {
            return new SupplierList();
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            return new TaskList();
        }

        @Override
        public ReadOnlyMenu getMenu() {
            return new Menu();
        }

        @Override
        public ReadOnlyOrderList getOrderList() {
            return new OrderList();
        }
    }

    private static class SampleCommands {
        /** String commnads that corresponds to Typical valid items */
        public static final String VALID_ADD_S = "add_s n/Alice Pauline p/94351253 e/alice@example.com t/friends "
                                                + "a/123, Jurong West Ave 6, #08-111";
        public static final String VALID_ADD_O = "add_o on/Chocolate Cookies d/01/01/2024 s/N q/3 n/John Doe "
                                                + "p/98765432 a/123 Main Street";
        public static final String VALID_ADD_T = "add_task n/Sort Inventory d/01/01/2024 s/N";
        public static final String VALID_ADD_M = "add_item n/Cupcakes pr/3 c/1";

        public static final String INVALID = "add_s p/94351253 e/alice@example.com a/123, Jurong West Ave 6, #08-111";

        public static List<String> withOneCommand(String c1) {
            List<String> command = new ArrayList<String>();
            command.add(c1);
            return command;
        }

        public static List<String> withMultipleValidCommand(String c1, String c2) {
            List<String> commands = new ArrayList<String>();
            commands.add(c1);
            commands.add(c2);
            return commands;
        }
    }
}
