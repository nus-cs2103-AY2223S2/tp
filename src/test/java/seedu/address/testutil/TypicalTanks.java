package seedu.address.testutil;

import seedu.address.model.TankList;
import seedu.address.model.tank.Tank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Tank} objects to be used in tests.
 */
public class TypicalTanks {
    public static final Tank TANK_A = new TankBuilder().withTankName("Tank A").build();
    public static final Tank TANK_B = new TankBuilder().withTankName("Tank B").build();
    public static final Tank TANK_C = new TankBuilder().withTankName("Tank C").build();

    //To be added later in test class JsonTankListStorageTest
    public static final Tank TANK_D = new TankBuilder().withTankName("Tank D").build();
    public static final Tank TANK_E = new TankBuilder().withTankName("Tank E").build();

    private TypicalTanks() {} // prevents instantiation

    /**
     * Returns an {@code TankList} with all the typical Fishes.
     */
    public static TankList getTypicalTankList() {
        TankList tl = new TankList();
        for (Tank tank : getTypicalTanks()) {
            tl.addTank(tank);
        }
        return tl;
    }

    public static List<Tank> getTypicalTanks() {
        return new ArrayList<>(Arrays.asList(TANK_A, TANK_B, TANK_C));
    }
}
