package seedu.address.testutil;

import static seedu.address.testutil.TypicalElderlys.AMY;
import static seedu.address.testutil.TypicalElderlys.BOB;
import static seedu.address.testutil.TypicalElderlys.CHARLIE;
import static seedu.address.testutil.TypicalVolunteers.DANIEL;
import static seedu.address.testutil.TypicalVolunteers.ELLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.FriendlyLink;
import seedu.address.model.pair.Pair;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPairs {

    public static final Pair PAIR1 = new PairBuilder().withElderly(AMY)
            .withVolunteer(ELLE).build();
    public static final Pair PAIR2 = new PairBuilder().withElderly(CHARLIE)
            .withVolunteer(DANIEL).build();
    public static final Pair PAIR3 = new PairBuilder().withElderly(BOB)
            .withVolunteer(ELLE).build();
    private TypicalPairs() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical pairs.
     */
    public static FriendlyLink getTypicalFriendlyLink() {
        FriendlyLink fl = new FriendlyLink();
        for (Pair pair : getTypicalPairs()) {
            fl.addPair(pair);
        }
        return fl;
    }

    public static List<Pair> getTypicalPairs() {
        return new ArrayList<>(Arrays.asList(PAIR1, PAIR2, PAIR3));
    }
}
