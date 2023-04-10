package wingman.model.item;

import java.util.List;

/**
 * The interface for objects that are identifiable by a unique ID.
 */
public interface Item {
    /**
     * Returns the ID of the object.
     *
     * @return the ID of the object
     */
    String getId();

    /**
     * Returns the display list of the object. This will be used for display
     * in JavaFX. Doing so would make it easier for us to work on the
     * "back-end" part of the application first without worrying much about
     * the front-end, and it significantly reduces the amount of work we will
     * need to do. However, this also means that we won't have as much
     * flexibility in the front-end.
     *
     * @return the display list of the object
     */
    default List<String> getDisplayList() {
        return List.of("ID: " + getId(), "NOT IMPLEMENTED");
    }

    /**
     * Returns true if the two objects are the same.
     *
     * @param a the first object
     * @param b the second object
     * @return true if the two objects are the same type and have the same ID
     */
    static boolean isSame(Item a, Item b) {
        if (a == b) {
            return true;
        }
        return a.getClass().equals(b.getClass()) && a.getId().equals(b.getId());
    }
}
