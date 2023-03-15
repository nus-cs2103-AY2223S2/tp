package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

/**
 * Represents a module's name in the tracker.<p>
 * Guarantees: immutable.
 */
public class ModuleName {

    private final String name;

    /**
     * Constructs a {@code ModuleName}.
     *
     * @param name A possibly empty, but non-NULL, name.
     */
    public ModuleName(String name) {
        requireNonNull(name);
        this.name = name;
    }

    /**
     * Returns true if the name is empty. Otherwise, returns false.
     *
     * @return True if the name is empty. Otherwise, false.
     */
    public boolean isEmpty() {
        return name.isEmpty();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ModuleName
                && name.equals(((ModuleName) other).name));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
