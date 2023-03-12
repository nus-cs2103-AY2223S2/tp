package seedu.vms.storage.vaccination;

import java.io.IOException;
import java.nio.file.Path;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.FileUtil;
import seedu.vms.model.vaccination.VaxTypeManager;


/**
 * An {@link VaxTypeStorage} to handle read and write operations from and to
 * JSON files containing {@link VaxTypeManager} data.
 */
public class JsonVaxTypeStorage implements VaxTypeStorage {
    public static final Path USER_VAX_FILE_PATH = Path.of("data", "vaxtype.json");


    @Override
    public VaxTypeManager loadUserVaxTypes() throws IOException {
        try {
            return VaxTypeLoader.load(USER_VAX_FILE_PATH);
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
        FileUtil.createIfMissing(USER_VAX_FILE_PATH);
        VaxTypeLoader.fromModelType(manager).write(USER_VAX_FILE_PATH);
    }
}
