package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Now temporarily make this Elderly, because NRIC not put in Person yet
 * Later this should actually be Predicate Person
 */
public class NricMatchesKeywordsPredicate implements Predicate<Elderly> {
    private final String keywords;

    public NricMatchesKeywordsPredicate(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Elderly elderly) {
        String nric = elderly.getNric().toString();
        return this.keywords.toLowerCase().equals(nric.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof NricMatchesKeywordsPredicate
                && keywords.equals(((NricMatchesKeywordsPredicate) other).keywords));
    }
}
