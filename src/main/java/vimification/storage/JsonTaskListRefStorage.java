package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import vimification.common.core.LogsCenter;
import vimification.common.exceptions.DataConversionException;
import vimification.common.exceptions.IllegalValueException;
import vimification.common.util.JsonUtil;
import vimification.model.TaskListRef;

public class JsonTaskListRefStorage implements TaskListRefStorage {

    private static final Logger LOGGER = LogsCenter.getLogger(JsonTaskListRefStorage.class);

    private Path filePath;

    public JsonTaskListRefStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskListRefFilePath() {
        return filePath;
    }

    public TaskListRef readTaskListRef() throws DataConversionException, IOException {
        try {
            return JsonUtil
                    .readJsonFile(filePath, JsonAdaptedTaskListRef.class)
                    .toModelType();
        } catch (IllegalValueException ive) {
            LOGGER.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    public void saveTaskListRef(TaskListRef ref) throws IOException {
        JsonUtil.saveJsonFile(new JsonAdaptedTaskListRef(ref), filePath);
    }
}
