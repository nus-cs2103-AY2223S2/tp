package seedu.vms.storage.appointment;

import java.io.IOException;
import java.nio.file.Path;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.FileUtil;
import seedu.vms.model.appointment.AppointmentManager;


/**
 * An {@link AppointmentStorage} to handle read and write operations from and to
 * JSON files containing {@link AppointmentManager} data.
 */
public class JsonAppointmentStorage implements AppointmentStorage {
    public static final Path USER_APPOINTMENT_PATH = Path.of("data", "appointment.json");


    @Override
    public AppointmentManager loadUserAppointments() throws IOException {
        try {
            return AppointmentLoader.load(USER_APPOINTMENT_PATH);
        } catch (IllegalValueException illValEx) {
            throw new IOException(illValEx.getMessage());
        }
    }


    @Override
    public AppointmentManager loadDefaultAppointments() throws RuntimeException {
        try {
            return AppointmentLoader.load();
        } catch (Throwable ex) {
            throw new RuntimeException("Unable to load defaults", ex);
        }
    }


    @Override
    public void saveAppointments(AppointmentManager manager) throws IOException {
        FileUtil.createIfMissing(USER_APPOINTMENT_PATH);
        AppointmentLoader.fromModelType(manager).write(USER_APPOINTMENT_PATH);
    }
}
