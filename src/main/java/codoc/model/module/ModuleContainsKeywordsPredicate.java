package codoc.model.module;

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

    @Override
    public boolean test(Person person) {
        if (person.getModules().isEmpty()) {
            return false;
        }

        String academicYear = "";
        for (String word : keywords) {
            if (word.matches(ACAD_YEAR_VALIDATION_REGEX)) {
                academicYear = word;
                continue;
            }
            if (academicYear.isEmpty()) {
                continue;
            }
            if (!person.getModules().contains(new Module(academicYear.concat(" ").concat(word)))) {
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
}
