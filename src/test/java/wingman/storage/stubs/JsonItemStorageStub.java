package wingman.storage.stubs;

import java.nio.file.Path;
import java.util.logging.Logger;

import wingman.commons.util.FileHelper;
import wingman.commons.util.JsonHelper;
import wingman.model.ReadOnlyItemManager;
import wingman.storage.json.JsonItemStorage;

/**
 * A stub class for {@link JsonItemStorage} that is used for testing.
 */
public class JsonItemStorageStub extends
        JsonItemStorage<ItemStub, JsonAdaptedIdentifiableStub, JsonItemManagerStub> {
    /**
     * Creates a new {@code JsonIdentifiableStorageStub} object.
     *
     * @param filePath   The path to the file to be read from and written to.
     * @param jsonHelper The {@link JsonHelper} to be used.
     * @param fileHelper The {@link FileHelper} to be used.
     * @param logger     The {@link Logger} to be used.
     */
    public JsonItemStorageStub(Path filePath, JsonHelper jsonHelper,
                               FileHelper fileHelper, Logger logger) {
        super(filePath, jsonHelper, fileHelper, logger);
    }

    @Override
    protected Class<JsonItemManagerStub> getManagerClass() {
        return JsonItemManagerStub.class;
    }

    @Override
    protected JsonItemManagerStub createManager(
            ReadOnlyItemManager<ItemStub> modelManager) {
        return JsonItemManagerStub.from(modelManager);
    }
}

