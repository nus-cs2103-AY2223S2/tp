package seedu.socket.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.socket.commons.core.Messages;
import seedu.socket.commons.core.index.Index;
import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.model.Model;
import seedu.socket.model.person.Name;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;

/**
 * Removes an existing member of a project from SOCket.
 */
public class UnassignCommand extends Command {

    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the specified member from the project "
        + "identified by the index number used in the displayed project list.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + PREFIX_NAME + "NAME (must be the name of a member of the project)\n"
        + "Example: " + COMMAND_WORD + " 1 n/Amy Bee";

    public static final String MESSAGE_UNASSIGN_SUCCESS = "Removed %1$s from project: %2$s";
    public static final String MESSAGE_NOT_IN_PROJECT = "%1$s is not a member of this project.";
    private final Index index;
    private final Name name;

    /**
     * @param index of the project in the filtered project list to remove member from
     * @param name of the member to remove from the project
     */
    public UnassignCommand(Index index, Name name) {
        requireNonNull(index);
        requireNonNull(name);

        this.index = index;
        this.name = name;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }
        Project projectToUnassign = lastShownList.get(index.getZeroBased());
        Set<Person> members = projectToUnassign.getMembers();
        Set<Person> newMembers = new HashSet<>();
        if (!projectToUnassign.hasMember(name)) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_PROJECT, name));
        }
        for (Person member : members) {
            if (!member.getName().equals(name)) {
                newMembers.add(member);
            }
        }
        Project editedProject = new Project(projectToUnassign.getName(), projectToUnassign.getRepoHost(),
                projectToUnassign.getRepoName(), projectToUnassign.getDeadline(), projectToUnassign.getMeeting(),
                newMembers);
        model.setProject(projectToUnassign, editedProject);
        model.commitSocket();
        return new CommandResult(String.format(MESSAGE_UNASSIGN_SUCCESS, name, editedProject.getName()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnassignCommand)) {
            return false;
        }

        // state check
        UnassignCommand e = (UnassignCommand) other;
        return index.equals(e.index)
            && name.equals(e.name);
    }
}
