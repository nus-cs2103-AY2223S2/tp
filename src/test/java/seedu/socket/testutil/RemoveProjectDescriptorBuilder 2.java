package seedu.socket.testutil;

import seedu.socket.logic.commands.RemoveProjectCommand.RemoveProjectDescriptor;
import seedu.socket.model.project.Project;
import seedu.socket.model.project.ProjectDeadline;
import seedu.socket.model.project.ProjectMeeting;
import seedu.socket.model.project.ProjectRepoHost;
import seedu.socket.model.project.ProjectRepoName;

/**
 * A utility class to help with building RemoveProjectDescriptor objects.
 */
public class RemoveProjectDescriptorBuilder {

    private RemoveProjectDescriptor descriptor;

    public RemoveProjectDescriptorBuilder() {
        descriptor = new RemoveProjectDescriptor();
    }

    public RemoveProjectDescriptorBuilder(RemoveProjectDescriptor descriptor) {
        this.descriptor = new RemoveProjectDescriptor(descriptor);
    }

    /**
     * Sets an {@code RemoveProjectDescriptor} with fields containing {@code project}'s details
     */
    public RemoveProjectDescriptorBuilder(Project project) {
        descriptor = new RemoveProjectDescriptor();
        descriptor.setProject(project);
        descriptor.setRepoHost(project.getRepoHost());
        descriptor.setRepoName(project.getRepoName());
        descriptor.setDeadline(project.getDeadline());
        descriptor.setMeeting(project.getMeeting());
    }

    /**
     * Sets the {@code person} of the {@code RemoveProjectDescriptor} that we are building.
     */
    public RemoveProjectDescriptorBuilder withProject(Project project) {
        descriptor.setProject(project);
        return this;
    }

    /**
     * Sets the {@code repoHost} of the {@code EditProjectDescriptor} that we are building.
     */
    public RemoveProjectDescriptorBuilder withRepoHost(String repoHost) {
        descriptor.setRepoHost(new ProjectRepoHost(repoHost));
        return this;
    }

    /**
     * Sets the {@code repoName} of the {@code RemoveProjectDescriptor} that we are building.
     */
    public RemoveProjectDescriptorBuilder withRepoName(String repoName) {
        descriptor.setRepoName(new ProjectRepoName(repoName));
        return this;
    }

    /**
     * Sets the {@code deadline} of the {@code RemoveProjectDescriptor} that we are building.
     */
    public RemoveProjectDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new ProjectDeadline(deadline));
        return this;
    }

    /**
     * Sets the {@code meeting} of the {@code RemoveProjectDescriptor} that we are building.
     */
    public RemoveProjectDescriptorBuilder withMeeting(String meeting) {
        descriptor.setMeeting(new ProjectMeeting(meeting));
        return this;
    }

    public RemoveProjectDescriptor build() {
        return descriptor;
    }

}

