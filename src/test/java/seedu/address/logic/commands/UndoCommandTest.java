package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalListings.getTypicalListingBook;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.ListingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.listing.Listing;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.ListingBuilder;

public class UndoCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalListingBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getListingBook(), new UserPrefs());
    }

    @Test
    public void execute_undoWithoutPreviousCommand_failure() {
        UndoCommand undoCommand = new UndoCommand();

        assertCommandFailure(undoCommand, model, Messages.MESSAGE_INVALID_UNDO);
    }
    @Test
    public void execute_undoAddApplicant_success() {
        Listing newListing = new ListingBuilder().build();
        model.addListing(newListing);

        Applicant applicantToAdd = new ApplicantBuilder().build();
        UndoCommand undoCommand = new UndoCommand();
        ArrayList<Applicant> editedApplicants = new ArrayList<>(newListing.getApplicants());

        expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());

        editedApplicants.add(applicantToAdd);
        Listing editedListing = new ListingBuilder(newListing).withApplicants(editedApplicants).build();

        model.setListing(newListing, editedListing);

        String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS);

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }
}
