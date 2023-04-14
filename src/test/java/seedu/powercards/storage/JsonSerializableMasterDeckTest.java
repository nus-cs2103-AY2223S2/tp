package seedu.powercards.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.powercards.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.powercards.commons.exceptions.IllegalValueException;
import seedu.powercards.commons.util.JsonUtil;
import seedu.powercards.model.MasterDeck;
import seedu.powercards.testutil.TypicalCards;

public class JsonSerializableMasterDeckTest {

    private static final Path TEST_DATA_FOLDER = Paths
            .get("src", "test", "data", "JsonSerializableMasterDeckTest");
    private static final Path TYPICAL_CARDS_FILE = TEST_DATA_FOLDER.resolve("typicalCardsMasterDeck.json");
    private static final Path INVALID_CARD_FILE = TEST_DATA_FOLDER.resolve("invalidCardMasterDeck.json");
    private static final Path DUPLICATE_CARD_FILE = TEST_DATA_FOLDER.resolve("duplicateCardMasterDeck.json");
    private static final Path MISSING_DECK_FILE = TEST_DATA_FOLDER.resolve("missingDeckMasterDeck.json");

    @Test
    public void toModelType_typicalCardsFile_success() throws Exception {
        JsonSerializableMasterDeck dataFromFile = JsonUtil.readJsonFile(TYPICAL_CARDS_FILE,
                JsonSerializableMasterDeck.class).get();
        MasterDeck masterDeckFromFile = dataFromFile.toModelType();
        MasterDeck typicalCardMasterDeck = TypicalCards.getTypicalMasterDeck();
        assertEquals(masterDeckFromFile, typicalCardMasterDeck);
    }

    @Test
    public void toModelType_invalidCardFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMasterDeck dataFromFile = JsonUtil.readJsonFile(INVALID_CARD_FILE,
                JsonSerializableMasterDeck.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCards_throwsIllegalValueException() throws Exception {
        JsonSerializableMasterDeck dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CARD_FILE,
                JsonSerializableMasterDeck.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMasterDeck.MESSAGE_DUPLICATE_CARD,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_cardExistsWithoutDeck_throwsIllegalValueException() throws Exception {
        JsonSerializableMasterDeck dataFromFile = JsonUtil.readJsonFile(MISSING_DECK_FILE,
                JsonSerializableMasterDeck.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMasterDeck.MESSAGE_MISSING_DECK,
                dataFromFile::toModelType);
    }

}
