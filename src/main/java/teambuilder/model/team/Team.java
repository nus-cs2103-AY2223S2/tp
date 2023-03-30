package teambuilder.model.team;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import teambuilder.model.person.Name;
import teambuilder.model.tag.Tag;

/**
 * Represents a Team in the address book.
 */
public class Team {

    public static final String MESSAGE_CONSTRAINTS =
            "Team names should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final TeamName teamName;
    private final Desc teamDesc;
    private final Set<Tag> skillTags = new HashSet<>();
    private final Set<Name> members = new HashSet<>();


    /**
     * Constructs a {@code Team}.
     *
     * @param teamName A valid team name.
     * @param teamDesc A team description.
     * @param skillTags A set of skill tags.
     */
    public Team(TeamName teamName, Desc teamDesc, Set<Tag> skillTags) {
        requireNonNull(teamName);
        this.teamName = teamName;
        this.teamDesc = teamDesc;
        this.skillTags.addAll(skillTags);
    }

    public TeamName getName() {
        return this.teamName;
    }

    public void addPerson(Name name) {
        members.add(name);
    }

    public void removePerson(Name name) {
        members.remove(name);
    }

    public Set<Name> getMembers() {
        return members;
    }

    public Set<Tag> getTags() {
        return skillTags;
    }

    @Override
    public int hashCode() {
        return teamName.hashCode();
    }

    public String toString() {
        return teamName.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Team // instanceof handles nulls
                && teamName.equals(((Team) other).teamName)); // state check
    }

    /**
     * Getter method for the team name of team instance.
     *
     * @return TeamName of Team instance.
     */
    public TeamName getTeamName() {
        return teamName;
    }

    public Desc getDesc() {
        return teamDesc;
    }

    public Set<Tag> getSkillTags() {
        return skillTags;
    }

    /**
     * Returns true if both teams have the same name.
     */
    public boolean isSameTeam(Team otherTeam) {
        if (otherTeam == this) {
            return true;
        }

        return otherTeam != null
                && otherTeam.getTeamName().equals(getTeamName());
    }


}
