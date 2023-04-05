package expresslibrary.testutil;

import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;

/**
 * A utility class to help with building ExpressLibrary objects.
 * Example usage: <br>
 * {@code ExpressLibrary el = new ExpressLibraryBuilder().withPerson(JOHN).withBook(BELOVED).build();}
 */
public class ExpressLibraryBuilder {

    private ExpressLibrary expressLibrary;

    public ExpressLibraryBuilder() {
        expressLibrary = new ExpressLibrary();
    }

    public ExpressLibraryBuilder(ExpressLibrary expressLibrary) {
        this.expressLibrary = expressLibrary;
    }

    /**
     * Adds a new {@code Person} to the {@code ExpressLibrary} that we are building.
     */
    public ExpressLibraryBuilder withPerson(Person person) {
        expressLibrary.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Book} to the {@code ExpressLibrary} that we are building.
     */
    public ExpressLibraryBuilder withBook(Book book) {
        expressLibrary.addBook(book);
        return this;
    }

    public ExpressLibrary build() {
        return expressLibrary;
    }
}
