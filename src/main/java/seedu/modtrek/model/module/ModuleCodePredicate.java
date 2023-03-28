package seedu.modtrek.model.module;

import java.util.Set;
import java.util.function.Predicate;

import seedu.modtrek.model.tag.Tag;

/**
 * Tests that a {@code Module}'s {@code Name} matches module code.
 */
public class ModuleCodePredicate implements Predicate<Module> {
    private final boolean isCode;
    private final String moduleCode;
    private final Set<String> codePrefixes;
    private final Set<Credit> credits;
    private final Set<SemYear> semYears;
    private final Set<Grade> grades;
    private final Set<Tag> tags;

    /**
     * Instantiates a new ModulePredicate.
     * @param isCode       whether to filter code or code prefix
     * @param moduleCode   the entire module code
     * @param codePrefixes the set of code prefixes
     * @param credits      the set of credits
     * @param semYears     the set semester-years
     * @param grades       the set of grades
     * @param tags         the set of tags
     */
    public ModuleCodePredicate(boolean isCode, String moduleCode, Set<String> codePrefixes,
                               Set<Credit> credits, Set<SemYear> semYears, Set<Grade> grades, Set<Tag> tags) {
        this.isCode = isCode;
        this.codePrefixes = codePrefixes;
        this.moduleCode = moduleCode;
        this.credits = credits;
        this.semYears = semYears;
        this.grades = grades;
        this.tags = tags;
    }

    @Override
    public boolean test(Module module) {
        if (isCode) {
            return module.getCode().toString().equals(moduleCode);
        }
        return (codePrefixes.isEmpty() || startsWithAnyPrefix(codePrefixes, module.getCode().toString()))
                && (credits.isEmpty() || credits.contains(module.getCredit()))
                && (semYears.isEmpty() || semYears.contains(module.getSemYear()))
                && (grades.isEmpty() || grades.contains(module.getGrade()))
                && (tags.isEmpty() || module.getTags().containsAll(tags));
    }

    /**
     * Checks if each module code starts with any of the code prefixes in the set.
     * @param codePrefixSet the set of code prefixes to be filtered
     * @param moduleCode    the module code
     * @return true if the module code starts with any of the code prefixes in the set, false otherwise
     */
    public boolean startsWithAnyPrefix(Set<String> codePrefixSet, String moduleCode) {
        for (String prefix : codePrefixSet) {
            if (moduleCode.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCodePredicate // instanceof handles nulls
                && moduleCode.equals(((ModuleCodePredicate) other).moduleCode) // state check
                && credits.equals(((ModuleCodePredicate) other).credits)
                && semYears.equals(((ModuleCodePredicate) other).semYears)
                && grades.equals(((ModuleCodePredicate) other).grades)
                && tags.equals(((ModuleCodePredicate) other).tags));
    }

}
