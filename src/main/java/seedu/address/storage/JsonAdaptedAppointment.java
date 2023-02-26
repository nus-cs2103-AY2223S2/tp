package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {

    private final String appointmentName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(String appointmentName) {
        this.appointmentName = appointmentName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointmentName = source.appointmentTime;
    }

    @JsonValue
    public String getAppointmentName() {
        return appointmentName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (!Appointment.isValidAppointment(appointmentName)) {
            throw new IllegalValueException(Appointment.MESSAGE_CONSTRAINTS);
        }
        return new Appointment(appointmentName);
    }

}
