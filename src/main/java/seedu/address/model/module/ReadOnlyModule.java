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
     */
    public ModuleCode getCode();

    /**
     * Returns the module's name.
     * @return
     */
    public ModuleName getName();

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags();

    /**
     * Returns an immutable lecture list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public ObservableList<? extends ReadOnlyLecture> getLectureList();

    /**
     * Returns the lecture whose name is the same as {@code name}. If no such lecture exist, return null.
     */
    public ReadOnlyLecture getLecture(LectureName name);

    /**
     * Returns true if a lecture with the same name as {@code lecture} exists in the module.
     */
    public boolean hasLecture(ReadOnlyLecture lecture);

    /**
     * Returns true if both modules have the same code.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module other);
}
