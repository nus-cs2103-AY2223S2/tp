package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Address} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {

    private NameContainsKeywordsPredicate namePredicate;
    private ProfileContainsKeywordsPredicate profilePredicate;
    private PhoneContainsKeywordsPredicate phonePredicate;
    private EmailContainsKeywordsPredicate emailPredicate;
    private AddressContainsKeywordsPredicate addressPredicate;
    private LanguageContainsKeywordsPredicate languagePredicate;
    private TagContainsKeywordsPredicate tagPredicate;

    public PersonContainsKeywordsPredicate(NameContainsKeywordsPredicate namePredicate,
                       ProfileContainsKeywordsPredicate profilePredicate,
                       PhoneContainsKeywordsPredicate phonePredicate,
                       EmailContainsKeywordsPredicate emailPredicate,
                       AddressContainsKeywordsPredicate addressPredicate,
                       LanguageContainsKeywordsPredicate languagePredicate,
                       TagContainsKeywordsPredicate tagPredicate) {
        this.namePredicate = namePredicate;
        this.profilePredicate = profilePredicate;
        this.phonePredicate = phonePredicate;
        this.emailPredicate = emailPredicate;
        this.addressPredicate = addressPredicate;
        this.languagePredicate = languagePredicate;
        this.tagPredicate = tagPredicate;
    }

    @Override
    public boolean test(Person person) {
        boolean isMatchName = namePredicate.test(person);
        boolean isMatchProfile = profilePredicate.test(person);
        boolean isMatchPhone = phonePredicate.test(person);
        boolean isMatchEmail = emailPredicate.test(person);
        boolean isMatchAddress = addressPredicate.test(person);
        boolean isMatchLanguage = languagePredicate.test(person);
        boolean isMatchTag = tagPredicate.test(person);

        return isMatchName
                || isMatchProfile
                || isMatchPhone
                || isMatchEmail
                || isMatchAddress
                || isMatchLanguage
                || isMatchTag;

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate
                && namePredicate.equals(((PersonContainsKeywordsPredicate) other).namePredicate)
                && profilePredicate.equals(((PersonContainsKeywordsPredicate) other).profilePredicate)
                && phonePredicate.equals(((PersonContainsKeywordsPredicate) other).phonePredicate)
                && emailPredicate.equals(((PersonContainsKeywordsPredicate) other).emailPredicate)
                && addressPredicate.equals(((PersonContainsKeywordsPredicate) other).addressPredicate)
                && languagePredicate.equals(((PersonContainsKeywordsPredicate) other).languagePredicate)
                && tagPredicate.equals(((PersonContainsKeywordsPredicate) other).tagPredicate));
    }

}
