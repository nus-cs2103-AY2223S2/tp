package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.model.MacroMap;

/**
 * Represents a storage for {@link MacroMap}.
 */
public interface MacroMapStorage {

    Path getMacroMapFilePath();

    MacroMap readMacroMap() throws IOException;

    void saveMacroMap(MacroMap macroMap) throws IOException;
}
