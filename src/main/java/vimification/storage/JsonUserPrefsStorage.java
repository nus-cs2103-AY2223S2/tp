package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.common.util.JsonUtil;
import vimification.model.UserPrefs;

/**
 * A class to access {@link UserPrefs} stored in the hard disk as a json file.
 */
public class JsonUserPrefsStorage implements UserPrefsStorage {

    private Path filePath;

    /**
     * Creates a new instance with the specified path.
     *
     * @param filePath the path to the data file
     */
    public JsonUserPrefsStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getUserPrefsFilePath() {
        return filePath;
    }

    @Override
    public UserPrefs readUserPrefs() throws IOException {
        return JsonUtil.readJsonFile(filePath, UserPrefs.class);
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        JsonUtil.saveJsonFile(userPrefs, filePath);
    }
}
