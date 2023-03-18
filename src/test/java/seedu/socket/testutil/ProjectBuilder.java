package seedu.socket.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;
import seedu.socket.model.project.ProjectDeadline;
import seedu.socket.model.project.ProjectName;
import seedu.socket.model.project.ProjectRepoHost;
import seedu.socket.model.project.ProjectRepoName;
import seedu.socket.model.util.SampleDataUtil;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_NAME = "ALPHA";
    public static final String DEFAULT_REPO_HOST = "";
    public static final String DEFAULT_REPO_NAME = "";
    public static final String DEFAULT_DEADLINE = "";

    private ProjectName name;
    private ProjectRepoHost repoHost;
    private ProjectRepoName repoName;
    private ProjectDeadline deadline;
    private Set<Person> members;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        name = new ProjectName(DEFAULT_NAME);
        repoHost = new ProjectRepoHost(DEFAULT_REPO_HOST);
        repoName = new ProjectRepoName(DEFAULT_REPO_NAME);
        deadline = new ProjectDeadline(DEFAULT_DEADLINE);
        members = new HashSet<>();
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        name = projectToCopy.getName();
        repoHost = projectToCopy.getRepoHost();
        repoName = projectToCopy.getRepoName();
        deadline = projectToCopy.getDeadline();
        members = new HashSet<>(projectToCopy.getMembers());
    }

    /**
     * Sets the {@code ProjectName} of the {@code Project} that we are building.
     */
    public ProjectBuilder withName(String name) {
        this.name = new ProjectName(name);
        return this;
    }

    /**
     * Sets the {@code RepoHost} of the {@code Project} that we are building.
     */
    public ProjectBuilder withRepoHost(String host) {
        this.repoHost = new ProjectRepoHost(host);
        return this;
    }

    /**
     * Sets the {@code RepoName} of the {@code Project} that we are building.
     */
    public ProjectBuilder withRepoName(String repoName) {
        this.repoName = new ProjectRepoName(repoName);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectDeadline(String deadline) {
        this.deadline = new ProjectDeadline(deadline);
        return this;
    }

    /**
     * Parses the {@code members} into a {@code Set<Member>} and set it to the {@code Project} that we are building.
     */
    public ProjectBuilder withMembers(Person ... members) {
        this.members = SampleDataUtil.getMemberSet(members);
        return this;
    }



    public Project build() {
        return new Project(name, repoHost, repoName, deadline, members);
    }

}
