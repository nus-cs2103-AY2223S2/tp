package seedu.address.model.tank.equipment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Equipment's name in the {@code Equipment}.
 * Guarantees: immutable; is valid as declared in {@link #isValidEquipmentName(String)}
 */
public class EquipmentName {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String equipmentName;

    /**
     * Constructs a {@code EquipmentName}.
     *
     * @param equipmentName A valid equipment name.
     */
    public EquipmentName(String equipmentName) {
        requireNonNull(equipmentName);
        checkArgument(isValidEquipmentName(equipmentName), MESSAGE_CONSTRAINTS);
        this.equipmentName = equipmentName;
    }

    public boolean isValidEquipmentName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return equipmentName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EquipmentName // instanceof handles nulls
                && equipmentName.equals(((EquipmentName) other).equipmentName)); // state check
    }

    @Override
    public int hashCode() {
        return equipmentName.hashCode();
    }
}
