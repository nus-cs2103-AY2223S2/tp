package seedu.internship.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.internship.commons.core.LogsCenter;
import seedu.internship.commons.exceptions.DataConversionException;
import seedu.internship.commons.exceptions.IllegalValueException;
import seedu.internship.commons.util.FileUtil;
import seedu.internship.commons.util.JsonUtil;
import seedu.internship.model.ReadOnlyInternshipCatalogue;

/**
 * A class to access InternshipCatalogue data stored as a json file on the hard disk.
 */
public class JsonInternshipCatalogueStorage implements InternshipCatalogueStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonInternshipCatalogueStorage.class);
    private Path filePath;

    public JsonInternshipCatalogueStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getInternshipCatalogueFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyInternshipCatalogue> readInternshipCatalogue() throws DataConversionException {
        return readInternshipCatalogue(filePath);
    }

    /**
     * Similar to {@link #readInternshipCatalogue()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyInternshipCatalogue> readInternshipCatalogue(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableInternshipCatalogue> jsonInternshipCatalogue = JsonUtil.readJsonFile(
                filePath, JsonSerializableInternshipCatalogue.class);
        if (!jsonInternshipCatalogue.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonInternshipCatalogue.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveInternshipCatalogue(ReadOnlyInternshipCatalogue internshipCatalogue) throws IOException {
        saveInternshipCatalogue(internshipCatalogue, filePath);
    }

    /**
     * Similar to {@link #saveInternshipCatalogue(ReadOnlyInternshipCatalogue)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInternshipCatalogue(ReadOnlyInternshipCatalogue internshipCatalogue, Path filePath) throws IOException {
        requireNonNull(internshipCatalogue);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInternshipCatalogue(internshipCatalogue), filePath);
    }
}
