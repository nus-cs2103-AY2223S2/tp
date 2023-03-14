package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Booking;
import seedu.address.model.person.Name;

public class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String name;
    private final String booking;

    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("name") String name, @JsonProperty("Booking") String booking) {
        this.name = name;
        this.booking = booking;
    }

    public JsonAdaptedAppointment(Appointment source) {
        name = source.getName().fullName; // todo change to nric
        booking = source.getBooking().toString();
    }

    public Appointment toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final Name modelName = new Name(name);

        if (booking == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Booking.class.getSimpleName()));
        }
        if (!Booking.isValidBookingFormat(booking)) {
            throw new IllegalValueException(Booking.MESSAGE_CONSTRAINTS);
        }

        final Booking modelBooking = new Booking(booking);

        return new Appointment(modelName, modelBooking);
    }
}
