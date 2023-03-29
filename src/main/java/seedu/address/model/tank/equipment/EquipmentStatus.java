package seedu.address.model.tank.equipment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Equipment's status in the {@code Equipment}.
 * Guarantees: immutable; is valid as declared in {@link #isValidEquipmentStatus(String)}
 */
public class EquipmentStatus {
    public static final String MESSAGE_CONSTRAINTS =
            "Equipment status should be either: faulty or working";

    public final String equipmentStatus;

    /**
     * Constructs a {@code EquipmentStatus}.
     *
     * @param equipmentStatus A valid equipment status.
     */
    public EquipmentStatus(String equipmentStatus) {
        requireNonNull(equipmentStatus);
        checkArgument(isValidEquipmentStatus(equipmentStatus), MESSAGE_CONSTRAINTS);
        this.equipmentStatus = equipmentStatus;
    }

    public boolean isValidEquipmentStatus(String test) {
        return test.equals("faulty") || test.equals("working");
    }

    @Override
    public String toString() {
        return equipmentStatus;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EquipmentStatus // instanceof handles nulls
                && equipmentStatus.equals(((EquipmentStatus) other).equipmentStatus)); // state check
    }

    @Override
    public int hashCode() {
        return equipmentStatus.hashCode();
    }
}
