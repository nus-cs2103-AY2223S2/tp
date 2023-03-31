package vimification.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import vimification.commons.core.LogsCenter;
import vimification.commons.exceptions.DataConversionException;
import vimification.commons.exceptions.IllegalValueException;
import vimification.commons.util.JsonUtil;
import vimification.model.LogicTaskList;
import vimification.model.TaskListRef;

/**
 * A class to access TaskPlanner data stored as a json file on the hard disk.
 */
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
        requireNonNull(ref);
        JsonUtil.saveJsonFile(new JsonAdaptedTaskListRef(ref), filePath);
    }
}
