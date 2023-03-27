package seedu.address.model.person;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;


/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {

    public static final String NAMEKEY = "name";
    public static final String ADDRESSKEY = "address";
    public static final String COMMSKEY = "comms";
    public static final String EMAILKEY = "email";
    public static final String GENDERKEY = "gender";
    public static final String MAJORKEY = "major";
    public static final String MODULESKEY = "mods";
    public static final String PHONEKEY = "phone";
    public static final String RACEKEY = "race";
    public static final String TAGKEY = "tag";
    public static final String FACULTYKEY = "faculty";

    private final HashMap<PredicateKey, Set<String>> keywords;


    public PersonContainsKeywordsPredicate() {
        this.keywords = new HashMap<>();
    }

    /**
     * Stores the field in the predicate.
     */
    public PersonContainsKeywordsPredicate withField(PredicateKey key, Collection<String> keywords) {
        this.keywords.putIfAbsent(key, new HashSet<>());
        this.keywords.get(key).addAll(keywords);
        return this;
    }

    @Override
    public boolean test(Person person) {
        return person.contains(this.keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }

}
