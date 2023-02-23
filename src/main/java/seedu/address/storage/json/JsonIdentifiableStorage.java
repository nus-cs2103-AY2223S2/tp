package seedu.address.storage.json;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.model.item.Identifiable;
import seedu.address.storage.IdentifiableStorage;

/**
 * Represents the base class for the persistent storage of a
 * {@link ReadOnlyIdentifiableManager}. Extend this class to use the features
 * provided by this.
 *
 * @param <T> the type of the {@link Identifiable} to be stored.
 * @param <F> the type of the {@link JsonAdaptedModel} to be stored.
 * @param <M> the type of the {@link JsonIdentifiableManager} to be stored.
 */
public abstract class JsonIdentifiableStorage<T extends Identifiable,
        F extends JsonAdaptedModel<T>, M extends JsonIdentifiableManager<T, F>>
        implements IdentifiableStorage<T> {

    /**
     * The logger to be used by this class.
     */
    private static final Logger logger = LogsCenter.getLogger(JsonIdentifiableStorage.class);

    private final Path filePath;

    protected abstract Class<M> getManagerClass();

    protected abstract M createManager(ReadOnlyIdentifiableManager<T> modelManager);

    /**
     * Creates a new JsonIdentifiableStorage object.
     *
     * @param filePath the path to the file to be read from and written to.
     */
    public JsonIdentifiableStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getPath() {
        return filePath;
    }

    @Override
    public Optional<? extends ReadOnlyIdentifiableManager<T>> read()
            throws DataConversionException, IOException {
        return read(filePath);
    }

    @Override
    public Optional<? extends ReadOnlyIdentifiableManager<T>> read(Path filePath)
            throws DataConversionException, IOException {
        requireNonNull(filePath);
        Optional<M> jsonManager = JsonUtil.readJsonFile(filePath, getManagerClass());
        if (jsonManager.isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": "
                    + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void save(ReadOnlyIdentifiableManager<T> identifiableManager)
            throws IOException {
        save(identifiableManager, filePath);
    }

    @Override
    public void save(ReadOnlyIdentifiableManager<T> identifiableManager,
                     Path filePath) throws IOException {
        requireAllNonNull(identifiableManager, filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(createManager(identifiableManager), filePath);
    }
}
