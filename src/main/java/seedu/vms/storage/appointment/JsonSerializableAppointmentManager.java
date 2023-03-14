package seedu.vms.storage.appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.commons.exceptions.LimitExceededException;
import seedu.vms.model.IdData;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.model.appointment.AppointmentManager;
import seedu.vms.model.appointment.ReadOnlyAppointmentManager;

/**
 * An Immutable AppointmentManager that is serializable to JSON format.
 */
@JsonRootName(value = "appointmentmanager")
class JsonSerializableAppointmentManager {

    public static final String DUPLICATE_ID = "Appointment list contains duplicate ID(s).";

    private final List<JsonAdaptedAppointmentData> datas = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAppointmentManager} with the given patients.
     */
    @JsonCreator
    public JsonSerializableAppointmentManager(@JsonProperty("datas") List<JsonAdaptedAppointmentData> datas) {
        this.datas.addAll(datas);
    }

    /**
     * Converts a given {@code ReadOnlyPatientManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePatientManager}.
     */
    public JsonSerializableAppointmentManager(ReadOnlyAppointmentManager source) {
        datas.addAll(source.getMapView()
                .values()
                .stream()
                .map(JsonAdaptedAppointmentData::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this patient manager into the model's {@code PatientManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AppointmentManager toModelType() throws IllegalValueException {
        AppointmentManager appointmentManager = new AppointmentManager();
        for (JsonAdaptedAppointmentData jsonAdaptedAppointmentData : datas) {
            IdData<Appointment> appointmentData = jsonAdaptedAppointmentData.toModelType();
            if (appointmentManager.contains(appointmentData.getId())) {
                throw new IllegalValueException(DUPLICATE_ID);
            }
            try {
                appointmentManager.add(appointmentData);
            } catch (LimitExceededException limitEx) {
                // TODO: better message
                throw new IllegalValueException("ID limit reached");
            }
        }
        return appointmentManager;
    }

}
