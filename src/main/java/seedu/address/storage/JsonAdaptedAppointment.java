package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.id.AppointmentId;
import seedu.address.model.id.PatientId;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String appointmentId;
    private final String timeslot;
    private final String description;
    private final String patientId;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("id") String appointmentId,
                                  @JsonProperty("timeslot") String timeslot,
                                  @JsonProperty("description") String description,
                                  @JsonProperty("patientId") String patientId,
                                  @JsonProperty("tags") List<JsonAdaptedTag> tagged) {
        this.appointmentId = appointmentId;
        this.timeslot = timeslot;
        this.description = description;
        this.patientId = patientId;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointmentId = source.getAppointmentId().getId();
        timeslot = source.getTimeslot().timeslotString;
        description = source.getDescription().description;
        patientId = source.getPatientId().getId();
        tagged.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        final List<Tag> appointmentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            appointmentTags.add(tag.toModelType());
        }

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

        if (patientId == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, PatientId.class.getSimpleName()));
        }
        if (!PatientId.isValidPatientId(patientId)) {
            throw new IllegalValueException(PatientId.MESSAGE_CONSTRAINTS);
        }
        final PatientId modelPatientId = new PatientId(patientId);

        final Set<Tag> modelTags = new HashSet<>(appointmentTags);

        final AppointmentId modelId = new AppointmentId(appointmentId);
        return new Appointment(modelId, modelTimeslot, modelDescription, modelPatientId, modelTags);
    }

}
