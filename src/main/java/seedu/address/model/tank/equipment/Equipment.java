package seedu.address.model.tank.equipment;

import seedu.address.model.tank.Tank;

/**
 *  Represents an Equipment in a tank.
 */
public class Equipment {
    private EquipmentName name;
    private EquipmentType type;
    private EquipmentStatus status;
    private LastMaintainedDate lastMaintainedDate;
    private Tank tank;

    /**
     * Creates an Equipment.
     * @param name Name of equipment.
     * @param type Type of equipment.
     * @param status Status of equipment.
     * @param lastMaintainedDate Last maintained date of equipment.
     * @param tank tank equipment belongs to
     */
    public Equipment(EquipmentName name, EquipmentType type, EquipmentStatus status,
                     LastMaintainedDate lastMaintainedDate, Tank tank) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.lastMaintainedDate = lastMaintainedDate;
        this.tank = tank;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.address.model.tank.equipment.Equipment)) {
            return false;
        }

        seedu.address.model.tank.equipment.Equipment otherEquipment =
                (seedu.address.model.tank.equipment.Equipment) other;
        return otherEquipment.equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return this.name.equipmentName;
    }
}
