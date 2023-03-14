package seedu.vms.storage.appointment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.IdData;
import seedu.vms.model.appointment.Appointment;


/**
 * Jackson-friendly version of {@code IdData<Patient>}.
 */
public class JsonAdaptedAppointmentData {
    private final boolean isActive;
    private final int id;
    private final JsonAdaptedAppointment appointment;


    /**
     * Constructs a {@code JsonAdaptedPatientData} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedAppointmentData(
            @JsonProperty("isActive") boolean isActive,
            @JsonProperty("id") int id,
            @JsonProperty("patient") JsonAdaptedAppointment appointment) {
        this.isActive = isActive;
        this.id = id;
        this.appointment = appointment;
    }


    /**
     * Converts a given {@code IdData<Patient>} into this class for Jackson use.
     */
    public JsonAdaptedAppointmentData(IdData<Appointment> AppointmentData) {
        isActive = AppointmentData.isActive();
        id = AppointmentData.getId();
        appointment = new JsonAdaptedAppointment(AppointmentData.getValue());
    }


    /**
     * Converts this Jackson-friendly adapted patient data object into the model's {@code IdData<Patient>} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient data.
     */
    public IdData<Appointment> toModelType() throws IllegalValueException {
        return new IdData<>(isActive, id, appointment.toModelType());
    }
}
