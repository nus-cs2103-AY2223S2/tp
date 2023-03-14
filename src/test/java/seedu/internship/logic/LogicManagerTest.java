package seedu.internship.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX;
import static seedu.internship.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.internship.logic.commands.CommandTestUtil.POSITION_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.COMPANY_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.STATUS_DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.DESCRIPTION_DESC_ML1;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalInternships.ML1;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.internship.logic.commands.AddCommand;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.ListCommand;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.ReadOnlyInternshipCatalogue;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.internship.Internship;
import seedu.internship.storage.JsonInternshipCatalogueStorage;
import seedu.internship.storage.JsonUserPrefsStorage;
import seedu.internship.storage.StorageManager;
import seedu.internship.testutil.InternshipBuilder;


public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonInternshipCatalogueStorage internshipCatalogueStorage =
                new JsonInternshipCatalogueStorage(temporaryFolder.resolve("internshipcatalogue.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(internshipCatalogueStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonInternshipCatalogueStorage internshipCatalogueStorage =
                new JsonInternshipCatalogueIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptioninternshipcatalogue.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(internshipCatalogueStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + POSITION_DESC_ML1 + COMPANY_DESC_ML1 + STATUS_DESC_ML1 +
                DESCRIPTION_DESC_ML1;
        Internship expectedInternship = new InternshipBuilder(ML1).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addInternship(expectedInternship);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredInternshipList().remove(0));
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
        Model expectedModel = new ModelManager(model.getInternshipCatalogue(), new UserPrefs());
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
    private static class JsonInternshipCatalogueIoExceptionThrowingStub extends JsonInternshipCatalogueStorage {
        private JsonInternshipCatalogueIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveInternshipCatalogue(ReadOnlyInternshipCatalogue internshipCatalogue, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
