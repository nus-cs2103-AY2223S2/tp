package mycelium.mycelium.testutil;

import java.util.Date;
import java.util.Optional;

import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;

public class ProjectBuilder {
    public static final String DEFAULT_NAME = "Default Project";
    public static final ProjectStatus DEFAULT_STATUS = ProjectStatus.NOT_STARTED;
    public static  final Email DEFAULT_CLIENT_EMAIL = new Email("ruido@gossum.co");
    public static final String DEFAULT_SOURCE = "fiverr";
    public static final String DEFAULT_DESCRIPTION = "Do this, and then that, and finally those.";
    public static final Date DEFAULT_ACCEPTED_ON = new Date();
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
    public ProjectBuilder() {}

    public ProjectBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProjectBuilder withStatus(ProjectStatus status) {
        this.status = status;
        return this;
    }

    public ProjectBuilder withClientEmail(Email email) {
        this.clientEmail = email;
        return this;
    }

    public ProjectBuilder withClientEmail(String email) {
        this.clientEmail = new Email(email);
        return this;
    }

    public ProjectBuilder withSource(String source) {
        this.source = source;
        return this;
    }

    public ProjectBuilder withDescription(String desc) {
        this.description = desc;
        return this;
    }

    public ProjectBuilder withAcceptedOn(Date acceptedOn) {
        this.acceptedOn = acceptedOn;
        return this;
    }

    public ProjectBuilder withDeadline(Date deadline) {
        this.deadline = deadline;
        return this;
    }

    public Project build() {
        return new Project(name, status, clientEmail, source, description, acceptedOn, Optional.ofNullable(deadline));
    }
}
