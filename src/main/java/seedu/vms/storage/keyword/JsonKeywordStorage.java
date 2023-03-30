package seedu.vms.storage.keyword;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.FileUtil;
import seedu.vms.model.keyword.KeywordManager;

/**
 * An {@link KeywordStorage} to handle read and write operations from and to
 * JSON files containing {@link KeywordManager} data.
 */
public class JsonKeywordStorage implements KeywordStorage {
    public static final Path USER_KEYWORD_PATH = Path.of("data", "keyword.json");


    @Override
    public KeywordManager loadKeywords() throws IOException {
        try {
            return KeywordLoader.load(USER_KEYWORD_PATH);
        } catch (IllegalValueException illValEx) {
            throw new IOException(illValEx.getMessage());
        }
    }

    @Override
    public KeywordManager loadEmptyKeywords() throws RuntimeException {
        try {
            return KeywordLoader.load();
        } catch (Throwable ex) {
            throw new RuntimeException("Unable to load defaults", ex);
        }
    }


    @Override
    public void saveKeywords(KeywordManager manager) throws IOException {
        requireNonNull(manager);

        FileUtil.createIfMissing(USER_KEYWORD_PATH);
        KeywordLoader.fromModelType(manager).write(USER_KEYWORD_PATH);
    }
}
