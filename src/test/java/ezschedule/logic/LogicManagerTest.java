package ezschedule.logic;

import static ezschedule.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static ezschedule.logic.commands.CommandTestUtil.DATE_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.END_TIME_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.NAME_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.START_TIME_DESC_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_NAME_A;
import static ezschedule.testutil.Assert.assertThrows;
import static ezschedule.testutil.TypicalEvents.EVENT_A;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ezschedule.commons.core.Messages;
import ezschedule.logic.commands.AddCommand;
import ezschedule.logic.commands.CommandResult;
import ezschedule.logic.commands.FindCommand;
import ezschedule.logic.commands.ListCommand;
import ezschedule.logic.commands.exceptions.CommandException;
import ezschedule.logic.parser.exceptions.ParseException;
import ezschedule.model.Model;
import ezschedule.model.ModelManager;
import ezschedule.model.ReadOnlyScheduler;
import ezschedule.model.UserPrefs;
import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.EventContainsKeywordsPredicate;
import ezschedule.model.event.EventMatchesDatePredicate;
import ezschedule.model.event.EventMatchesKeywordsAndDatePredicate;
import ezschedule.storage.JsonSchedulerStorage;
import ezschedule.storage.JsonUserPrefsStorage;
import ezschedule.storage.StorageManager;
import ezschedule.testutil.EventBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonSchedulerStorage schedulerStorage =
                new JsonSchedulerStorage(temporaryFolder.resolve("scheduler.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(schedulerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, String.format(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, 9));
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonSchedulerIoExceptionThrowingStub
        JsonSchedulerStorage schedulerStorage =
                new JsonSchedulerIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionSchedule.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(schedulerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_A + DATE_DESC_A + START_TIME_DESC_A + END_TIME_DESC_A;
        Event expectedEvent = new EventBuilder(EVENT_A).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addEvent(expectedEvent);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getScheduler_success() {
        assertEquals(model.getScheduler(), logic.getScheduler());
    }

    @Test
    public void getEventList_success() {
        assertEquals(model.getEventList(), logic.getEventList());
    }

    @Test
    public void getFilteredEventList_success() {
        assertEquals(model.getFilteredEventList(), logic.getFilteredEventList());
    }

    @Test
    public void getUpcomingEventList_success() {
        assertEquals(model.getUpcomingEventList(), logic.getUpcomingEventList());
    }

    @Test
    public void getFindEventList_success() {
        assertEquals(model.getFindEventList(), logic.getFindEventList());
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getEventList().remove(0));
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredEventList().remove(0));
    }

    @Test
    public void getUpcomingEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getUpcomingEventList().remove(0));
    }

    @Test
    public void getFindEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFindEventList().remove(0));
    }

    @Test
    public void updateFilteredEventList_modifyList_success() throws CommandException, ParseException {
        // EventContainsKeywordsPredicate - no such event found
        EventContainsKeywordsPredicate namePredicate = new EventContainsKeywordsPredicate(Arrays.asList(VALID_NAME_A));
        model.updateFilteredEventList(namePredicate);
        String findCommand = FindCommand.COMMAND_WORD + NAME_DESC_A;
        assertCommandSuccess(findCommand, String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0), model);

        // EventMatchesDatePredicate - no such event found
        EventMatchesDatePredicate datePredicate = new EventMatchesDatePredicate(new Date(VALID_DATE_A));
        model.updateFilteredEventList(datePredicate);
        findCommand = FindCommand.COMMAND_WORD + DATE_DESC_A;
        assertCommandSuccess(findCommand, String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0), model);

        // EventMatchesKeywordsAndDatePredicate - no such event found
        EventMatchesKeywordsAndDatePredicate nameAndDatePredicate =
                new EventMatchesKeywordsAndDatePredicate(Arrays.asList(VALID_NAME_A), new Date(VALID_DATE_A));
        model.updateFilteredEventList(nameAndDatePredicate);
        findCommand = FindCommand.COMMAND_WORD + NAME_DESC_A + DATE_DESC_A;
        assertCommandSuccess(findCommand, String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0), model);
    }

    @Test
    public void getSchedulerFilePath_equalsModelGetSchedulerFilePath() {
        assertEquals(model.getSchedulerFilePath(), logic.getSchedulerFilePath());
    }

    @Test
    public void getSchedulerFilePath_equalsModelGetGuiSettings() {
        assertEquals(model.getGuiSettings(), logic.getGuiSettings());
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
        Model expectedModel = new ModelManager(model.getScheduler(), new UserPrefs());
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
    private static class JsonSchedulerIoExceptionThrowingStub extends JsonSchedulerStorage {
        private JsonSchedulerIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveScheduler(ReadOnlyScheduler scheduler, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
