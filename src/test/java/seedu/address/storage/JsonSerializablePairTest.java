package seedu.address.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.model.FriendlyLink;

public class JsonSerializablePairTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePairTest");
    private static final Path TYPICAL_PAIRS_FILE = TEST_DATA_FOLDER.resolve("typicalPairs.json");
    private static final Path INVALID_PAIR_FILE = TEST_DATA_FOLDER.resolve("invalidPair.json");
    private static final Path DUPLICATE_PAIRS_FILE = TEST_DATA_FOLDER.resolve("duplicatePairs.json");
    private static final FriendlyLink appTestCache = new FriendlyLink();

    // TODO: toModelType_typicalPairsFile_success(), follow JsonSerializableElderlyTest

    // TODO: toModelType_invalidPairFile_throwsIllegalValueException(), follow JsonSerializableElderlyTest

    // TODO: toModelType_duplicatePairs_throwsIllegalValueException(), follow JsonSerializableElderlyTest

}
