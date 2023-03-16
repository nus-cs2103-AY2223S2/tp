package seedu.socket.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.socket.model.person.Person;

/**
 * Tests that a {@code Person}'s field matches any of the keywords given to the respective field.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {

    private NameContainsKeywordsPredicate namePredicate;
    private ProfileContainsKeywordsPredicate profilePredicate;
    private PhoneContainsKeywordsPredicate phonePredicate;
    private EmailContainsKeywordsPredicate emailPredicate;
    private AddressContainsKeywordsPredicate addressPredicate;
    private LanguageContainsKeywordsPredicate languagePredicate;
    private TagContainsKeywordsPredicate tagPredicate;

    /**
     * Constructor for PersonContainsKeywordsPredicate class
     */
    public PersonContainsKeywordsPredicate(List<String> nameKeywords,
                                           List<String> profileKeywords,
                                           List<String> phoneKeywords,
                                           List<String> emailKeywords,
                                           List<String> addressKeywords,
                                           List<String> languageKeywords,
                                           List<String> tagKeywords) {
        this.namePredicate = new NameContainsKeywordsPredicate(nameKeywords);
        this.profilePredicate = new ProfileContainsKeywordsPredicate(profileKeywords);
        this.phonePredicate = new PhoneContainsKeywordsPredicate(phoneKeywords);
        this.emailPredicate = new EmailContainsKeywordsPredicate(emailKeywords);
        this.addressPredicate = new AddressContainsKeywordsPredicate(addressKeywords);
        this.languagePredicate = new LanguageContainsKeywordsPredicate(languageKeywords);
        this.tagPredicate = new TagContainsKeywordsPredicate(tagKeywords);
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
