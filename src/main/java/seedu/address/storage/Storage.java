package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTankList;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.tank.readings.ReadOnlyAmmoniaLevels;
import seedu.address.storage.fish.AddressBookStorage;
import seedu.address.storage.userprefs.UserPrefsStorage;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    // ================ TaskList methods ==============================

    Path getTaskListFilePath();

    Optional<ReadOnlyTaskList> readTaskList()
            throws DataConversionException,
            IOException;

    Optional<ReadOnlyTaskList> readTaskList(Path filePath)
            throws DataConversionException, IOException;

    void saveTaskList(ReadOnlyTaskList taskList) throws IOException;

    void saveTaskList(ReadOnlyTaskList taskList, Path filePath) throws IOException;

    // ================ TankList methods ==============================

    Path getTankListFilePath();

    Optional<ReadOnlyTankList> readTankList()
            throws DataConversionException,
            IOException;

    Optional<ReadOnlyTankList> readTankList(Path filePath)
            throws DataConversionException, IOException;

    void saveTankList(ReadOnlyTankList tankList) throws IOException;

    void saveTankList(ReadOnlyTankList tankList, Path filePath) throws IOException;

    // ================ Readings methods ==============================
    Path getFullAmmoniaLevelsFilePath();

    Optional<ReadOnlyAmmoniaLevels> readFullAmmoniaLevels()
            throws DataConversionException,
            IOException;

    Optional<ReadOnlyAmmoniaLevels> readFullAmmoniaLevels(Path filePath)
            throws DataConversionException, IOException;

    void saveFullAmmoniaLevels(ReadOnlyAmmoniaLevels fullAmmoniaLevels) throws IOException;

    void saveFullAmmoniaLevels(ReadOnlyAmmoniaLevels fullAmmoniaLevels, Path filePath) throws IOException;

}
