package seedu.vms.storage.appointment;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.util.JsonUtil;
import seedu.vms.model.appointment.AppointmentManager;


/** A JSON file loader of {@code Appointment}. */
public class AppointmentLoader {
    private static final String JSON_FILE_PATH = "/data/appointment.json";

    private final List<JsonAdaptedAppointment> data;


    /** Constructs a {@code AppointmentLoader}. */
    @JsonCreator
    public AppointmentLoader(@JsonProperty("data") List<JsonAdaptedAppointment> data) {
        this.data = data;
    }


    /**
     * Converts the specified {@code AppointmentManager} to a {@code AppointmentLoader}.
     */
    public static AppointmentLoader fromModelType(AppointmentManager manager) {
        List<JsonAdaptedAppointment> data = manager
                .asUnmodifiableObservableMap()
                .values()
                .stream()
                .map(JsonAdaptedAppointment::fromModelType)
                .collect(Collectors.toList());
        return new AppointmentLoader(data);
    }


    /**
     * Loads all the appointments contained in the appointment JSON file.
     *
     * @throws IllegalValueException if there are errors in the fields of the
     *      JSON file.
     * @throws IOException if an I/O error occurs.
     */
    public static AppointmentManager load() throws IllegalValueException, IOException {
        AppointmentLoader loader = JsonUtil.deserializeFromResource(JSON_FILE_PATH, AppointmentLoader.class);
        return loader.toModelType();
    }


    /**
     * Loads all the appointments contained in the specified JSON file.
     *
     * @throws IllegalValueException if there are errors in the fields of the
     *      JSON file.
     * @throws IOException if an I/O error occurs.
     */
    public static AppointmentManager load(Path path) throws IllegalValueException, IOException {
        AppointmentLoader loader = JsonUtil.deserializeFromFile(path, AppointmentLoader.class);
        return loader.toModelType();
    }


    private AppointmentManager toModelType() throws IllegalValueException {
        AppointmentManager storage = new AppointmentManager();
        for (JsonAdaptedAppointment adapted : data) {
            adapted.toModelType(storage);
        }
        return storage;
    }


    /**
     * Writes the data of this {@code AppointmentLoader} to the specified file.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void write(Path path) throws IOException {
        JsonUtil.serializeToFile(path, this);
    }
}
