package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A class to write Export data stored as a json file on the hard disk.
 */
public class JsonExportStorage implements ExportStorage {

    private Path filePath;

    /**
     * Constructs a {@code JsonExportStorage} with the given {@code filePath}.
     */
    public JsonExportStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFilePath() {
        return filePath;
    }

    @Override
    public Path getExportFilePath() {
        return filePath;
    }

    @Override
    public void exportPerson(Person personToExport) throws IOException {
        exportPerson(personToExport, filePath);
    }

    /**
     * Similar to {@link #exportPerson(Person)}.
     *
     * @param exportFilePath location of the data. Cannot be null.
     */
    public void exportPerson(Person personToExport, Path exportFilePath) throws IOException {
        requireNonNull(personToExport);
        requireNonNull(exportFilePath);

        FileUtil.createIfMissing(exportFilePath);
        AddressBook exportAddressBook = new AddressBook();
        exportAddressBook.addPerson(personToExport);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(exportAddressBook), exportFilePath);
    }
}
