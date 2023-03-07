package mycelium.mycelium.ui.project;

import java.util.Date;
import java.util.Optional;

import mycelium.mycelium.model.person.Email;
// Stub for Project

/**
 * A stub model for Projects
 */
public class Project {
    public final String name;
    public final ProjectStatus status;
    public final Email clientEmail;
    public final String source;
    public final String description;
    public final Date acceptedOn;
    public final Optional<Date> deadline;

    /**
     * Creates a stub project
     */
    public Project() {
        name = "Bing";
        status = ProjectStatus.NOT_STARTED;
        clientEmail = new Email("johndoe@gmail.com");
        source = "fiver";
        description = "Create the next google AKA bing";
        acceptedOn = new Date();
        deadline = null;
    }
}

enum ProjectStatus {
    NOT_STARTED,
    IN_PROGRESS,
    DONE,
}
