package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_BENEDICT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_CHRIS;
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
import seedu.address.model.applicant.Applicant;
import seedu.address.model.listing.Listing;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.ListingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteApplicantCommand.
 */
public class DeleteApplicantCommandTest {
    private static final Applicant BENEDICT_1 = new ApplicantBuilder().withName(VALID_APPLICANT_NAME_BENEDICT).build();
    private static final Applicant BENEDICT_2 = new ApplicantBuilder().withName(VALID_APPLICANT_NAME_BENEDICT).build();
    private static final Applicant CHRIS = new ApplicantBuilder().withName(VALID_APPLICANT_NAME_CHRIS).build();
    private static final String INVALID_BENEDICT_1_NAME_WITH_ID = VALID_APPLICANT_NAME_BENEDICT + "#"
            + (BENEDICT_1.hashCode() - 1);
    private static final String VALID_BENEDICT_1_NAME_WITH_ID = VALID_APPLICANT_NAME_BENEDICT + "#"
            + BENEDICT_1.hashCode();
    private static final String VALID_BENEDICT_2_NAME_WITH_ID = VALID_APPLICANT_NAME_BENEDICT + "#"
            + BENEDICT_2.hashCode();

    private Model model = new ModelManager(getTypicalListingBook(), new UserPrefs());

    @Test
    public void execute_deleteUniqueApplicantName_success() {
        Listing newListing = new ListingBuilder().withApplicants(new ArrayList<>(Arrays.asList(
                new Applicant[] {BENEDICT_1, CHRIS}))).build();
        model.addListing(newListing);

        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())), VALID_APPLICANT_NAME_BENEDICT);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());

        ObservableList<Listing> originalListingBook = model.getListingBook().getListingList();
        Listing originalListing = originalListingBook.get(originalListingBook.size() - 1);
        expectedModel.setListing(originalListing, new ListingBuilder(originalListing).withApplicants(
                new ArrayList<>(Arrays.asList(new Applicant[] {CHRIS}))
        ).build());

        String expectedMessage = String.format(DeleteApplicantCommand.MESSAGE_SUCCESS,
                VALID_APPLICANT_NAME_BENEDICT,
                originalListing.getTitle().fullTitle);

        assertCommandSuccess(deleteApplicantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteDuplicatedApplicantNames_success() {
        Listing newListing = new ListingBuilder().withApplicants(new ArrayList<>(Arrays.asList(
                new Applicant[] {BENEDICT_1, BENEDICT_2, CHRIS}))).build();
        model.addListing(newListing);

        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())), VALID_BENEDICT_2_NAME_WITH_ID);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());

        ObservableList<Listing> originalListingBook = model.getListingBook().getListingList();
        Listing originalListing = originalListingBook.get(originalListingBook.size() - 1);
        expectedModel.setListing(originalListing, new ListingBuilder(originalListing).withApplicants(
                new ArrayList<>(Arrays.asList(new Applicant[] {BENEDICT_1, CHRIS}))
        ).build());

        String expectedMessage = String.format(DeleteApplicantCommand.MESSAGE_SUCCESS,
                VALID_BENEDICT_2_NAME_WITH_ID,
                originalListing.getTitle().fullTitle);

        assertCommandSuccess(deleteApplicantCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_outOfBoundIndex_failure() {
        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(
                Index.fromOneBased(model.getListingBook().getListingList().size() + 1), VALID_APPLICANT_NAME_BENEDICT);

        assertCommandFailure(deleteApplicantCommand, model, Messages.MESSAGE_INVALID_LISTING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_missingApplicantWithoutId_failure() {
        Listing newListing = new ListingBuilder().withApplicants(new ArrayList<>(Arrays.asList(
                new Applicant[] {BENEDICT_1, CHRIS}))).build();
        model.addListing(newListing);

        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())), "Donkey");

        String expectedErrorMessage = String.format(DeleteApplicantCommand.MESSAGE_APPLICANT_NOT_FOUND, "Donkey",
                newListing.getTitle().fullTitle);

        assertCommandFailure(deleteApplicantCommand, model, expectedErrorMessage);
    }

    @Test
    public void execute_missingApplicantWithId_failure() {
        Listing newListing = new ListingBuilder().withApplicants(new ArrayList<>(Arrays.asList(
                new Applicant[] {BENEDICT_1, CHRIS}))).build();
        model.addListing(newListing);

        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())), INVALID_BENEDICT_1_NAME_WITH_ID);

        String expectedErrorMessage = String.format(DeleteApplicantCommand.MESSAGE_APPLICANT_NOT_FOUND,
                INVALID_BENEDICT_1_NAME_WITH_ID,
                newListing.getTitle().fullTitle);

        assertCommandFailure(deleteApplicantCommand, model, expectedErrorMessage);
    }

    @Test
    public void execute_ambiguousDuplicatedApplicantName_failure() {
        Listing newListing = new ListingBuilder().withApplicants(new ArrayList<>(Arrays.asList(
                new Applicant[] {BENEDICT_1, BENEDICT_2, CHRIS}))).build();
        model.addListing(newListing);

        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(
                getIndexLastListing(new ArrayList<>(model.getDisplayedListingBook())), VALID_APPLICANT_NAME_BENEDICT);

        String expectedErrorMessage = String.format(DeleteApplicantCommand.MESSAGE_AMBIGUOUS_APPLICANT,
                VALID_APPLICANT_NAME_BENEDICT,
                newListing.getTitle().fullTitle);

        assertCommandFailure(deleteApplicantCommand, model, expectedErrorMessage);
    }

}
