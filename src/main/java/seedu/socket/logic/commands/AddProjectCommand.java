package seedu.socket.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_HOST;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_REPO_NAME;

import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.model.Model;
import seedu.socket.model.project.Project;

/**
 * Adds a project to SOCket.
 */
public class AddProjectCommand extends Command {
    public static final String COMMAND_WORD = "addpj";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a project to SOCket. "
            + "Parameters: "
            + PREFIX_NAME + "PROJECT_NAME "
            + PREFIX_REPO_HOST + "REPO_HOST "
            + PREFIX_REPO_NAME + "REPO_NAME "
            + PREFIX_DEADLINE + "DEADLINE "
            + "[" + PREFIX_MEETING + "MEETING]\n "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Project 1 "
            + PREFIX_REPO_HOST + "project-1 "
            + PREFIX_REPO_NAME + "ProjectOne "
            + PREFIX_DEADLINE + "29/04/22-0900 "
            + PREFIX_MEETING + "24/03/22-1400";

    public static final String MESSAGE_SUCCESS = "New project added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in SOCket";

    private final Project toAdd;

    /**
     * Creates an AddProjectCommand to add the specified {@code Project}
     */
    public AddProjectCommand(Project project) {
        requireNonNull(project);
        assert project != null;
        toAdd = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert model != null;

        if (model.hasProject(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        model.addProject(toAdd);
        model.commitSocket();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddProjectCommand // instanceof handles nulls
                && toAdd.equals(((AddProjectCommand) other).toAdd));
    }
}
