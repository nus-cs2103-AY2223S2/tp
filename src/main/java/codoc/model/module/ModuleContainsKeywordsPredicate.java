package codoc.model.module;

import static codoc.logic.parser.CliSyntax.PREFIX_MOD;

import java.util.List;
import java.util.function.Predicate;

import codoc.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Module} matches any of the keywords given.
 */
public class ModuleContainsKeywordsPredicate implements Predicate<Person> {
    public static final String ACAD_YEAR_VALIDATION_REGEX = "^AY[0-9]{4}S[12]";

    private final List<String> keywords;

    public ModuleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    private boolean isAcademicYear(String word) {
        return word.matches(ACAD_YEAR_VALIDATION_REGEX);
    }

    private String constructWhatUserIsSearchingFor(String academicYear, String module) {
        if (academicYear.equals(module)) {
            return academicYear;
        }
        return academicYear.concat(" ").concat(module).trim().toUpperCase();
    }

    private boolean doesNotContain(String whatUserIsSearchingFor, Person person) {
        return person.getModules().stream().noneMatch(module -> module.moduleName.contains(whatUserIsSearchingFor));
    }

    @Override
    public boolean test(Person person) {
        String academicYear = "";

        for (String word : keywords) {
            if (isAcademicYear(word)) {
                academicYear = word;
            }

            String whatUserIsSearchingFor = constructWhatUserIsSearchingFor(academicYear, word);
            if (doesNotContain(whatUserIsSearchingFor, person)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleContainsKeywordsPredicate) other).keywords)); // state check
    }
    @Override
    public String toString() {
        return PREFIX_MOD + keywords.stream().reduce("", (combine, s) ->
                combine.concat(" ").concat(s)).trim();
    }
}
