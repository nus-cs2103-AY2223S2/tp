package seedu.vms.model.appointment;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import seedu.vms.commons.core.index.Index;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.Age;
import seedu.vms.model.GroupName;


/**
 * A factory class to create, rename or edit {@link Appointment} to its set
 * {@link AppointmentManager}.
 */
public class AppointmentBuilder {
    private static final String MESSAGE_DUPLICATE_TYPE = "Appointment already exist";
    private static final String MESSAGE_MISSING_TYPE = "Appointment does not exist";

    private final Index refId;
    private final Index patientId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final GroupName vaccine;


    private AppointmentBuilder(Index refId, Index patientId, LocalDateTime startTime,
                               LocalDateTime endTime, GroupName vaccine) {
        this.refId = refId;
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.vaccine = vaccine;
    }


    /**
     * Factory method to create a {@code AppointmentBuilder}.
     *
     * @param refId - the patient id of the existing appointment to build
     *      from.
     * @param patientId - the patient id of the {@code Appointment} to create.
     */
    public static AppointmentBuilder of(Index refId, Index patientId) {
        return new AppointmentBuilder(refId, patientId,
                null, null, null);
    }


    /**
     * Factory method to create a {@code AppointmentBuilder} without a reference.
     *
     * @param patientId - the patient id of the {@code Appointment} to create.
     */
    public static AppointmentBuilder of(Index patientId) {
        return AppointmentBuilder.of(patientId, patientId);
    }

    public AppointmentBuilder setPatientId(Index patientId) {
        return new AppointmentBuilder(refId, patientId, startTime, endTime, vaccine);
    }

    public AppointmentBuilder setStartTime(LocalDateTime startTime) {
        return new AppointmentBuilder(refId, patientId, startTime, endTime, vaccine);
    }

    public AppointmentBuilder setEndTime(LocalDateTime endTime) {
        return new AppointmentBuilder(refId, patientId, startTime, endTime, vaccine);
    }

    public AppointmentBuilder setVaccine(GroupName vaccine) {
        return new AppointmentBuilder(refId, patientId, startTime, endTime, vaccine);
    }


    /**
     * Builds the vaccination type and adds it to the specified
     * {@code VaxTypeManager}.
     *
     * @return the built {@code VaxType}.
     * @throws IllegalValueException if the appointment might be replaced.
     */
    public Appointment create(AppointmentManager manager) throws IllegalValueException {
        if (manager.contains(refId.getZeroBased()) || manager.contains(patientId.getZeroBased())) {
            throw new IllegalValueException(MESSAGE_DUPLICATE_TYPE);
        }
        return build(manager);
    }


    /**
     * Builds and updates the vaccination type in the specified
     * {@code VaxTypeManager}.
     *
     * @return the built {@code VaxType}.
     * @throws IllegalValueException if the reference vaccination is not
     *      present or if the vaccination type is being renamed to one that
     *      already exists.
     */
    public Appointment update(AppointmentManager manager) throws IllegalValueException {
        if (!manager.contains(refId.getZeroBased())
                    || (!refId.equals(patientId) && manager.contains(patientId.getZeroBased()))) {
            throw new IllegalValueException(MESSAGE_MISSING_TYPE);
        }
        return build(manager);
    }


    private Appointment build(AppointmentManager manager) throws IllegalValueException {
        Appointment appointment = new Appointment(patientId, startTime, endTime, vaccine);
        manager.add(appointment);
        return appointment;
    }
}
