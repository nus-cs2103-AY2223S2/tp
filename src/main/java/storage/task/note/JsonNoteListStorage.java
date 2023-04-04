package storage.task.note;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyNote;

/**
 * A class to access NoteList data stored as a json file on the hard disk.
 */
public class JsonNoteListStorage implements NoteStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonNoteListStorage.class);

    private Path filePath;

    public JsonNoteListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getNoteListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyNote> readNoteList() throws DataConversionException {
        return readNoteList(filePath);
    }

    /**
     * Similar to {@link #readNoteList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyNote> readNoteList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableNoteList> jsonNoteList = JsonUtil.readJsonFile(
                filePath, JsonSerializableNoteList.class);
        if (!jsonNoteList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonNoteList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveNoteList(ReadOnlyNote noteList) throws IOException {
        saveNoteList(noteList, filePath);
    }

    /**
     * Similar to {@link #saveNoteList(ReadOnlyNote)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveNoteList(ReadOnlyNote noteList, Path filePath) throws IOException {
        requireNonNull(noteList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNoteList(noteList), filePath);
    }

}
