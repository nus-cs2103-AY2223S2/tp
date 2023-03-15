package seedu.address.model.experimental;

import seedu.address.model.entity.Mob;

/** Abstraction of all operations on mobs */
public class RerollMobs extends RerollEntities<Mob> {

    // for convenience
    private final UniqueEntityList mobs = entities;

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RerollMobs
                && mobs.equals(((RerollMobs) other).mobs));
    }
}
