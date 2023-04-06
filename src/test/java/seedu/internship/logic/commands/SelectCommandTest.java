package seedu.internship.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.testutil.TypicalEvents.getTypicalEventCatalogue;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.internship.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternshipCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.internship.commons.core.Messages;
import seedu.internship.commons.core.index.Index;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventByInternship;
import seedu.internship.model.internship.Internship;

public class SelectCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipCatalogue(), getTypicalEventCatalogue(), new UserPrefs());

    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Internship internshipToSelect = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST_INTERNSHIP);

        // Expected Results
        expectedModel = new ModelManager(model.getInternshipCatalogue(), model.getEventCatalogue(), new UserPrefs());
        expectedModel.updateSelectedInternship(internshipToSelect);

        expectedModel.updateFilteredEventList(new EventByInternship(internshipToSelect));
        ObservableList<Event> events = expectedModel.getFilteredEventList();

        CommandResult expectedCommandResult =
                new CommandResult(String.format(SelectCommand.MESSAGE_VIEW_INTERNSHIP_SUCCESS,
                        internshipToSelect.getPosition(),
                internshipToSelect.getCompany()), ResultType.SHOW_INFO, internshipToSelect, events);

        CommandTestUtil.assertCommandSuccess(selectCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        SelectCommand selectCommand = new SelectCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(selectCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Internship internshipToSelect = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST_INTERNSHIP);


        Model expectedModel = new ModelManager(model.getInternshipCatalogue(),
                model.getEventCatalogue(), new UserPrefs());
        expectedModel.updateSelectedInternship(internshipToSelect);
        // After Select Internship , it is important to select all the events associated.
        expectedModel.updateFilteredEventList(new EventByInternship(internshipToSelect));
        ObservableList<Event> events = expectedModel.getFilteredEventList();

        CommandResult expectedCommandResult =
                new CommandResult(String.format(SelectCommand.MESSAGE_VIEW_INTERNSHIP_SUCCESS,
                internshipToSelect.getPosition(),
                internshipToSelect.getCompany()), ResultType.SHOW_INFO, internshipToSelect, events);

        CommandTestUtil.assertCommandSuccess(selectCommand, model, expectedCommandResult, expectedModel);

    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of internship catalogue
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipCatalogue().getInternshipList().size());

        SelectCommand selectCommand = new SelectCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(selectCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SelectCommand selectFirstCommand = new SelectCommand(INDEX_FIRST_INTERNSHIP);
        SelectCommand selectSecondCommand = new SelectCommand(INDEX_SECOND_INTERNSHIP);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectCommand selectFirstCommandCopy = new SelectCommand(INDEX_FIRST_INTERNSHIP);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }




}
