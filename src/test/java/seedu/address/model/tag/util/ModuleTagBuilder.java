package seedu.address.model.tag.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.commitment.Lesson;
import seedu.address.model.tag.ModuleTag;

/**
 * Creates a ModuleTag.
 */
public class ModuleTagBuilder {
    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final Set<Lesson> DEFAULT_LESSONS = new HashSet<>();

    private String moduleCode;
    private Set<Lesson> lessons;

    /**
     * Constructor for a {@code ModuleTagBuilder}.
     */
    public ModuleTagBuilder() {
        moduleCode = DEFAULT_MODULE_CODE;
        lessons = DEFAULT_LESSONS;
    }

    /**
     * Constructor that copies information from a {@code ModuleTag}.
     */
    public ModuleTagBuilder(ModuleTag moduleTag) {
        moduleCode = moduleTag.tagName;
        lessons = moduleTag.getImmutableLessons();
    }

    /**
     * Creates a new ModuleTagBuilder with an updated field.
     */
    public ModuleTagBuilder withModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    /**
     * Creates a new ModuleTagBuilder with an updated field.
     */
    public ModuleTagBuilder withLessons(Collection<? extends Lesson> lessons) {
        this.lessons = new HashSet<>(lessons);
        return this;
    }

    /**
     * Creates a new ModuleTagBuilder with an updated field.
     */
    public ModuleTagBuilder withLessons(Lesson... lessons) {
        return withLessons(Set.of(lessons));
    }

    /**
     * Creates a new ModuleTagBuilder with an updated field.
     */
    public ModuleTagBuilder withExtraLessons(Collection<? extends Lesson> lessons) {
        this.lessons.addAll(lessons);
        return this;
    }

    /**
     * Creates a new ModuleTagBuilder with an updated field.
     */
    public ModuleTagBuilder withExtraLessons(Lesson... lessons) {
        return withExtraLessons(Set.of(lessons));
    }

    /**
     * Creates a new ModuleTagBuilder with the updated fields.
     */
    public ModuleTag build() {
        return new ModuleTag(moduleCode, lessons);
    }
}
