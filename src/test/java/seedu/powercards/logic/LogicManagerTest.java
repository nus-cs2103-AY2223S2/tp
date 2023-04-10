package seedu.powercards.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX;
import static seedu.powercards.commons.core.Messages.MESSAGE_NO_DECK_SELECTED;
import static seedu.powercards.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.powercards.testutil.Assert.assertThrows;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.powercards.commons.core.GuiSettings;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.deckcommands.AddDeckCommand;
import seedu.powercards.logic.commands.deckcommands.UnselectDeckCommand;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.logic.commands.reviewcommands.EndReviewCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;
import seedu.powercards.model.MasterDeck;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.ModelState;
import seedu.powercards.model.ReadOnlyMasterDeck;
import seedu.powercards.model.UserPrefs;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.tag.Tag;
import seedu.powercards.storage.JsonMasterDeckStorage;
import seedu.powercards.storage.JsonUserPrefsStorage;
import seedu.powercards.storage.StorageManager;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;
    private StorageManager storage;

    @BeforeEach
    public void setUp() {
        JsonMasterDeckStorage masterDeckStorage =
                new JsonMasterDeckStorage(temporaryFolder.resolve("masterdeck.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        storage = new StorageManager(masterDeckStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() { // test to delete deck, no deck selected
        String deleteDeckCommand = "deleteDeck 10";
        assertCommandException(deleteDeckCommand, MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidCommandWhenDeckNotSelected_throwsParseException() { // deck needs to be selected
        String invalidCommandWhenDeckNotSelected = UnselectDeckCommand.COMMAND_WORD;
        assertParseException(invalidCommandWhenDeckNotSelected,
                String.format(MESSAGE_NO_DECK_SELECTED, UnselectDeckCommand.COMMAND_WORD));
    }

    @Test
    public void execute_validCommandWhenDeckSelected_success() throws Exception { // select deck when deck selected
        String unselectDeck = UnselectDeckCommand.COMMAND_WORD;
        Model modelWithDeckSelected = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        modelWithDeckSelected.selectDeck(INDEX_FIRST);
        Logic logic = new LogicManager(modelWithDeckSelected, storage);
        assertLogicSuccess(unselectDeck, UnselectDeckCommand.MESSAGE_SUCCESS, logic);
    }

    @Test
    public void execute_validCommandWhenReviewMode_success() throws Exception {
        String endReview = EndReviewCommand.COMMAND_WORD;
        Model modelInReviewMode = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        modelInReviewMode.reviewDeck(INDEX_FIRST, List.of(new Tag.TagName[]{Tag.TagName.HARD}));
        Logic logic = new LogicManager(modelInReviewMode, storage);
        assertLogicSuccess(endReview, EndReviewCommand.MESSAGE_SUCCESS, logic);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonMasterDeckIoExceptionThrowingStub
        JsonMasterDeckStorage masterDeckStorage =
             new JsonMasterDeckIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionMasterDeck.json"));
        JsonUserPrefsStorage userPrefsStorage =
            new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(masterDeckStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addDeckCommand = AddDeckCommand.COMMAND_WORD + " " + "Science";
        Deck expectedDeck = new Deck("Science");
        ModelManager expectedModel = new ModelManager();
        expectedModel.addDeck(expectedDeck);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addDeckCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredCardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredCardList().remove(0));
    }

    @Test
    public void getMasterDeck_returnsCorrectDeck() {
        ReadOnlyMasterDeck readOnlyMasterDeck = logic.getMasterDeck();
        assertEquals(model.getMasterDeck(), readOnlyMasterDeck);
    }

    @Test
    public void getFilteredCardList_returnsCorrectList() {
        List<Card> expectedList = new ArrayList<>();
        assertEquals(expectedList, logic.getFilteredCardList());
    }

    @Test
    public void getMasterDeckFilePath_returnsCorrectPath() {
        Path expectedPath = Paths.get("data", "masterdeck.json");
        assertEquals(expectedPath, logic.getMasterDeckFilePath());
    }

    @Test
    public void getGuiSettings_returnsCorrectGuiSettings() {
        assertEquals(model.getGuiSettings(), logic.getGuiSettings());
    }
    @Test
    public void setGuiSettings_setsCorrectGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(100, 200, 1, 2);
        logic.setGuiSettings(guiSettings);
        assertEquals(guiSettings, logic.getGuiSettings());
    }

    @Test
    public void getUnselectedMode_success() {
        Model modelUnselectedMode = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        Logic logic = new LogicManager(modelUnselectedMode, storage);
        assertEquals(ModelState.MAIN_UNSELECTED_MODE, logic.getMode());
    }

    @Test
    public void getSelectedMode_success() {
        Model modelSelectedMode = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        Logic logic = new LogicManager(modelSelectedMode, storage);
        modelSelectedMode.selectDeck(INDEX_FIRST);
        assertEquals(ModelState.MAIN_SELECTED_MODE, logic.getMode());
    }

    @Test
    public void getReviewMode_success() {
        Model modelInReviewMode = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        modelInReviewMode.reviewDeck(INDEX_FIRST, List.of(new Tag.TagName[]{Tag.TagName.HARD}));
        Logic logic = new LogicManager(modelInReviewMode, storage);
        modelInReviewMode.selectDeck(INDEX_FIRST);
        assertEquals(ModelState.REVIEW_MODE, logic.getMode());
    }

    @Test
    public void factoryReset_success() {
        Model expectedModel = new ModelManager(new MasterDeck(), model.getUserPrefs());
        logic.factoryReset();
        assertEquals(expectedModel, model);
    }

    @Test
    public void getFilteredDeckList_success() {
        Model expectedModel = new ModelManager(getTypicalMasterDeck(), model.getUserPrefs());
        expectedModel.reviewDeck(INDEX_FIRST, List.of(new Tag.TagName[]{Tag.TagName.HARD}));
        Logic logic = new LogicManager(expectedModel, storage);
        assertEquals(logic.getFilteredDeckList(), expectedModel.getFilteredDeckList());
    }

    @Test
    public void getReviewStatsList_success() {
        Model expectedModel = new ModelManager(getTypicalMasterDeck(), model.getUserPrefs());
        expectedModel.reviewDeck(INDEX_FIRST, List.of(new Tag.TagName[]{Tag.TagName.HARD}));
        Logic logic = new LogicManager(expectedModel, storage);
        assertEquals(logic.getReviewStatsList(), expectedModel.getReviewStatsList());
    }

    @Test
    public void getReviewCardList_success() {
        Model expectedModel = new ModelManager(getTypicalMasterDeck(), model.getUserPrefs());
        expectedModel.reviewDeck(INDEX_FIRST, List.of(new Tag.TagName[]{Tag.TagName.HARD}));
        Logic logic = new LogicManager(expectedModel, storage);
        assertEquals(logic.getReviewCardList(), expectedModel.getReviewCardList());
    }

    private void assertLogicSuccess(String inputCommand, String expectedMessage,
                                    Logic logic) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
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
        Model expectedModel = new ModelManager(model.getMasterDeck(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonMasterDeckIoExceptionThrowingStub extends JsonMasterDeckStorage {
        private JsonMasterDeckIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveMasterDeck(ReadOnlyMasterDeck masterDeck, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
