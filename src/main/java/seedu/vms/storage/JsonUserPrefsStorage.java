package seedu.vms.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

import seedu.vms.commons.exceptions.DataConversionException;
import seedu.vms.commons.util.FileUtil;
import seedu.vms.commons.util.JsonUtil;
import seedu.vms.model.ReadOnlyUserPrefs;
import seedu.vms.model.UserPrefs;

/**
 * A class to access UserPrefs stored in the hard disk as a json file
 */
public class JsonUserPrefsStorage implements UserPrefsStorage {
    private static final Path USER_PREF_FILE_PATH = Path.of("data", "userprefs.json");

    private Path filePath;

    public JsonUserPrefsStorage() {
        this(USER_PREF_FILE_PATH);
    }

    public JsonUserPrefsStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public UserPrefs readUserPrefs() throws IOException {
        return readUserPrefs(filePath);
    }

    /**
     * Similar to {@link #readUserPrefs()}
     * @param prefsFilePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public UserPrefs readUserPrefs(Path prefsFilePath) throws IOException {
        Objects.requireNonNull(prefsFilePath);
        return JsonUtil.deserializeFromFile(prefsFilePath, UserPrefs.class);
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        Objects.requireNonNull(userPrefs);
        FileUtil.createIfMissing(filePath);
        JsonUtil.serializeToFile(filePath, userPrefs);
    }
}
