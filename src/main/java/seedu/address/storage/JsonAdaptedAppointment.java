package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Booking;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String nric;
    private final String booking;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("nric") String nric, @JsonProperty("Booking") String booking) {
        this.nric = nric;
        this.booking = booking;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        nric = source.getPatientNric().toString(); // todo change to nric
        booking = source.getBooking().toString();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final Nric modelNric = new Nric(nric);

        if (booking == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Booking.class.getSimpleName()));
        }
        if (!Booking.isValidBookingFormat(booking)) {
            throw new IllegalValueException(Booking.MESSAGE_CONSTRAINTS);
        }

        final Booking modelBooking = new Booking(booking);

        return new Appointment(modelNric, modelBooking);
    }
}
