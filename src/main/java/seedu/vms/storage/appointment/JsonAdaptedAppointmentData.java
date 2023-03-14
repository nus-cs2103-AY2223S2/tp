package seedu.vms.storage.appointment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.IdData;
import seedu.vms.model.appointment.Appointment;


/**
 * Jackson-friendly version of {@code IdData<Appointment>}.
 */
public class JsonAdaptedAppointmentData {
    private final boolean isActive;
    private final int id;
    private final JsonAdaptedAppointment appointment;


    /**
     * Constructs a {@code JsonAdaptedAppointmentData} with the given appointment details.
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
     * Converts a given {@code IdData<Appointment>} into this class for Jackson use.
     */
    public JsonAdaptedAppointmentData(IdData<Appointment> appointmentData) {
        isActive = appointmentData.isActive();
        id = appointmentData.getId();
        appointment = new JsonAdaptedAppointment(appointmentData.getValue());
    }


    /**
     * Converts this Jackson-friendly adapted appointment data object into the model's
     *      {@code IdData<Appointment>} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the
     *      adapted appointment data.
     */
    public IdData<Appointment> toModelType() throws IllegalValueException {
        return new IdData<>(isActive, id, appointment.toModelType());
    }
}
