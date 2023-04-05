package seedu.vms.storage.vaccination;

import java.io.IOException;

import seedu.vms.model.vaccination.ReadOnlyVaxTypeManage;
import seedu.vms.model.vaccination.VaxTypeManager;


/**
 * Represents the storage for {@link seedu.vms.model.vaccination.VaxTypeManager}.
 */
public interface VaxTypeStorage {
    /**
     * Loads the {@code VaxTypeManager} as defined by the user.
     *
     * @throws IOException if an I/O error occurs.
     */
    public VaxTypeManager loadUserVaxTypes() throws IOException;


    /**
     * Loads the {@code VaxTypeManager} as defined in the application
     * resources.
     *
     * @throws RuntimeException if an error occurs.
     */
    public VaxTypeManager loadDefaultVaxTypes() throws RuntimeException;


    /**
     * Saves the specified {@code VaxTypeManager} to hard disk.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void saveVaxTypes(ReadOnlyVaxTypeManage manager) throws IOException;
}
