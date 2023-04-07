package seedu.address.testutil;

import static seedu.address.testutil.TypicalEntities.RIZZ;
import static seedu.address.testutil.TypicalEntities.SPOON;

import java.util.Arrays;

import seedu.address.model.entity.Inventory;
import seedu.address.model.entity.Stats;

/**
 * A utility class containing fields for Entity to use.
 */
public class TypicalFields {

    public static final Inventory BIG_INVENTORY = new Inventory(Arrays.asList(RIZZ, SPOON));
    public static final Inventory SMALL_INVENTORY = new Inventory(Arrays.asList(SPOON));
    public static final Stats ORC_STATS = new Stats(15, 6, 1);
    public static final Stats ELF_STATS = new Stats(6, 10, 10);
    public static final Stats HUMAN_STATS = new Stats(7, 9, 9);

}
