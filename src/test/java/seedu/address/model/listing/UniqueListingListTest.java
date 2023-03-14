package seedu.address.model.listing;

import org.junit.jupiter.api.Test;
import seedu.address.model.listing.exceptions.DuplicateListingException;
import seedu.address.model.listing.exceptions.ListingNotFoundException;
import seedu.address.testutil.ListingBuilder;
import seedu.address.testutil.TypicalApplicants;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalListings.CHICKEN_RICE_UNCLE;
import static seedu.address.testutil.TypicalListings.TOILET_CLEANER;


public class UniqueListingListTest {

    private final UniqueListingList uniqueListingList = new UniqueListingList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueListingList.contains(null));
    }

    @Test
    public void contains_listingNotInList_returnsFalse() {
        assertFalse(uniqueListingList.contains(CHICKEN_RICE_UNCLE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueListingList.add(CHICKEN_RICE_UNCLE);
        assertTrue(uniqueListingList.contains(CHICKEN_RICE_UNCLE));
    }

    @Test
    public void contains_listingWithSameIdentityFieldsInList_returnsTrue() {
        uniqueListingList.add(CHICKEN_RICE_UNCLE);
        Listing editedChickenRiceUncle = new ListingBuilder().withTitle("Chicken Rice Uncle")
                .withDescription("Make delicious chicken rice!")
                .withApplicants(new ArrayList<>(TypicalApplicants.getTypicalApplicants()))
                .build();
        assertTrue(uniqueListingList.contains(editedChickenRiceUncle));
    }

    @Test
    public void add_nullListing_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueListingList.add(null));
    }

    @Test
    public void add_duplicateListing_throwsDuplicateListingException() {
        uniqueListingList.add(CHICKEN_RICE_UNCLE);
        assertThrows(DuplicateListingException.class, () -> uniqueListingList.add(CHICKEN_RICE_UNCLE));
    }

    @Test
    public void setListing_nullListing_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueListingList.setListing(null, CHICKEN_RICE_UNCLE));
    }

    @Test
    public void setListing_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueListingList.setListing(CHICKEN_RICE_UNCLE, null));
    }
    @Test
    public void setlListing_targetListingNotInList_throwsListingNotFoundException() {
        assertThrows(ListingNotFoundException.class, () -> uniqueListingList.setListing(CHICKEN_RICE_UNCLE, CHICKEN_RICE_UNCLE));
    }

    @Test
    public void setListing_editedListingIsSameListing_success() {
        uniqueListingList.add(CHICKEN_RICE_UNCLE);
        uniqueListingList.setListing(CHICKEN_RICE_UNCLE, CHICKEN_RICE_UNCLE);
        UniqueListingList expectedUniqueListingList = new UniqueListingList();
        expectedUniqueListingList.add(CHICKEN_RICE_UNCLE);
        assertEquals(expectedUniqueListingList, uniqueListingList);
    }

    @Test
    public void setListing_editedListingHasSameIdentity_success() {
        uniqueListingList.add(CHICKEN_RICE_UNCLE);
        Listing editedChickenRiceUncle = new ListingBuilder().withTitle("Chicken Rice Uncle")
                .withDescription("Make delicious chicken rice!")
                .withApplicants(new ArrayList<>(TypicalApplicants.getTypicalApplicants()))
                .build();
        uniqueListingList.setListing(CHICKEN_RICE_UNCLE, editedChickenRiceUncle);
        UniqueListingList expectedUniqueListingList = new UniqueListingList();
        expectedUniqueListingList.add(editedChickenRiceUncle);
        assertEquals(expectedUniqueListingList, uniqueListingList);
    }

    @Test
    public void setListing_editedListingDifferentIdentity_success() {
        uniqueListingList.add(CHICKEN_RICE_UNCLE);
        uniqueListingList.setListing(CHICKEN_RICE_UNCLE,TOILET_CLEANER);
        UniqueListingList expectedUniqueListingList = new UniqueListingList();
        expectedUniqueListingList.add(TOILET_CLEANER);
        assertEquals(expectedUniqueListingList, uniqueListingList);
    }

    @Test
    public void setListing_editedListingHasNonUniqueIdentity_throwsDuplicateListingException() {
        uniqueListingList.add(CHICKEN_RICE_UNCLE);
        uniqueListingList.add(TOILET_CLEANER);
        assertThrows(DuplicateListingException.class
                , () -> uniqueListingList.setListing(CHICKEN_RICE_UNCLE, TOILET_CLEANER));
    }

    @Test
    public void remove_nullListing_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueListingList.remove(null));
    }

    @Test
    public void remove_listingDoesNotExist_throwsListingNotFoundException() {
        assertThrows(ListingNotFoundException.class, () -> uniqueListingList.remove((CHICKEN_RICE_UNCLE)));
    }

    @Test
    public void remove_existingListing_removesListing() {
        uniqueListingList.add(CHICKEN_RICE_UNCLE);
        uniqueListingList.remove(CHICKEN_RICE_UNCLE);
        UniqueListingList expectedUniqueListingList = new UniqueListingList();
        assertEquals(expectedUniqueListingList, uniqueListingList);
    }

    @Test
    public void setListings_nullUniqueListingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueListingList.setListings((UniqueListingList)  null));
    }

    @Test
    public void setListings_uniqueListingList_replaceOwnListWithProvidedUniqueListingList() {
        uniqueListingList.add(CHICKEN_RICE_UNCLE);
        UniqueListingList expectedUniqueListingList = new UniqueListingList();
        expectedUniqueListingList.add(TOILET_CLEANER);
        uniqueListingList.setListings(expectedUniqueListingList);
        assertEquals(expectedUniqueListingList, uniqueListingList);
    }

    @Test
    public void setListings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueListingList.setListings((List<Listing>) null));
    }
}
