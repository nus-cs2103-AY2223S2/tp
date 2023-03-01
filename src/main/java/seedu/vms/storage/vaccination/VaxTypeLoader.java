package seedu.vms.storage.vaccination;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.JsonUtil;


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
     * Loads all the vaccination types contained in the vaccination type JSON
     * file.
     *
     * @throws IllegalValueException if there are errors in the fields of the
     *      JSON file.
     * @throws IOException if an I/O error occurs.
     */
    public static void load() throws IllegalValueException, IOException {
        VaxTypeLoader adaptedList = JsonUtil
                .deserializeFromResource(JSON_FILE_PATH, VaxTypeLoader.class);
        for (JsonAdaptedVaxType adapted : adaptedList.types) {
            adapted.toModelType();
        }
    }
}
