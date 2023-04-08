package seedu.socket.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_HOST;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_NAME;
import static seedu.socket.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
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
 * Removes the details of an existing project in the SOCket.
 */
public class RemoveProjectCommand extends Command {

    public static final String COMMAND_WORD = "removepj";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the field of the project identified "
            + "by the index number used in the displayed project list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_REPO_HOST + "[REPO_HOST]] "
            + "[" + PREFIX_REPO_NAME + "[REPO_NAME]] "
            + "[" + PREFIX_DEADLINE + "[DEADLINE]] "
            + "[" + PREFIX_MEETING + "[MEETING]]\n "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DEADLINE + " "
            + PREFIX_MEETING + "01/01/23-2359";

    public static final String MESSAGE_REMOVE_FIELD_SUCCESS = "Removed field from Project: %1$s";

    public static final String MESSAGE_NOT_REMOVE = "At least one field to remove must be provided.";

    public static final String MESSAGE_REMOVE_FIELD_NOT_MATCH = "The field provided does not exist in the SOCket.";

    private final Index index;

    private final RemoveProjectDescriptor removeProjectDescriptor;

    /**
     * @param index of the project in the filtered project list to remove
     * @param removeProjectDescriptor details to remove from the project
     */
    public RemoveProjectCommand(Index index, RemoveProjectDescriptor removeProjectDescriptor) {
        requireNonNull(index);
        this.index = index;
        this.removeProjectDescriptor = new RemoveProjectDescriptor(removeProjectDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Project> lastShownList = model.getFilteredProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectFieldToRemove = lastShownList.get(index.getZeroBased());
        Project removedFieldProject = createRemoveFieldProject(projectFieldToRemove, removeProjectDescriptor);

        if (!removeProjectDescriptor.isAnyFieldRemoved()) {
            throw new CommandException(MESSAGE_REMOVE_FIELD_NOT_MATCH);
        }

        model.setProject(projectFieldToRemove, removedFieldProject);
        model.commitSocket();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REMOVE_FIELD_SUCCESS, removedFieldProject));
    }
    private static Project createRemoveFieldProject(Project projectToRemoveField,
                     RemoveProjectCommand.RemoveProjectDescriptor removeProjectDescriptor) {
        assert projectToRemoveField != null;

        removeProjectDescriptor.setProject(projectToRemoveField);

        ProjectName defaultName = projectToRemoveField.getName();
        ProjectRepoHost updatedRepoHost = removeProjectDescriptor.getRemoveRepoHost()
                .orElse(projectToRemoveField.getRepoHost());
        ProjectRepoName updatedRepoName = removeProjectDescriptor.getRemoveRepoName()
                .orElse(projectToRemoveField.getRepoName());
        ProjectDeadline updatedDeadline = removeProjectDescriptor.getRemoveDeadline()
                .orElse(projectToRemoveField.getDeadline());
        ProjectMeeting updatedMeeting = removeProjectDescriptor.getRemoveMeeting()
                .orElse(projectToRemoveField.getMeeting());
        Set<Person> members = projectToRemoveField.getMembers();
        return new Project(defaultName, updatedRepoHost, updatedRepoName, updatedDeadline, updatedMeeting, members);
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveProjectCommand)) {
            return false;
        }

        // state check
        RemoveProjectCommand e = (RemoveProjectCommand) other;
        return index.equals(e.index)
                && removeProjectDescriptor.equals(e.removeProjectDescriptor);
    }

    /**
     * Stores the details to remove the project with. Each non-empty field value will remove the
     * corresponding field value of the project.
     */
    public static class RemoveProjectDescriptor {
        private ProjectRepoHost repoHost;
        private ProjectRepoName repoName;
        private ProjectMeeting meeting;
        private ProjectDeadline deadline;

        private Project project;

        public RemoveProjectDescriptor() {}

        /**
         * Copy constructor.
         */
        public RemoveProjectDescriptor(RemoveProjectCommand.RemoveProjectDescriptor toCopy) {
            setRepoHost(toCopy.repoHost);
            setRepoName(toCopy.repoName);
            setDeadline(toCopy.deadline);
            setMeeting(toCopy.meeting);
        }

        public boolean isAnyFieldRemoved() {
            return CollectionUtil.isAnyNonNull(repoHost, repoName, deadline, meeting);
        }

        public void setProject(Project project) {
            this.project = project;
        }

        public void setRepoHost(ProjectRepoHost repoHost) {
            this.repoHost = repoHost;
        }

        public Optional<ProjectRepoHost> getRemoveRepoHost() {
            if (repoHost == null || project == null) {
                return Optional.empty();
            }

            if (!repoHost.equals(project.getRepoHost()) && !repoHost.isEmptyRepoHost()) {
                setRepoHost(null);
                return Optional.empty();
            }

            return Optional.of(new ProjectRepoHost(""));
        }

        public void setRepoName(ProjectRepoName repoName) {
            this.repoName = repoName;
        }

        public Optional<ProjectRepoName> getRemoveRepoName() {
            if (repoName == null || project == null) {
                return Optional.empty();
            }

            if (!repoName.equals(project.getRepoName()) && !repoName.isEmptyRepoName()) {
                setRepoName(null);
                return Optional.empty();
            }

            return Optional.of(new ProjectRepoName(""));
        }

        public void setDeadline(ProjectDeadline deadline) {
            this.deadline = deadline;
        }

        public Optional<ProjectDeadline> getRemoveDeadline() {

            if (deadline == null || project == null) {
                return Optional.empty();
            }

            if (!deadline.equals(project.getDeadline()) && !deadline.isEmptyDeadline()) {
                setDeadline(null);
                return Optional.empty();
            }

            return Optional.of(new ProjectDeadline(""));
        }

        public void setMeeting(ProjectMeeting meeting) {
            this.meeting = meeting;
        }

        public Optional<ProjectMeeting> getRemoveMeeting() {
            if (meeting == null || project == null) {
                return Optional.empty();
            }

            if (!meeting.equals(project.getMeeting()) && !meeting.isEmptyMeeting()) {
                setMeeting(null);
                return Optional.empty();
            }

            return Optional.of(new ProjectMeeting(""));
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof RemoveProjectCommand.RemoveProjectDescriptor)) {
                return false;
            }

            // state check
            RemoveProjectCommand.RemoveProjectDescriptor e = (RemoveProjectCommand.RemoveProjectDescriptor) other;

            return getRemoveRepoHost().equals(e.getRemoveRepoHost())
                    && getRemoveRepoName().equals(e.getRemoveRepoName())
                    && getRemoveDeadline().equals(e.getRemoveDeadline())
                    && getRemoveMeeting().equals(e.getRemoveMeeting());
        }
    }
}

