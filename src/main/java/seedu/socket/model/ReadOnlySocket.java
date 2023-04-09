package seedu.socket.model;

import javafx.collections.ObservableList;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;

/**
 * Unmodifiable view of a {@code Socket}.
 */
public interface ReadOnlySocket {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the projects list.
     * This list will not contain any duplicate projects.
     */
    ObservableList<Project> getProjectList();
}
