package seedu.address.testutil;

import seedu.address.experimental.model.Reroll;
import seedu.address.model.entity.Entity;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Reroll ab = new RerollBuilder().withPerson("John", "Doe").build();}
 */
public class RerollBuilder {

    private Reroll reroll;

    public RerollBuilder() {
        reroll = new Reroll();
    }

    public RerollBuilder(Reroll reroll) {
        this.reroll = reroll;
    }

    /**
     * Adds a new {@code Person} to the {@code Reroll} that we are building.
     */
    public RerollBuilder withEntity(Entity entity) {
        //reroll.addEntity(entity);
        return this;
    }

    public Reroll build() {
        return reroll;
    }
}
