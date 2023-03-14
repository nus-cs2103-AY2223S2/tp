package seedu.vms.storage.appointment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    private final JsonAdaptedPatientId patientId;
    private final JsonAdaptedLocalDateTime startTime;
    private final JsonAdaptedLocalDateTime endTime;
    private final JsonAdaptedGroupName vaccine;


    /** Constructs a {@code JsonAdaptedAppointment}. */
    @JsonCreator
    public JsonAdaptedAppointment(
                @JsonProperty("patientId") JsonAdaptedPatientId patientId,
                @JsonProperty("startTime") JsonAdaptedLocalDateTime startTime,
                @JsonProperty("endTime") JsonAdaptedLocalDateTime endTime,
                @JsonProperty("vaccine") JsonAdaptedGroupName vaccine) {
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.vaccine = vaccine;
    }


    /**
     * Converts the specified {@code Appointment} to a
     * {@code JsonAdaptedAppointment}.
     */
    public static JsonAdaptedAppointment fromModelType(Appointment appointment) {
        JsonAdaptedPatientId patientId = JsonAdaptedPatientId.fromModelType(appointment.getPatient());
        JsonAdaptedLocalDateTime startTime = JsonAdaptedLocalDateTime.fromModelType(appointment.getAppointmentTime());
        JsonAdaptedLocalDateTime endTime = JsonAdaptedLocalDateTime.fromModelType(appointment.getAppointmentEndTime());
        JsonAdaptedGroupName vaccine = JsonAdaptedGroupName.fromModelType(appointment.getVaccination());

        return new JsonAdaptedAppointment(patientId, startTime, endTime, vaccine);
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
        AppointmentBuilder builder = AppointmentBuilder.of(patientId.toModelType());

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "START TIME"));
        }
        builder.setStartTime(startTime.toModelType());

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "END TIME"));
        }
        builder.setEndTime(endTime.toModelType());

        if (vaccine == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "VACCINE"));
        }
        builder.setVaccine(vaccine.toModelType());

        return builder.create(manager);
    }
}
