package seedu.address.model.person;

import java.util.HashMap;
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

    private final HashMap<String, String> keywords;


    public PersonContainsKeywordsPredicate() {
        this.keywords = new HashMap<>();
    }

    public void withName(String name) {
        this.keywords.put(NAMEKEY, name);
    }
    public void withComms(String comms) {
        this.keywords.put(COMMSKEY, comms);
    }
    public void withAddress(String address) {
        this.keywords.put(ADDRESSKEY, address);
    }
    public void withEmail(String email) {
        this.keywords.put(EMAILKEY, email);
    }
    public void withGender(String gender) {
        this.keywords.put(GENDERKEY, gender);
    }
    public void withMajor(String major) {
        this.keywords.put(MAJORKEY, major);
    }
    public void withModules(String mod) {
        this.keywords.put(MODULESKEY, mod);
    }
    public void withPhone(String phone) {
        this.keywords.put(PHONEKEY, phone);
    }
    public void withRace(String race) {
        this.keywords.put(RACEKEY, race);
    }
    public void withTag(String tag) {
        this.keywords.put(TAGKEY, tag);
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
