package teambuilder.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import teambuilder.model.person.Person;
import teambuilder.model.person.UniquePersonList;
import teambuilder.model.team.Team;
import teambuilder.model.team.UniqueTeamList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TeamBuilder implements ReadOnlyTeamBuilder {

    private final UniquePersonList persons;
    private final UniqueTeamList teams;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        teams = new UniqueTeamList();
    }

    public TeamBuilder() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public TeamBuilder(ReadOnlyTeamBuilder toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setTeams(List<Team> teams) {
        this.teams.setTeams(teams);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyTeamBuilder newData) {
        requireNonNull(newData);
        //TODO:
        setPersons(newData.getPersonList());
        setTeams(newData.getTeamList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the TeamBuilder.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the TeamBuilder.
     * The person must not already exist in the TeamBuilder.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the TeamBuilder.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the TeamBuilder.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Returns true if a team with the same identity as {@code team} exists in the TeamBuilder.
     */
    public boolean hasTeam(Team team) {
        requireNonNull(team);
        return teams.contains(team);
    }

    public void setTeam(Team target, Team editedteam) {
        requireNonNull(editedteam);
        teams.setTeam(target, editedteam);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the TeamBuilder.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Adds a team to the TeamBuilder.
     * The person must not already exist in the TeamBuilder.
     */
    public void addTeam(Team team) {
        teams.add(team);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the TeamBuilder.
     */
    public void removeTeam(Team key) {
        teams.remove(key);
    }

    public void updatePersonInTeams(Person person) {
        teams.updatePersonInTeams(person);
    }

    public void removeFromAllTeams(Person person) {
        teams.removeFromAllTeams(person);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Team> getTeamList() {
        return teams.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof TeamBuilder)) {
            return false;
        }
        if (!persons.equals(((TeamBuilder) other).persons)) {
            return false;
        }
        return teams.equals(((TeamBuilder) other).teams);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
