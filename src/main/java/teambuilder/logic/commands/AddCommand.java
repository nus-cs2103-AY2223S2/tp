package teambuilder.logic.commands;

import static java.util.Objects.requireNonNull;
import static teambuilder.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static teambuilder.logic.parser.CliSyntax.PREFIX_EMAIL;
import static teambuilder.logic.parser.CliSyntax.PREFIX_MAJOR;
import static teambuilder.logic.parser.CliSyntax.PREFIX_NAME;
import static teambuilder.logic.parser.CliSyntax.PREFIX_PHONE;
import static teambuilder.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import teambuilder.commons.core.Memento;
import teambuilder.commons.util.HistoryUtil;
import teambuilder.logic.commands.exceptions.CommandException;
import teambuilder.model.Model;
import teambuilder.model.person.Person;
import teambuilder.model.tag.Tag;
import teambuilder.model.team.Team;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    // @formatter:off
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_MAJOR + "MAJOR "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_MAJOR + "Computer Science "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    public static final String MESSAGE_TEAM_NOT_FOUND = "The team does not exist in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        // throw exception if team tags not from existing teams
        List<Team> teamList = model.getTeamList();
        Object[] teamTags = toAdd.getTeams().toArray();
        for (Object tag : teamTags) {
            Tag castedTag = (Tag) tag;
            boolean isPresent = false;
            for (Team team : teamList) {
                if (castedTag.getName().equals(team.toString())) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                throw new CommandException(MESSAGE_TEAM_NOT_FOUND);
            }
        }

        Memento old = model.save();
        HistoryUtil.getInstance().storePast(old, COMMAND_WORD + " " + toAdd);

        model.addPerson(toAdd);
        model.updatePersonInTeams(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                        && toAdd.equals(((AddCommand) other).toAdd));
    }
}
