package mycelium.mycelium.logic.commands;

import static java.util.Objects.requireNonNull;
import static mycelium.mycelium.commons.util.CollectionUtil.requireAllNonNull;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_ACCEPTED_DATE;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_NEW_PROJECT_NAME;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_DESCRIPTION;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_PROJECT_STATUS;
import static mycelium.mycelium.logic.parser.CliSyntax.PREFIX_SOURCE;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.uiaction.TabSwitchAction;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.project.ProjectStatus;
import mycelium.mycelium.model.util.NonEmptyString;

/**
 * Updates a project.
 */
public class UpdateProjectCommand extends Command {
    public static final String COMMAND_ACRONYM = "up";
    public static final String MESSAGE_USAGE =
        COMMAND_ACRONYM + ": Updates the selected project.\n"

            + "Compulsory Arguments: "
            + PREFIX_PROJECT_NAME + "PROJECT NAME\n"
            + "Optional Arguments: "
            + PREFIX_NEW_PROJECT_NAME + "NEW PROJECT NAME "
            + PREFIX_PROJECT_STATUS + "PROJECT STATUS "
            + PREFIX_CLIENT_EMAIL + "CLIENT EMAIL "
            + PREFIX_SOURCE + "SOURCE "
            + PREFIX_PROJECT_DESCRIPTION + "DESCRIPTION "
            + PREFIX_ACCEPTED_DATE + "ACCEPTED DATE "
            + PREFIX_DEADLINE_DATE + "DEADLINE\n"

            + "Example: " + COMMAND_ACRONYM + " "
            + PREFIX_PROJECT_NAME + "Mycelium "
            + PREFIX_NEW_PROJECT_NAME + "Mycelium 2.0 "
            + PREFIX_PROJECT_STATUS + "in_progress";

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
     * Creates an updated project from the given project and descriptor. Returns Optional.empty() if no fields are
     * changed, and the updated project otherwise.
     *
     * @param project project to update
     * @param desc    descriptor of the updated project
     * @return updated project, or Optional.empty() if no fields are changed
     */
    private static Optional<Project> createUpdatedProject(Project project, UpdateProjectDescriptor desc) {
        requireAllNonNull(project, desc);
        if (!desc.isAnyFieldUpdated()) {
            return Optional.empty();
        }

        NonEmptyString updatedName = desc.getName().orElse(project.getName());
        ProjectStatus updatedStatus = desc.getStatus().orElse(project.getStatus());
        Email updatedClientEmail = desc.getClientEmail().orElse(project.getClientEmail());
        Optional<NonEmptyString> updatedSource = desc.getSource().or(project::getSource);
        Optional<String> updatedDescription = desc.getDescription().or(project::getDescription);
        LocalDate updatedAcceptedOn = desc.getAcceptedOn().orElse(project.getAcceptedOn());
        Optional<LocalDate> updatedDeadline = desc.getDeadline().or(project::getDeadline);

        Project updatedProject = new Project(updatedName,
            updatedStatus,
            updatedClientEmail,
            updatedSource,
            updatedDescription,
            updatedAcceptedOn,
            updatedDeadline);

        if (project.equals(updatedProject)) {
            return Optional.empty();
        }

        return Optional.of(updatedProject);
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
        TabSwitchAction action = new TabSwitchAction(TabSwitchAction.TabSwitch.PROJECT);
        Optional<Project> target = model.getUniqueProject(p -> p.getName().equals(projectName));
        if (target.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT, action);
        }

        Optional<Project> updatedProject = createUpdatedProject(target.get(), desc);
        if (updatedProject.isEmpty()) {
            throw new CommandException(MESSAGE_NOT_UPDATED, action);
        }
        if (desc.name.isPresent() && model.hasProject(updatedProject.get())) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT, action);
        }
        model.setProject(target.get(), updatedProject.get());
        return new CommandResult(String.format(MESSAGE_UPDATE_PROJECT_SUCCESS, updatedProject.get()), action);
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

        /**
         * Constructs a new UpdateProjectDescriptor by copying the values of an existing descriptor.
         */
        public UpdateProjectDescriptor(UpdateProjectDescriptor toCopy) {
            setName(toCopy.name);
            setStatus(toCopy.status);
            setClientEmail(toCopy.clientEmail);
            setSource(toCopy.source);
            setDescription(toCopy.description);
            setAcceptedOn(toCopy.acceptedOn);
            setDeadline(toCopy.deadline);
        }

        /**
         * Constructs a new UpdateProjectDescriptor by copying the values of an existing project.
         */
        public UpdateProjectDescriptor(Project toCopy) {
            setName(toCopy.getName());
            setStatus(toCopy.getStatus());
            setClientEmail(toCopy.getClientEmail());
            setSource(toCopy.getSource());
            setDescription(toCopy.getDescription());
            setAcceptedOn(toCopy.getAcceptedOn());
            setDeadline(toCopy.getDeadline());
        }

        public void setName(NonEmptyString name) {
            this.name = Optional.ofNullable(name);
        }

        public void setName(Optional<NonEmptyString> name) {
            requireNonNull(name);
            this.name = name;
        }

        public void setStatus(ProjectStatus status) {
            this.status = Optional.ofNullable(status);
        }


        public void setStatus(Optional<ProjectStatus> status) {
            requireNonNull(status);
            this.status = status;
        }

        public void setClientEmail(Email clientEmail) {
            this.clientEmail = Optional.ofNullable(clientEmail);
        }


        public void setClientEmail(Optional<Email> clientEmail) {
            requireNonNull(clientEmail);
            this.clientEmail = clientEmail;
        }

        public void setSource(NonEmptyString source) {
            this.source = Optional.ofNullable(source);
        }


        public void setSource(Optional<NonEmptyString> source) {
            requireNonNull(source);
            this.source = source;
        }

        public void setDescription(String description) {
            this.description = Optional.ofNullable(description);
        }


        public void setDescription(Optional<String> description) {
            requireNonNull(description);
            this.description = description;
        }

        public void setAcceptedOn(LocalDate acceptedOn) {
            this.acceptedOn = Optional.ofNullable(acceptedOn);
        }


        public void setAcceptedOn(Optional<LocalDate> acceptedOn) {
            requireNonNull(acceptedOn);
            this.acceptedOn = acceptedOn;
        }

        public void setDeadline(LocalDate deadline) {
            this.deadline = Optional.ofNullable(deadline);
        }


        public void setDeadline(Optional<LocalDate> deadline) {
            requireNonNull(deadline);
            this.deadline = deadline;
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
