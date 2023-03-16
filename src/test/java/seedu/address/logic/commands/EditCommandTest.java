package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LISTING;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LISTING_ALT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_BENEDICT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_ALT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showListingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LISTING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LISTING;
import static seedu.address.testutil.TypicalListings.getTypicalListingBook;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditListingDescriptor;
import seedu.address.model.ListingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Name;
import seedu.address.model.listing.Listing;
import seedu.address.testutil.EditListingDescriptorBuilder;
import seedu.address.testutil.ListingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalListingBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Listing editedListing = new ListingBuilder().build();
        EditListingDescriptor descriptor = new EditListingDescriptorBuilder(editedListing).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_LISTING, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_LISTING_SUCCESS, editedListing);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());
        expectedModel.setListing(model.getFilteredListingList().get(0), editedListing);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastListing = Index.fromOneBased(model.getFilteredListingList().size());
        Listing lastListing = model.getFilteredListingList().get(indexLastListing.getZeroBased());

        ListingBuilder listingInList = new ListingBuilder(lastListing);
        Listing editedListing = listingInList.withTitle(VALID_TITLE).withDescription(VALID_DESCRIPTION)
                .withApplicants(new ArrayList<>(Arrays
                        .asList(new Applicant(new Name(VALID_APPLICANT_NAME_BENEDICT))))).build();

        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withJobTitle(VALID_TITLE)
                .withJobDescription(VALID_DESCRIPTION).withApplicants(VALID_APPLICANT_NAME_BENEDICT).build();
        EditCommand editCommand = new EditCommand(indexLastListing, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_LISTING_SUCCESS, editedListing);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());
        expectedModel.setListing(lastListing, editedListing);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_LISTING, new EditListingDescriptor());
        Listing editedListing = model.getFilteredListingList().get(INDEX_FIRST_LISTING.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_LISTING_SUCCESS, editedListing);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showListingAtIndex(model, INDEX_FIRST_LISTING);

        Listing listingInFilteredList = model.getFilteredListingList().get(INDEX_FIRST_LISTING.getZeroBased());
        Listing editedListing = new ListingBuilder(listingInFilteredList).withTitle(VALID_TITLE_ALT).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_LISTING,
                new EditListingDescriptorBuilder().withJobTitle(VALID_TITLE_ALT).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_LISTING_SUCCESS, editedListing);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());
        expectedModel.setListing(model.getFilteredListingList().get(0), editedListing);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateListingUnfilteredList_failure() {
        Listing firstListing = model.getFilteredListingList().get(INDEX_FIRST_LISTING.getZeroBased());
        EditListingDescriptor descriptor = new EditListingDescriptorBuilder(firstListing).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_LISTING, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_LISTING);
    }

    @Test
    public void execute_duplicateListingFilteredList_failure() {
        showListingAtIndex(model, INDEX_FIRST_LISTING);

        // edit listing in filtered list into a duplicate in address book
        Listing listingInList = model.getListingBook().getListingList().get(INDEX_SECOND_LISTING.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_LISTING,
                new EditListingDescriptorBuilder(listingInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_LISTING);
    }

    @Test
    public void execute_invalidListingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredListingList().size() + 1);
        EditListingDescriptor descriptor = new EditListingDescriptorBuilder().withJobTitle(VALID_TITLE).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidListingIndexFilteredList_failure() {
        showListingAtIndex(model, INDEX_FIRST_LISTING);
        Index outOfBoundIndex = INDEX_SECOND_LISTING;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getListingBook().getListingList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditListingDescriptorBuilder().withJobTitle(VALID_TITLE).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_LISTING, DESC_LISTING);

        // same values -> returns true
        EditListingDescriptor copyDescriptor = new EditListingDescriptor(DESC_LISTING);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_LISTING, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_LISTING, DESC_LISTING)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_LISTING, DESC_LISTING_ALT)));
    }

}
