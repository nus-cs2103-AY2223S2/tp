package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyDeliveryJobSystem;

/**
 * DeliveryJobSystemStorage
 */
public interface DeliveryJobSystemStorage {

    Path getDeliveryJobFilePath();

    Optional<ReadOnlyDeliveryJobSystem> readDeliveryJobSystem() throws DataConversionException, IOException;

    Optional<ReadOnlyDeliveryJobSystem> readDeliveryJobSystem(Path filePath)
            throws DataConversionException, IOException;

    void saveDeliveryJobSystem(ReadOnlyDeliveryJobSystem deliveryJobSystem) throws IOException;

    void saveDeliveryJobSystem(ReadOnlyDeliveryJobSystem deliveryJobSystem, Path filePath) throws IOException;
}
