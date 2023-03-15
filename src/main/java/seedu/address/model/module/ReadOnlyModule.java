package seedu.address.model.module;

import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of a module.
 */
public interface ReadOnlyModule {
    /**
     * Returns the module code.
     *
     * @return The module code.
     */
    public ModuleCode getCode();

    /**
     * Returns the module's name.
     *
     * @return The module name.
     */
    public ModuleName getName();

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return An immutable tag set.
     */
    public Set<Tag> getTags();

    /**
     * Returns an immutable lecture list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return An immutable lecture list.
     */
    public ObservableList<? extends ReadOnlyLecture> getLectureList();

    /**
     * Returns the lecture whose name is the same as {@code name}. If no such lecture exist, return null.
     *
     * @param name The name of the lecture to get.
     * @return The lecture whose name is the same as {@code name}. If no such lecture exist, return null.
     */
    public ReadOnlyLecture getLecture(LectureName name);

    /**
     * Returns true if a lecture with the same name as {@code lecture} exists in the module.
     *
     * @param lecture The lecture to check if it is in the module.
     * @return True if a lecture with the same name as {@code lecture} exists in the module. Otherwise, false.
     */
    public boolean hasLecture(ReadOnlyLecture lecture);

    /**
     * Returns true if both modules have the same code.<p>
     * This defines a weaker notion of equality between two modules.
     *
     * @param other The module to check if this module is the same with.
     * @return True if both modules have the same code. Otherwise, false.
     */
    public boolean isSameModule(Module other);
}
