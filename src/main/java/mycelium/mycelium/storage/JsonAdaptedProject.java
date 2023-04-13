package mycelium.mycelium.storage;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import mycelium.mycelium.commons.exceptions.IllegalValueException;
import mycelium.mycelium.commons.util.DateUtil;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * Jackson friendly version of {@link Project}.
 */
public class JsonAdaptedProject extends JsonAdaptedEntity {
    private static final String ENTITY_NAME = "Project";
    private final String name;
    private final ProjectStatus status;
    private final String clientEmail;
    private final String source;
    private final String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/uuuu")
    @JsonDeserialize(using = DateUtil.class)
    private final LocalDate acceptedOn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/uuuu")
    @JsonDeserialize(using = DateUtil.class)
    private final LocalDate deadline; // NOTE: it is okay for the deadline to be null

    /**
     * Constructs this class from its properties.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("name") String name,
                              @JsonProperty("status") ProjectStatus status,
                              @JsonProperty("clientEmail") String clientEmail,
                              @JsonProperty("source") String source,
                              @JsonProperty("description") String description,
                              @JsonProperty("acceptedOn") LocalDate acceptedOn,
                              @JsonProperty("deadline") LocalDate deadline) {
        super(ENTITY_NAME);
        this.name = name;
        this.status = status;
        this.clientEmail = clientEmail;
        this.source = source;
        this.description = description;
        this.acceptedOn = acceptedOn;
        this.deadline = deadline;
    }

    /**
     * Constructs this class from a vanilla {@link Project}.
     */
    public JsonAdaptedProject(Project project) {
        super(ENTITY_NAME);
        name = project.getName().toString();
        status = project.getStatus();
        clientEmail = project.getClientEmail().value;
        source = project.getSource().map(NonEmptyString::toString).orElse(null);
        description = project.getDescription().orElse(null);
        acceptedOn = project.getAcceptedOn();
        deadline = project.getDeadline().orElse(null);
    }

    /**
     * Constructs a {@link Project} from this class.
     *
     * @return The new {@code Project}
     * @throws IllegalValueException if some null fields exist
     */
    public Project toModelType() throws IllegalValueException {
        nullCheck(name, "name");
        nullCheck(status, "status");
        nullCheck(clientEmail, "clientEmail");
        nullCheck(acceptedOn, "acceptedOn");

        // NOTE: validity checks for status, acceptedOn, and deadline are done in the constructor
        validityCheck(NonEmptyString.isValid(name), "name");
        validityCheck(Email.isValidEmail(clientEmail), "clientEmail");
        if (source != null) {
            validityCheck(NonEmptyString.isValid(source), "source");
        }

        // NOTE: it is okay for the deadline and description to be null
        return new Project(NonEmptyString.of(name),
            status,
            new Email(clientEmail),
            NonEmptyString.ofOptional(source),
            Optional.ofNullable(description),
            acceptedOn,
            Optional.ofNullable(deadline));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonAdaptedProject that = (JsonAdaptedProject) o;
        return Objects.equals(name, that.name) && status == that.status && Objects.equals(clientEmail,
            that.clientEmail) && Objects.equals(source, that.source) && Objects.equals(description,
            that.description) && Objects.equals(acceptedOn, that.acceptedOn) && Objects.equals(deadline,
            that.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status, clientEmail, source, description, acceptedOn, deadline);
    }
}
