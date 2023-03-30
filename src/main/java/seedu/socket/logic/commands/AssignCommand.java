package seedu.socket.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.socket.commons.core.Messages;
import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.model.Model;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;

/**
 * Adds a person in the currently displayed list to a project in SOCket.
 */
public class AssignCommand extends Command {
    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to a project.\n"
            + "Person & project are identified by the index number "
            + "used in the displayed person & project list respectively.\n"
            + "Parameters: PERSON_INDEX (must be a positive integer) "
            + "PROJECT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 3";

    public static final String MESSAGE_ASSIGN_SUCCESS = "Added %1$s to project: %2$s";
    public static final String MESSAGE_ALREADY_IN_PROJECT = "%1$s is already a member of this project.";
    private final Index personIndex;
    private final Index projectIndex;

    /**
     * @param personIndex Index of the person in the person list to be added to project
     * @param projectIndex Index of the project in the project list that person is to be added to
     */
    public AssignCommand(Index personIndex, Index projectIndex) {
        requireNonNull(personIndex);
        requireNonNull(projectIndex);

        this.personIndex = personIndex;
        this.projectIndex = projectIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Project> lastShownProjectList = model.getFilteredProjectList();

        // Checks if index provided is valid.
        if (personIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (projectIndex.getZeroBased() >= lastShownProjectList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        // Gets Project and Person to be assigned, prepares Set for transfer.
        Person personToAssign = lastShownPersonList.get(personIndex.getZeroBased());
        Project projectToAssign = lastShownProjectList.get(projectIndex.getZeroBased());
        Set<Person> members = projectToAssign.getMembers();
        Set<Person> newMembers = new HashSet<>();

        // Checks if Person is already in Project.
        if (projectToAssign.hasMember(personToAssign.getName())) {
            throw new CommandException(String.format(MESSAGE_ALREADY_IN_PROJECT, personToAssign.getName()));
        }

        // Transfers members from old set to new set, then add new member.
        for (Person member : members) {
            newMembers.add(member);
        }
        newMembers.add(personToAssign);

        // Creates new Project with updated members
        Project editedProject = new Project(projectToAssign.getName(), projectToAssign.getRepoHost(),
                projectToAssign.getRepoName(), projectToAssign.getDeadline(), projectToAssign.getMeeting(),
                newMembers);
        model.setProject(projectToAssign, editedProject);
        model.commitSocket();
        return new CommandResult(String.format(
                MESSAGE_ASSIGN_SUCCESS, personToAssign.getName(), editedProject.getName()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignCommand)) {
            return false;
        }

        // state check
        AssignCommand e = (AssignCommand) other;
        return personIndex.equals(e.personIndex)
                && projectIndex.equals(e.projectIndex);
    }
}
