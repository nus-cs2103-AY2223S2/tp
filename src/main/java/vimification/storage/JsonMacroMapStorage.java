package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.common.util.JsonUtil;
import vimification.model.MacroMap;

/**
 * One implementation of {@link MacroMapStorage}, using JSON as serialization format.
 */
public class JsonMacroMapStorage implements MacroMapStorage {

    private Path filePath;

    /**
     * Creates a new instance with the specified path.
     *
     * @param filePath the path to the data file
     */
    public JsonMacroMapStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getMacroMapFilePath() {
        return filePath;
    }

    @Override
    public MacroMap readMacroMap() throws IOException {
        return JsonUtil.readJsonFile(filePath, MacroMap.class);
    }

    @Override
    public void saveMacroMap(MacroMap macroMap) throws IOException {
        JsonUtil.saveJsonFile(macroMap, filePath);
    }
}
