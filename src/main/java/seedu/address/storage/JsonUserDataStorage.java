package seedu.address.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.UserData;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * A class to access UserData stored in the hard disk as a json file
 *
 * @author Haiqel Bin Hanaffi
 */
public class JsonUserDataStorage implements UserDataStorage {

    private Path filepath;

    public JsonUserDataStorage(Path filePath) {
        this.filepath = filePath;
    }

    @Override
    public Path getUserDataFilePath() {
        return this.filepath;
    }

    @Override
    public Optional<UserData> readUserData() throws DataConversionException {
        return JsonUtil.readJsonFile(this.filepath, UserData.class);
    }

    @Override
    public void saveUserData(UserData userData) throws IOException {
        JsonUtil.saveJsonFile(userData, this.filepath);
    }

}
