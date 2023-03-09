package seedu.address.testutil;

import seedu.address.model.FitBook;
import seedu.address.model.client.Client;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code FitBook ab = new FitBookBuilder().withClient("John", "Doe").build();}
 */
public class FitBookBuilder {

    private FitBook fitBook;

    public FitBookBuilder() {
        fitBook = new FitBook();
    }

    public FitBookBuilder(FitBook fitBook) {
        this.fitBook = fitBook;
    }

    /**
     * Adds a new {@code Client} to the {@code FitBook} that we are building.
     */
    public FitBookBuilder withClient(Client client) {
        fitBook.addClient(client);
        return this;
    }

    public FitBook build() {
        return fitBook;
    }
}
