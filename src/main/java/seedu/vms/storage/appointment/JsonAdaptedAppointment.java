package seedu.vms.storage.appointment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

import seedu.vms.commons.core.index.Index;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.model.appointment.AppointmentBuilder;
import seedu.vms.model.appointment.AppointmentManager;
import seedu.vms.storage.JsonAdaptedGroupName;
import seedu.vms.storage.JsonAdaptedLocalDateTime;
import seedu.vms.storage.JsonAdaptedPatientId;


/** A JSON friendly version of {@link Appointment}. */
public class JsonAdaptedAppointment {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment [%s] is missing";

    private final Integer patientId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final JsonAdaptedGroupName vaccine;


    /** Constructs a {@code JsonAdaptedAppointment}. */
    @JsonCreator
    public JsonAdaptedAppointment(
                @JsonProperty("patientId") Integer patientId,
                @JsonProperty("startTime") LocalDateTime startTime,
                @JsonProperty("endTime") LocalDateTime endTime,
                @JsonProperty("vaccine") JsonAdaptedGroupName vaccine) {
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.vaccine = vaccine;
    }

    /**
     * Converts a given {@code Appointment} into a {@code JsonAdaptedAppointment}.
     */
    public JsonAdaptedAppointment(Appointment appointment) {
        this.patientId = appointment.getPatient().getOneBased();
        this.startTime = appointment.getAppointmentTime();
        this.endTime = appointment.getAppointmentEndTime();
        this.vaccine = new JsonAdaptedGroupName(appointment.getVaccination().getName());
    }


    /**
     * Converts the specified {@code Appointment} to a
     * {@code JsonAdaptedAppointment}.
     */
    public static JsonAdaptedAppointment fromModelType(Appointment appointment) {
        Integer patientId = appointment.getPatient().getOneBased();
        LocalDateTime startTime = appointment.getAppointmentTime();
        LocalDateTime endTime = appointment.getAppointmentEndTime();
        JsonAdaptedGroupName vaccine = JsonAdaptedGroupName.fromModelType(appointment.getVaccination());

        return new JsonAdaptedAppointment(patientId, startTime, endTime, vaccine);
    }


    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    public Appointment toModelType() throws IllegalValueException {

        if (patientId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "PATIENT ID"));
        }
        AppointmentBuilder builder = AppointmentBuilder.of(Index.fromOneBased(patientId));

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "START TIME"));
        }
        builder.setStartTime(startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "END TIME"));
        }
        builder.setEndTime(endTime);

        if (vaccine == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "VACCINE"));
        }
        builder.setVaccine(vaccine.toModelType());

        return builder.create(new AppointmentManager());
    }


    /**
     * Converts this JSON friendly version to an {@link Appointment} instance. The
     * type is added in to the vaccination type map in the process.
     *
     * @throws IllegalValueException if name field is missing.
     */
    public Appointment toModelType(AppointmentManager manager) throws IllegalValueException {

        if (patientId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "PATIENT ID"));
        }
        AppointmentBuilder builder = AppointmentBuilder.of(Index.fromOneBased(patientId));

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "START TIME"));
        }
        builder.setStartTime(startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "END TIME"));
        }
        builder.setEndTime(endTime);

        if (vaccine == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "VACCINE"));
        }
        builder.setVaccine(vaccine.toModelType());

        return builder.create(manager);
    }
}
