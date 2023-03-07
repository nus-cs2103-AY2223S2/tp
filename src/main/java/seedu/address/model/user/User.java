package seedu.address.model.user;


import java.util.Set;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.*;
import seedu.address.model.tag.Tag;


/**
 * Store all relevant information about the User.
 */
public class User extends Person {

    private Person user;
    // todo: Add Event schedule


    public User(Name name, Phone phone, Email email, Address address, Gender gender,
                Major major, Modules modules, Race race, Set<Tag> tags, CommunicationChannel comms,
                Favorite favorite) {
        super(name, phone, email, address, gender, major, modules, race, tags, comms);
    }

    public User() {
        super();
    }

}
