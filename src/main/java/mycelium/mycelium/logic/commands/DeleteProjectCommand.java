package mycelium.mycelium.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.CliSyntax;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.project.Project;

/**
 * A command to delete an existing project.
 */
public class DeleteProjectCommand extends Command {
    public static final String COMMAND_ACRONYM = "dp";

    public static final String MESSAGE_USAGE =
        COMMAND_ACRONYM
            + ": Deletes the project with the given project name.\n"

            + "Arguments: "
            + CliSyntax.PREFIX_PROJECT_NAME + " PROJECT NAME\n"

            + "Example: "
            + COMMAND_ACRONYM + " " + CliSyntax.PREFIX_PROJECT_NAME + " Mycelium";

    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "Deleted Project: %1$s";

    private final String targetProjectName;

    public DeleteProjectCommand(String targetProjectName) {
        this.targetProjectName = targetProjectName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Project> targetProject = model.getUniqueProject(p -> p.getName().equals(targetProjectName));
        if (targetProject.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT);
        }
        model.deleteProject(targetProject.get());
        return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS, targetProject.get()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteProjectCommand// instanceof handles nulls
            && targetProjectName.equals(((DeleteProjectCommand) other).targetProjectName)); // state check
    }
}
