package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.listing.Listing;

/**
 * Represents the in-memory model of the listing book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final ListingBook listingBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Listing> filteredListings;

    /**
     * Initializes a ModelManager with the given listingBook and userPrefs.
     */
    public ModelManager(ReadOnlyListingBook listingBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(listingBook, userPrefs);

        logger.fine("Initializing with listing book: " + listingBook + " and user prefs " + userPrefs);

        this.listingBook = new ListingBook(listingBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredListings = new FilteredList<>(this.listingBook.getListingList());
    }

    public ModelManager() {
        this(new ListingBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getListingBookFilePath() {
        return userPrefs.getListingBookFilePath();
    }

    @Override
    public void setListingBookFilePath(Path listingBookFilePath) {
        requireNonNull(listingBookFilePath);
        userPrefs.setListingBookFilePath(listingBookFilePath);
    }

    //=========== ListingBook ================================================================================

    @Override
    public void setListingBook(ReadOnlyListingBook listingBook) {
        this.listingBook.resetData(listingBook);
    }

    @Override
    public ReadOnlyListingBook getListingBook() {
        return listingBook;
    }

    @Override
    public boolean hasListing(Listing listing) {
        requireNonNull(listing);
        return listingBook.hasListing(listing);
    }

    @Override
    public void deleteListing(Listing target) {
        listingBook.removeListing(target);
    }

    @Override
    public void addListing(Listing listing) {
        listingBook.addListing(listing);
        updateFilteredListingList(PREDICATE_SHOW_ALL_LISTINGS);
    }

    @Override
    public void setListing(Listing target, Listing editedListing) {
        requireAllNonNull(target, editedListing);

        listingBook.setListing(target, editedListing);
    }

    //=========== Filtered Listing List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Listing> getFilteredListingList() {
        return filteredListings;
    }

    @Override
    public void updateFilteredListingList(Predicate<Listing> predicate) {
        requireNonNull(predicate);
        filteredListings.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return listingBook.equals(other.listingBook)
                && userPrefs.equals(other.userPrefs)
                && filteredListings.equals(other.listingBook);
    }
}
