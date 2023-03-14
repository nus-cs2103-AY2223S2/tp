package seedu.vms.storage.vaccination;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.JsonUtil;
import seedu.vms.model.vaccination.VaxTypeManager;


/** A JSON file loader of {@code VaxType}. */
public class VaxTypeLoader {
    private static final String JSON_FILE_PATH = "/data/vaxTypes.json";

    private final List<JsonAdaptedVaxType> types;


    /** Constructs a {@code VaxTypeLoader}. */
    @JsonCreator
    public VaxTypeLoader(@JsonProperty("types") List<JsonAdaptedVaxType> types) {
        this.types = types;
    }


    /**
     * Converts the specified {@code VaxTypeManager} to a {@code VaxTypeLoader}.
     */
    public static VaxTypeLoader fromModelType(VaxTypeManager manager) {
        List<JsonAdaptedVaxType> types = manager
                .asUnmodifiableObservableMap()
                .values()
                .stream()
                .map(vaxType -> JsonAdaptedVaxType.fromModelType(vaxType))
                .collect(Collectors.toList());
        return new VaxTypeLoader(types);
    }


    /**
     * Loads all the vaccination types contained in the vaccination type JSON
     * file.
     *
     * @throws IllegalValueException if there are errors in the fields of the
     *      JSON file.
     * @throws IOException if an I/O error occurs.
     */
    public static VaxTypeManager load() throws IllegalValueException, IOException {
        VaxTypeLoader loader = JsonUtil.deserializeFromResource(JSON_FILE_PATH, VaxTypeLoader.class);
        return loader.toModelType();
    }


    /**
     * Loads all the vaccination types contained in the specified JSON file.
     *
     * @throws IllegalValueException if there are errors in the fields of the
     *      JSON file.
     * @throws IOException if an I/O error occurs.
     */
    public static VaxTypeManager load(Path path) throws IllegalValueException, IOException {
        VaxTypeLoader loader = JsonUtil.deserializeFromFile(path, VaxTypeLoader.class);
        return loader.toModelType();
    }


    private VaxTypeManager toModelType() throws IllegalValueException {
        VaxTypeManager storage = new VaxTypeManager();
        for (JsonAdaptedVaxType adapted : types) {
            adapted.toModelType(storage);
        }
        return storage;
    }


    /**
     * Writes the data of this {@code VaxTypeLoader} to the specified file.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void write(Path path) throws IOException {
        JsonUtil.serializeToFile(path, this);
    }
}
