package teambuilder.model.team;

import static java.util.Objects.requireNonNull;
import static teambuilder.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import teambuilder.model.person.Name;
import teambuilder.model.person.Person;
import teambuilder.model.tag.Tag;
import teambuilder.model.team.exceptions.DuplicateTeamException;
import teambuilder.model.team.exceptions.TeamNotFoundException;

/**
 * The type Unique team list.
 */
public class UniqueTeamList implements Iterable<Team> {

    private final ObservableList<Team> internalList = FXCollections.observableArrayList();
    private final ObservableList<Team> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent team as the given argument.
     */
    public boolean contains(Team toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTeam);
    }

    /**
     * Adds a team to the list.
     * The team must not already exist in the list.
     */
    public void add(Team toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTeamException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Team {@code target} in the list with {@code editedTeam}.
     * {@code target} must exist in the list.
     * The Team identity of {@code editedTeam} must not be the same as another existing team in the list.
     */
    public void setTeam(Team target, Team editedTeam) {
        requireAllNonNull(target, editedTeam);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TeamNotFoundException();
        }

        if (!target.isSameTeam(editedTeam) && contains(editedTeam)) {
            throw new DuplicateTeamException();
        }

        internalList.set(index, editedTeam);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Team toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TeamNotFoundException();
        }
    }

    /**
     * Add or delete person from TeamList depending on presence or absence of team tag respectively.
     */
    public void updatePersonInTeams(Person person) {
        requireNonNull(person);
        Tag[] allTeamTags = new Tag[person.getTeams().size()];
        person.getTeams().toArray(allTeamTags);

        for (Team team : internalList) {
            boolean isPresent = false;
            for (Tag tag : allTeamTags) {
                if (tag.getName().equals(team.toString())) {
                    Team editedTeam = addNameToTeamMember(team, person.getName());
                    setTeam(team, editedTeam);
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Team editedTeam = removeNameFromTeamMember(team, person.getName());
                setTeam(team, editedTeam);
            }
        }

    }

    /**
     * delete person from all teams in TeamList.
     */
    public void removeFromAllTeams(Person person) {
        requireNonNull(person);
        Name nameToRemove = person.getName();
        for (Team team : internalList) {
            if (team.getMembers().contains(nameToRemove)) {
                Team editedTeam = removeNameFromTeamMember(team, nameToRemove);
                setTeam(team, editedTeam);
            }
        }

    }

    public void setTeams(UniqueTeamList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code teams}.
     * {@code teams} must not contain duplicate teams.
     */
    public void setTeams(List<Team> teams) {
        requireAllNonNull(teams);
        if (!teamsAreUnique(teams)) {
            throw new DuplicateTeamException();
        }

        internalList.setAll(teams);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Team> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Team> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTeamList // instanceof handles nulls
                        && internalList.equals(((UniqueTeamList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code teams} contains only unique teams.
     */
    private boolean teamsAreUnique(List<Team> teams) {
        for (int i = 0; i < teams.size() - 1; i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                if (teams.get(i).isSameTeam(teams.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private Team addNameToTeamMember(Team team, Name name) {
        HashSet<Name> editedNames = new HashSet<>();
        editedNames.addAll(team.getMembers());
        editedNames.add(name);
        return new Team(team, editedNames);
    }

    private Team removeNameFromTeamMember(Team team, Name name) {
        HashSet<Name> editedNames = new HashSet<>();
        editedNames.addAll(team.getMembers());
        editedNames.remove(name);
        return new Team(team, editedNames);
    }

}
