package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.patient.Name;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String patientName;
    private final String timeslot;
    private final String description;
    private final String doctor;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("timeslot") String timeslot,
                                  @JsonProperty("description") String description,
                                  @JsonProperty("name") String patientName,
                                  @JsonProperty("doctor") String doctor) {
        this.timeslot = timeslot;
        this.description = description;
        this.patientName = patientName;
        this.doctor = doctor;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        timeslot = source.getTimeslot().timeslotString;
        description = source.getDescription().description;
        patientName = source.getPatientName().fullName;
        doctor = source.getDoctor().doctor;
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (patientName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(patientName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelPatientName = new Name(patientName);

        if (timeslot == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Timeslot.class.getSimpleName()));
        }
        if (!Timeslot.isValidTimeslot(timeslot)) {
            throw new IllegalValueException(Timeslot.MESSAGE_CONSTRAINTS);
        }
        final Timeslot modelTimeslot = new Timeslot(timeslot);

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (doctor == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Doctor.class.getSimpleName()));
        }
        if (!Doctor.isValidDoctor(doctor)) {
            throw new IllegalValueException(Doctor.MESSAGE_CONSTRAINTS);
        }
        final Doctor modelDoctor = new Doctor(doctor);

        return new Appointment(modelPatientName, modelTimeslot, modelDescription, modelDoctor);
    }

}
