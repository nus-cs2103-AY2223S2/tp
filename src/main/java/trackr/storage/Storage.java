package trackr.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import trackr.commons.exceptions.DataConversionException;
import trackr.model.ReadOnlyMenu;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.ReadOnlyUserPrefs;
import trackr.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TrackrStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTrackrFilePath();

    @Override
    Optional<ReadOnlySupplierList> readSupplierList() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyMenu> readMenu() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyOrderList> readOrderList() throws DataConversionException, IOException;

    @Override
    void saveTrackr(ReadOnlySupplierList addressBook, ReadOnlyTaskList taskList,
            ReadOnlyMenu menu, ReadOnlyOrderList orderList) throws IOException;
}
