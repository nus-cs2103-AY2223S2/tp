package seedu.address.model.tag.util;

import seedu.address.model.commitment.Lesson;
import seedu.address.model.tag.ModuleTag;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ModuleTagBuilder {
    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final Set<Lesson> DEFAULT_LESSONS = new HashSet<>();

    private String moduleCode;
    private Set<Lesson> lessons;

    public ModuleTagBuilder() {
        moduleCode = DEFAULT_MODULE_CODE;
        lessons = DEFAULT_LESSONS;
    }

    public ModuleTagBuilder(ModuleTag moduleTag) {
        moduleCode = moduleTag.tagName;
        lessons = moduleTag.getImmutableLessons();
    }

    public ModuleTagBuilder withModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public ModuleTagBuilder withLessons(Collection<? extends Lesson> lessons) {
        this.lessons = new HashSet<>(lessons);
        return this;
    }

    public ModuleTagBuilder withLessons(Lesson... lessons) {
        return withLessons(Set.of(lessons));
    }

    public ModuleTagBuilder withExtraLessons(Collection<? extends Lesson> lessons) {
        this.lessons.addAll(lessons);
        return this;
    }

    public ModuleTagBuilder withExtraLessons(Lesson... lessons) {
        return withExtraLessons(Set.of(lessons));
    }

    public ModuleTag build() {
        return new ModuleTag(moduleCode, lessons);
    }
}
