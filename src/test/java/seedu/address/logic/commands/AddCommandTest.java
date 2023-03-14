package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ListingBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyListingBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.listing.Listing;
import seedu.address.testutil.ListingBuilder;

public class AddCommandTest {
    @Test
    public void constructor_nullListing_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_listingAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingListingAdded modelStub = new ModelStubAcceptingListingAdded();
        Listing validListing = new ListingBuilder().build();

        CommandResult commandResult = new AddCommand(validListing).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validListing), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validListing), modelStub.listingsAdded);
    }

    @Test
    public void execute_duplicateListing_throwsCommandException() {
        Listing validListing = new ListingBuilder().build();
        AddCommand addCommand = new AddCommand(validListing);
        ModelStub modelStub = new ModelStubWithListing(validListing);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_LISTING, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Listing listing1 = new ListingBuilder().withTitle("Title 1").withDescription("Description 1").build();
        Listing listing2 = new ListingBuilder().withTitle("Title 2").withDescription("Description 2").build();
        AddCommand addListing1Command = new AddCommand(listing1);
        AddCommand addListing2Command = new AddCommand(listing2);

        // same object -> returns true
        assertTrue(addListing1Command.equals(addListing1Command));

        // same values -> returns true
        AddCommand addListing1CommandCopy = new AddCommand(listing1);
        assertTrue(addListing1Command.equals(addListing1CommandCopy));

        // different types -> returns false
        assertFalse(addListing1Command.equals(1));

        // null -> returns false
        assertFalse(addListing1Command.equals(null));

        // different person -> returns false
        assertFalse(addListing1Command.equals(addListing2Command));
    }
    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getListingBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setListingBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addListing(Listing listing) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setListingBook(ReadOnlyListingBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyListingBook getListingBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasListing(Listing listing) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteListing(Listing target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setListing(Listing target, Listing editedListing) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Listing> getFilteredListingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredListingList(Predicate<Listing> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single listing.
     */
    private class ModelStubWithListing extends ModelStub {
        private final Listing listing;

        ModelStubWithListing(Listing listing) {
            requireNonNull(listing);
            this.listing = listing;
        }

        @Override
        public boolean hasListing(Listing listing) {
            requireNonNull(listing);
            return this.listing.isSameListing(listing);
        }
    }

    /**
     * A Model stub that always accept the listing being added.
     */
    private class ModelStubAcceptingListingAdded extends ModelStub {
        final ArrayList<Listing> listingsAdded = new ArrayList<>();

        @Override
        public boolean hasListing(Listing listing) {
            requireNonNull(listing);
            return listingsAdded.stream().anyMatch(listing::isSameListing);
        }

        @Override
        public void addListing(Listing listing) {
            requireNonNull(listing);
            listingsAdded.add(listing);
        }

        @Override
        public ReadOnlyListingBook getListingBook() {
            return new ListingBook();
        }
    }
}
