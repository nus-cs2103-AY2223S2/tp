package seedu.vms.storage.appointment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.vms.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.vms.commons.util.JsonUtil;

class JsonSerializableAppointmentManagerTest {

    private static final Path APPOINTMENT_FILEPATH = Paths.get("src", "test", "data", "appointment");
    private static final Path VALID_APPOINTMENT_FILE = APPOINTMENT_FILEPATH.resolve("validAppointmentManager.json");
    private static final Path INVALID_APPOINTMENT_FILE = APPOINTMENT_FILEPATH.resolve("invalidAppointmentManager.json");

    @Test
    void toModelType() throws IOException {
        JsonSerializableAppointmentManager dataFromFile;

        dataFromFile = JsonUtil.deserializeFromFile(VALID_APPOINTMENT_FILE,
                JsonSerializableAppointmentManager.class);
        assertDoesNotThrow(dataFromFile::toModelType);

        dataFromFile = JsonUtil.deserializeFromFile(INVALID_APPOINTMENT_FILE,
                JsonSerializableAppointmentManager.class);
        assertThrows(IllegalArgumentException.class, dataFromFile::toModelType);
    }
}
