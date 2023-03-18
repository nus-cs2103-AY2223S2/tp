package seedu.address.model.entity.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Technican}'s id matches given id.
 */
public class TechnicianIdPredicate implements Predicate<Technician> {
    private final int id;

    public TechnicianIdPredicate(int id) {
        this.id = id;
    }

    @Override
    public boolean test(Technician person) {
        return person.getId() == id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TechnicianIdPredicate // instanceof handles nulls
                && id == ((TechnicianIdPredicate) other).id); // state check
    }

}
