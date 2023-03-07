package mycelium.mycelium.testutil;

import java.util.Date;
import java.util.Optional;

import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;

/**
 * A utility class to easily construct Projects.
 */
public class ProjectBuilder {
    public static final String DEFAULT_NAME = "Default Project";
    public static final ProjectStatus DEFAULT_STATUS = ProjectStatus.NOT_STARTED;
    public static final Email DEFAULT_CLIENT_EMAIL = new Email("ruido@gossum.co");
    public static final String DEFAULT_SOURCE = "fiverr";
    public static final String DEFAULT_DESCRIPTION = "Do this, and then that, and finally those.";
    public static final Date DEFAULT_ACCEPTED_ON = new Date(0);
    private String name = DEFAULT_NAME;
    private ProjectStatus status = DEFAULT_STATUS;
    private Email clientEmail = DEFAULT_CLIENT_EMAIL;
    private String source = DEFAULT_SOURCE;
    private String description = DEFAULT_DESCRIPTION;
    private Date acceptedOn = DEFAULT_ACCEPTED_ON;
    private Date deadline = null;

    /**
     * Initializes a new {@code PersonBuilder} with default fields.
     */
    public ProjectBuilder() {
    }

    /**
     * Constructs a new {@code PersonBuilder} using existing fields on some {@code Project}.
     */
    public ProjectBuilder(Project project) {
        this.name = project.getName();
        this.status = project.getStatus();
        this.clientEmail = project.getClientEmail();
        this.source = project.getSource();
        this.description = project.getDescription();
        this.acceptedOn = project.getAcceptedOn();
        this.deadline = project.getDeadline().orElse(null);
    }

    /**
     * Sets the project's name.
     */
    public ProjectBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the project's status.
     */
    public ProjectBuilder withStatus(ProjectStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Sets the project's client email.
     */
    public ProjectBuilder withClientEmail(Email email) {
        this.clientEmail = email;
        return this;
    }

    /**
     * Sets the project's client email.
     */
    public ProjectBuilder withClientEmail(String email) {
        this.clientEmail = new Email(email);
        return this;
    }

    /**
     * Sets the project's source.
     */
    public ProjectBuilder withSource(String source) {
        this.source = source;
        return this;
    }

    /**
     * Sets the project's description.
     */
    public ProjectBuilder withDescription(String desc) {
        this.description = desc;
        return this;
    }

    /**
     * Sets the project's accepted on date.
     */
    public ProjectBuilder withAcceptedOn(Date acceptedOn) {
        this.acceptedOn = acceptedOn;
        return this;
    }

    /**
     * Sets the project's deadline.
     */
    public ProjectBuilder withDeadline(Date deadline) {
        this.deadline = deadline;
        return this;
    }

    /**
     * Builds a project with the given fields.
     */
    public Project build() {
        return new Project(name, status, clientEmail, source, description, acceptedOn, Optional.ofNullable(deadline));
    }
}
