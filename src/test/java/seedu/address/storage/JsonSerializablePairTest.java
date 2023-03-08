package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PAIR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.getNoPairsTypicalFriendlyLink;
import static seedu.address.testutil.TypicalPairs.PAIR1;
import static seedu.address.testutil.TypicalPairs.getTypicalPairs;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.FriendlyLink;
import seedu.address.model.pair.Pair;
import seedu.address.storage.pair.JsonSerializablePair;

public class JsonSerializablePairTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePairTest");
    private static final Path TYPICAL_PAIRS_FILE = TEST_DATA_FOLDER.resolve("typicalPairs.json");
    private static final Path INVALID_PAIR_FILE = TEST_DATA_FOLDER.resolve("invalidPair.json");
    private static final Path DUPLICATE_PAIRS_FILE = TEST_DATA_FOLDER.resolve("duplicatePairs.json");
    private static final FriendlyLink appTestCache = new FriendlyLink();

    @Test
    public void toModelType_typicalPairsFile_success() throws Exception {
        FriendlyLink appTestCache = getNoPairsTypicalFriendlyLink();
        JsonSerializablePair dataFromFile = JsonUtil.readJsonFile(TYPICAL_PAIRS_FILE,
                JsonSerializablePair.class).get();
        FriendlyLink friendlyLinkFromFile = dataFromFile.toModelType(appTestCache);
        List<Pair> typicalPairs = getTypicalPairs();
        assertEquals(friendlyLinkFromFile.getPairList(), typicalPairs);
    }

    @Test
    public void toModelType_invalidPairFile_throwsIllegalValueException() throws Exception {
        FriendlyLink appTestCache = getNoPairsTypicalFriendlyLink();
        JsonSerializablePair dataFromFile = JsonUtil.readJsonFile(INVALID_PAIR_FILE,
                JsonSerializablePair.class).get();
        assertThrows(IllegalValueException.class, () -> dataFromFile.toModelType(appTestCache));
    }

    @Test
    public void toModelType_duplicatePair_throwsIllegalValueException() throws Exception {
        FriendlyLink appTestCache = getNoPairsTypicalFriendlyLink();
        JsonSerializablePair dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PAIRS_FILE,
                JsonSerializablePair.class).get();
        String expectedMessage = String.format(
                MESSAGE_DUPLICATE_PAIR, PAIR1.getElderly().getNric(), PAIR1.getVolunteer().getNric());
        assertThrows(IllegalValueException.class,
                expectedMessage, () -> dataFromFile.toModelType(appTestCache));
    }

}
