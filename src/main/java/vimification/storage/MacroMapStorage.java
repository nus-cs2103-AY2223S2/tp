package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.model.MacroMap;

/**
 * Represents a storage for {@link MacroMap}.
 */
public interface MacroMapStorage {

    /**
     * Returns the file path of the data file.
     *
     * @return the file path of the data file.
     */
    Path getMacroMapFilePath();

    /**
     * Returns MacroMap data as a {@link MacroMap}. Returns {@code Optional.empty()} if storage file
     * is not found.
     *
     * @throws IOException if there was any problem when reading from the storage.
     */
    MacroMap readMacroMap() throws IOException;

    /**
     * Save the given {@link MacroMap} to the storage.
     *
     * @param macroMap cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMacroMap(MacroMap macroMap) throws IOException;
}
