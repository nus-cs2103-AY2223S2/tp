package seedu.modtrek.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.modtrek.commons.core.LogsCenter;
import seedu.modtrek.commons.exceptions.DataConversionException;
import seedu.modtrek.commons.exceptions.IllegalValueException;
import seedu.modtrek.commons.util.FileUtil;
import seedu.modtrek.commons.util.JsonUtil;
import seedu.modtrek.model.ReadOnlyDegreeProgression;

/**
 * The type Json degree progression storage.
 */
public class JsonDegreeProgressionStorage implements DegreeProgressionStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonDegreeProgressionStorage.class);

    private Path filePath;

    /**
     * Instantiates a new Json degree progression storage.
     *
     * @param filePath the file path
     */
    public JsonDegreeProgressionStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDegreeProgressionFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDegreeProgression> readDegreeProgression() throws DataConversionException {
        return readDegreeProgression(filePath);
    }

    /**
     * Similar to {@link #readDegreeProgression()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyDegreeProgression> readDegreeProgression(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDegreeProgression> jsonDegreeProgression = JsonUtil.readJsonFile(
                filePath, JsonSerializableDegreeProgression.class);
        if (!jsonDegreeProgression.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDegreeProgression.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDegreeProgression(ReadOnlyDegreeProgression degreeProgression) throws IOException {
        saveDegreeProgression(degreeProgression, filePath);
    }

    /**
     * Similar to {@link #saveDegreeProgression(ReadOnlyDegreeProgression)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDegreeProgression(ReadOnlyDegreeProgression degreeProgression, Path filePath) throws IOException {
        requireNonNull(degreeProgression);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDegreeProgression(degreeProgression), filePath);
    }

}
