package seedu.vms.storage.vaccination;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.JsonUtil;


/** A JSON file loader of {@code VaxType}. */
public class JsonAdaptedVaxTypeList {
    private static final String JSON_FILE_PATH = "/data/vaxTypes.json";

    private final List<JsonAdaptedVaxType> types;


    /** Constructs a {@code JsonAdaptedVaxTypeList}. */
    @JsonCreator
    public JsonAdaptedVaxTypeList(@JsonProperty("types") List<JsonAdaptedVaxType> types) {
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
        JsonAdaptedVaxTypeList adaptedList = JsonUtil
                .deserializeFromResource(JSON_FILE_PATH, JsonAdaptedVaxTypeList.class);
        for (JsonAdaptedVaxType adapted : adaptedList.types) {
            adapted.toModelType();
        }
    }
}
