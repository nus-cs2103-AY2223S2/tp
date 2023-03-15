package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building  {@code Module} objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_CODE = "CS2040S";
    public static final String DEFAULT_NAME = "Data Structures and Algorithms";

    private ModuleCode code;
    private ModuleName name;
    private Set<Tag> tags;
    private List<Lecture> lectures;

    /**
     * Creates a {@code ModuleBuilder} with default details.
     */
    public ModuleBuilder() {
        code = new ModuleCode(DEFAULT_CODE);
        name = new ModuleName(DEFAULT_NAME);
        tags = new HashSet<>();
        lectures = new ArrayList<>();
    }

    /**
     * Initializes the {@code ModuleBuilder} with the data of {@code moduleToCopy}.
     *
     * @param moduleToCopy The module containing the data to copy.
     */
    public ModuleBuilder(Module moduleToCopy) {
        code = moduleToCopy.getCode();
        name = moduleToCopy.getName();
        tags = new HashSet<>(moduleToCopy.getTags());
        lectures = moduleToCopy
                .getLectureList()
                .stream()
                .map((l) -> (Lecture) l)
                .collect(Collectors.toList());
    }

    /**
     * Sets the {@code code} of the {@code Module} that we are building.
     *
     * @param code The code to set to.
     * @return This {@code ModuleBuilder}.
     */
    public ModuleBuilder withCode(String code) {
        this.code = new ModuleCode(code);
        return this;
    }

    /**
     * Sets the {@code name} of the {@code Module} that we are building.
     *
     * @param name The name to set to.
     * @return This {@code ModuleBuilder}.
     */
    public ModuleBuilder withName(String name) {
        this.name = new ModuleName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Module} that we are building.
     *
     * @param tags The tags to set to.
     * @return This {@code ModuleBuilder}.
     */
    public ModuleBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code lectures} into a {@code List<Lecture>} and set it to the {@code Module} that we are building.
     *
     * @param lectures The lectures to set to.
     * @return This {@code ModuleBuilder}.
     */
    public ModuleBuilder withLectures(Lecture ... lectures) {
        this.lectures = Arrays.asList(lectures);
        return this;
    }

    /**
     * Returns a {@code Module} object with values set to those of this object.
     *
     * @return A {@code Module} object with values set to those of this object.
     */
    public Module build() {
        return new Module(code, name, tags, lectures);
    }

}
