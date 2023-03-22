package seedu.dengue.model.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.dengue.commons.util.StringUtil;
import seedu.dengue.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Predicate<String> containsName =
                keyword -> StringUtil.containsSubstringIgnoreCase(person.getName().fullName, keyword);
        Predicate<String> containsPostalWithS =
                keynum -> StringUtil.startsWithSubstringIgnoreCaseatIndex(
                        person.getPostal().value, keynum, 1);
        Predicate<String> containsPostalWithoutS = keynum -> StringUtil.startsWithSubstringIgnoreCaseatIndex(
                person.getPostal().value, keynum, 0);
        Predicate<String> containsPostal = containsPostalWithoutS.or(containsPostalWithS);

        Predicate<String> containsPostalOrName = containsPostal.or(containsName);
        return keywords.stream()
                .anyMatch(containsPostalOrName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }
}
