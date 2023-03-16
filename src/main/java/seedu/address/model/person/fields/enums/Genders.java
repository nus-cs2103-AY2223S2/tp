package seedu.address.model.person.fields.enums;

/**
 * Contains list of general genders to be specified in the addressbook.
 */
public enum Genders {

    MALE("male"),
    FEMALE("female"),
    DNS("dns");

    public final String gender;

    Genders(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.gender;
    }

}
