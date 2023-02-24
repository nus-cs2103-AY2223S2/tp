package seedu.address.storage.stubs;

import java.nio.file.Path;
import java.util.logging.Logger;

import seedu.address.commons.util.FileHelper;
import seedu.address.commons.util.JsonHelper;
import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.storage.json.JsonIdentifiableStorage;

/**
 * A stub class for {@link JsonIdentifiableStorage} that is used for testing.
 */
public class JsonIdentifiableStorageStub extends
        JsonIdentifiableStorage<IdentifiableStub,
                                       JsonAdaptedIdentifiableStub,
                                       JsonIdentifiableManagerStub> {
    /**
     * Creates a new {@code JsonIdentifiableStorageStub} object.
     *
     * @param filePath   The path to the file to be read from and written to.
     * @param jsonHelper The {@link JsonHelper} to be used.
     * @param fileHelper The {@link FileHelper} to be used.
     * @param logger     The {@link Logger} to be used.
     */
    public JsonIdentifiableStorageStub(Path filePath, JsonHelper jsonHelper,
                                       FileHelper fileHelper, Logger logger) {
        super(filePath, jsonHelper, fileHelper, logger);
    }

    @Override
    protected Class<JsonIdentifiableManagerStub> getManagerClass() {
        return JsonIdentifiableManagerStub.class;
    }

    @Override
    protected JsonIdentifiableManagerStub createManager(
            ReadOnlyIdentifiableManager<IdentifiableStub> modelManager) {
        return JsonIdentifiableManagerStub.from(modelManager);
    }
}

