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
    public boolean isSameProject(Project other) {
        if (this == other) {
            return true;
        }
        return other != null && this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((clientEmail == null) ? 0 : clientEmail.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((acceptedOn == null) ? 0 : acceptedOn.hashCode());
        result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Project other = (Project) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (status != other.status) {
            return false;
        }
        if (clientEmail == null) {
            if (other.clientEmail != null) {
                return false;
            }
        } else if (!clientEmail.equals(other.clientEmail)) {
            return false;
        }
        if (source == null) {
            if (other.source != null) {
                return false;
            }
        } else if (!source.equals(other.source)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (acceptedOn == null) {
            if (other.acceptedOn != null) {
                return false;
            }
        } else if (!acceptedOn.equals(other.acceptedOn)) {
            return false;
        }
        if (deadline == null) {
            if (other.deadline != null) {
                return false;
            }
        } else if (!deadline.equals(other.deadline)) {
            return false;
        }
        return true;
    }
}

