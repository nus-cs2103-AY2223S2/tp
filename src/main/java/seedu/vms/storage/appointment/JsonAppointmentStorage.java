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


    @Override
    public AppointmentManager loadAppointments() throws IOException {
        try {
            return JsonUtil
                    .deserializeFromFile(USER_APPOINTMENT_PATH, JsonSerializableAppointmentManager.class)
                    .toModelType();
        } catch (IllegalValueException illValEx) {
            throw new IOException(illValEx.getMessage());
        }
    }


    @Override
    public void saveAppointments(AppointmentManager manager) throws IOException {
        requireNonNull(manager);

        FileUtil.createIfMissing(USER_APPOINTMENT_PATH);
        JsonUtil.serializeToFile(USER_APPOINTMENT_PATH, new JsonSerializableAppointmentManager(manager));
    }
}
