package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_CODE = "CS2103";
    public static final String DEFAULT_NAME = "software_eng";

    private ModuleCode moduleCode;
    private ModuleName moduleName;
    private Set<Tag> tags = new HashSet<>();
    private List<Lecture> lectures = new ArrayList<>();

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleCode = new ModuleCode(DEFAULT_CODE);
        moduleName = new ModuleName(DEFAULT_NAME);
    }

    /**
     * Sets the {@code moduleCode} of the {@code Module} that we are building.
     */
    public ModuleBuilder withCode(String code) {
        this.moduleCode = new ModuleCode(code);
        return this;
    }

    /**
     * Sets the {@code moduleName} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.moduleName = new ModuleName(name);
        return this;
    }

    /**
     * Sets the {@code moduleName} of the {@code Module} that we are building.
     */
    public ModuleBuilder withLectures(List<Lecture> lectures) {
        this.lectures = lectures;
        return this;
    }

    /**
     * Sets the {@code moduleName} of the {@code Module} that we are building.
     */
    public ModuleBuilder withTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Module build() {
        return new Module(moduleCode, moduleName, tags, lectures);
    }

}
