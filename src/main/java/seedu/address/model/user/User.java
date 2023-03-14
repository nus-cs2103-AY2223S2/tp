package seedu.address.model.user;


import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.subfields.Tag;


/**
 * Store all relevant information about the User.
 */
public class User extends Person {

    private Person user;
    //todo: Add event list!

    public User(Name name, Phone phone, Email email, Address address, Gender gender,
                Major major, Modules modules, Race race, Set<Tag> tags, CommunicationChannel comms,
                Favorite favorite) {
        super(name, phone, email, address, gender, major, modules, race, tags, comms);
    }

}

