package seedu.address.model.opening;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KEYDATE_FEB_OA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOpenings.GOOGLE;
import static seedu.address.testutil.TypicalOpenings.SHOPEE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.OpeningBuilder;
import seedu.ultron.model.opening.Opening;
import seedu.ultron.model.opening.UniqueOpeningList;
import seedu.ultron.model.opening.exceptions.DuplicateOpeningException;
import seedu.ultron.model.opening.exceptions.OpeningNotFoundException;

public class UniqueOpeningListTest {

    private final UniqueOpeningList uniqueOpeningList = new UniqueOpeningList();

    @Test
    public void contains_nullOpening_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOpeningList.contains(null));
    }

    @Test
    public void contains_openingNotInList_returnsFalse() {
        assertFalse(uniqueOpeningList.contains(SHOPEE));
    }

    @Test
    public void contains_openingInList_returnsTrue() {
        uniqueOpeningList.add(SHOPEE);
        assertTrue(uniqueOpeningList.contains(SHOPEE));
    }

    @Test
    public void contains_openingWithSameIdentityFieldsInList_returnsTrue() {
        uniqueOpeningList.add(SHOPEE);
        Opening editedShopee = new OpeningBuilder(SHOPEE).withStatus(VALID_STATUS_GOOGLE)
                .withKeydates(VALID_KEYDATE_FEB_OA).build();
        assertTrue(uniqueOpeningList.contains(editedShopee));
    }

    @Test
    public void add_nullOpening_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOpeningList.add(null));
    }

    @Test
    public void add_duplicateOpening_throwsDuplicateOpeningException() {
        uniqueOpeningList.add(SHOPEE);
        assertThrows(DuplicateOpeningException.class, () -> uniqueOpeningList.add(SHOPEE));
    }

    @Test
    public void setOpening_nullTargetOpening_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOpeningList.setOpening(null, SHOPEE));
    }

    @Test
    public void setOpening_nullEditedOpening_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOpeningList.setOpening(SHOPEE, null));
    }

    @Test
    public void setOpening_targetOpeningNotInList_throwsOpeningNotFoundException() {
        assertThrows(OpeningNotFoundException.class, () -> uniqueOpeningList.setOpening(SHOPEE, SHOPEE));
    }

    @Test
    public void setOpening_editedOpeningIsSameOpening_success() {
        uniqueOpeningList.add(SHOPEE);
        uniqueOpeningList.setOpening(SHOPEE, SHOPEE);
        UniqueOpeningList expectedUniqueOpeningList = new UniqueOpeningList();
        expectedUniqueOpeningList.add(SHOPEE);
        assertEquals(expectedUniqueOpeningList, uniqueOpeningList);
    }

    @Test
    public void setOpening_editedOpeningHasSameIdentity_success() {
        uniqueOpeningList.add(SHOPEE);
        Opening editedShopee = new OpeningBuilder(SHOPEE).withStatus(VALID_STATUS_GOOGLE)
                .withKeydates(VALID_KEYDATE_FEB_OA).build();
        uniqueOpeningList.setOpening(SHOPEE, editedShopee);
        UniqueOpeningList expectedUniqueOpeningList = new UniqueOpeningList();
        expectedUniqueOpeningList.add(editedShopee);
        assertEquals(expectedUniqueOpeningList, uniqueOpeningList);
    }

    @Test
    public void setOpening_editedOpeningHasDifferentIdentity_success() {
        uniqueOpeningList.add(SHOPEE);
        uniqueOpeningList.setOpening(SHOPEE, GOOGLE);
        UniqueOpeningList expectedUniqueOpeningList = new UniqueOpeningList();
        expectedUniqueOpeningList.add(GOOGLE);
        assertEquals(expectedUniqueOpeningList, uniqueOpeningList);
    }

    @Test
    public void setOpening_editedOpeningHasNonUniqueIdentity_throwsDuplicateOpeningException() {
        uniqueOpeningList.add(SHOPEE);
        uniqueOpeningList.add(GOOGLE);
        assertThrows(DuplicateOpeningException.class, () -> uniqueOpeningList.setOpening(SHOPEE, GOOGLE));
    }

    @Test
    public void remove_nullOpening_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOpeningList.remove(null));
    }

    @Test
    public void remove_openingDoesNotExist_throwsOpeningNotFoundException() {
        assertThrows(OpeningNotFoundException.class, () -> uniqueOpeningList.remove(SHOPEE));
    }

    @Test
    public void remove_existingOpening_removesOpening() {
        uniqueOpeningList.add(SHOPEE);
        uniqueOpeningList.remove(SHOPEE);
        UniqueOpeningList expectedUniqueOpeningList = new UniqueOpeningList();
        assertEquals(expectedUniqueOpeningList, uniqueOpeningList);
    }

    @Test
    public void setOpenings_nullUniqueOpeningList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOpeningList.setOpenings((UniqueOpeningList) null));
    }

    @Test
    public void setOpenings_uniqueOpeningList_replacesOwnListWithProvidedUniqueOpeningList() {
        uniqueOpeningList.add(SHOPEE);
        UniqueOpeningList expectedUniqueOpeningList = new UniqueOpeningList();
        expectedUniqueOpeningList.add(GOOGLE);
        uniqueOpeningList.setOpenings(expectedUniqueOpeningList);
        assertEquals(expectedUniqueOpeningList, uniqueOpeningList);
    }

    @Test
    public void setOpenings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOpeningList.setOpenings((List<Opening>) null));
    }

    @Test
    public void setOpenings_list_replacesOwnListWithProvidedList() {
        uniqueOpeningList.add(SHOPEE);
        List<Opening> openingList = Collections.singletonList(GOOGLE);
        uniqueOpeningList.setOpenings(openingList);
        UniqueOpeningList expectedUniqueOpeningList = new UniqueOpeningList();
        expectedUniqueOpeningList.add(GOOGLE);
        assertEquals(expectedUniqueOpeningList, uniqueOpeningList);
    }

    @Test
    public void setOpenings_listWithDuplicateOpenings_throwsDuplicateOpeningException() {
        List<Opening> listWithDuplicateOpenings = Arrays.asList(SHOPEE, SHOPEE);
        assertThrows(DuplicateOpeningException.class, () -> uniqueOpeningList.setOpenings(listWithDuplicateOpenings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueOpeningList.asUnmodifiableObservableList().remove(0));
    }
}
