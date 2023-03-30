package mycelium.mycelium.testutil;

import java.time.LocalDate;
import java.util.Optional;

import mycelium.mycelium.logic.commands.UpdateProjectCommand;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * A utility class to help with building {@code UpdateProjectDescriptor} objects.
 */
public class UpdateProjectDescriptorBuilder {
    private UpdateProjectCommand.UpdateProjectDescriptor descriptor;

    /**
     * Creates a new {@code UpdateProjectDescriptorBuilder} with all fields empty.
     */
    public UpdateProjectDescriptorBuilder() {
        descriptor = new UpdateProjectCommand.UpdateProjectDescriptor();
    }

    /**
     * Creates a new {@code UpdateProjectDescriptorBuilder} with the fields of the given {@code descriptor} copied.
     */
    public UpdateProjectDescriptorBuilder(UpdateProjectCommand.UpdateProjectDescriptor descriptor) {
        this.descriptor = new UpdateProjectCommand.UpdateProjectDescriptor(descriptor);
    }

    /**
     * Creates a new {@code UpdateProjectDescriptorBuilder} with the fields of the given {@code project} copied.
     */
    public UpdateProjectDescriptorBuilder(Project project) {
        descriptor = new UpdateProjectCommand.UpdateProjectDescriptor(project);
    }

    /**
     * Sets the project name of the {@code UpdateProjectDescriptor} that we are building.
     */
    public UpdateProjectDescriptorBuilder withName(String name) {
        descriptor.setName(Optional.ofNullable(name).map(NonEmptyString::new));
        return this;
    }

    /**
     * Sets the project name of the {@code UpdateProjectDescriptor} that we are building.
     */
    public UpdateProjectDescriptorBuilder withName(NonEmptyString name) {
        descriptor.setName(Optional.ofNullable(name));
        return this;
    }

    /**
     * Sets the project status of the {@code UpdateProjectDescriptor} that we are building.
     */
    public UpdateProjectDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(Optional.ofNullable(status).map(ProjectStatus::fromString));
        return this;
    }

    /**
     * Sets the client email of the {@code UpdateProjectDescriptor} that we are building.
     */
    public UpdateProjectDescriptorBuilder withClientEmail(String email) {
        descriptor.setClientEmail(Optional.ofNullable(email).map(Email::new));
        return this;
    }

    /**
     * Sets the project source of the {@code UpdateProjectDescriptor} that we are building.
     */
    public UpdateProjectDescriptorBuilder withSource(String source) {
        descriptor.setSource(Optional.ofNullable(source).map(NonEmptyString::new));
        return this;
    }

    /**
     * Sets the project description of the {@code UpdateProjectDescriptor} that we are building.
     */
    public UpdateProjectDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(description);
        return this;
    }

    /**
     * Sets the project's acceptedOn date of the {@code UpdateProjectDescriptor} that we are building.
     */
    public UpdateProjectDescriptorBuilder withAcceptedOn(String acceptedOn) {
        descriptor.setAcceptedOn(Optional.ofNullable(acceptedOn).map(s -> LocalDate.parse(s, Project.DATE_FMT)));
        return this;
    }

    /**
     * Sets the project's deadline of the {@code UpdateProjectDescriptor} that we are building.
     */
    public UpdateProjectDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(Optional.ofNullable(deadline).map(s -> LocalDate.parse(s, Project.DATE_FMT)));
        return this;
    }

    /**
     * Builds the {@code UpdateProjectDescriptor} object.
     */
    public UpdateProjectCommand.UpdateProjectDescriptor build() {
        return descriptor;
    }
}
