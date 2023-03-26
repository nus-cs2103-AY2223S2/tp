package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.experimental.model.ReadOnlyReroll;
import seedu.address.experimental.model.Reroll;
import seedu.address.model.entity.Entity;

/**
 * A utility class containing a list of {@code Entity} objects to be used in tests.
 */
public class TypicalEntities {

    public static final Entity LEEROY = new EntityBuilder().withName("Leeroy Jenkins").buildChar();
    public static final Entity SPOON = new EntityBuilder().withName("Large Spoon").buildItem();
    public static final Entity CARL = new EntityBuilder().withName("Carl Sagan").buildChar();
    public static final Entity RAT = new EntityBuilder().withName("The Rat").buildMob();
    public static final Entity IMPSOSTER = new EntityBuilder().withName("Impsoster").buildMob();
    public static final Entity RIZZ = new EntityBuilder().withName("Rizz").buildItem();

    // Manually added - Entity's details found in {@code CommandTestUtil}
    public static final Entity AMY = new EntityBuilder().withName(VALID_NAME_AMY).buildChar();
    public static final Entity BOB = new EntityBuilder().withName(VALID_NAME_BOB)
                                                        .buildChar();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEntities() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical entities.
     */
    public static ReadOnlyReroll getTypicalReroll() {
        Reroll ab = new Reroll();
        for (Entity entity : getTypicalEntities()) {
            ab.addEntity(entity);
        }
        return ab;
    }

    public static List<Entity> getTypicalEntities() {
        return new ArrayList<>(Arrays.asList(LEEROY, SPOON, CARL, RAT, IMPSOSTER, RIZZ));
    }
}
