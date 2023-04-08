package seedu.socket.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_HOST;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_NAME;
import static seedu.socket.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.socket.commons.core.Messages;
import seedu.socket.commons.core.index.Index;
import seedu.socket.commons.util.CollectionUtil;
import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.model.Model;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;
import seedu.socket.model.project.ProjectDeadline;
import seedu.socket.model.project.ProjectMeeting;
import seedu.socket.model.project.ProjectName;
import seedu.socket.model.project.ProjectRepoHost;
import seedu.socket.model.project.ProjectRepoName;



/**
 * Edits the details of an existing person in SOCket.
 */
public class EditProjectCommand extends Command {

    public static final String COMMAND_WORD = "editpj";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of this project identified "
            + "by the index number used in the displayed project list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "PROJECT_NAME] "
            + "[" + PREFIX_REPO_HOST + "REPO_HOST] "
            + "[" + PREFIX_REPO_NAME + "REPO_NAME] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "[" + PREFIX_MEETING + "MEETING]\n "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Socket Project "
            + PREFIX_DEADLINE + "02/01/23-2359";

    public static final String MESSAGE_EDIT_PROJECT_SUCCESS = "Edited Project: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in SOCket.";

    private final Index index;
    private final EditProjectDescriptor editProjectDescriptor;

    /**
     * @param index of the person in the filtered project list to edit
     * @param editProjectDescriptor details to edit the project with
     */
    public EditProjectCommand(Index index, EditProjectDescriptor editProjectDescriptor) {
        requireNonNull(index);
        requireNonNull(editProjectDescriptor);

        this.index = index;
        this.editProjectDescriptor = new EditProjectDescriptor(editProjectDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(index.getZeroBased());
        Project editedProject = createEditedProject(projectToEdit, editProjectDescriptor);

        if (!projectToEdit.isSameProject(editedProject) && model.hasProject(editedProject)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        model.setProject(projectToEdit, editedProject);
        model.commitSocket();
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PROJECT_SUCCESS, editedProject));
    }

    /**
     * Creates and returns a {@code Project} with the details of {@code projectToEdit}
     * edited with {@code editProjectDescriptor}.
     */
    private static Project createEditedProject(Project projectToEdit, EditProjectDescriptor editProjectDescriptor) {
        assert projectToEdit != null;

        ProjectName updatedName = editProjectDescriptor.getName().orElse(projectToEdit.getName());
        ProjectRepoHost updatedRepoHost = editProjectDescriptor.getRepoHost().orElse(projectToEdit.getRepoHost());
        ProjectRepoName updatedRepoName = editProjectDescriptor.getRepoName().orElse(projectToEdit.getRepoName());
        ProjectDeadline updatedDeadline = editProjectDescriptor.getDeadline().orElse(projectToEdit.getDeadline());
        ProjectMeeting updatedMeeting = editProjectDescriptor.getMeeting().orElse(projectToEdit.getMeeting());
        Set<Person> members = projectToEdit.getMembers();

        return new Project(updatedName, updatedRepoHost, updatedRepoName, updatedDeadline, updatedMeeting, members);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditProjectCommand)) {
            return false;
        }

        // state check
        EditProjectCommand e = (EditProjectCommand) other;
        return index.equals(e.index)
                && editProjectDescriptor.equals(e.editProjectDescriptor);
    }

    /**
     * Stores the details to edit the project with. Each non-empty field value will replace the
     * corresponding field value of the project.
     */
    public static class EditProjectDescriptor {
        private ProjectName name;
        private ProjectRepoHost repoHost;
        private ProjectRepoName repoName;
        private ProjectDeadline deadline;
        private ProjectMeeting meeting;

        public EditProjectDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditProjectDescriptor(EditProjectDescriptor toCopy) {
            setName(toCopy.name);
            setRepoHost(toCopy.repoHost);
            setRepoName(toCopy.repoName);
            setDeadline(toCopy.deadline);
            setMeeting(toCopy.meeting);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, repoHost, repoName, deadline, meeting);
        }

        public void setName(ProjectName name) {
            this.name = name;
        }

        public Optional<ProjectName> getName() {
            return Optional.ofNullable(name);
        }

        public void setRepoHost(ProjectRepoHost repoHost) {
            this.repoHost = repoHost;
        }

        public Optional<ProjectRepoHost> getRepoHost() {
            return Optional.ofNullable(repoHost);
        }

        public void setRepoName(ProjectRepoName repoName) {
            this.repoName = repoName;
        }

        public Optional<ProjectRepoName> getRepoName() {
            return Optional.ofNullable(repoName);
        }

        public void setDeadline(ProjectDeadline deadline) {
            this.deadline = deadline;
        }

        public Optional<ProjectDeadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setMeeting(ProjectMeeting meeting) {
            this.meeting = meeting;
        }

        public Optional<ProjectMeeting> getMeeting() {
            return Optional.ofNullable(meeting);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditProjectDescriptor)) {
                return false;
            }

            // state check
            EditProjectDescriptor e = (EditProjectDescriptor) other;

            return getName().equals(e.getName())
                    && getRepoHost().equals(e.getRepoHost())
                    && getRepoName().equals(e.getRepoName())
                    && getDeadline().equals(e.getDeadline())
                    && getMeeting().equals(e.getMeeting());
        }
    }
}
