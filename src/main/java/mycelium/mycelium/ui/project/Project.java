package mycelium.mycelium.ui.project;

import java.util.Date;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public Project(
        String name,
        ProjectStatus status,
        Email email,
        String source,
        String description,
        Date acceptedOn,
        Date deadline
    ) {
        this.name = name;
        this.status = status;
        this.clientEmail = email;
        this.source = source;
        this.description = description;
        this.acceptedOn = acceptedOn;
        this.deadline = Optional.ofNullable(deadline);
    }

    /**
     * Generates a list of projects for testing
     * @return observable list of projects
     */
    public static ObservableList<Project> generateStub() {
        ObservableList<Project> projectStub = FXCollections.observableArrayList();
        projectStub.addAll(
            new Project(
                "Bing",
                ProjectStatus.NOT_STARTED,
                new Email("johndoe@gmail.com"),
                "fiver",
                "Create the next google AKA bing",
                new Date(),
                null
            ),
            new Project(
                "Havard2.0",
                ProjectStatus.IN_PROGRESS,
                new Email("EluidKipchoge@gmail.com"),
                "Behind the alley",
                "University on the streets",
                new Date(),
                new Date()
            ),
            new Project(
                "Build Skynet",
                ProjectStatus.DONE,
                new Email("VladPutin@hotmale.com"),
                "Russia",
                "Conquer the world",
                new Date(),
                null
            ));
        return projectStub;
    }
}

enum ProjectStatus {
    NOT_STARTED,
    IN_PROGRESS,
    DONE,
}
