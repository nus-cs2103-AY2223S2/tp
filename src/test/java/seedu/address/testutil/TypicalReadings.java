package seedu.address.testutil;

import static seedu.address.testutil.TypicalTanks.getTypicalTanks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.tank.readings.FullReadingLevels;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;

/**
 * A utility class containing a list of {@code FullReadingLevels} objects to be used in tests.
 */
public class TypicalReadings {
    /**
     * Returns an {@code FullReadingLevels} with all the typical Individual Reading levels
     */
    public static FullReadingLevels getTypicalFullReadingLevels() {
        FullReadingLevels readings = new FullReadingLevels();
        for (UniqueIndividualReadingLevels r : getTypicalIndividualReadings()) {
            readings.addIndividualReadingLevel(r);
        }
        return readings;
    }

    public static List<UniqueIndividualReadingLevels> getTypicalIndividualReadings() {
        UniqueIndividualReadingLevels r1 = new UniqueIndividualReadingLevels();
        r1.setTank(getTypicalTanks().get(0));
        UniqueIndividualReadingLevels r2 = new UniqueIndividualReadingLevels();
        r2.setTank(getTypicalTanks().get(1));
        return new ArrayList<>(Arrays.asList(r1, r2));
    }
}
