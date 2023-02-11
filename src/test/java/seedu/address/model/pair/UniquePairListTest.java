package seedu.address.model.pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPairs.PAIR1;
import static seedu.address.testutil.TypicalPairs.PAIR2;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.pair.exceptions.DuplicatePairException;
import seedu.address.model.pair.exceptions.PairNotFoundException;
import seedu.address.testutil.PairBuilder;

public class UniquePairListTest {

    private final UniquePairList uniquePairList = new UniquePairList();

    @Test
    public void contains_nullPair_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePairList.contains(null));
    }

    @Test
    public void contains_pairNotInList_returnsFalse() {
        assertFalse(uniquePairList.contains(PAIR1));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePairList.add(PAIR1);
        assertTrue(uniquePairList.contains(PAIR1));
    }

    @Test
    public void contains_pairWithSameElderlyAndVolunteer_returnsTrue() {
        uniquePairList.add(PAIR1);
        Pair editedPair1 = new PairBuilder(PAIR1).withElderly(ALICE).withVolunteer(BOB)
                .build();
        assertTrue(uniquePairList.contains(editedPair1));
    }

    @Test
    public void add_nullPair_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePairList.add(null));
    }

    @Test
    public void add_duplicatePair_throwsDuplicatePairException() {
        uniquePairList.add(PAIR1);
        assertThrows(DuplicatePairException.class, () -> uniquePairList.add(PAIR1));
    }

    @Test
    public void setPair_nullTargetPair_throwsNullPointerException() {
        // todo
        //assertThrows(NullPointerException.class, () -> uniquePairList.setPair(null, PAIR1));
    }

    @Test
    public void setPair_nullEditedPair_throwsNullPointerException() {
        // todo
        //assertThrows(NullPointerException.class, () -> uniquePairList.setPair(PAIR1, null));
    }

    @Test
    public void setPair_targetPairNotInList_throwsPairNotFoundException() {
        // todo
        //assertThrows(PairNotFoundException.class, () -> uniquePairList.setPair(ALICE, ALICE));
    }

    @Test
    public void setPair_editedPairIsSamePair_success() {
        // todo
        /*
        uniquePairList.add(PAIR1);
        uniquePairList.setPair(PAIR1, PAIR1);
        UniquePairList expectedUniquePairList = new UniquePairList();
        expectedUniquePairList.add(PAIR1);
        assertEquals(expectedUniquePairList, uniquePairList);
         */
    }

    @Test
    public void setPair_editedPairHasSameIdentity_success() {
        // todo
        /*
        uniquePairList.add(PAIR1);
        Pair editedPair1 = new PairBuilder(PAIR1).build();
        uniquePairList.setPair(PAIR1, editedPair1);
        UniquePairList expectedUniquePairList = new UniquePairList();
        expectedUniquePairList.add(editedPair1);
        assertEquals(expectedUniquePairList, uniquePairList);
         */
    }

    @Test
    public void setPair_editedPairHasDifferentIdentity_success() {
        // todo
        uniquePairList.add(PAIR1);
        uniquePairList.setPair(PAIR1, PAIR2);
        UniquePairList expectedUniquePairList = new UniquePairList();
        expectedUniquePairList.add(PAIR2);
        assertEquals(expectedUniquePairList, uniquePairList);
    }

    @Test
    public void setPair_editedPairHasNonUniqueIdentity_throwsDuplicatePairException() {
        uniquePairList.add(PAIR1);
        uniquePairList.add(PAIR2);
        assertThrows(DuplicatePairException.class, () -> uniquePairList.setPair(PAIR1, PAIR2));
    }

    @Test
    public void remove_nullPair_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePairList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPairNotFoundException() {
        assertThrows(PairNotFoundException.class, () -> uniquePairList.remove(PAIR1));
    }

    @Test
    public void remove_existingPair_removesPair() {
        uniquePairList.add(PAIR1);
        uniquePairList.remove(PAIR1);
        UniquePairList expectedUniquePairList = new UniquePairList();
        assertEquals(expectedUniquePairList, uniquePairList);
    }

    @Test
    public void setPairs_nullUniquePairList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePairList.setPairs((UniquePairList) null));
    }

    @Test
    public void setPairs_uniquePairList_replacesOwnListWithProvidedUniquePairList() {
        uniquePairList.add(PAIR1);
        UniquePairList expectedUniquePairList = new UniquePairList();
        expectedUniquePairList.add(PAIR2);
        uniquePairList.setPairs(expectedUniquePairList);
        assertEquals(expectedUniquePairList, uniquePairList);
    }

    @Test
    public void setPairs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePairList.setPairs((List<Pair>) null));
    }

    @Test
    public void setPairs_list_replacesOwnListWithProvidedList() {
        // todo
        /*
        uniquePairList.add(PAIR1);
        List<Pair> pairList = Collections.singletonList(PAIR2);
        uniquePairList.setPairs(pairList);
        UniquePairList expectedUniquePairList = new UniquePairList();
        expectedUniquePairList.add(PAIR2);
        assertEquals(expectedUniquePairList, uniquePairList);
        */
    }

    @Test
    public void setPairs_listWithDuplicatePairs_throwsDuplicatePairException() {
        List<Pair> listWithDuplicatePairs = Arrays.asList(PAIR1, PAIR1);
        assertThrows(DuplicatePairException.class, () -> uniquePairList.setPairs(listWithDuplicatePairs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePairList.asUnmodifiableObservableList().remove(0));
    }
}
