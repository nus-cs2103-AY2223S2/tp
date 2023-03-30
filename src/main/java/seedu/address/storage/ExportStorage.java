package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.model.person.Person;

/**
 * Represents a storage for an exported person
 */
public interface ExportStorage {

    /**
     * Returns the file path of the export data file.
     */
    Path getExportFilePath();

    /**
     * Exports {@code Person}
     * @param personToExport cannot be null
     * @throws IOException if there was any problem creating the file.
     */
    void exportPerson(Person personToExport) throws IOException;

    /**
     * @see #exportPerson(Person)
     */
    void exportPerson(Person personToExport, Path filePath) throws IOException;


}

