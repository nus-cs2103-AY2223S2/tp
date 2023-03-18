package seedu.socket.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.socket.commons.exceptions.IllegalValueException;
import seedu.socket.model.person.GitHubProfile;
import seedu.socket.model.person.Name;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;
import seedu.socket.model.project.ProjectDeadline;
import seedu.socket.model.project.ProjectName;
import seedu.socket.model.project.ProjectRepoHost;
import seedu.socket.model.project.ProjectRepoName;

/**
 * Jackson-friendly version of {@link Project}.
 */
public class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String projectName;
    private final String projectRepoHost;
    private final String projectRepoName;
    private final String projectDeadline;
    private final List<JsonAdaptedPerson> members = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("projectName") String projectName,
                             @JsonProperty("projectRepoHost") String projectRepoHost,
                             @JsonProperty("projectRepoName") String projectRepoName,
                             @JsonProperty("projectDeadline") String projectDeadline,
                             @JsonProperty("members") List<JsonAdaptedPerson> members) {
        this.projectName = projectName;
        this.projectRepoHost = projectRepoHost;
        this.projectRepoName = projectRepoName;
        this.projectDeadline = projectDeadline;
        if (members != null) {
            this.members.addAll(members);
        }
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        projectName = source.getName().projectName;
        projectRepoHost = source.getRepoHost().value;
        projectRepoName = source.getRepoName().value;
        projectDeadline = source.getDeadline().deadline;
        members.addAll(source.getMembers().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType() throws IllegalValueException {
        final List<Person> projectMembers = new ArrayList<>();
        for (JsonAdaptedPerson person : members) {
            projectMembers.add(person.toModelType());
        }

        if (projectName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectName.class.getSimpleName()));
        }
        if (!ProjectName.isValidProjectName(projectName)) {
            throw new IllegalValueException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        final ProjectName modelName = new ProjectName(projectName);

        if (projectRepoHost == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProjectRepoHost.class.getSimpleName()));
        }
        if (!ProjectRepoHost.isValidProjectRepoHost(projectRepoHost)) {
            throw new IllegalValueException(ProjectRepoHost.MESSAGE_CONSTRAINTS);
        }
        final ProjectRepoHost modelRepoHost = new ProjectRepoHost(projectRepoHost);

        if (projectRepoName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProjectRepoName.class.getSimpleName()));
        }
        if (!ProjectRepoName.isValidProjectRepoName(projectRepoName)) {
            throw new IllegalValueException(ProjectRepoName.MESSAGE_CONSTRAINTS);
        }
        final ProjectRepoName modelRepoName = new ProjectRepoName(projectRepoName);

        if (projectDeadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProjectDeadline.class.getSimpleName()));
        }
        if (!ProjectDeadline.isValidProjectDeadline(projectDeadline)) {
            throw new IllegalValueException(ProjectDeadline.MESSAGE_CONSTRAINTS);
        }
        final ProjectDeadline modelDeadline = new ProjectDeadline(projectDeadline);

        final Set<Person> modelMembers = new HashSet<>(projectMembers);
        return new Project(modelName, modelRepoHost, modelRepoName, modelDeadline, modelMembers);
    }

}
