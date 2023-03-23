package seedu.address.model.id;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * represents an id in the addressbook
 * guarantees: id is immutable.
 */
public abstract class Id {
    public static final String MESSAGE_CONSTRAINTS =
        "ID should not be blank";
    public static final String VALIDATION_REGEX = ".*";
    public final String id;

    protected Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

    public static String generateUniqueId() {
        return "1";
    }

    public String getId() {
        return id;
    }

    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Id)) {
            return false;
        }
        Id other = (Id) obj;
        return id.equals(other.getId());
    }

    @Override
    public String toString() {
        return id;
    }
}
