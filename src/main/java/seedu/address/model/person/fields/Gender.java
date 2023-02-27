package seedu.address.model.person.fields;

import seedu.address.model.person.fields.enums.Genders;

import java.util.Locale;
import java.util.Objects;

public class Gender {

    public static final String MESSAGE_CONSTRAINTS = "Gender must be either: " +
            "Male, Female or DNS (i.e. Did not specify)";
    public Genders gender;

    public Gender(String gender) {
        switch (gender.toLowerCase(Locale.ROOT)) {
        case "male":
            this.gender = Genders.MALE;
            break;
        case "female":
            this.gender = Genders.FEMALE;
            break;
        case "dns":
        case "":
            this.gender = Genders.DNS;
            break;
        default:
            throw new IllegalArgumentException(Gender.MESSAGE_CONSTRAINTS + " " + gender);
        }
    }

    public static boolean isValidGender(String trimmedGender) {
        String lowerCaseGender = trimmedGender.toLowerCase(Locale.ROOT);
        return Objects.equals(lowerCaseGender, "male") || Objects.equals(lowerCaseGender, "female") ||
                Objects.equals(lowerCaseGender, "dns") || Objects.equals(lowerCaseGender, "");
    }

    @Override
    public String toString() {
        return gender.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && this.gender.equals(((Gender) other).gender)); // state check
    }

    @Override
    public int hashCode() {
        return this.gender.hashCode();
    }

}
