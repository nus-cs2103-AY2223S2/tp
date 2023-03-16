package mycelium.mycelium.model.project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.util.IsSame;

/**
 * Represents a project.
 */
public class Project implements IsSame<Project> {
    public static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
    private final Optional<String> source;

    /**
     * Arbitrary description for the project
     */
    private final Optional<String> description;

    /**
     * The date on which this project was accepted
     */
    private final LocalDate acceptedOn;

    /**
     * A potential deadline for this project
     */
    private final Optional<LocalDate> deadline;

    /**
     * Creates a new project from the minimal set of required fields. The remaining fields are filled with default
     * values.
     */
    public Project(String name, Email clientEmail) {
        // TODO we should probably enforce an invariant that the name is non-empty?
        this.name = name;
        this.status = ProjectStatus.NOT_STARTED;
        this.clientEmail = clientEmail;
        this.source = Optional.empty();
        this.description = Optional.empty();
        this.acceptedOn = LocalDate.now(); // use current date
        this.deadline = Optional.empty();
    }

    /**
     * Creates a new {@code Project} with every field present and not null.
     */
    public Project(String name,
                   ProjectStatus status,
                   Email clientEmail,
                   Optional<String> source,
                   Optional<String> description,
                   LocalDate acceptedOn,
                   Optional<LocalDate> deadline) {
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

    public Optional<String> getSource() {
        return source;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public LocalDate getAcceptedOn() {
        return acceptedOn;
    }

    public Optional<LocalDate> getDeadline() {
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

    @Override
    public String toString() {
        return String.format("%s from client %s", name, clientEmail);
    }
}

