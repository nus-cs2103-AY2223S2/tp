package seedu.address.model.util;

import seedu.address.model.tank.Tank;
import seedu.address.model.tank.readings.FullReadingLevels;
import seedu.address.model.tank.readings.ReadOnlyReadingLevels;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;

/**
 * Contains utility methods for populating {@code FullReadingLevels}! with sample data.
 */
public class SampleReadingsUtil {
    public static UniqueIndividualReadingLevels[] getSampleIndividualReadings() {
        //There are 2 sample tanks, so 1 individual readings for each tank
        Tank[] sampleTanks = SampleTankUtil.getSampleTanks();
        UniqueIndividualReadingLevels[] ret = new UniqueIndividualReadingLevels[] {
            new UniqueIndividualReadingLevels(),
            new UniqueIndividualReadingLevels()
        };
        ret[0].setTank(sampleTanks[0]);
        ret[1].setTank(sampleTanks[1]);
        return ret;
    }

    public static ReadOnlyReadingLevels getSampleFullReadingLevels() {
        FullReadingLevels sampleFullReadings = new FullReadingLevels();
        for (UniqueIndividualReadingLevels r : getSampleIndividualReadings()) {
            sampleFullReadings.addIndividualReadingLevel(r);
        }
        return sampleFullReadings;
    }
}
