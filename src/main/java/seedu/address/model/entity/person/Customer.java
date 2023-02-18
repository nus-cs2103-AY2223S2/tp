package seedu.address.model.entity.person;

import seedu.address.model.Vehicle;
import seedu.address.model.tag.Tag;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

public class Customer extends Person {

    private static int incrementalId = 0;
    private int id;
    public ArrayList<Vehicle> vehicles;

    // Service History

    /**
     * {@inheritDoc}
     */
    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        id = ++incrementalId;
    }
}
