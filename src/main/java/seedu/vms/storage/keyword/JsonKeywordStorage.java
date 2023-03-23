package seedu.vms.storage.keyword;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.FileUtil;
import seedu.vms.commons.util.JsonUtil;
import seedu.vms.model.keyword.KeywordManager;

/**
 * An {@link KeywordStorage} to handle read and write operations from and to
 * JSON files containing {@link KeywordManager} data.
 */
public class JsonKeywordStorage implements KeywordStorage {
    public static final Path USER_KEYWORD_PATH = Path.of("data", "keyword.json");

    private final Path filePath;

    public JsonKeywordStorage() {
        this(USER_KEYWORD_PATH);
    }

    public JsonKeywordStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public KeywordManager loadKeywords() throws IOException {
        try {
            return JsonUtil
                    .deserializeFromFile(USER_KEYWORD_PATH, JsonSerializableKeywordManager.class)
                    .toModelType();
        } catch (IllegalValueException illValEx) {
            throw new IOException(illValEx.getMessage());
        }
    }


    @Override
    public void saveKeywords(KeywordManager manager) throws IOException {
        requireNonNull(manager);

        FileUtil.createIfMissing(USER_KEYWORD_PATH);
        JsonUtil.serializeToFile(USER_KEYWORD_PATH, new JsonSerializableKeywordManager(manager));
    }
}
