package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.shop.CaseInsensitiveHashMap;
import seedu.address.model.service.Service;
import seedu.address.model.service.ServiceStatus;


/**
 * Jackson-friendly version of {@link Service}.
 */
class JsonAdaptedService {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Service's %s field is missing!";

    private final int id;
    private final int vehicleId;
    private final String entryDate;
    private final String description;
    private final String estimatedFinishDate;
    private final String status;
    private final List<JsonAdaptedPart> parts = new ArrayList<>();
    private final List<Integer> assignedToIds = new ArrayList<>();



    /**
     * Constructs a {@code JsonAdaptedService} with the given service details.
     */
    @JsonCreator
    public JsonAdaptedService(@JsonProperty("id") int id, @JsonProperty("vehicleId") int vehicleId,
                              @JsonProperty("entryDate") String entryDate,
                              @JsonProperty("description") String description,
                              @JsonProperty("estimatedFinishDate") String estimatedFinishDate,
                              @JsonProperty("status") String status,
                              @JsonProperty("parts") List<JsonAdaptedPart> parts,
                              @JsonProperty("assignedToIds") List<Integer> assignedToIds) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.entryDate = entryDate;
        this.description = description;
        this.estimatedFinishDate = estimatedFinishDate;
        this.status = status;
        if (parts != null) {
            this.parts.addAll(parts);
        }
        if (assignedToIds != null) {
            this.assignedToIds.addAll(assignedToIds);
        }
    }

    /**
     * Converts a given {@code Service} into this class for Jackson use.
     */
    public JsonAdaptedService(Service source) {
        id = source.getId();
        vehicleId = source.getVehicleId();
        entryDate = source.getEntryDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        description = source.getDescription();
        estimatedFinishDate = source.getEstimatedFinishDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        status = source.getStatus().getValue();
        Map<String, Integer> sourceRequiredParts = source.getRequiredParts();
        for (Map.Entry<String, Integer> e : sourceRequiredParts.entrySet()) {
            parts.add(new JsonAdaptedPart(e.getKey(), e.getValue()));
        }
        assignedToIds.addAll(source.getAssignedToIds().stream()
                .map(Integer::new)
                .collect(Collectors.toList()));
    }

    /**
     * Returns the ServiceStatus based on the String input given
     * by iterating through the existing ServiceStatus values
     *
     * @return the ServiceStatus based on the String input given
     */
    private ServiceStatus getServiceStatus(String input) {
        for (ServiceStatus s : ServiceStatus.values()) {
            if (s.isEqual(input)) {
                return s;
            }
        }
        return ServiceStatus.ON_HOLD;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Service} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted service.
     */
    public Service toModelType() throws IllegalValueException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        final Map<String, Integer> modelParts = new CaseInsensitiveHashMap<>();
        for (JsonAdaptedPart part : parts) {
            Map.Entry<String, Integer> partEntry = part.toModelType();
            modelParts.put(partEntry.getKey(), partEntry.getValue());
        }
        final List<Integer> serviceAssignedToIds = new ArrayList<>();
        for (Integer id : assignedToIds) {
            serviceAssignedToIds.add(id);
        }

        if (id <= 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Id"));
        }
        final int modelId = id;

        if (vehicleId <= 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Vehicle Id"));
        }
        final int modelVehicleId = vehicleId;

        if (entryDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Entry Date"));
        }
        final LocalDate modelEntryDate = LocalDate.parse(entryDate, dtf);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Description"));
        }
        final String modelDescription = description;

        if (estimatedFinishDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Estimated Finish Date"));
        }
        final LocalDate modelEstimatedFinishDate = LocalDate.parse(estimatedFinishDate, dtf);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Status"));
        }
        final ServiceStatus modelStatus = getServiceStatus(status);
        final Set<Integer> modelAssignedToIds = new HashSet<>(serviceAssignedToIds);
        return new Service(modelId, modelVehicleId, modelEntryDate, modelParts, modelDescription,
                modelEstimatedFinishDate, modelStatus, modelAssignedToIds);
    }


}
