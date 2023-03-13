package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {
    public static final Group FRIENDS_GROUP = new Group("Friends");
    public static final Group CS2103_GROUP = new Group("CS2103");
    public static final Person CS2103_MEMBER = new PersonBuilder().withGroups("CS2103").build();

    private TypicalGroups() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical groups.
     */
    public static AddressBook getTypicalAddressBookWithEmptyGroups() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroups()) {
            ab.addGroup(group);
        }
        return ab;
    }

    public static AddressBook getTypicalAddressBookWithSingleMember() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroups()) {
            ab.addGroup(group);
        }
        ab.addPerson(CS2103_MEMBER);
        return ab;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(FRIENDS_GROUP, CS2103_GROUP));
    }
}
