package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFriendlyLink;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.elderly.ElderlyStorage;
import seedu.address.storage.volunteer.VolunteerStorage;

/**
 * API of the Storage component
 */
public interface Storage extends FriendlyLinkStorage, VolunteerStorage, ElderlyStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFriendlyLinkFilePath();

    @Override
    Path getElderlyFilePath();

    @Override
    Path getVolunteerFilePath();

    @Override
    Optional<ReadOnlyFriendlyLink> readFriendlyLink() throws DataConversionException, IOException;

    @Override
    void saveFriendlyLink(ReadOnlyFriendlyLink friendlyLink) throws IOException;

}
