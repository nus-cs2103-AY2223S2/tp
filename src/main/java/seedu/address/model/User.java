package seedu.address.model;

import seedu.address.model.person.Person;

public class User {
    public final Person user;

    public User() {
        this.user = new Person();
    }
}
