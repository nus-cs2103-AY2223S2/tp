package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ListingBook;
import seedu.address.model.ReadOnlyListingBook;
import seedu.address.model.listing.Listing;

/**
 * An Immutable ListingBook that is serializable to JSON format.
 */
@JsonRootName(value = "listingbook")
class JsonSerializableListingBook {

    public static final String MESSAGE_DUPLICATE_LISTING = "Listings list contains duplicate listing(s).";

    private final List<JsonAdaptedListing> listings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableListingBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableListingBook(@JsonProperty("listings") List<JsonAdaptedListing> listings) {
        this.listings.addAll(listings);
    }

    /**
     * Converts a given {@code ReadOnlyListingBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableListingBook}.
     */
    public JsonSerializableListingBook(ReadOnlyListingBook source) {
        listings.addAll(source.getListingList().stream().map(JsonAdaptedListing::new).collect(Collectors.toList()));
    }

    /**
     * Converts this listing book into the model's {@code ListingBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ListingBook toModelType() throws IllegalValueException {
        ListingBook listingBook = new ListingBook();
        for (JsonAdaptedListing jsonAdaptedListing : listings) {
            Listing listing = jsonAdaptedListing.toModelType();
            if (listingBook.hasListing(listing)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LISTING);
            }
            listingBook.addListing(listing);
        }
        return listingBook;
    }

}
