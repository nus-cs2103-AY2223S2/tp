package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Gender {
    public static final String MESSAGE_CONSTRAINTS =
            "Gender can indicated either by Male, Female or others";

    public enum types {
        MALE,
        FEMALE,
        OTHERS
    }

    public final String gender;

    /**
     * Constructs a {@code gender}.
     *
     * @param gender A given gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.gender = gender;
    }

    public static boolean isValidGender(String gender) {
        switch (gender.toLowerCase()) {
            case "male":
            case "female":
            case "others":
                return true;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return gender;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && gender.equals(((Gender) other).gender)); // state check
    }

}
