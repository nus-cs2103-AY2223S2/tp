package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.commons.util.JsonUtil;
import vimification.model.MacroMap;

public class JsonMacroMapStorage {

    private Path filePath;

    public JsonMacroMapStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMacroMapFilePath() {
        return filePath;
    }

    public MacroMap readMacroMap() throws IOException {
        return JsonUtil.readJsonFile(filePath, JsonAdaptedMacroMap.class).toModelType();
    }

    public void saveMacroMap(MacroMap macroMap) throws IOException {
        JsonUtil.saveJsonFile(new JsonAdaptedMacroMap(macroMap), filePath);
    }

}
