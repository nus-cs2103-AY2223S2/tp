package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TankList;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;

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

    //Other tanks
    public static final Tank TANK_ONE = new Tank(new TankName("Saltwater Tank 1"));
    public static final Tank TANK_TWO = new Tank(new TankName("Freshwater Tank 3"));

    private TypicalTanks() {} // prevents instantiation

    /**
     * Returns an {@code TankList} with all the typical tanks.
     */
    public static TankList getTypicalTankList() {
        TankList tl = new TankList();
        for (Tank task : getTypicalTanks()) {
            tl.addTank(task);
        }
        return tl;
    }

    public static List<Tank> getTypicalTanks() {
        return new ArrayList<>(Arrays.asList(TANK_ONE, TANK_TWO));
    }

    /**
     * Returns an {@code TankList} with all the typical tanks (version 2).
     * Used in JsonSerializableTankListTest and JsonTankListStorageTest
     */
    public static TankList getTypicalTankListVersionTwo() {
        TankList tl = new TankList();
        for (Tank task : getTypicalTanksVersionTwo()) {
            tl.addTank(task);
        }
        return tl;
    }
    public static List<Tank> getTypicalTanksVersionTwo() {
        return new ArrayList<>(Arrays.asList(TANK_A, TANK_B, TANK_C));
    }
}
