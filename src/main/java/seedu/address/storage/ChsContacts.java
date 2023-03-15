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
 * Contains contacts from CHS to be imported
 */
public class ChsContacts {
    private static final Person chngShuSin = new Person(new Name("Chng Shu Sin"),
            new Phone("99999999"), new Email("abcd@nus.u.edu"),
            new Address("1234 Street"), new HashSet<Tag>(List.of(new Tag("CHS"), new Tag("Professor"))), new Image());

    private static final Person chewFookTim = new Person(new Name("Chew Fook Tim"),
            new Phone("99999999"), new Email("abcd@nus.u.edu"),
            new Address("1234 Street"), new HashSet<Tag>(List.of(new Tag("CHS"), new Tag("Professor"))), new Image());

    public static final ArrayList<Person> CHS_CONTACTS = new ArrayList<Person>(
            Arrays.asList(chngShuSin, chewFookTim));
}
