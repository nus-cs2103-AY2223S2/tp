package seedu.address.storage.volunteer;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyVolunteer;
import seedu.address.storage.JsonAppStorage;

/**
 * A class to access volunteer. data stored as a json file on the hard disk.
 */
public class JsonVolunteerStorage extends JsonAppStorage<ReadOnlyVolunteer, FriendlyLink, JsonSerializableVolunteer>
        implements VolunteerStorage {

    private static final Logger logger =
            LogsCenter.getLogger(seedu.address.storage.volunteer.JsonVolunteerStorage.class);

    public JsonVolunteerStorage(Path filePath) {
        super(filePath);
    }

    @Override
    public Path getVolunteerFilePath() {
        return super.getFilePath();
    }


    @Override
    public Optional<ReadOnlyVolunteer> readVolunteer(FriendlyLink friendlyLink) throws DataConversionException {
        return super.read(JsonSerializableVolunteer.class, logger, friendlyLink);
    }

    /**
     * Similar to {@link VolunteerStorage#readVolunteer(FriendlyLink)}.
     *
     * @param filePath     location of the data. Cannot be null.
     * @param friendlyLink the cache the volunteer list will be read to.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyVolunteer> readVolunteer(Path filePath, FriendlyLink friendlyLink)
            throws DataConversionException {
        return super.read(filePath, JsonSerializableVolunteer.class, logger, friendlyLink);
    }

    @Override
    public void saveVolunteer(ReadOnlyVolunteer friendlyLink) throws IOException {
        saveVolunteer(friendlyLink, super.getFilePath());
    }

    /**
     * Similar to {@link #saveVolunteer(ReadOnlyVolunteer)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveVolunteer(ReadOnlyVolunteer volunteer, Path filePath) throws IOException {
        requireNonNull(volunteer);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableVolunteer(volunteer), filePath);
    }
}
