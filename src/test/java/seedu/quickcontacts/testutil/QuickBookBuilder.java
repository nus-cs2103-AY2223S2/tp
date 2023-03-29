package seedu.quickcontacts.testutil;

import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.person.Person;

/**
 * A utility class to help with building QuickBook objects.
 * Example usage: <br>
 *     {@code QuickBook qb = new QuickBookBuilder().withPerson("John", "Doe").build();}
 */
public class QuickBookBuilder {

    private QuickBook quickBook;

    public QuickBookBuilder() {
        quickBook = new QuickBook();
    }

    public QuickBookBuilder(QuickBook quickBook) {
        this.quickBook = quickBook;
    }

    /**
     * Adds a new {@code Person} to the {@code QuickBook} that we are building.
     */
    public QuickBookBuilder withPerson(Person person) {
        quickBook.addPerson(person);
        return this;
    }

    public QuickBook build() {
        return quickBook;
    }
}
