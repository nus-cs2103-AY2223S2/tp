package seedu.fitbook.testutil.client;

import seedu.fitbook.model.FitBook;
import seedu.fitbook.model.client.Client;

/**
 * A utility class to help with building FitBook objects.
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
