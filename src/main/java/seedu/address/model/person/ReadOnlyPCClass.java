package seedu.address.model.person;

import java.util.List;

public interface ReadOnlyPCClass {
    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    List<Class> getClassList();
}
