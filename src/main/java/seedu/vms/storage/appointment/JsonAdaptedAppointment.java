package seedu.vms.storage.appointment;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.core.index.Index;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.model.appointment.AppointmentBuilder;
import seedu.vms.model.appointment.AppointmentManager;
import seedu.vms.storage.JsonAdaptedGroupName;


/** A JSON friendly version of {@link Appointment}. */
public class JsonAdaptedAppointment {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment [%s] is missing";

    private final Integer patientId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final JsonAdaptedGroupName vaccine;
    private final Boolean isCompleted;


    /** Constructs a {@code JsonAdaptedAppointment}. */
    @JsonCreator
    public JsonAdaptedAppointment(
                @JsonProperty("patientId") Integer patientId,
                @JsonProperty("startTime") LocalDateTime startTime,
                @JsonProperty("endTime") LocalDateTime endTime,
                @JsonProperty("vaccine") JsonAdaptedGroupName vaccine,
                @JsonProperty("isCompleted") Boolean isCompleted) {
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.vaccine = vaccine;
        this.isCompleted = isCompleted;
    }

    /**
     * Converts a given {@code Appointment} into a {@code JsonAdaptedAppointment}.
     */
    public JsonAdaptedAppointment(Appointment appointment) {
        this.patientId = appointment.getPatient().getOneBased();
        this.startTime = appointment.getAppointmentTime();
        this.endTime = appointment.getAppointmentEndTime();
        this.vaccine = new JsonAdaptedGroupName(appointment.getVaccination().getName());
        this.isCompleted = appointment.getStatus();
    }


    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    public Appointment toModelType() throws IllegalValueException {

        if (patientId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "PATIENT ID"));
        } else if (patientId < 1) {
            throw new IllegalValueException("Invalid PATIENT ID");
        }
        AppointmentBuilder builder = AppointmentBuilder.of(Index.fromOneBased(patientId));

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "START TIME"));
        }
        builder = builder.setStartTime(startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "END TIME"));
        }
        builder = builder.setEndTime(endTime);

        if (vaccine == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "VACCINE"));
        }
        builder = builder.setVaccine(vaccine.toModelType());

        if (isCompleted == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "IS_COMPLETED"));
        }
        builder = builder.setStatus(isCompleted);

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
        builder = builder.setStartTime(startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "END TIME"));
        }
        builder = builder.setEndTime(endTime);

        if (vaccine == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "VACCINE"));
        }
        builder = builder.setVaccine(vaccine.toModelType());

        return builder.create(manager);
    }
}
