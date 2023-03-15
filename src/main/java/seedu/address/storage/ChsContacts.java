package seedu.address.storage;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Contains contacts from SOC to be imported
 */
public class ChsContacts {
    private static final Person benLeong = new Person(new Name("Chng Shu Sin"), new Phone("99999999"), new Email("abcd@nus.u.edu"),
            new Address("1234 Street"), Collections.<Tag>emptySet());

    private static final Person stevenHalim = new Person(new Name("Chew Fook Tim"), new Phone("99999999"), new Email("abcd@nus.u.edu"),
            new Address("1234 Street"), Collections.<Tag>emptySet());

    public static final ArrayList<Person> CHS_CONTACTS = new ArrayList<Person>(
            Arrays.asList(benLeong, stevenHalim));
}