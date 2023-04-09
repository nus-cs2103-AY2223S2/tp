package seedu.vms.storage.appointment;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.FileUtil;
import seedu.vms.commons.util.JsonUtil;
import seedu.vms.model.appointment.AppointmentManager;


/**
 * An {@link AppointmentStorage} to handle read and write operations from and to
 * JSON files containing {@link AppointmentManager} data.
 */
public class JsonAppointmentStorage implements AppointmentStorage {
    public static final Path USER_APPOINTMENT_PATH = Path.of("data", "appointment.json");

    private final Path filePath;

    public JsonAppointmentStorage() {
        this(USER_APPOINTMENT_PATH);
    }

    public JsonAppointmentStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public AppointmentManager loadAppointments() throws IOException {
        try {
            return JsonUtil
                    .deserializeFromFile(filePath, JsonSerializableAppointmentManager.class)
                    .toModelType();
        } catch (IllegalValueException illValEx) {
            throw new IOException(illValEx.getMessage());
        }
    }

    @Override
    public void saveAppointments(AppointmentManager manager) throws IOException {
        requireNonNull(manager);

        FileUtil.createIfMissing(filePath);
        JsonUtil.serializeToFile(filePath, new JsonSerializableAppointmentManager(manager));
    }
}
