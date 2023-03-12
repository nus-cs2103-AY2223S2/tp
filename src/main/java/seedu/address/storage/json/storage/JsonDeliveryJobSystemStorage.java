package seedu.address.storage.json.storage;

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
import seedu.address.model.ReadOnlyDeliveryJobSystem;
import seedu.address.storage.DeliveryJobSystemStorage;
import seedu.address.storage.json.serializable.JsonSerializableDeliveryJobSystem;

/**
 * JsonDeliveryJobSystemStorage
 */
public class JsonDeliveryJobSystemStorage implements DeliveryJobSystemStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonDeliveryJobSystemStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDeliveryJobFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDeliveryJobSystem> readDeliveryJobSystem() throws DataConversionException, IOException {
        return readDeliveryJobSystem(filePath);
    }

    @Override
    public Optional<ReadOnlyDeliveryJobSystem> readDeliveryJobSystem(Path filePath)
            throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableDeliveryJobSystem> jsonDeliveryJobSystem = JsonUtil.readJsonFile(
                filePath, JsonSerializableDeliveryJobSystem.class);
        if (!jsonDeliveryJobSystem.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDeliveryJobSystem.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDeliveryJobSystem(ReadOnlyDeliveryJobSystem deliveryJobSystem) throws IOException {
        saveDeliveryJobSystem(deliveryJobSystem, filePath);
    }

    @Override
    public void saveDeliveryJobSystem(ReadOnlyDeliveryJobSystem deliveryJobSystem, Path filePath) throws IOException {
        requireNonNull(deliveryJobSystem);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDeliveryJobSystem(deliveryJobSystem), filePath);
    }
}
