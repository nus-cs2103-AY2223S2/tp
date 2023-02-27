package seedu.address.model.person.fields.enums;

public enum Genders {

    MALE("male"),
    FEMALE("female"),
    DNS("dns");

    private final String gender;

    Genders(String gender) {
        this.gender = gender;
    }

}
