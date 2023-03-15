package seedu.address.experimental.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.experimental.model.ReadOnlyReroll;

/***/
public interface RerollStorage {
    Path getRerollFilePath();

    Optional<ReadOnlyReroll> readReroll() throws DataConversionException, IOException;

    Optional<ReadOnlyReroll> readReroll(Path filePath) throws DataConversionException, IOException;

    void saveReroll(ReadOnlyReroll rr) throws IOException;

    void saveReroll(ReadOnlyReroll rr, Path filePath) throws IOException;
}
