package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyUserPrefsNew;
import seedu.address.model.UserPrefsNew;

/**
 * A class to access UserPrefs stored in the hard disk as a json file
 */
public class JsonUserPrefsStorageNew implements UserPrefsStorageNew {

    private Path filePath;

    public JsonUserPrefsStorageNew(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getUserPrefsFilePath() {
        return filePath;
    }

    @Override
    public Optional<UserPrefsNew> readUserPrefs() throws DataConversionException {
        return readUserPrefs(filePath);
    }

    /**
     * Similar to {@link #readUserPrefs()}
     * @param prefsFilePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public Optional<UserPrefsNew> readUserPrefs(Path prefsFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(prefsFilePath, UserPrefsNew.class);
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefsNew userPrefs) throws IOException {
        JsonUtil.saveJsonFile(userPrefs, filePath);
    }

}
