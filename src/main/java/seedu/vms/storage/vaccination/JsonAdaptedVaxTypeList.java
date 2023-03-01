package seedu.vms.storage.vaccination;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.JsonUtil;


public class JsonAdaptedVaxTypeList {
    private static final String JSON_FILE_PATH = "/data/vaxTypes.json";

    private final List<JsonAdaptedVaxType> types;


    @JsonCreator
    public JsonAdaptedVaxTypeList(@JsonProperty("types") List<JsonAdaptedVaxType> types) {
        this.types = types;
    }


    public static void load() throws IllegalValueException, IOException {
        JsonAdaptedVaxTypeList adaptedList = JsonUtil
                .deserializeFromResource(JSON_FILE_PATH, JsonAdaptedVaxTypeList.class);
        for (JsonAdaptedVaxType adapted : adaptedList.types) {
            adapted.toModelType();
        }
    }
}
