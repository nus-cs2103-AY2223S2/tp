package seedu.address.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

import seedu.address.model.tag.Tag;

/**
 * Contains contacts from SOC to be imported
 */
public class SocContacts {
    private static final Person benLeong = new Person(new Name("Ben Leong"),
            new Phone("99999999"), new Email("abcd@nus.u.edu"),
            new Address("1234 Street"), new HashSet<Tag>(List.of(new Tag("SOC"), new Tag("Professor"))), new Image());

    private static final Person stevenHalim = new Person(new Name("Steven Halim"),
            new Phone("99999999"), new Email("abcd@nus.u.edu"),
            new Address("1234 Street"), new HashSet<Tag>(List.of(new Tag("SOC"), new Tag("Professor"))), new Image());

    public static final ArrayList<Person> SOC_CONTACTS = new ArrayList<Person>(
            Arrays.asList(benLeong, stevenHalim));
}
