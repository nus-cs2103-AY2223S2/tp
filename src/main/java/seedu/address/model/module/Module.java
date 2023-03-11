package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.lecture.Lecture;
import seedu.address.model.lecture.UniqueLectureList;
import seedu.address.model.tag.Tag;

/**
 * Represents a module in the tracker.
 * Guarantees: details are not null, field values are validated, immutable.
 */
public class Module {

    private final ModuleCode code;
    private final ModuleName name;

    private final Set<Tag> tags = new HashSet<>();

    private final UniqueLectureList lectures = new UniqueLectureList();

    /**
     * Every field must be not null.
     *
     * @param code The module's code.
     * @param name The name of the module.
     * @param tags The tags applied to the module.
     * @param lectures The lectures of the module.
     */
    public Module(ModuleCode code, ModuleName name, Set<Tag> tags, List<Lecture> lectures) {
        requireAllNonNull(code, name, tags, lectures);
        this.code = code;
        this.name = name;
        this.tags.addAll(tags);
        this.lectures.setLectures(lectures);
    }

    public ModuleCode getCode() {
        return code;
    }

    public ModuleName getName() {
        return name;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable lecture list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Lecture> getLectures() {
        return lectures.asUnmodifiableList();
    }

    /**
     * Returns true if both modules have the same code.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getCode().equals(getCode());
    }

    /**
     * Returns true if both modules have the same fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getCode().equals(getCode())
                && otherModule.getName().equals(getName())
                && otherModule.getTags().equals(getTags())
                && otherModule.getLectures().equals(getLectures());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(code, name, tags, lectures);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCode());

        if (!name.isEmpty()) {
            builder.append("; Name: ")
                .append(getName());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        List<Lecture> lectures = getLectures();
        if (!lectures.isEmpty()) {
            builder.append("; Lectures: ");
            lectures.forEach(builder::append);
        }

        return builder.toString();
    }
}
