package mycelium.mycelium.logic.commands;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.commons.core.index.Index;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.CliSyntax;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.project.Project;


import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeleteProjectCommand extends Command {
    public static final String COMMAND_ACRONYM = "dp";

    public static final String MESSAGE_USAGE = COMMAND_ACRONYM
            + ": Deletes the project with the given project name.\n"
            + "Parameters: PROJECT NAME \n"
            + "Example: " + COMMAND_ACRONYM + " " + CliSyntax.PREFIX_PROJECT_NAME + " Mycelium";

    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "Deleted Project: %1$s";

    private final String targetProjectName;

    public DeleteProjectCommand(String targetProjectName) {
        this.targetProjectName = targetProjectName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> listWithTargetProject =
                model.getFilteredProjectList().filtered(p -> p.getName().equals(targetProjectName));

        if (listWithTargetProject.size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT);
        }

        Project projectToDelete = listWithTargetProject.get(0);
        model.deleteProject(projectToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteProjectCommand// instanceof handles nulls
                && targetProjectName.equals(((DeleteProjectCommand) other).targetProjectName)); // state check
    }
}
