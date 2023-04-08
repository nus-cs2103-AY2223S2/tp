package seedu.internship.logic.commands.event;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.FindCommand;
import seedu.internship.logic.commands.ResultType;
import seedu.internship.model.*;
import seedu.internship.model.event.*;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Position;
import seedu.internship.model.internship.Status;
import seedu.internship.testutil.EditInternshipDescriptorBuilder;
import seedu.internship.testutil.EventBuilder;
import seedu.internship.testutil.FilterInternshipDescriptorBuilder;
import seedu.internship.testutil.InternshipBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.commands.CommandTestUtil.*;
import static seedu.internship.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.internship.testutil.TypicalEvents.*;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.internship.testutil.TypicalInternships.*;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FindCommand.
 */
public class EventFindCommandTest {

    private Model model = new ModelManager(getTypicalInternshipCatalogue(),
            new EventCatalogue(getTypicalEventCatalogue()), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Event eventFind = new EventBuilder().build();
        EventFindCommand.FilterEventDescriptor descriptor = new EventFindCommand.FilterEventDescriptor();
        descriptor.setName(new Name("Interview"));
        descriptor.setStart(new Start(LocalDateTime.parse("04/04/2023 1500",
                Start.NUMERIC_DATE_TIME_FORMATTER)));
        descriptor.setEnd(new End(LocalDateTime.parse("04/04/2023 1800",
                End.NUMERIC_DATE_TIME_FORMATTER)));
        EventFindCommand eventFindCommand = new EventFindCommand(descriptor);

        Model expectedModel = new ModelManager(new InternshipCatalogue(model.getInternshipCatalogue()),
                new EventCatalogue(model.getEventCatalogue()), new UserPrefs());

        // Changes in Model after Filtering the list
//        ArrayList<Internship> l = new ArrayList<>();
//        l.add(SE3);
//        ReadOnlyInternshipCatalogue r = new InternshipCatalogue();
//        ((InternshipCatalogue) r).setInternships(l);
////        expectedModel.setInternshipCatalogue(r);
        expectedModel.updateFilteredEventList(x -> x.equals(EM11));

//        expectedModel.updateFilteredEventList(new EventByInternship(expectedModel.getSelectedInternship()));
//        ObservableList<Event> events = expectedModel.getFilteredEventList();
        CommandResult expectedCommandResult = new CommandResult(
                String.format(EventFindCommand.MESSAGE_SUCCESS, 1),
                ResultType.FIND_EVENT, expectedModel.getFilteredEventList());

        assertCommandSuccess(eventFindCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        Internship findInternship = new InternshipBuilder().withPosition().;
//        FindCommand.FilterInternshipDescriptor descriptor = new FilterInternshipDescriptorBuilder(findInternship).build();
//        FindCommand findCommand = new FindCommand(descriptor);

        EventFindCommand.FilterEventDescriptor descriptor = new EventFindCommand.FilterEventDescriptor();
        descriptor.setName(new Name("HR Meeting"));
        EventFindCommand findCommand = new EventFindCommand(descriptor);

        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        Model expectedModel = new ModelManager(new InternshipCatalogue(model.getInternshipCatalogue()),
                new EventCatalogue(model.getEventCatalogue()), new UserPrefs());

        // Changes in Model after Filtering the list
//        ArrayList<Internship> l = new ArrayList<>();
//        l.add(SE3);
//        l.add(SE4);
//        l.add(BE1);
//        ReadOnlyInternshipCatalogue r = new InternshipCatalogue();
//        ((InternshipCatalogue) r).setInternships(l);
//        expectedModel.setInternshipCatalogue(r);
        expectedModel.updateFilteredEventList(x -> x.equals(EM12) || x.equals(EM21));

//        expectedModel.updateFilteredEventList(new EventByInternship(expectedModel.getSelectedInternship()));
//        ObservableList<Event> events = expectedModel.getFilteredEventList();
        CommandResult expectedCommandResult = new CommandResult(
                String.format(EventFindCommand.MESSAGE_SUCCESS, 2),
                ResultType.FIND_EVENT, expectedModel.getFilteredEventList());

        assertCommandSuccess(findCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EventFindCommand.FilterEventDescriptor descriptor = new EventFindCommand.FilterEventDescriptor();
        EventFindCommand eventFindCommand = new EventFindCommand(descriptor);

        assertCommandFailure(eventFindCommand, model, String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventFindCommand.MESSAGE_USAGE));
    }

//    @Test
//    public void equals() {
//        final EventFindCommand standardCommand = new EventFindCommand();
//
//        // same values -> returns true
//        EventFindCommand.FilterEventDescriptor copyDescriptor = new EventFindCommand.FilterEventDescriptor(FDESC_ML1);
//        EventFindCommand commandWithSameValues = new EventFindCommand(copyDescriptor);
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        // uncomment when clear command is implemented.
//        //assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new FindCommand(FDESC_SE1)));
//    }



}

