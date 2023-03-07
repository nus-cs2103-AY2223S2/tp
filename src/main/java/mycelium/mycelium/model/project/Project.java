package mycelium.mycelium.model.project;

import java.util.Date;
import java.util.Optional;

import mycelium.mycelium.model.person.Email;

/**
 * Represents a project.
 */
public class Project {
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
     * Every field must be present and not null.
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
    public boolean isSameProject(Project other) {
        if (this == other) {
            return true;
        }
        return other != null && this.name.equals(other.name);
    }
}

