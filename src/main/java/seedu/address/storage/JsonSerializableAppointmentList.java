package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AppointmentList;
import seedu.address.model.ReadOnlyAppointmentList;
import seedu.address.model.appointment.Appointment;

/**
 * An immutable Appointment List that is serializable to JSON format.
 */
@JsonRootName(value = "appointmentlist")
class JsonSerializableAppointmentList {

    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointment list contains duplicate appointment(s).";

    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAppointmentList} with the given appointments.
     */
    @JsonCreator
    public JsonSerializableAppointmentList(@JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.appointments.addAll(appointments);
    }

    /**
     * Converts a given {@code ReadOnlyAppointmentList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAppointmentList}.
     */
    public JsonSerializableAppointmentList(ReadOnlyAppointmentList source) {
        appointments.addAll(
            source.getAppointmentList().stream().map(JsonAdaptedAppointment::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AppointmentList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AppointmentList toModelType() throws IllegalValueException {
        AppointmentList addressBook = new AppointmentList();
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (addressBook.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            addressBook.addAppointment(appointment);
        }
        return addressBook;
    }

}
