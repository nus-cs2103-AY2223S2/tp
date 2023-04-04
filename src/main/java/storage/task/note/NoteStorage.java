package storage.task.note;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyNote;

/**
 * Represents a storage for {@link seedu.address.model.NoteList}.
 */
public interface NoteStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getNoteListFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyNote}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyNote> readNoteList() throws DataConversionException, IOException;

    /**
     * @see #getNoteListFilePath()
     */
    Optional<ReadOnlyNote> readNoteList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyNote} to the storage.
     *
     * @param noteList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveNoteList(ReadOnlyNote noteList) throws IOException;

    /**
     * @see #saveNoteList(ReadOnlyNote)
     */
    void saveNoteList(ReadOnlyNote noteList, Path filePath) throws IOException;

}
