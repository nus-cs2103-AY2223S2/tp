package mycelium.mycelium.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.CliSyntax;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.project.Project;

/**
 * Adds a new project to Mycelium.
 */
public class AddProjectCommand extends Command {
    public static final String COMMAND_ACRONYM = "p";

    public static final String MESSAGE_USAGE =
        COMMAND_ACRONYM + ": Adds a project to Mycelium. "

            + "Compulsory Arguments: "
            + CliSyntax.PREFIX_PROJECT_NAME + "PROJECT NAME "
            + CliSyntax.PREFIX_CLIENT_EMAIL + "CLIENT EMAIL\n"
            + "Optional Arguments: "
            + CliSyntax.PREFIX_PROJECT_STATUS + "PROJECT STATUS "
            + CliSyntax.PREFIX_SOURCE + "PROJECT SOURCE "
            + CliSyntax.PREFIX_PROJECT_DESCRIPTION + "PROJECT DESCRIPTION "
            + CliSyntax.PREFIX_ACCEPTED_DATE + "DATE PROJECT WAS ACCEPTED "
            + CliSyntax.PREFIX_DEADLINE_DATE + "DEADLINE OF PROJECT\n"

            + "Example: " + COMMAND_ACRONYM + " "
            + CliSyntax.PREFIX_PROJECT_NAME + "Mycelium "
            + CliSyntax.PREFIX_CLIENT_EMAIL + "alice_baker@gmail.com "
            + CliSyntax.PREFIX_PROJECT_STATUS + "done "
            + CliSyntax.PREFIX_SOURCE + "Fiverr "
            + CliSyntax.PREFIX_PROJECT_DESCRIPTION + "Cli-based project management app "
            + CliSyntax.PREFIX_ACCEPTED_DATE + "01/12/2023 "
            + CliSyntax.PREFIX_DEADLINE_DATE + "31/12/2023";

    public static final String MESSAGE_SUCCESS = "New project added: %1$s";

    public static final String MESSAGE_DUPLICATE_PROJECT_NAME = "This project name already exists in your project list";

    private final Project toAdd;

    /**
     * Creates a command to add the new project.
     *
     * @param project The new project.
     */
    public AddProjectCommand(Project project) {
        requireNonNull(project);
        toAdd = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProject(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT_NAME);
        }

        model.addProject(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddProjectCommand that = (AddProjectCommand) o;
        return toAdd.isSame(that.toAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toAdd);
    }
}
