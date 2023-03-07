package mycelium.mycelium.model.project;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.util.IsSame;

/**
 * Represents a project.
 */
public class Project implements IsSame<Project> {
    /**
     * The project's name
     */
    private final String name;

    /**
     * The project's status
     */
    private final ProjectStatus status;

    /**
     * The email of the client who submitted this project. We only keep the client's email here for more convenient
     * (de)serializing.
     */
    private final Email clientEmail;

    /**
     * The project's source, e.g. Fiverr
     */
    private final String source;

    /**
     * Arbitrary description for the project
     */
    private final String description;

    /**
     * The date on which this project was accepted
     */
    private final Date acceptedOn;

    /**
     * A potential deadline for this project
     */
    private final Optional<Date> deadline;

    /**
     * Creates a new project from the minimal set of required fields. The remaining fields are filled with default
     * values.
     */
    public Project(String name, Email clientEmail) {
        this.name = name;
        this.status = ProjectStatus.NOT_STARTED;
        this.clientEmail = clientEmail;
        this.source = "";
        this.description = "";
        this.acceptedOn = new Date();
        this.deadline = Optional.empty();
    }

    /**
     * Creates a new {@code Project} with every field present and not null.
     */
    public Project(String name,
                   ProjectStatus status,
                   Email clientEmail,
                   String source,
                   String description,
                   Date acceptedOn,
                   Optional<Date> deadline) {
        this.name = name;
        this.status = status;
        this.clientEmail = clientEmail;
        this.source = source;
        this.description = description;
        this.acceptedOn = acceptedOn;
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public Email getClientEmail() {
        return clientEmail;
    }

    public String getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public Date getAcceptedOn() {
        return acceptedOn;
    }

    public Optional<Date> getDeadline() {
        return deadline;
    }

    /**
     * Checks if two projects refer to the same project. For now, two projects are considered the same if they have
     * the same name.
     *
     * @param other The other project
     * @return True if the two projects refer to the same physical project
     */
    public boolean isSame(Project other) {
        if (this == other) {
            return true;
        }
        return other != null && this.name.equals(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(name, project.name)
            && status == project.status
            && Objects.equals(clientEmail,
            project.clientEmail)
            && Objects.equals(source, project.source)
            && Objects.equals(description, project.description)
            && Objects.equals(acceptedOn, project.acceptedOn)
            && Objects.equals(deadline, project.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status, clientEmail, source, description, acceptedOn, deadline);
    }
}

