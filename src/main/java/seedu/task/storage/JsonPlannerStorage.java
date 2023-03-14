package seedu.task.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.commons.util.FileUtil;
import seedu.task.commons.util.JsonUtil;
import seedu.task.model.ReadOnlyPlanner;

/**
 * A class to access Planner data stored as a json file on the hard disk.
 */
public class JsonPlannerStorage implements PlannerStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonPlannerStorage.class);
    private Path filePath;

    public JsonPlannerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPlannerFilePath() {
        return filePath;
    }

    /**
     * Similar to {@link #readPlanner()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPlanner> readPlanner(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePlanner> jsonTaskBook = JsonUtil.readJsonFile(
                filePath, JsonSerializablePlanner.class);
        if (!jsonTaskBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public Optional<ReadOnlyPlanner> readPlanner() throws DataConversionException {
        return readPlanner(filePath);
    }

    @Override
    public void savePlanner(ReadOnlyPlanner planner) throws IOException {
        savePlanner(planner, filePath);
    }

    /**
     * @see #savePlanner(ReadOnlyPlanner)
     */
    public void savePlanner(ReadOnlyPlanner planner, Path filePath) throws IOException {
        requireNonNull(planner);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePlanner(planner), filePath);
    }
}
