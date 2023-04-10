package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalListings.CHICKEN_RICE_UNCLE;
import static seedu.address.testutil.TypicalListings.getTypicalListingBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.listing.Listing;
import seedu.address.model.listing.exceptions.DuplicateListingException;
import seedu.address.testutil.ListingBuilder;
import seedu.address.testutil.TypicalApplicants;


public class ListingBookTest {

    private final ListingBook listingBook = new ListingBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), listingBook.getListingList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> listingBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyListingBook_replacesData() {
        ListingBook newData = getTypicalListingBook();
        listingBook.resetData(newData);
        assertEquals(newData, listingBook);
    }

    @Test
    public void resetData_withDuplicateListings_throwsDuplicateListingException() {
        // Two listings with the same identity fields
        Listing editedChickenRiceUncle = new ListingBuilder().withTitle("Chicken Rice Uncle")
                .withDescription("Make delicious chicken rice!")
                .withApplicants(new ArrayList<>(TypicalApplicants.getTypicalApplicants()))
                .build();
        List<Listing> newListings = Arrays.asList(CHICKEN_RICE_UNCLE, editedChickenRiceUncle);
        ListingBookStub newData = new ListingBookStub(newListings);

        assertThrows(DuplicateListingException.class, () -> listingBook.resetData(newData));
    }

    @Test
    public void hasListing_nullListing_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> listingBook.hasListing(null));
    }

    @Test
    public void hasListing_listingNotInListingBook_returnsFalse() {
        assertFalse(listingBook.hasListing(CHICKEN_RICE_UNCLE));
    }

    @Test
    public void hasListing_listingInListingBook_returnsTrue() {
        listingBook.addListing(CHICKEN_RICE_UNCLE);
        assertTrue(listingBook.hasListing(CHICKEN_RICE_UNCLE));
    }

    @Test
    public void hasListing_listingWithSameIdentityFieldsInListingBook_returnsTrue() {
        listingBook.addListing(CHICKEN_RICE_UNCLE);
        Listing editedChickenRiceUncle = new ListingBuilder().withTitle("Chicken Rice Uncle")
                .withDescription("Make delicious chicken rice!")
                .withApplicants(new ArrayList<>(TypicalApplicants.getTypicalApplicants()))
                .build();
        assertTrue(listingBook.hasListing(editedChickenRiceUncle));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> listingBook.getListingList().remove(0));
    }

    /**
     * A stub ReadOnlyListingBook whose listings list can violate interface constraints.
     */
    private static class ListingBookStub implements ReadOnlyListingBook {
        private final ObservableList<Listing> listings = FXCollections.observableArrayList();

        ListingBookStub(Collection<Listing> listings) {
            this.listings.setAll(listings);
        }

        @Override
        public ObservableList<Listing> getListingList() {
            return listings;
        }
    }
}
