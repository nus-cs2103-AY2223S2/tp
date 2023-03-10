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

    public static final Tank TASK_ONE = new Tank(new TankName("Saltwater Tank 1"), new AddressBook());
    public static final Tank TASK_TWO = new Tank(new TankName("Freshwater Tank 3"), new AddressBook());

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
        return new ArrayList<>(Arrays.asList(TASK_ONE, TASK_TWO));
    }
}
