package seedu.address.model.tank;

import seedu.address.model.AddressBook;

/**
 * Default tank to be assigned to a {@code Fish} when it is created in
 * AddCommandParser. Main difference is this tank is equal to any other tank
 * so in the case where all the attributes of fish A (except Tank), the user wants to add is
 * equal to Fish B that already exists, it is considered as a duplicate
 */
public class UnassignedTank extends Tank {
    public UnassignedTank(TankName tankName, AddressBook fishList) {
        super(tankName, fishList);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.address.model.tank.Tank)) {
            return false;
        }
        //Other is a tank
        return true;
    }
}
