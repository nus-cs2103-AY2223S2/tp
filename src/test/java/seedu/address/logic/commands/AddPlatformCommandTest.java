package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LISTING;
import static seedu.address.testutil.TypicalIndexes.getIndexLastListing;
import static seedu.address.testutil.TypicalListings.getTypicalListingBook;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ListingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.listing.Listing;
import seedu.address.model.platform.Platform;
import seedu.address.testutil.ListingBuilder;
import seedu.address.testutil.PlatformBuilder;

public class AddPlatformCommandTest {
    private Model model = new ModelManager(getTypicalListingBook(), new UserPrefs());

    @Test
    public void constructor_nullListing_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPlatformCommand(
                null, new PlatformBuilder().build()));
        assertThrows(NullPointerException.class, () -> new AddPlatformCommand(
                INDEX_FIRST_LISTING, null));
    }

    @Test
    public void execute_allFieldsPresent_success() {
        Listing newListing = new ListingBuilder().build();
        model.addListing(newListing);

        Platform platformToAdd = new PlatformBuilder().build();
        AddPlatformCommand addPlatformCommand = new AddPlatformCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())),
                platformToAdd);

        ArrayList<Platform> editedPlatforms = new ArrayList<>(newListing.getPlatforms());
        editedPlatforms.add(platformToAdd);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());
        Listing editedListing = new ListingBuilder(newListing).withPlatforms(editedPlatforms).build();

        expectedModel.setListing(newListing, editedListing);

        String expectedMessage = String.format(
                AddPlatformCommand.MESSAGE_SUCCESS,
                platformToAdd.getPlatformName().fullPlatformName,
                newListing.getTitle().fullTitle);

        assertCommandSuccess(addPlatformCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_outOfBoundIndex_failure() {
        AddPlatformCommand addPlatformCommand = new AddPlatformCommand(
                Index.fromOneBased(model.getListingBook().getListingList().size() + 1), new PlatformBuilder().build());

        assertCommandFailure(addPlatformCommand, model, Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicatedPlatform_failure() {
        Platform duplicatedPlatform = new PlatformBuilder().build();
        Listing newListing = new ListingBuilder().withPlatforms(
                new ArrayList<>(Arrays.asList(new Platform[]{duplicatedPlatform}))).build();
        model.addListing(newListing);

        AddPlatformCommand addPlatformCommand = new AddPlatformCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())), duplicatedPlatform);

        String expectedErrorMessage = AddPlatformCommand.MESSAGE_DUPLICATE_PLATFORM;

        assertCommandFailure(addPlatformCommand, model, expectedErrorMessage);
    }
}
