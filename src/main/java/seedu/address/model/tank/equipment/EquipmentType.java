package seedu.address.model.tank.equipment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Equipment's type in the {@code Equipment}.
 * Guarantees: immutable; is valid as declared in {@link #isValidEquipmentType(String)}
 */
public class EquipmentType {
    public static final String MESSAGE_CONSTRAINTS =
            "Type should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String equipmentType;

    /**
     * Constructs a {@code EquipmentType}.
     *
     * @param equipmentType A valid equipment type.
     */
    public EquipmentType(String equipmentType) {
        requireNonNull(equipmentType);
        checkArgument(isValidEquipmentType(equipmentType), MESSAGE_CONSTRAINTS);
        this.equipmentType = equipmentType;
    }

    public boolean isValidEquipmentType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return equipmentType;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EquipmentType // instanceof handles nulls
                && equipmentType.equals(((EquipmentType) other).equipmentType)); // state check
    }

    @Override
    public int hashCode() {
        return equipmentType.hashCode();
    }
}
