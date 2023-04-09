package seedu.vms.storage.appointment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.vms.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.vms.model.appointment.AppointmentManager;

class JsonAppointmentStorageTest {

    private static final Path APPOINTMENT_FILEPATH = Paths.get("src", "test", "data", "appointment");
    private static final Path VALID_APPOINTMENT_FILE = APPOINTMENT_FILEPATH.resolve("validAppointmentManager.json");
    private static final Path INVALID_APPOINTMENT_FILE = APPOINTMENT_FILEPATH.resolve("invalidAppointmentManager.json");
    private static final Path INVALID_FILEPATH = APPOINTMENT_FILEPATH.resolve("invalidFilepath");

    @TempDir
    public Path testFolder;

    @Test
    void loadAppointments() {
        JsonAppointmentStorage storage;

        storage = new JsonAppointmentStorage(VALID_APPOINTMENT_FILE);
        assertDoesNotThrow(storage::loadAppointments);

        storage = new JsonAppointmentStorage(INVALID_APPOINTMENT_FILE);
        assertThrows(IllegalArgumentException.class, storage::loadAppointments);

        storage = new JsonAppointmentStorage(null);
        assertThrows(NullPointerException.class, storage::loadAppointments);

        storage = new JsonAppointmentStorage(INVALID_FILEPATH);
        assertThrows(FileNotFoundException.class, storage::loadAppointments);
    }

    @Test
    void saveAppointments() throws IOException {
        JsonAppointmentStorage storage = new JsonAppointmentStorage(VALID_APPOINTMENT_FILE);
        assertDoesNotThrow(storage::loadAppointments);

        JsonAppointmentStorage tempStorage = new JsonAppointmentStorage(testFolder.resolve("testSaveAppointmentManager.json"));
        assertDoesNotThrow(() -> tempStorage.saveAppointments(storage.loadAppointments()));
    }
}
