package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LISTING;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LISTING_ALT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_BENEDICT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICANT_NAME_CHRIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_ALT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showListingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LISTING;
import static seedu.address.testutil.TypicalIndexes.INDEX_LAST_LISTING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LISTING;
import static seedu.address.testutil.TypicalListings.getTypicalListingBook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditListingDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ListingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Name;
import seedu.address.model.listing.Listing;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.EditListingDescriptorBuilder;
import seedu.address.testutil.ListingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteApplicantCommand.
 */
public class DeleteApplicantCommandTest {

    private Model model = new ModelManager(getTypicalListingBook(), new UserPrefs());

    /**
     *
     * 1. Test success when only one applicant
     * 2. Test success when 2 applicant
     * 3. Test wrong format
     * 4. Test applicant not found (without id)
     * 5. Test applicant not found (with id)
     * 6. Test listing not found
     * 7. Test ambiguous applicant
     */

    @Test
    public void execute_deleteUniqueApplicantName_success() {
        final Applicant BENEDICT = new ApplicantBuilder().withName(VALID_APPLICANT_NAME_BENEDICT).build();
        final Applicant CHRIS = new ApplicantBuilder().withName(VALID_APPLICANT_NAME_CHRIS).build();
        Listing newListing = new ListingBuilder().withApplicants(new ArrayList<>(Arrays.asList(
                new Applicant[] {BENEDICT, CHRIS}))).build();
        model.addListing(newListing);

        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(
                INDEX_LAST_LISTING(new ArrayList<>(model.getFilteredListingList())), VALID_APPLICANT_NAME_BENEDICT);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());

        ObservableList<Listing> originalListingBook = model.getListingBook().getListingList();
        Listing originalListing = originalListingBook.get(originalListingBook.size() - 1);
        expectedModel.setListing(originalListing, new ListingBuilder(originalListing).withApplicants(
                new ArrayList<>(Arrays.asList(new Applicant[] {CHRIS}))
        ).build());

        String expectedMessage = String.format(DeleteApplicantCommand.MESSAGE_SUCCESS,
                VALID_APPLICANT_NAME_BENEDICT,
                originalListing.getTitle().fullTitle);

//        Debugging tools
//        try {
//            CommandResult result = deleteApplicantCommand.execute(model);
//            System.out.println(model.equals(expectedModel));
//            System.out.println(result.equals(new CommandResult(expectedMessage)));
//        } catch (Error | CommandException e) {
//            System.out.println(e);
//        }

        assertCommandSuccess(deleteApplicantCommand, model, expectedMessage, expectedModel);
    }

}
