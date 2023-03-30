package mycelium.mycelium.model.project;

import static mycelium.mycelium.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Objects;
import java.util.Optional;

import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.util.FuzzyComparable;
import mycelium.mycelium.model.util.IsSame;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * Represents a project.
 */
public class Project implements IsSame<Project>, FuzzyComparable<String> {
    /**
     * Parses dates in the dd/MM/uuuu format. Range of allowed years is [-9999, 9999].
     */
    public static final DateTimeFormatter DATE_FMT =
        DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    /**
     * The project's name
     */
    private final NonEmptyString name;

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
    private final Optional<NonEmptyString> source;

    /**
     * Arbitrary description for the project
     */
    private final Optional<String> description; // Can be empty

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
    public Project(NonEmptyString name, Email clientEmail) {
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
    public Project(NonEmptyString name,
                   ProjectStatus status,
                   Email clientEmail,
                   Optional<NonEmptyString> source,
                   Optional<String> description,
                   LocalDate acceptedOn,
                   Optional<LocalDate> deadline) {
        requireAllNonNull(name, status, clientEmail, source, description, acceptedOn, deadline);
        this.name = name;
        this.status = status;
        this.clientEmail = clientEmail;
        this.source = source;
        this.description = description;
        this.acceptedOn = acceptedOn;
        this.deadline = deadline;
    }

    public NonEmptyString getName() {
        return name;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public Email getClientEmail() {
        return clientEmail;
    }

    public Optional<NonEmptyString> getSource() {
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

    /**
     * Compares this project with other project based on deadline. Both projects must have deadline field
     * to be compared. For now, two projects are considered equal if {@code equals} return true.
     * Else, whichever project has earlier deadline, the function will return -1. In case when the
     * deadline is the same, ties will be broken using name.
     *
     * @param other The other project
     * @return 0 if two projects are equal with regards to {@code equals}, 1 if this project has deadline after the
     *     given project or with same deadline but the name is topologically smaller, and -1 for the rest
     */
    public int compareToWithDeadline(Project other) {
        assert this.getDeadline().isPresent();
        assert other.getDeadline().isPresent();

        LocalDate thisDeadline = this.getDeadline().get();
        LocalDate otherDeadline = other.getDeadline().get();

        if (this.equals(other)) {
            return 0;
        } else if (thisDeadline.isBefore(otherDeadline)) {
            return -1;
        } else if (thisDeadline.isEqual(otherDeadline)) {
            return this.getName().getValue().compareTo(other.getName().getValue());
        } else {
            return 1;
        }

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

    @Override
    public String getFuzzyField() {
        return name.getValue();
    }
}

