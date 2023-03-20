package expresslibrary.model;

import static expresslibrary.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_AUTHOR_ROWLING;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_TITLE_HARRY;
import static expresslibrary.testutil.Assert.assertThrows;
import static expresslibrary.testutil.TypicalBooks.BELOVED;
import static expresslibrary.testutil.TypicalExpressLibrary.getTypicalExpressLibrary;
import static expresslibrary.testutil.TypicalPersons.ALICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;
import expresslibrary.model.person.exceptions.DuplicatePersonException;
import expresslibrary.testutil.BookBuilder;
import expresslibrary.testutil.PersonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExpressLibraryTest {

    private final ExpressLibrary expressLibrary = new ExpressLibrary();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), expressLibrary.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expressLibrary.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyExpressLibrary_replacesData() {
        ExpressLibrary newData = getTypicalExpressLibrary();
        expressLibrary.resetData(newData);
        assertEquals(newData, expressLibrary);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Book> newBooks = Arrays.asList(BELOVED, BELOVED);
        ExpressLibraryStub newData = new ExpressLibraryStub(newPersons, newBooks);

        assertThrows(DuplicatePersonException.class, () -> expressLibrary.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expressLibrary.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInExpressLibrary_returnsFalse() {
        assertFalse(expressLibrary.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInExpressLibrary_returnsTrue() {
        expressLibrary.addPerson(ALICE);
        assertTrue(expressLibrary.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInExpressLibrary_returnsTrue() {
        expressLibrary.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(expressLibrary.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> expressLibrary.getPersonList().remove(0));
    }

    @Test
    public void resetData_withDuplicateBooks_throwsDuplicateBookException() {
        // Two books with the same identity fields
        Book editedBeloved = new BookBuilder(BELOVED).withAuthor(VALID_AUTHOR_ROWLING).withTitle(VALID_TITLE_HARRY)
            .build();
        List<Book> newBooks = Arrays.asList(BELOVED, editedBeloved);
        List<Person> newPersons = Arrays.asList(ALICE, ALICE);
        ExpressLibraryStub newData = new ExpressLibraryStub(newPersons, newBooks);

        assertThrows(DuplicatePersonException.class, () -> expressLibrary.resetData(newData));
    }

    /**
     * A stub ReadOnlyExpressLibrary whose persons list or books list can violate interface
     * constraints.
     */
    private static class ExpressLibraryStub implements ReadOnlyExpressLibrary {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Book> books = FXCollections.observableArrayList();

        ExpressLibraryStub(Collection<Person> persons, Collection<Book> books) {
            this.persons.setAll(persons);
            this.books.setAll(books);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Book> getBookList() {
            return books;
        }
    }

}
