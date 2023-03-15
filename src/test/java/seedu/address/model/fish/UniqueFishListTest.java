package seedu.address.model.fish;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEEDING_INTERVAL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFishes.ALICE;
import static seedu.address.testutil.TypicalFishes.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.fish.exceptions.DuplicateFishException;
import seedu.address.model.fish.exceptions.FishNotFoundException;
import seedu.address.testutil.FishBuilder;

public class UniqueFishListTest {

    private final UniqueFishList uniqueFishList = new UniqueFishList();

    @Test
    public void contains_nullFish_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFishList.contains(null));
    }

    @Test
    public void contains_fishNotInList_returnsFalse() {
        assertFalse(uniqueFishList.contains(ALICE));
    }

    @Test
    public void contains_fishInList_returnsTrue() {
        uniqueFishList.add(ALICE);
        assertTrue(uniqueFishList.contains(ALICE));
    }

    @Test
    public void contains_fishWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFishList.add(ALICE);
        Fish editedAlice = new FishBuilder(ALICE).withFeedingInterval(VALID_FEEDING_INTERVAL_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueFishList.contains(editedAlice));
    }

    @Test
    public void add_nullFish_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFishList.add(null));
    }

    @Test
    public void add_duplicateFish_throwsDuplicateFishException() {
        uniqueFishList.add(ALICE);
        assertThrows(DuplicateFishException.class, () -> uniqueFishList.add(ALICE));
    }

    @Test
    public void setFish_nullTargetFish_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFishList.setFish(null, ALICE));
    }

    @Test
    public void setFish_nullEditedFish_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFishList.setFish(ALICE, null));
    }

    @Test
    public void setFish_targetFishNotInList_throwsFishNotFoundException() {
        assertThrows(FishNotFoundException.class, () -> uniqueFishList.setFish(ALICE, ALICE));
    }

    @Test
    public void setFish_editedFishIsSameFish_success() {
        uniqueFishList.add(ALICE);
        uniqueFishList.setFish(ALICE, ALICE);
        UniqueFishList expectedUniqueFishList = new UniqueFishList();
        expectedUniqueFishList.add(ALICE);
        assertEquals(expectedUniqueFishList, uniqueFishList);
    }

    @Test
    public void setFish_editedFishHasSameIdentity_success() {
        uniqueFishList.add(ALICE);
        Fish editedAlice = new FishBuilder(ALICE).withFeedingInterval(VALID_FEEDING_INTERVAL_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueFishList.setFish(ALICE, editedAlice);
        UniqueFishList expectedUniqueFishList = new UniqueFishList();
        expectedUniqueFishList.add(editedAlice);
        assertEquals(expectedUniqueFishList, uniqueFishList);
    }

    @Test
    public void setFish_editedFishHasDifferentIdentity_success() {
        uniqueFishList.add(ALICE);
        uniqueFishList.setFish(ALICE, BOB);
        UniqueFishList expectedUniqueFishList = new UniqueFishList();
        expectedUniqueFishList.add(BOB);
        assertEquals(expectedUniqueFishList, uniqueFishList);
    }

    @Test
    public void setFish_editedFishHasNonUniqueIdentity_throwsDuplicateFishException() {
        uniqueFishList.add(ALICE);
        uniqueFishList.add(BOB);
        assertThrows(DuplicateFishException.class, () -> uniqueFishList.setFish(ALICE, BOB));
    }

    @Test
    public void remove_nullFish_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFishList.remove(null));
    }

    @Test
    public void remove_fishDoesNotExist_throwsFishNotFoundException() {
        assertThrows(FishNotFoundException.class, () -> uniqueFishList.remove(ALICE));
    }

    @Test
    public void remove_existingFish_removesFish() {
        uniqueFishList.add(ALICE);
        uniqueFishList.remove(ALICE);
        UniqueFishList expectedUniqueFishList = new UniqueFishList();
        assertEquals(expectedUniqueFishList, uniqueFishList);
    }

    @Test
    public void setFishes_nullUniqueFishList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFishList.setFishes((UniqueFishList) null));
    }

    @Test
    public void setFishes_uniqueFishList_replacesOwnListWithProvidedUniqueFishList() {
        uniqueFishList.add(ALICE);
        UniqueFishList expectedUniqueFishList = new UniqueFishList();
        expectedUniqueFishList.add(BOB);
        uniqueFishList.setFishes(expectedUniqueFishList);
        assertEquals(expectedUniqueFishList, uniqueFishList);
    }

    @Test
    public void setFishes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFishList.setFishes((List<Fish>) null));
    }

    @Test
    public void setFishes_list_replacesOwnListWithProvidedList() {
        uniqueFishList.add(ALICE);
        List<Fish> fishList = Collections.singletonList(BOB);
        uniqueFishList.setFishes(fishList);
        UniqueFishList expectedUniqueFishList = new UniqueFishList();
        expectedUniqueFishList.add(BOB);
        assertEquals(expectedUniqueFishList, uniqueFishList);
    }

    @Test
    public void setFishes_listWithDuplicateFishes_throwsDuplicateFishException() {
        List<Fish> listWithDuplicateFish = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateFishException.class, () -> uniqueFishList.setFishes(listWithDuplicateFish));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFishList.asUnmodifiableObservableList().remove(0));
    }
}
