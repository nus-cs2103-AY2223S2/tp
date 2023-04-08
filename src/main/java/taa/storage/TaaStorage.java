package taa.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import taa.commons.exceptions.DataConversionException;
import taa.model.ClassList;
import taa.model.ReadOnlyStudentList;
import taa.model.assignment.AssignmentList;

/**
 * Represents a storage for {@link ClassList} and {@link AssignmentList}.`
 */
public interface TaaStorage {

    /**
     * @return the file path of the data file.
     */
    Path getTaaDataFilePath();

    /**
     * @return {@link ClassList} and {@link AssignmentList} data as a {@link TaaData}} or {@code Optional.empty()} if
     *         storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<TaaData> readTaaData() throws DataConversionException, IOException;

    /**
     * @see #getTaaDataFilePath()
     */
    Optional<TaaData> readTaaData(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyStudentList} to the storage.
     *
     * @param taaData cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaaData(TaaData taaData) throws IOException;

    /**
     * @see #saveTaaData(TaaData)
     */
    void saveTaaData(TaaData taaData, Path filePath) throws IOException;

}
