package seedu.address.model.shared;

import java.util.Objects;
import java.util.UUID;


/**
 * Represents an ID to identify the object
 */
public class Id {

    public final UUID value;

    /**
     * Constructs a {@code Id}.
     **/
    public Id() {
        this.value = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Id) // instanceof handles nulls
            && value.equals(((Id) other).value); // state check
    }
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
