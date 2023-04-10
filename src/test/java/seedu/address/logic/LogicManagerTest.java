package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_FISH_INDEX_OUTOFBOUNDS;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.FEEDING_INTERVAL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LAST_FED_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SPECIES_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TANK_DESC;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFishes.AMY;
import static seedu.address.testutil.TypicalTanks.getTypicalTanks;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.fish.FishAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.TankList;
import seedu.address.model.UserPrefs;
import seedu.address.model.fish.Fish;
import seedu.address.storage.StorageManager;
import seedu.address.storage.fish.JsonAddressBookStorage;
import seedu.address.storage.tank.JsonTankListStorage;
import seedu.address.storage.tank.readings.ammonialevels.FullReadingLevelsStorage;
import seedu.address.storage.tank.readings.ammonialevels.JsonFullReadingLevelsStorage;
import seedu.address.storage.task.JsonTaskListStorage;
import seedu.address.storage.userprefs.JsonUserPrefsStorage;
import seedu.address.testutil.FishBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonTaskListStorage taskListStorage = new JsonTaskListStorage(temporaryFolder.resolve("taskList.json"));
        JsonTankListStorage tankListStorage = new JsonTankListStorage(temporaryFolder.resolve("tankList.json"));
        FullReadingLevelsStorage ammoniaLevelsStorage = new JsonFullReadingLevelsStorage(temporaryFolder
                .resolve("ammonialevels"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage,
                taskListStorage, tankListStorage, ammoniaLevelsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "fish delete 9";
        assertCommandException(deleteCommand, MESSAGE_FISH_INDEX_OUTOFBOUNDS);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD + " fishes";
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS_FISHES, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonTaskListStorage taskListStorage = new JsonTaskListStorage(temporaryFolder.resolve("taskList.json"));
        JsonTankListStorage tankListStorage = new JsonTankListStorage(temporaryFolder.resolve("tankList.json"));
        FullReadingLevelsStorage ammoniaLevelsStorage = new JsonFullReadingLevelsStorage(temporaryFolder
                .resolve("ammonialevels"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage,
                taskListStorage, tankListStorage, ammoniaLevelsStorage);
        // Initializes Tanks
        TankList tankList = new TankList();
        tankList.setTanks(getTypicalTanks());
        model.setTankList(tankList);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = FishAddCommand.COMMAND_WORD + " " + FishAddCommand.FISH_COMMAND_WORD
                + NAME_DESC_AMY + LAST_FED_DATE_DESC_AMY
                + SPECIES_DESC_AMY + FEEDING_INTERVAL_DESC_AMY + TANK_DESC;
        Fish expectedFish = new FishBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();

        // Manually sets tanklist
        expectedModel.setTankList(tankList);
        expectedModel.addFish(expectedFish);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredFishList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredFishList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
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
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getTaskList(),
                model.getTankList(), model.getFullReadingLevels());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
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
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonAddressBookStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
