package seedu.socket.model.person.predicate;

import java.util.Set;
import java.util.function.Predicate;

import seedu.socket.model.person.Person;
import seedu.socket.model.person.tag.Language;


/**
 * Tests that a {@code Person}'s {@code Language} matches any of the languages given.
 */
public class ListCommandLanguagePredicate implements Predicate<Person> {
    private final Set<Language> languages;

    public ListCommandLanguagePredicate(Set<Language> languages) {
        this.languages = languages;
    }

    @Override
    public boolean test(Person person) {
        if (languages.isEmpty()) {
            return true;
        }

        Set<Language> personLanguages = person.getLanguages();
        boolean languagesPresent = true;

        for (Language language: languages) {
            languagesPresent = languagesPresent && personLanguages.contains(language);
        }

        return languagesPresent;

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommandLanguagePredicate // instanceof handles nulls
                && languages.equals(((ListCommandLanguagePredicate) other).languages)); // state check
    }
}
