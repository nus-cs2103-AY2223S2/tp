package mycelium.mycelium.storage;

import java.util.Date;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import mycelium.mycelium.commons.exceptions.IllegalValueException;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;

/**
 * Jackson friendly version of {@link Project}.
 */
public class JsonAdaptedProject {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing";
    private final String name;
    private final ProjectStatus status;
    private final String client; // TODO this should be the client's email instead?
    private final String source;
    private final String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private final Date acceptedOn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private final Date deadline;

    /**
     * Constructs this class from its properties.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("name") String name,
                              @JsonProperty("status") ProjectStatus status,
                              @JsonProperty("client") String client,
                              @JsonProperty("source") String source,
                              @JsonProperty("description") String description,
                              @JsonProperty("acceptedOn") Date acceptedOn,
                              @JsonProperty("deadline") Date deadline) {
        this.name = name;
        this.status = status;
        this.client = client;
        this.source = source;
        this.description = description;
        this.acceptedOn = acceptedOn;
        this.deadline = deadline;
    }

    /**
     * Constructs this class from a vanilla {@link Project}.
     */
    public JsonAdaptedProject(Project project) {
        name = project.getName();
        status = project.getStatus();
        client = project.getClient(); // TODO update to actual client's email
        source = project.getSource();
        description = project.getDescription();
        acceptedOn = project.getAcceptedOn();
        deadline = project.getDeadline().orElse(null);
    }

    private static void assertField(boolean cond, String fieldName) throws IllegalValueException {
        if (!cond) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, fieldName));
        }
    }

    /**
     * Constructs a {@link Project} from this class.
     *
     * @return The new {@code Project}
     * @throws IllegalValueException if some null fields exist
     */
    public Project toModelType() throws IllegalValueException {
        assertField(name != null, "name");
        assertField(status != null, "status");
        assertField(client != null, "client");
        assertField(source != null, "source");
        assertField(description != null, "description");
        assertField(acceptedOn != null, "acceptedOn");
        // NOTE: it is okay for the deadline to be null.
        return new Project(name, status, client, source, description, acceptedOn, Optional.ofNullable(deadline));
    }
}
