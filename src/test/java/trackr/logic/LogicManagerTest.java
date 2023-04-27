package trackr.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static trackr.commons.core.Messages.MESSAGE_INVALID_SUPPLIER_DISPLAYED_INDEX;
import static trackr.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static trackr.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static trackr.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static trackr.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static trackr.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalSuppliers.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.collections.ObservableList;
import trackr.commons.core.GuiSettings;
import trackr.logic.commands.CommandResult;
import trackr.logic.commands.ListItemCommand;
import trackr.logic.commands.exceptions.CommandException;
import trackr.logic.commands.supplier.AddSupplierCommand;
import trackr.logic.commands.supplier.ListSupplierCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.ReadOnlyMenu;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.UserPrefs;
import trackr.model.order.Order;
import trackr.model.person.Supplier;
import trackr.model.task.Task;
import trackr.storage.JsonTrackrStorage;
import trackr.storage.JsonUserPrefsStorage;
import trackr.storage.StorageManager;
import trackr.testutil.SupplierBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    //@@author liumc-sg-reused
    @BeforeEach
    public void setUp() {
        JsonTrackrStorage trackrStorage =
                new JsonTrackrStorage(temporaryFolder.resolve("trackr.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(trackrStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }
    //@@author

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete_supplier 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_SUPPLIER_DISPLAYED_INDEX);
    }

    //@@author liumc-sg-reused
    @Test
    public void execute_validCommand_success() throws Exception {
        String listSupplierCommand = ListSupplierCommand.COMMAND_WORD;
        assertCommandSuccess(listSupplierCommand,
                String.format(ListItemCommand.MESSAGE_SUCCESS, ModelEnum.SUPPLIER.toString().toLowerCase()),
                model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonTrackrIoExceptionThrowingStub
        JsonTrackrStorage trackrStorage =
                new JsonTrackrIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTrackr.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(trackrStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addSupplierCommand = AddSupplierCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Supplier expectedSupplier = new SupplierBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addItem(expectedSupplier, ModelEnum.SUPPLIER);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addSupplierCommand, CommandException.class, expectedMessage, expectedModel);
    }
    //@@author

    @Test
    public void getFilteredSupplierList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredSupplierList().remove(0));
    }

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTaskList().remove(0));
    }
    //@@author

    //@@author chongweiguan-reused
    @Test
    public void getFilteredOrderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredOrderList().remove(0));
    }
    //@@author

    @Test
    public void getSupplierList() {
        ReadOnlySupplierList expected = model.getSupplierList();
        assertEquals(expected, logic.getSupplierList());
    }

    @Test
    public void getTaskList() {
        ReadOnlyTaskList expected = model.getTaskList();
        assertEquals(expected, logic.getTaskList());
    }

    //@@author chongweiguan-reused
    @Test
    public void getOrderList() {
        ReadOnlyOrderList expected = model.getOrderList();
        assertEquals(expected, logic.getOrderList());
    }
    //@@author

    public void getFilteredSupplierList() {
        ObservableList<Supplier> expected = model.getFilteredSupplierList();
        assertEquals(expected, logic.getFilteredSupplierList());
    }

    @Test
    public void getFilteredTaskList() {
        ObservableList<Task> expected = model.getFilteredTaskList();
        assertEquals(expected, logic.getFilteredTaskList());
    }

    //@@author chongweiguan-reused
    @Test
    public void getFilteredOrderList() {
        ObservableList<Order> expected = model.getFilteredOrderList();
        assertEquals(expected, logic.getFilteredOrderList());
    }
    //@@author

    @Test
    public void getTrackrFilePath() {
        Path expected = model.getTrackrFilePath();
        assertEquals(expected, logic.getTrackrFilePath());
    }

    //@@author liumc-sg-reused
    @Test
    public void getGuiSettings() {
        GuiSettings expected = new GuiSettings(0, 0, 0, 0);
        logic.setGuiSettings(expected);
        assertEquals(expected, logic.getGuiSettings());
    }
    //@@author

    @Test
    public void setGuiSettings() {
        GuiSettings expected = model.getGuiSettings();
        assertEquals(expected, logic.getGuiSettings());
    }


    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel = new ModelManager(model.getSupplierList(), model.getTaskList(),
                model.getMenu(), model.getOrderList(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonTrackrIoExceptionThrowingStub extends JsonTrackrStorage {
        private JsonTrackrIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTrackr(ReadOnlySupplierList addressBook, ReadOnlyTaskList taskList,
                               ReadOnlyMenu menu, ReadOnlyOrderList orderList, Path filePath)
                throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
