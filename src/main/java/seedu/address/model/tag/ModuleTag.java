package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import seedu.address.model.commitment.Lesson;
import seedu.address.model.time.TimePeriod;

/**
 * Represents a ModuleTag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class ModuleTag extends Tag implements Comparable<ModuleTag> {

    public static final String MESSAGE_CONSTRAINTS =
            "NUS Modules should have 2 - 3 letter prefix, followed by 4 digits and optional 1 - 3 alphabets";
    public static final String VALIDATION_REGEX = "[a-zA-Z]{2,4}[0-9]{4}[a-zA-Z]{0,3}";

    private final Set<Lesson> lessons;

    /**
     * Constructs a {@code ModuleTag}.
     *
     * @param tagName A valid tag name.
     */
    public ModuleTag(String tagName) {
        super(tagName.toUpperCase());
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.lessons = new HashSet<>();
    }

    /**
     * Overloaded constructor for a {@code ModuleTag}
     */
    public ModuleTag(String moduleCode, Lesson... lessons) {
        super(moduleCode.toUpperCase());
        requireAllNonNull(moduleCode, lessons);
        this.lessons = Set.of(lessons);
    }

    /**
     * Overloaded constructor for a {@code ModuleTag}
     */
    public ModuleTag(String moduleCode, Collection<Lesson> lessons) {
        super(moduleCode.toUpperCase());
        requireAllNonNull(moduleCode, lessons);
        this.lessons = new HashSet<>(lessons);
    }

    /**
     * Gets module code of the module tag.
     */
    public String getModuleCode() {
        return tagName;
    }

    /**
     * Combines the lesson set of two module tags.
     */
    public ModuleTag mergeWith(ModuleTag otherModuleTag) {
        assert tagName.equals(otherModuleTag.tagName);
        Set<Lesson> newLessons = new HashSet<>(lessons);
        newLessons.addAll(otherModuleTag.lessons);
        return new ModuleTag(tagName, newLessons);
    }

    /**
     * Gets an immutable copy of lessons.
     */
    public Set<Lesson> getImmutableLessons() {
        return Set.copyOf(lessons);
    }

    /**
     * Formats the lessons into a string.
     */
    public String getLessonsAsStr() {
        return lessons.stream()
                .map(Lesson::getTimePeriod)
                .map(TimePeriod::toString)
                .collect(Collectors.joining(", "));
    }

    /**
     * Checks whether the module tag contains all of the lessons provided.
     */
    public boolean containsLessons(Collection<? extends Lesson> lessons) {
        return this.lessons.containsAll(lessons);
    }

    /**
     * Adds a lesson to the module tag.
     */
    public void addLesson(Lesson lesson) {
        requireNonNull(lesson);
        lessons.add(lesson);
    }

    /**
     * Adds multiple lessons to the module tag.
     */
    public void addLessons(Collection<Lesson> lessons) {
        requireNonNull(lessons);
        for (Lesson lesson : lessons) {
            addLesson(lesson);
        }
    }

    /**
     * Removes a lessons from the module tag.
     */
    public void removeLesson(Lesson lesson) {
        requireNonNull(lesson);
        lessons.remove(lesson);
    }

    /**
     * Removes multiple lessons from the module tag.
     */
    public void removeLessons(Collection<Lesson> lessons) {
        requireNonNull(lessons);
        for (Lesson lesson : lessons) {
            removeLesson(lesson);
        }
    }

    /**
     * Copies the information of the module tag to preserve immutability.
     */
    public ModuleTag copy() {
        return new ModuleTag(tagName, new HashSet<>(lessons));
    }

    @Override
    boolean isValidTagName(String test, String regex) {
        requireAllNonNull(test, regex);
        return Pattern.matches(VALIDATION_REGEX, test);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleTag // instanceof handles nulls
                && tagName.equals(((ModuleTag) other).tagName)
                && lessons.equals(((ModuleTag) other).lessons)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName, lessons);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        String lessonsStr = getLessonsAsStr();
        if (lessonsStr.isEmpty()) {
            lessonsStr = "None";
        }
        return String.format("%s: Lessons: %s", tagName, lessonsStr);
    }

    @Override
    public int compareTo(ModuleTag otherModuleTag) {
        return tagName.compareTo(otherModuleTag.tagName);
    }

    /**
     * Flags if this moduleTag is a tag for the basic functionality, with no lesson parameters tied to it.
     * @return boolean if moduleTag is a generated from a simple input with no extra parameters.
     */
    public boolean isBasicTag() {
        return lessons.isEmpty();
    }
}
