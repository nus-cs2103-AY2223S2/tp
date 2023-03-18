package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.Person;
import seedu.address.model.todo.InternshipTodo;
import seedu.address.model.todo.Note;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    ObservableList<InternshipApplication> getInternshipList();
    ObservableList<InternshipTodo> getTodoList();
    ObservableList<Note> getNoteList();

}
