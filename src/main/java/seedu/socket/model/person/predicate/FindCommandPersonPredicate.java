package seedu.socket.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.socket.model.person.Person;

/**
 * Tests that a {@code Person}'s field matches any of the keywords given to the respective field.
 */
public class FindCommandPersonPredicate implements Predicate<Person> {

    private FindCommandNamePredicate namePredicate;
    private FindCommandProfilePredicate profilePredicate;
    private FindCommandPhonePredicate phonePredicate;
    private FindCommandEmailPredicate emailPredicate;
    private FindCommandAddressPredicate addressPredicate;
    private FindCommandLanguagePredicate languagePredicate;
    private FindCommandTagPredicate tagPredicate;

    /**
     * Constructor for FindCommandPersonPredicate class
     */
    public FindCommandPersonPredicate(List<String> nameKeywords,
                                      List<String> profileKeywords,
                                      List<String> phoneKeywords,
                                      List<String> emailKeywords,
                                      List<String> addressKeywords,
                                      List<String> languageKeywords,
                                      List<String> tagKeywords) {
        this.namePredicate = new FindCommandNamePredicate(nameKeywords);
        this.profilePredicate = new FindCommandProfilePredicate(profileKeywords);
        this.phonePredicate = new FindCommandPhonePredicate(phoneKeywords);
        this.emailPredicate = new FindCommandEmailPredicate(emailKeywords);
        this.addressPredicate = new FindCommandAddressPredicate(addressKeywords);
        this.languagePredicate = new FindCommandLanguagePredicate(languageKeywords);
        this.tagPredicate = new FindCommandTagPredicate(tagKeywords);
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
                || (other instanceof FindCommandPersonPredicate
                && namePredicate.equals(((FindCommandPersonPredicate) other).namePredicate)
                && profilePredicate.equals(((FindCommandPersonPredicate) other).profilePredicate)
                && phonePredicate.equals(((FindCommandPersonPredicate) other).phonePredicate)
                && emailPredicate.equals(((FindCommandPersonPredicate) other).emailPredicate)
                && addressPredicate.equals(((FindCommandPersonPredicate) other).addressPredicate)
                && languagePredicate.equals(((FindCommandPersonPredicate) other).languagePredicate)
                && tagPredicate.equals(((FindCommandPersonPredicate) other).tagPredicate));
    }

}
