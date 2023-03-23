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
import seedu.address.model.applicant.Applicant;
import seedu.address.model.listing.Listing;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.ListingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class AddApplicantCommandTest {

    private Model model = new ModelManager(getTypicalListingBook(), new UserPrefs());

    @Test
    public void constructor_nullListing_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApplicantCommand(
                null, new ApplicantBuilder().build()));
        assertThrows(NullPointerException.class, () -> new AddApplicantCommand(
                INDEX_FIRST_LISTING, null));
    }

    @Test
    public void execute_allFieldsPresent_success() {
        Listing newListing = new ListingBuilder().build();
        model.addListing(newListing);

        Applicant applicantToAdd = new ApplicantBuilder().build();
        AddApplicantCommand addApplicantCommand = new AddApplicantCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())),
                applicantToAdd);

        ArrayList<Applicant> editedApplicants = new ArrayList<>(newListing.getApplicants());
        editedApplicants.add(applicantToAdd);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());
        Listing editedListing = new ListingBuilder(newListing).withApplicants(editedApplicants).build();

        expectedModel.setListing(newListing, editedListing);

        String expectedMessage = String.format(
                AddApplicantCommand.MESSAGE_SUCCESS,
                applicantToAdd.getName().fullName,
                newListing.getTitle().fullTitle);

        assertCommandSuccess(addApplicantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_outOfBoundIndex_failure() {
        AddApplicantCommand addApplicantCommand = new AddApplicantCommand(
                Index.fromOneBased(model.getListingBook().getListingList().size() + 1), new ApplicantBuilder().build());

        assertCommandFailure(addApplicantCommand, model, Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_duplicatedApplicant_failure() {
        Applicant duplicatedApplicant = new ApplicantBuilder().build();
        Listing newListing = new ListingBuilder().withApplicants(
                new ArrayList<>(Arrays.asList(new Applicant[]{duplicatedApplicant}))).build();
        model.addListing(newListing);

        AddApplicantCommand addApplicantCommand = new AddApplicantCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())), duplicatedApplicant);

        String expectedErrorMessage = AddApplicantCommand.MESSAGE_DUPLICATE_APPLICANT;

        assertCommandFailure(addApplicantCommand, model, expectedErrorMessage);
    }

}
