package teambuilder.logic.commands;

import static java.util.Objects.requireNonNull;
import static teambuilder.logic.parser.CliSyntax.PREFIX_TAG;
import static teambuilder.logic.parser.CliSyntax.PREFIX_TEAMDESC;
import static teambuilder.logic.parser.CliSyntax.PREFIX_TEAMNAME;

import teambuilder.commons.core.Memento;
import teambuilder.commons.util.HistoryUtil;
import teambuilder.logic.commands.exceptions.CommandException;
import teambuilder.model.Model;
import teambuilder.model.team.Team;

/**
 * Creates a team for TeamBuilder.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";

    // @formatter:off
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new team. "
            + "Parameters: "
            + PREFIX_TEAMNAME + "TEAMNAME "
            + PREFIX_TEAMDESC + "TEAMDESC "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TEAMNAME + "Team A "
            + PREFIX_TEAMDESC + "Team for upcoming hackathon "
            + PREFIX_TAG + "Python "
            + PREFIX_TAG + "ReactNative";

    public static final String MESSAGE_SUCCESS = "New team created: %1$s";

    public static final String MESSAGE_DUPLICATE_TEAM = "This team already exists in TeamBuilder";

    private final Team toCreate;

    /**
     * Creates an CreateCommand to add the specified
     */
    public CreateCommand(Team team) {
        requireNonNull(team);
        toCreate = team;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTeam(toCreate)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEAM);
        }
        Memento old = model.save();
        HistoryUtil.getInstance().storePast(old, COMMAND_WORD + " " + toCreate);

        model.addTeam(toCreate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toCreate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateCommand // instanceof handles nulls
                && toCreate.equals(((CreateCommand) other).toCreate));
    }

}
