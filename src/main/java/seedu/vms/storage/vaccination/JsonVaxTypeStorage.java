package seedu.vms.storage.vaccination;

import java.io.IOException;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.vaccination.VaxTypeManager;


/**
 * An {@link VaxTypeStorage} to handle read and write operations from and to
 * JSON files containing {@link VaxTypeManager} data.
 */
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


    @Override
    public void saveVaxTypes(VaxTypeManager manager) throws IOException {
        VaxTypeLoader.fromModelType(manager).write(USER_FILE_PATH);
    }
}
