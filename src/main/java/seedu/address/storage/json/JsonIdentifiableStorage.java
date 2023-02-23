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
import seedu.address.commons.util.FileHelper;
import seedu.address.commons.util.JsonHelper;
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
                                                     F extends JsonAdaptedModel<T>,
                                                     M extends JsonIdentifiableManager<T, F>>
        implements IdentifiableStorage<T> {

    /**
     * The logger to be used by this class.
     */
    private final Logger logger;

    private final Path filePath;

    /**
     * The dependency to handle JSON operations, this is just so that we can
     * have better separation of concerns in unit testing.
     */
    private final JsonHelper jsonHelper;

    /**
     * The dependency to handle file operations, this is just so that we can
     * have better separation of concerns in unit testing.
     */
    private final FileHelper fileHelper;

    /**
     * Creates a new JsonIdentifiableStorage object.
     *
     * @param filePath the path to the file to be read from and written to.
     */
    public JsonIdentifiableStorage(Path filePath) {
        this(filePath, JsonHelper.INSTANCE, FileHelper.INSTANCE, LogsCenter.getLogger(JsonIdentifiableStorage.class));
    }

    /**
     * Creates a new JsonIdentifiableStorage object.
     *
     * @param filePath   the path to the file to be read from and written to.
     * @param jsonHelper the JsonHelper to be used.
     * @param fileHelper the FileHelper to be used.
     */
    protected JsonIdentifiableStorage(Path filePath, JsonHelper jsonHelper,
                                      FileHelper fileHelper, Logger logger) {
        this.filePath = filePath;
        this.jsonHelper = jsonHelper;
        this.fileHelper = fileHelper;
        this.logger = logger;
    }

    /**
     * Gets the class of the {@link JsonIdentifiableManager} that was used, i
     * .e. gets the class of the {@code M} type parameter. This is needed
     * because the information about the type parameter is erased at runtime.
     *
     * @return the class of the {@link JsonIdentifiableManager} that was used.
     */
    protected abstract Class<M> getManagerClass();

    /**
     * Creates a new {@link JsonIdentifiableManager} from the given
     * {@link ReadOnlyIdentifiableManager}. This is needed because we cannot
     * specify a constructor for the {@code M} type parameter.
     *
     * @param modelManager the {@link ReadOnlyIdentifiableManager} to be used
     *                     to create the new {@link JsonIdentifiableManager}.
     * @return a new {@link JsonIdentifiableManager} created from the given
     */
    protected abstract M createManager(ReadOnlyIdentifiableManager<T> modelManager);

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
        Optional<M> jsonManager = jsonHelper.readJsonFile(filePath, getManagerClass());
        if (jsonManager.isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.warning("Illegal values found in " + filePath + ": "
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
        fileHelper.createIfMissing(filePath);
        jsonHelper.saveJsonFile(createManager(identifiableManager), filePath);
    }
}
