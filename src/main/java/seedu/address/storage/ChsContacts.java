package seedu.address.storage;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.*;

/**
 * Contains contacts from SOC to be imported
 */
public class ChsContacts {
    private static final Person chngShuSin = new Person(new Name("Chng Shu Sin"), new Phone("99999999"), new Email("abcd@nus.u.edu"),
            new Address("1234 Street"), new HashSet<Tag>(List.of(new Tag("CHS"), new Tag("Professor"))), new Image());

    private static final Person chewFookTim = new Person(new Name("Chew Fook Tim"), new Phone("99999999"), new Email("abcd@nus.u.edu"),
            new Address("1234 Street"), new HashSet<Tag>(List.of(new Tag("CHS"), new Tag("Professor"))), new Image());

    public static final ArrayList<Person> CHS_CONTACTS = new ArrayList<Person>(
            Arrays.asList(chngShuSin, chewFookTim));
}