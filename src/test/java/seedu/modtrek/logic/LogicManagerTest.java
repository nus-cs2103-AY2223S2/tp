package seedu.modtrek.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modtrek.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.modtrek.logic.commands.CommandTestUtil.CODE_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.CREDIT_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.GRADE_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.SEMYEAR_DESC_CS1101S;
import static seedu.modtrek.testutil.Assert.assertThrows;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.modtrek.commons.core.Messages;
import seedu.modtrek.logic.commands.AddCommand;
import seedu.modtrek.logic.commands.CommandResult;
import seedu.modtrek.logic.commands.ViewCommand;
import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;
import seedu.modtrek.model.ReadOnlyDegreeProgression;
import seedu.modtrek.model.UserPrefs;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.storage.JsonDegreeProgressionStorage;
import seedu.modtrek.storage.JsonUserPrefsStorage;
import seedu.modtrek.storage.StorageManager;
import seedu.modtrek.testutil.ModuleBuilder;


public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonDegreeProgressionStorage addressBookStorage =
                new JsonDegreeProgressionStorage(temporaryFolder.resolve("degreeprogression.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_findCommandGetFiltersList_success() throws CommandException, ParseException {
        String validCommandModule = "find CS1101S";
        assertCommandSuccess(validCommandModule, String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW,
                model.getFilteredModuleList().size()), model);
        List<String> filtersList = new ArrayList<>();
        filtersList.add("CS1101S");
        assertEquals(logic.getFiltersList(), filtersList);

        String validCommandPrefix = "find /m CS /g A";
        assertCommandSuccess(validCommandPrefix, String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW,
                model.getFilteredModuleList().size()), model);
        filtersList.clear();
        filtersList.add("/m CS");
        filtersList.add("/g A");
        assertEquals(logic.getFiltersList(), filtersList);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String viewModulesCommand = ViewCommand.COMMAND_WORD + " modules";
        assertCommandSuccess(viewModulesCommand, ViewCommand.MESSAGE_MODULES_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonDegreeProgressionStorage addressBookStorage =
                new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + CODE_DESC_CS1101S + CREDIT_DESC_CS1101S + SEMYEAR_DESC_CS1101S
                + GRADE_DESC_CS1101S;
        Module expectedModule = new ModuleBuilder(CS1101S).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addModule(expectedModule);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredModuleList().remove(0));
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
        Model expectedModel = new ModelManager(model.getDegreeProgression(), new UserPrefs());
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
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonDegreeProgressionStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveDegreeProgression(ReadOnlyDegreeProgression addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
