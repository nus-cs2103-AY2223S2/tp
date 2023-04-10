package seedu.address.testutil;

import seedu.ultron.model.Ultron;
import seedu.ultron.model.opening.Opening;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Ultron ab = new UltronBuilder().withOpening("John", "Doe").build();}
 */
public class UltronBuilder {

    private Ultron ultron;

    public UltronBuilder() {
        ultron = new Ultron();
    }

    public UltronBuilder(Ultron ultron) {
        this.ultron = ultron;
    }

    /**
     * Adds a new {@code Opening} to the {@code Ultron} that we are building.
     */
    public UltronBuilder withOpening(Opening opening) {
        ultron.addOpening(opening);
        return this;
    }

    public Ultron build() {
        return ultron;
    }
}
