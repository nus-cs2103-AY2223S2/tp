package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.MasterDeck;
import seedu.address.testutil.TypicalCards;

public class JsonSerializableMasterDeckTest {

    private static final Path TEST_DATA_FOLDER = Paths
            .get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_CARDS_FILE = TEST_DATA_FOLDER.resolve("typicalCardsMasterDeck.json");
    private static final Path INVALID_CARD_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_CARD_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
    private static final Path MISSING_DECK_FILE = TEST_DATA_FOLDER.resolve("missingDeckMasterDeck.json");

    @Test
    public void toModelType_typicalCardsFile_success() throws Exception {
        JsonSerializableMasterDeck dataFromFile = JsonUtil.readJsonFile(TYPICAL_CARDS_FILE,
                JsonSerializableMasterDeck.class).get();
        MasterDeck addressBookFromFile = dataFromFile.toModelType();
        MasterDeck typicalPersonsAddressBook = TypicalCards.getTypicalMasterDeck();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMasterDeck dataFromFile = JsonUtil.readJsonFile(INVALID_CARD_FILE,
                JsonSerializableMasterDeck.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCards_throwsIllegalValueException() throws Exception {
        JsonSerializableMasterDeck dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CARD_FILE,
                JsonSerializableMasterDeck.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMasterDeck.MESSAGE_DUPLICATE_PERSON,
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
