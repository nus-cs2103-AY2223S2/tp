package seedu.address.model.item;

/**
 * The interface for objects that are identifiable by a unique ID.
 */
public interface Identifiable {
    /**
     * Returns the ID of the object.
     *
     * @return the ID of the object
     */
    String getId();


    /**
     * Returns true if the two objects are the same.
     *
     * @param a the first object
     * @param b the second object
     * @return true if the two objects are the same type and have the same ID
     */
    static boolean isSame(Identifiable a, Identifiable b) {
        if (a == b) {
            return true;
        }
        return a.getClass().equals(b.getClass()) && a.getId().equals(b.getId());
    }
}
