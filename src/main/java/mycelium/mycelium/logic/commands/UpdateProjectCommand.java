package mycelium.mycelium.logic.commands;

import static java.util.Objects.requireNonNull;
import static mycelium.mycelium.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.uiaction.TabSwitchAction;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * Updates a project.
 */
public class UpdateProjectCommand extends Command {
    public static final String COMMAND_WORD = "up";
    public static final String
        MESSAGE_USAGE =
        COMMAND_WORD + ": Updates the selected project.\n";
    // TODO add more description
    public static final String MESSAGE_UPDATE_PROJECT_SUCCESS = "Updated project: %1$s";
    public static final String MESSAGE_NOT_UPDATED = "Project not updated";
    public static final String MESSAGE_DUPLICATE_PROJECT = "A project with the requested name already exists!";

    private final NonEmptyString projectName;
    private final UpdateProjectDescriptor desc;

    /**
     * Creates an UpdateProjectCommand to update the specified {@code Project}. The project is allowed to not exist,
     * and the descriptor may contain no updated fields.
     *
     * @param projectName name of the project to update
     * @param desc        descriptor of the updated project
     */
    public UpdateProjectCommand(NonEmptyString projectName, UpdateProjectDescriptor desc) {
        requireAllNonNull(projectName, desc);
        this.projectName = projectName;
        this.desc = desc;
    }

    /**
     * Creates an updated project from the given project and descriptor.
     *
     * @param project project to update
     * @param desc    descriptor of the updated project
     * @return updated project
     */
    private static Project createUpdatedProject(Project project, UpdateProjectDescriptor desc) {
        requireAllNonNull(project, desc);
        if (!desc.isAnyFieldUpdated()) {
            return project;
        }

        NonEmptyString updatedName = desc.getName().orElse(project.getName());
        ProjectStatus updatedStatus = desc.getStatus().orElse(project.getStatus());
        Email updatedClientEmail = desc.getClientEmail().orElse(project.getClientEmail());
        Optional<NonEmptyString> updatedSource = desc.getSource().or(project::getSource);
        Optional<String> updatedDescription = desc.getDescription().or(project::getDescription);
        LocalDate updatedAcceptedOn = desc.getAcceptedOn().orElse(project.getAcceptedOn());
        Optional<LocalDate> updatedDeadline = desc.getDeadline().or(project::getDeadline);

        return new Project(updatedName, updatedStatus, updatedClientEmail, updatedSource, updatedDescription,
            updatedAcceptedOn, updatedDeadline);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateProjectCommand that = (UpdateProjectCommand) o;
        return Objects.equals(projectName, that.projectName) && Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, desc);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Project> target = model.getUniqueProject(p -> p.getName().equals(projectName));
        if (target.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT,
                new TabSwitchAction(TabSwitchAction.TabSwitch.PROJECT));
        }
        Project updatedProject = createUpdatedProject(target.get(), desc);
        model.addProject(updatedProject);
        return new CommandResult(String.format(MESSAGE_UPDATE_PROJECT_SUCCESS, updatedProject),
            new TabSwitchAction(TabSwitchAction.TabSwitch.PROJECT));
    }

    /**
     * Stores the details to update the project with. Each non-empty field value can be used to perform partial
     * updates on a project.
     */
    public static class UpdateProjectDescriptor {
        private Optional<NonEmptyString> name = Optional.empty();
        private Optional<ProjectStatus> status = Optional.empty();
        private Optional<Email> clientEmail = Optional.empty();
        private Optional<NonEmptyString> source = Optional.empty();
        private Optional<String> description = Optional.empty();
        private Optional<LocalDate> acceptedOn = Optional.empty();
        private Optional<LocalDate> deadline = Optional.empty();

        /**
         * Creates a new UpdateProjectDescriptor with all fields set to empty.
         */
        public UpdateProjectDescriptor() {
        }

        public void setName(NonEmptyString name) {
            this.name = Optional.ofNullable(name);
        }

        public void setStatus(ProjectStatus status) {
            this.status = Optional.ofNullable(status);
        }

        public void setClientEmail(Email clientEmail) {
            this.clientEmail = Optional.ofNullable(clientEmail);
        }

        public void setSource(NonEmptyString source) {
            this.source = Optional.ofNullable(source);
        }

        public void setDescription(String description) {
            this.description = Optional.ofNullable(description);
        }

        public void setAcceptedOn(LocalDate acceptedOn) {
            this.acceptedOn = Optional.ofNullable(acceptedOn);
        }

        public void setDeadline(LocalDate deadline) {
            this.deadline = Optional.ofNullable(deadline);
        }

        public Optional<NonEmptyString> getName() {
            return name;
        }

        public Optional<ProjectStatus> getStatus() {
            return status;
        }

        public Optional<Email> getClientEmail() {
            return clientEmail;
        }

        public Optional<NonEmptyString> getSource() {
            return source;
        }

        public Optional<String> getDescription() {
            return description;
        }

        public Optional<LocalDate> getAcceptedOn() {
            return acceptedOn;
        }

        public Optional<LocalDate> getDeadline() {
            return deadline;
        }

        /**
         * Returns true if at least one field is updated.
         */
        public boolean isAnyFieldUpdated() {
            return name.isPresent() || status.isPresent() || clientEmail.isPresent() || source.isPresent()
                || description.isPresent() || acceptedOn.isPresent() || deadline.isPresent();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            UpdateProjectDescriptor that = (UpdateProjectDescriptor) o;
            return Objects.equals(name, that.name)
                && Objects.equals(status, that.status)
                && Objects.equals(clientEmail, that.clientEmail)
                && Objects.equals(source, that.source)
                && Objects.equals(description, that.description)
                && Objects.equals(acceptedOn, that.acceptedOn)
                && Objects.equals(deadline, that.deadline);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, status, clientEmail, source, description, acceptedOn, deadline);
        }
    }
}
