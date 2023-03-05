package seedu.vms.storage.vaccination;

import java.io.IOException;

import seedu.vms.model.vaccination.VaxTypeManager;

public interface VaxTypeStorage {
    public VaxTypeManager loadUserVaxTypes() throws IOException;
    public VaxTypeManager loadDefaultVaxTypes() throws RuntimeException;
}
