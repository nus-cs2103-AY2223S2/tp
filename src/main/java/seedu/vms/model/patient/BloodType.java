package seedu.vms.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.vms.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's bloodType in VMS.
 * Guarantees: immutable; is valid as declared in {@link #isValidBloodType(String)}
 */
public class BloodType {
    /**
     * Blood type enum to ensure only valid blood types are recorded.
     * It cannot be saved as "O-" as trailing symbols are not valid var names.
     */
    public enum BloodTypes {
        oMinus("O-"),
        oPlus("O+"),
        aMinus("A-"),
        aPlus("A+"),
        bMinus("B-"),
        bPlus("B+"),
        abMinus("AB-"),
        abPlus("AB+");

        private final String representation;

        BloodTypes(String representation) {
            this.representation = representation;
        }

        public String getRepresentation() {
            return representation;
        }

        /**
         * Turns a String into a enum type
         * @param representation of what the user will view blood type as.
         * @return Enum value of the matched string representation
         */
        public static BloodTypes fromRepresentation(String representation) {
            for (BloodTypes i : BloodTypes.values()) {
                if (i.representation.equals(representation.toUpperCase())) {
                    return i;
                }
            }
            throw new IllegalArgumentException("Bloodtype " + BloodTypes.class.getCanonicalName()
                    + " with representation " + representation + " found");
        }
    }

    public static final String MESSAGE_CONSTRAINTS = "BloodType can only be A+, A-, B+, B-, AB+, AB-, O+, O-";

    /*
     * The first character of the bloodType must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */

    public final BloodTypes value;

    /**
     * Constructs an {@code BloodType}.
     *
     * @param bloodType A valid bloodType.
     */
    public BloodType(String bloodType) {
        requireNonNull(bloodType);
        checkArgument(isValidBloodType(bloodType), MESSAGE_CONSTRAINTS);
        value = BloodTypes.fromRepresentation(bloodType);
    }

    /**
     * Returns true if a given string is a valid blood type.
     */
    public static boolean isValidBloodType(String test) {
        try {
            BloodTypes.fromRepresentation(test);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value.getRepresentation();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BloodType // instanceof handles nulls
                        && value.equals(((BloodType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
