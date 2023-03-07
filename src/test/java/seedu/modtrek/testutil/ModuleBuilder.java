package seedu.modtrek.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.model.util.SampleDataUtil;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_CODE = "CS1101S";
    public static final String DEFAULT_CREDIT = "4";
    public static final String DEFAULT_SEMYEAR = "Y1S1";
    public static final String DEFAULT_GRADE = "";

    private Code code;
    private Credit credit;
    private SemYear semYear;
    private Grade grade;
    private Set<Tag> tags;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        code = new Code(DEFAULT_CODE);
        credit = new Credit(DEFAULT_CREDIT);
        semYear = new SemYear(DEFAULT_SEMYEAR);
        grade = new Grade(DEFAULT_GRADE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        code = moduleToCopy.getCode();
        credit = moduleToCopy.getCredit();
        semYear = moduleToCopy.getSemYear();
        grade = moduleToCopy.getGrade();
        tags = new HashSet<>(moduleToCopy.getTags());
    }

    /**
     * Sets the {@code Code} of the {@code Module} that we are building.
     */
    public ModuleBuilder withCode(String code) {
        this.code = new Code(code);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Module} that we are building.
     */
    public ModuleBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Module} that we are building.
     */
    public ModuleBuilder withGrade(String grade) {
        this.grade = new Grade(grade);
        return this;
    }

    /**
     * Sets the {@code Credit} of the {@code Module} that we are building.
     */
    public ModuleBuilder withCredit(String credit) {
        this.credit = new Credit(credit);
        return this;
    }

    /**
     * Sets the {@code SemYear} of the {@code Module} that we are building.
     */
    public ModuleBuilder withSemYear(String semYear) {
        this.semYear = new SemYear(semYear);
        return this;
    }

    public Module build() {
        return new Module(code, credit, semYear, tags, grade);
    }

}
