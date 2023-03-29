package seedu.vms.storage.keyword;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.FileUtil;
import seedu.vms.commons.util.JsonUtil;
import seedu.vms.model.ModelManager;
import seedu.vms.model.keyword.Keyword;
import seedu.vms.model.keyword.KeywordManager;
import seedu.vms.storage.patient.JsonSerializablePatientManager;

/**
 * An {@link KeywordStorage} to handle read and write operations from and to
 * JSON files containing {@link KeywordManager} data.
 */
public class JsonKeywordStorage implements KeywordStorage {
    public static final Path USER_KEYWORD_PATH = Path.of("data", "keywords.json");

//    private final Path filePath;

//    public JsonKeywordStorage() {
//        this(USER_KEYWORD_PATH);
//    }

//    public JsonKeywordStorage(Path filePath) {
//        this.filePath = filePath;
//    }

    @Override
    public KeywordManager loadKeywords() throws IOException {
        try {
            return KeywordLoader.load(USER_KEYWORD_PATH);
        } catch (IllegalValueException illValEx) {
            throw new IOException(illValEx.getMessage());
        }
    }


    @Override
    public void saveKeywords(KeywordManager manager) throws IOException {
        requireNonNull(manager);

        FileUtil.createIfMissing(USER_KEYWORD_PATH);
        KeywordLoader.fromModelType(manager).write(USER_KEYWORD_PATH);
//        HashMap<String, Keyword> keywordMap = manager.getKeywordMap();
//        for (Keyword keyword : keywordMap.values()) {
//            JsonUtil.serializeToFile(USER_PATIENT_KEYWORD_PATH, JsonAdaptedKeyword.fromModelType(keyword));
//        }
    }
}
