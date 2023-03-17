package seedu.address.model.person.fields;

import static java.util.Objects.requireNonNull;

import java.util.Locale;
import java.util.Objects;

import seedu.address.model.person.fields.enums.Genders;

/**
 * Represents a Person's gender in the address book.
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS = "Gender must be either: "
            + "Male, Female or DNS (i.e. Did not specify)";
    public final Genders gender;


    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid name.
     */
    public Gender(String gender) {
        requireNonNull(gender);
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

    /**
     * Returns if a given string is a valid gender.
     */
    public static boolean isValidGender(String trimmedGender) {
        String lowerCaseGender = trimmedGender.toLowerCase(Locale.ROOT);
        return Objects.equals(lowerCaseGender, "male") || Objects.equals(lowerCaseGender, "female")
                || Objects.equals(lowerCaseGender, "dns") || Objects.equals(lowerCaseGender, "");
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
