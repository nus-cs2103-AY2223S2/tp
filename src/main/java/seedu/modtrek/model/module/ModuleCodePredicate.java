package seedu.modtrek.model.module;

import java.util.HashSet;
import java.util.function.Predicate;

import seedu.modtrek.model.tag.Tag;

/**
 * Tests that a {@code Module}'s {@code Name} matches module code.
 */
public class ModuleCodePredicate implements Predicate<Module> {
    private final String moduleCode;
    private final String credit;
    private final String semYear;
    private final String grade;
    private final HashSet<Tag> tags;

    /**
     * Instantiates a new ModulePredicate.
     * @param moduleCode either the code prefix or the entire code
     * @param credit     the credit
     * @param semYear    the semester year
     * @param grade      the grade
     * @param tags       the tags
     */
    public ModuleCodePredicate(String moduleCode, String credit, String semYear, String grade, HashSet<Tag> tags) {
        this.moduleCode = moduleCode;
        this.credit = credit;
        this.semYear = semYear;
        this.grade = grade;
        this.tags = tags;
    }

    @Override
    public boolean test(Module module) {
        return (moduleCode.isEmpty() || module.getCode().toString().startsWith(moduleCode))
                && (credit.isEmpty() || module.getCredit().equals(new Credit(credit)))
                && (semYear.isEmpty() || module.getSemYear().equals(new SemYear(semYear)))
                && (grade.isEmpty() || module.getGrade().equals(new Grade(grade)))
                && (tags.isEmpty() || module.getTags().containsAll(tags));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCodePredicate // instanceof handles nulls
                && moduleCode.equals(((ModuleCodePredicate) other).moduleCode) // state check
                && credit.equals(((ModuleCodePredicate) other).credit)
                && semYear.equals(((ModuleCodePredicate) other).semYear)
                && grade.equals(((ModuleCodePredicate) other).grade)
                && tags.equals(((ModuleCodePredicate) other).tags));
    }

}
