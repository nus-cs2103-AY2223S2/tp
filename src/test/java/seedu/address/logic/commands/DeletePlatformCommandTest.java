package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLATFORM_NAME_GLINTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLATFORM_NAME_LINKEDIN;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.getIndexLastListing;
import static seedu.address.testutil.TypicalListings.getTypicalListingBook;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
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

public class DeletePlatformCommandTest {
    private static final Platform LINKEDIN = new PlatformBuilder().withName(VALID_PLATFORM_NAME_LINKEDIN).build();
    private static final Platform GLINTS = new PlatformBuilder().withName(VALID_PLATFORM_NAME_GLINTS).build();
    private static final String INVALID_LINKEDIN_NAME = VALID_PLATFORM_NAME_LINKEDIN + "#";

    private Model model = new ModelManager(getTypicalListingBook(), new UserPrefs());

    @Test
    public void execute_deleteUniquePlatformName_success() {
        Listing newListing = new ListingBuilder().withPlatforms(new ArrayList<>(Arrays.asList(
                new Platform[] {LINKEDIN, GLINTS}))).build();
        model.addListing(newListing);

        DeletePlatformCommand deletePlatformCommand = new DeletePlatformCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())), VALID_PLATFORM_NAME_LINKEDIN);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());

        ObservableList<Listing> originalListingBook = model.getListingBook().getListingList();
        Listing originalListing = originalListingBook.get(originalListingBook.size() - 1);
        expectedModel.setListing(originalListing, new ListingBuilder(originalListing).withPlatforms(
                new ArrayList<>(Arrays.asList(new Platform[] {GLINTS}))
        ).build());

        String expectedMessage = String.format(DeletePlatformCommand.MESSAGE_SUCCESS,
                VALID_PLATFORM_NAME_LINKEDIN,
                originalListing.getTitle().fullTitle);

        assertCommandSuccess(deletePlatformCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_outOfBoundIndex_failure() {
        DeletePlatformCommand deletePlatformCommand = new DeletePlatformCommand(
                Index.fromOneBased(model.getListingBook().getListingList().size() + 1), VALID_PLATFORM_NAME_LINKEDIN);

        assertCommandFailure(deletePlatformCommand, model, Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_missingPlatform_failure() {
        Listing newListing = new ListingBuilder().withPlatforms(new ArrayList<>(Arrays.asList(
                new Platform[] {LINKEDIN, GLINTS}))).build();
        model.addListing(newListing);

        DeletePlatformCommand deletePlatformCommand = new DeletePlatformCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())), "Donkey");

        String expectedErrorMessage = String.format(DeletePlatformCommand.MESSAGE_PLATFORM_NOT_FOUND, "Donkey",
                newListing.getTitle().fullTitle);

        assertCommandFailure(deletePlatformCommand, model, expectedErrorMessage);
    }

    /*
    @Test
    public void test_getPlatformToDeleteObject_method() {
        Listing newListing = new ListingBuilder().withPlatforms(new ArrayList<>(Arrays.asList(
                new Platform[] {LINKEDIN, GLINTS}))).build();
        model.addListing(newListing);

        String targetPlatformName = VALID_PLATFORM_NAME_LINKEDIN;

        Optional<Platform> result = getPlatformToDeleteObject(newListing, targetPlatformName);

        assertEquals(LINKEDIN, result.get());
    }

     */

    @Test
    public void testEquals() {
        DeletePlatformCommand command1 = new DeletePlatformCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())), VALID_PLATFORM_NAME_LINKEDIN);

        DeletePlatformCommand command2 = new DeletePlatformCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())), VALID_PLATFORM_NAME_LINKEDIN);

        DeletePlatformCommand command3 = new DeletePlatformCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())), VALID_PLATFORM_NAME_GLINTS);

        assertTrue(command1.equals(command2));
        assertFalse(command1.equals(command3));
    }


}
