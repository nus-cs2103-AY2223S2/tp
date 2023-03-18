package seedu.address.model.person;

import java.util.List;

/**
 * A interface for ReadOnlyPcClass
 */
public interface ReadOnlyPcClass {
    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    List<Class> getClassList();
}
