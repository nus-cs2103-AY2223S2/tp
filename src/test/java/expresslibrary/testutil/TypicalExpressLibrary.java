package expresslibrary.testutil;

import static expresslibrary.testutil.TypicalBooks.getTypicalBooks;
import static expresslibrary.testutil.TypicalPersons.getTypicalPersons;

import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;
/**
 * A utility class containing a list of {@code Person} objects and a list of {@code Book} to be used in tests.
 */
public class TypicalExpressLibrary {
    /**
     * Returns an {@code ExpressLibrary} with all the typical persons and books.
     */
    public static ExpressLibrary getTypicalExpressLibrary() {
        ExpressLibrary el = new ExpressLibrary();
        for (Person person : getTypicalPersons()) {
            el.addPerson(person);
        }
        for (Book book : getTypicalBooks()) {
            el.addBook(book);
        }
        return el;
    }
}
