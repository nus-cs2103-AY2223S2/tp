package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.service.appointment.Appointment;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final int id;
    private final int customerId;
    private final String timeDate;
    private final List<Integer> staffIds = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("id") int id, @JsonProperty("customerId") int customerId,
                                  @JsonProperty("timeDate") String timeDate,
                                  @JsonProperty("staffIds") List<Integer> staffIds) {
        this.id = id;
        this.customerId = customerId;
        this.timeDate = timeDate;
        if (staffIds != null) {
            this.staffIds.addAll(staffIds);
        }
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        id = source.getId();
        customerId = source.getCustomerId();
        timeDate = source.getTimeDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"));
        staffIds.addAll(source.getStaffIds().stream()
                .map(Integer::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        final List<Integer> appointmentStaffIds = new ArrayList<>();
        for (Integer staffId : staffIds) {
            appointmentStaffIds.add(staffId);
        }

        if (id <= 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Id"));
        }
        final int modelId = id;

        if (customerId <= 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Customer Id"));
        }
        final int modelCustomerId = customerId;

        if (timeDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Datetime"));
        }
        final LocalDateTime modelTimeDate = LocalDateTime.parse(timeDate, dtf);

        final Set<Integer> moedlStaffIds = new HashSet<>(appointmentStaffIds);

        return new Appointment(modelId, modelCustomerId, modelTimeDate, moedlStaffIds);
    }
}
