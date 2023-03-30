package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.appointment.Appointment;
import seedu.address.model.client.appointment.AppointmentName;
import seedu.address.model.client.appointment.MeetupDate;

/**
 * Jackson-friendly version of {@Link Appointment}.
 */
public class JsonAdaptedAppointment {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";
    public final String appointmentName;
    public final String meetupDate;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     * @param appointmentName The name of the appointment.
     * @param meetupDate The meetup date of the appointment.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("appointmentName") String appointmentName,
                                  @JsonProperty("meetupDate") String meetupDate) {
        this.appointmentName = appointmentName;
        this.meetupDate = meetupDate;
    }

    /**
     * Constructs a given {@code Appointment} into this class for Jackson use.
     * @param source The appointment that we are interested in.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointmentName = source.getAppointmentName().appointmentName;
        meetupDate = source.getMeetupDate().toString();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        final AppointmentName modelAppointmentName;
        final MeetupDate modelMeetupDate;
        if (appointmentName == null && meetupDate == null) {
            modelMeetupDate = new MeetupDate();
            modelAppointmentName = new AppointmentName();
            return new Appointment(modelAppointmentName, modelMeetupDate);
        }

        if (appointmentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentName.class.getSimpleName()));
        }

        if (meetupDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MeetupDate.class.getSimpleName()));
        }

        if (!AppointmentName.isValidName(appointmentName)) {
            throw new IllegalValueException(AppointmentName.MESSAGE_CONSTRAINTS);
        }

        if (!MeetupDate.isValidDate(meetupDate)) {
            throw new IllegalValueException(MeetupDate.MESSAGE_CONSTRAINTS);
        }

        modelAppointmentName = new AppointmentName(appointmentName);
        modelMeetupDate = new MeetupDate(meetupDate);
        return new Appointment(modelAppointmentName, modelMeetupDate);
    }
}
