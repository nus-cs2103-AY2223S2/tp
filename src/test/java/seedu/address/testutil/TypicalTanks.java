package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.TankList;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;

/**
 * A utility class containing a list of {@code Tank} objects to be used in tests.
 */
public class TypicalTanks {
    public static final Tank TANK_A = new TankBuilder().withTankName("Tank A").build();
    public static final Tank TANK_B = new TankBuilder().withTankName("Tank B").build();
    public static final Tank TANK_C = new TankBuilder().withTankName("Tank C").build();
    public static final String TYPICAL_TANK_1_STRING = "Saltwater Tank 1";
    public static final String TYPICAL_TANK_2_STRING = "Freshwater Tank 3";
    //To be added later in test class JsonTankListStorageTest
    public static final Tank TANK_D = new TankBuilder().withTankName("Tank D").build();
    public static final Tank TANK_E = new TankBuilder().withTankName("Tank E").build();

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
        Tank tankOne = new Tank(new TankName(TYPICAL_TANK_1_STRING), new AddressBook(),
                new UniqueIndividualReadingLevels());
        Tank tankTwo = new Tank(new TankName(TYPICAL_TANK_2_STRING), new AddressBook(),
                new UniqueIndividualReadingLevels());
        return new ArrayList<>(Arrays.asList(tankOne, tankTwo));
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
