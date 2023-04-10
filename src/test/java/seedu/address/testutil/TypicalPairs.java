package seedu.address.testutil;

import static seedu.address.testutil.TypicalElderly.ALICE;
import static seedu.address.testutil.TypicalElderly.BENSON;
import static seedu.address.testutil.TypicalElderly.CARL;
import static seedu.address.testutil.TypicalElderly.FIONA;
import static seedu.address.testutil.TypicalVolunteers.DANIEL;
import static seedu.address.testutil.TypicalVolunteers.ELLE;
import static seedu.address.testutil.TypicalVolunteers.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.pair.Pair;

/**
 * A utility class containing a list of {@code Pair} objects to be used in tests.
 */
public class TypicalPairs {

    public static final Pair PAIR1 = new PairBuilder().withElderly(ALICE)
            .withVolunteer(ELLE).build();
    public static final Pair PAIR2 = new PairBuilder().withElderly(CARL)
            .withVolunteer(DANIEL).build();
    public static final Pair PAIR3 = new PairBuilder().withElderly(BENSON)
            .withVolunteer(ELLE).build();
    public static final Pair PAIR4 = new PairBuilder().withElderly(FIONA)
            .withVolunteer(GEORGE).build();

    private TypicalPairs() {} // prevents instantiation

    public static List<Pair> getTypicalPairs() {
        return new ArrayList<>(Arrays.asList(PAIR1, PAIR2, PAIR3));
    }
}
