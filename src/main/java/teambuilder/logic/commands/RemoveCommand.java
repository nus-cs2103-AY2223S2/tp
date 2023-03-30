package teambuilder.logic.commands;

import static java.util.Objects.requireNonNull;
import static teambuilder.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import teambuilder.commons.core.Memento;
import teambuilder.commons.core.Messages;
import teambuilder.commons.util.HistoryUtil;
import teambuilder.logic.commands.exceptions.CommandException;
import teambuilder.model.Model;
import teambuilder.model.person.Person;
import teambuilder.model.tag.Tag;
import teambuilder.model.team.Team;
import teambuilder.model.team.TeamName;

/**
 * Removes a team identified using its name from the address book.
 */
public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the team identified by its name.\n"
            + "Parameters: TEAMNAME \n" + "Example: " + COMMAND_WORD + " Team A";

    public static final String MESSAGE_DELETE_TEAM_SUCCESS = "Removed Team: %1$s";

    private final TeamName targetName;

    public RemoveCommand(TeamName targetName) {
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Team> lastShownList = model.getTeamList();
        Team teamToDelete = null;

        for (Team team : lastShownList) {
            if (team.getName().equals(targetName)) {
                teamToDelete = team;
                break;
            }
        }

        if (teamToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAM);
        }

        Memento old = model.save();
        HistoryUtil.getInstance().storePast(old, COMMAND_WORD + " " + teamToDelete);

        Tag teamTag = getTeamTag(teamToDelete);
        deleteMembersTeamTag(model, teamTag);

        model.deleteTeam(teamToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TEAM_SUCCESS, teamToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                        && targetName.equals(((RemoveCommand) other).targetName)); // state check
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code teamMember} edited with
     * {@code the team tag deleted}.
     */
    private static Person createEditedPerson(Person teamMember, Tag teamTagToDelete) {
        assert teamMember != null;

        Set<Tag> teamTags = teamMember.getTeams();
        Tag[] teamMemberTeams = new Tag[teamTags.size()];
        teamTags.toArray(teamMemberTeams);
        HashSet<Tag> updatedTeamTag = new HashSet<Tag>();

        for (Tag i : teamMemberTeams) {
            if (i.equals(teamTagToDelete)) {
                continue;
            }
            updatedTeamTag.add(i);
        }

        return new Person(teamMember.getName(), teamMember.getPhone(), teamMember.getEmail(), teamMember.getAddress(),
                teamMember.getMajor(), teamMember.getTags(), updatedTeamTag);
    }

    private Tag getTeamTag(Team team) {
        return new Tag(team.getName().toString());
    }

    private boolean deleteMembersTeamTag(Model model, Tag teamTag) {
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        ObservableList<Person> members = model.getSortedPersonList();
        for (int i = 0; i < members.size(); i++) {
            Person member = members.get(i);

            if (member.getTeams().contains(teamTag)) {
                Person updatedPerson = createEditedPerson(member, teamTag);
                model.setPerson(member, updatedPerson);
            }
        }

        return true;
    }
}
