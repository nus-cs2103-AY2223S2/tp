package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyTankList;
import seedu.address.model.TankList;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;

/**
 * Contains utility methods for populating {@code Tanklist}! with sample data.
 */
public class SampleTankUtil {
    public static Tank[] getSampleTanks() {
        return new Tank[] {
            new Tank(new TankName("freshwater tank"), new AddressBook()),
            new Tank(new TankName("saltwater tank"), new AddressBook())
        };
    }

    public static ReadOnlyTankList getSampleTankList() {
        TankList sampleTankList = new TankList();
        for (Tank sampleTank : getSampleTanks()) {
            sampleTankList.addTank(sampleTank);
        }
        return sampleTankList;
    }
}
