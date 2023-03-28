package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalListings.getTypicalListingBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.ListingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.comparator.ListingComparator;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class SortCommandTest {

    private Model model = new ModelManager(getTypicalListingBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedNoneComparator_success() {
        SortCommand sortCommand = new SortCommand(ListingComparator.NONE);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());
        expectedModel.updateSortedListingBook(ListingComparator.NONE);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedTitleComparator_success() {
        SortCommand sortCommand = new SortCommand(ListingComparator.TITLE);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());
        expectedModel.updateSortedListingBook(ListingComparator.TITLE);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedDescriptionComparator_success() {
        SortCommand sortCommand = new SortCommand(ListingComparator.DESCRIPTION);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());
        expectedModel.updateSortedListingBook(ListingComparator.DESCRIPTION);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedApplicantsComparator_success() {
        SortCommand sortCommand = new SortCommand(ListingComparator.APPLICANTS);

        Model expectedModel = new ModelManager(new ListingBook(model.getListingBook()), new UserPrefs());
        expectedModel.updateSortedListingBook(ListingComparator.APPLICANTS);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void constructor_nullListing_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null));
    }

}
