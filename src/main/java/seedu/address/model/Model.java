package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.listing.Listing;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Listing> PREDICATE_SHOW_ALL_LISTINGS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' listing book file path.
     */
    Path getListingBookFilePath();

    /**
     * Sets the user prefs' listing book file path.
     */
    void setListingBookFilePath(Path listingBookFilePath);

    /**
     * Replaces listing book data with the data in {@code listingBook}.
     */
    void setListingBook(ReadOnlyListingBook listingBook);

    /** Returns the AddressBook */
    ReadOnlyListingBook getListingBook();

    /**
     * Returns true if a listing with the same identity as {@code listing} exists in the listing book.
     */
    boolean hasListing(Listing listing);

    /**
     * Deletes the given listing.
     * The listing must exist in the listing book.
     */
    void deleteListing(Listing target);

    /**
     * Adds the given listing.
     * {@code listing} must not already exist in the address book.
     */
    void addListing(Listing person);

    /**
     * Replaces the given listing {@code target} with {@code editedListing}.
     * {@code target} must exist in the listing book.
     * The person identity of {@code editedListing} must not be the same
     * as another existing listing in the listing book.
     */
    void setListing(Listing target, Listing editedListing);

    /** Returns an unmodifiable view of the filtered listing book */
    ObservableList<Listing> getFilteredListingList();

    /**
     * Updates the filter of the filtered listing book to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredListingList(Predicate<Listing> predicate);
}
