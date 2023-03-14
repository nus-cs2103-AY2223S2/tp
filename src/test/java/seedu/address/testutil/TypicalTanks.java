package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.TankList;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;

/**
 * A utility class containing a list of {@code Tank} objects to be used in tests.
 */
public class TypicalTanks {

    private TypicalTanks() {} // prevents instantiation

    /**
     * Returns an {@code TankList} with all the typical tasks.
     */
    public static TankList getTypicalTankList() {
        TankList tl = new TankList();
        for (Tank task : getTypicalTanks()) {
            tl.addTank(task);
        }
        return tl;
    }

    public static List<Tank> getTypicalTanks() {
        Tank tankOne = new Tank(new TankName("Saltwater Tank 1"), new AddressBook());
        Tank tankTwo = new Tank(new TankName("Freshwater Tank 3"), new AddressBook());
        return new ArrayList<>(Arrays.asList(tankOne, tankTwo));
    }
}
