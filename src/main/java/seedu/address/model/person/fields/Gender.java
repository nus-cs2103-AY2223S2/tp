package seedu.address.model.person.fields;

import seedu.address.model.person.fields.enums.Genders;

import java.util.Objects;

public class Gender {

    public static final String MESSAGE_CONSTRAINTS = "";
    public Genders gender;

    public Gender(Genders gender) {
        this.gender = gender;
    }

    public static boolean isValidGender(String trimmedGender) {
        return Objects.equals(trimmedGender, "male") || Objects.equals(trimmedGender, "female") ||
                Objects.equals(trimmedGender, "dns");
    }
}
