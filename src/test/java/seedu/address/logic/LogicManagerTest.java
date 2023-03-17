package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.AGE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_VOLUNTEER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REGION_DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalVolunteers.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddVolunteerCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyElderly;
import seedu.address.model.ReadOnlyPair;
import seedu.address.model.ReadOnlyVolunteer;
import seedu.address.model.person.Volunteer;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.elderly.JsonElderlyStorage;
import seedu.address.storage.pair.JsonPairStorage;
import seedu.address.storage.volunteer.JsonVolunteerStorage;
import seedu.address.testutil.ModelManagerBuilder;
import seedu.address.testutil.VolunteerBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManagerBuilder().build();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonPairStorage friendlyLinkStorage =
                new JsonPairStorage(temporaryFolder.resolve("friendlylink.json"));
        JsonElderlyStorage elderlyStorage =
                new JsonElderlyStorage(temporaryFolder.resolve("elderly.json"));
        JsonVolunteerStorage volunteerStorage =
                new JsonVolunteerStorage(temporaryFolder.resolve("volunteer.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage =
                new StorageManager(friendlyLinkStorage, elderlyStorage, volunteerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete_volunteer T9999999I";
        assertCommandException(deleteCommand, Messages.MESSAGE_NRIC_NOT_EXIST);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonFriendlyLinkIoExceptionThrowingStub
        JsonPairStorage friendlyLinkStorage =
                new JsonPairIoExceptionThrowingStub(
                        temporaryFolder.resolve("ioExceptionFriendlyLink.json"));
        JsonElderlyStorage elderlyStorage =
                new JsonElderlyIoExceptionThrowingStub(
                        temporaryFolder.resolve("ioExceptionElderly.json"));
        JsonVolunteerStorage volunteerStorage =
                new JsonVolunteerIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionVolunteer.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));

        StorageManager storage =
                new StorageManager(friendlyLinkStorage, elderlyStorage, volunteerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add volunteer command
        String addVolunteerCommand = AddVolunteerCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NRIC_VOLUNTEER_DESC_AMY + AGE_DESC_AMY + REGION_DESC_AMY;
        Volunteer expectedVolunteer = new VolunteerBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManagerBuilder().build();
        expectedModel.addVolunteer(expectedVolunteer);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addVolunteerCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredVolunteerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredVolunteerList().remove(0));
    }

    @Test
    public void getFilteredElderlyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredElderlyList().remove(0));
    }

    @Test
    public void getFilteredPairList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPairList().remove(0));
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
        Model expectedModel = new ModelManagerBuilder()
                .withFriendlyLink(model.getFriendlyLink())
                .build();
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
    private static class JsonPairIoExceptionThrowingStub extends JsonPairStorage {
        private JsonPairIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void savePair(ReadOnlyPair friendlyLink, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonElderlyIoExceptionThrowingStub extends JsonElderlyStorage {
        private JsonElderlyIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveElderly(ReadOnlyElderly elderly, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }


    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonVolunteerIoExceptionThrowingStub extends JsonVolunteerStorage {
        public JsonVolunteerIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveVolunteer(ReadOnlyVolunteer volunteer, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
