package teambuilder.model;

import javafx.collections.ObservableList;
import teambuilder.model.person.Person;
import teambuilder.model.team.Team;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTeamBuilder {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the teams list.
     * This list will not contain any duplicate teams.
     */
    ObservableList<Team> getTeamList();

}
