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

    private final HashMap<String, Set<String>> keywords;


    public PersonContainsKeywordsPredicate() {
        this.keywords = new HashMap<>();
    }

    /**
     * Stores the name in the predicate.
     */
    public PersonContainsKeywordsPredicate withName(Collection<String> name) {
        if (!this.keywords.containsKey(NAMEKEY)) {
            this.keywords.put(NAMEKEY, new HashSet<>());
        }
        this.keywords.get(NAMEKEY).addAll(name);
        return this;
    }
    /**
     * Stores the comms in the predicate.
     */
    public PersonContainsKeywordsPredicate withComms(Collection<String> comms) {
        if (!this.keywords.containsKey(COMMSKEY)) {
            this.keywords.put(COMMSKEY, new HashSet<>());
        }
        this.keywords.get(COMMSKEY).addAll(comms);
        return this;
    }
    /**
     * Stores the address in the predicate.
     */
    public PersonContainsKeywordsPredicate withAddress(Collection<String> address) {
        if (!this.keywords.containsKey(ADDRESSKEY)) {
            this.keywords.put(ADDRESSKEY, new HashSet<>());
        }
        this.keywords.get(ADDRESSKEY).addAll(address);
        return this;
    }
    /**
     * Stores the email in the predicate.
     */
    public PersonContainsKeywordsPredicate withEmail(Collection<String> email) {
        if (!this.keywords.containsKey(EMAILKEY)) {
            this.keywords.put(EMAILKEY, new HashSet<>());
        }
        this.keywords.get(EMAILKEY).addAll(email);
        return this;
    }
    /**
     * Stores the gender in the predicate.
     */
    public PersonContainsKeywordsPredicate withGender(Collection<String> gender) {
        if (!this.keywords.containsKey(GENDERKEY)) {
            this.keywords.put(GENDERKEY, new HashSet<>());
        }
        this.keywords.get(GENDERKEY).addAll(gender);
        return this;
    }
    /**
     * Stores the major in the predicate.
     */
    public PersonContainsKeywordsPredicate withMajor(Collection<String> major) {
        if (!this.keywords.containsKey(MAJORKEY)) {
            this.keywords.put(MAJORKEY, new HashSet<>());
        }
        this.keywords.get(MAJORKEY).addAll(major);
        return this;
    }
    /**
     * Stores the modules in the predicate.
     */
    public PersonContainsKeywordsPredicate withModules(Collection<String> mod) {
        if (!this.keywords.containsKey(MODULESKEY)) {
            this.keywords.put(MODULESKEY, new HashSet<>());
        }
        this.keywords.get(MODULESKEY).addAll(mod);
        return this;
    }
    /**
     * Stores the phone in the predicate.
     */
    public PersonContainsKeywordsPredicate withPhone(Collection<String> phone) {
        if (!this.keywords.containsKey(PHONEKEY)) {
            this.keywords.put(PHONEKEY, new HashSet<>());
        }
        this.keywords.get(PHONEKEY).addAll(phone);
        return this;
    }
    /**
     * Stores the race in the predicate.
     */
    public PersonContainsKeywordsPredicate withRace(Collection<String> race) {
        if (!this.keywords.containsKey(RACEKEY)) {
            this.keywords.put(RACEKEY, new HashSet<>());
        }
        this.keywords.get(RACEKEY).addAll(race);
        return this;
    }
    /**
     * Stores the tag in the predicate.
     */
    public PersonContainsKeywordsPredicate withTag(Collection<String> tag) {
        if (!this.keywords.containsKey(TAGKEY)) {
            this.keywords.put(TAGKEY, new HashSet<>());
        }
        this.keywords.get(TAGKEY).addAll(tag);
        return this;
    }

    /**
     * Stores the faculty in the predicate.
     */
    public PersonContainsKeywordsPredicate withFaculty(Collection<String> faculties) {
        if (!this.keywords.containsKey(FACULTYKEY)) {
            this.keywords.put(FACULTYKEY, new HashSet<>());
        }
        this.keywords.get(FACULTYKEY).addAll(faculties);
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
