package seedu.address.model.person.predicate;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Language;

/**
 * Tests that a {@code Person}'s {@code Language} matches any of the keywords given.
 */
public class LanguageContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public LanguageContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Set<Language> languages = person.getLanguages();
        return keywords.stream()
                .anyMatch(keyword -> languages.stream().anyMatch(
                        language -> StringUtil.containsWordIgnoreCase(language.languageName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LanguageContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LanguageContainsKeywordsPredicate) other).keywords)); // state check
    }

}
