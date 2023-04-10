package seedu.address.logic.commands;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyElderly;
import seedu.address.model.ReadOnlyPair;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyVolunteer;
import seedu.address.model.UserPrefs;
import seedu.address.storage.Storage;


/**
 * A default storage stub that have all the methods failing.
 */
public class StorageStub implements Storage {

    @Override
    public FriendlyLink read() throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getUserPrefsFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getElderlyFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyElderly> readElderly(FriendlyLink friendlyLink)
            throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyElderly> readElderly(Path filePath, FriendlyLink friendlyLink)
            throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveElderly(ReadOnlyElderly elderly) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveElderly(ReadOnlyElderly elderly, Path filePath) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getVolunteerFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyVolunteer> readVolunteer(FriendlyLink friendlyLink)
            throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyVolunteer> readVolunteer(Path filePath, FriendlyLink friendlyLink)
            throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveVolunteer(ReadOnlyVolunteer volunteer) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveVolunteer(ReadOnlyVolunteer volunteer, Path filePath) throws IOException {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public Path getPairFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyPair> readPair(
            FriendlyLink friendlyLink) throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyPair> readPair(
            Path filePath, FriendlyLink friendlyLink) throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void savePair(ReadOnlyPair pair) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void savePair(ReadOnlyPair pair, Path filePath) throws IOException {
        throw new AssertionError("This method should not be called.");
    }
}
