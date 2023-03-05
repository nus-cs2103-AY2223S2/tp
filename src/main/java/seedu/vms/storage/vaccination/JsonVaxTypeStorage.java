package seedu.vms.storage.vaccination;

import java.io.IOException;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.vaccination.VaxTypeManager;



public class JsonVaxTypeStorage implements VaxTypeStorage {
    public static final String USER_FILE_PATH = "data/vaxtype.json";


    @Override
    public VaxTypeManager loadUserVaxTypes() throws IOException {
        try {
            return VaxTypeLoader.load(USER_FILE_PATH);
        } catch (IllegalValueException illValEx) {
            throw new IOException(illValEx.getMessage());
        }
    }


    @Override
    public VaxTypeManager loadDefaultVaxTypes() throws RuntimeException {
        try {
            return VaxTypeLoader.load();
        } catch (Throwable ex) {
            throw new RuntimeException("Unable to load defaults", ex);
        }
    }
}
