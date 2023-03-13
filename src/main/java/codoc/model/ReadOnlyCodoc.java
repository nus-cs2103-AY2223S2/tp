package codoc.model;

import codoc.model.person.Person;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an CoDoc
 */
public interface ReadOnlyCodoc {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
