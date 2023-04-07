package seedu.internship.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.internship.commons.core.index.Index;
import seedu.internship.model.*;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventByInternship;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Position;
import seedu.internship.model.internship.Status;
import seedu.internship.testutil.EditInternshipDescriptorBuilder;
import seedu.internship.testutil.FilterInternshipDescriptorBuilder;
import seedu.internship.testutil.InternshipBuilder;

import java.util.ArrayList;
import java.util.List;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.commands.CommandTestUtil.*;
import static seedu.internship.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.internship.testutil.TypicalEvents.getTypicalEventCatalogue;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.internship.testutil.TypicalInternships.*;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FindCommand.
 */
public class FindCommandTest {

    private Model model = new ModelManager(getTypicalInternshipCatalogue(),
            new EventCatalogue(getTypicalEventCatalogue()), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Internship findInternship = new InternshipBuilder().buildForFind();
        FindCommand.FilterInternshipDescriptor descriptor = new FilterInternshipDescriptorBuilder(findInternship).build();
        FindCommand findCommand = new FindCommand(descriptor);

        Model expectedModel = new ModelManager(new InternshipCatalogue(model.getInternshipCatalogue()),
                new EventCatalogue(model.getEventCatalogue()), new UserPrefs());

        // Changes in Model after Filtering the list
//        ArrayList<Internship> l = new ArrayList<>();
//        l.add(SE3);
//        ReadOnlyInternshipCatalogue r = new InternshipCatalogue();
//        ((InternshipCatalogue) r).setInternships(l);
////        expectedModel.setInternshipCatalogue(r);
        expectedModel.updateFilteredInternshipList(x -> x.equals(SE3));

//        expectedModel.updateFilteredEventList(new EventByInternship(expectedModel.getSelectedInternship()));
//        ObservableList<Event> events = expectedModel.getFilteredEventList();
        CommandResult expectedCommandResult = new CommandResult(
                String.format(FindCommand.MESSAGE_SUCCESS, 1),
                ResultType.FIND);

        assertCommandSuccess(findCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        Internship findInternship = new InternshipBuilder().withPosition().;
//        FindCommand.FilterInternshipDescriptor descriptor = new FilterInternshipDescriptorBuilder(findInternship).build();
//        FindCommand findCommand = new FindCommand(descriptor);

        FindCommand.FilterInternshipDescriptor descriptor = new FindCommand.FilterInternshipDescriptor();
        descriptor.setPosition(new Position("Engineer"));
        descriptor.setStatus(new Status(1));
        FindCommand findCommand = new FindCommand(descriptor);

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
        expectedModel.updateFilteredEventList(x -> x.equals(SE3) || x.equals(SE4) || x.equals(BE1));

//        expectedModel.updateFilteredEventList(new EventByInternship(expectedModel.getSelectedInternship()));
//        ObservableList<Event> events = expectedModel.getFilteredEventList();
        CommandResult expectedCommandResult = new CommandResult(
                String.format(FindCommand.MESSAGE_SUCCESS, 3),
                ResultType.FIND);

        assertCommandSuccess(findCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        FindCommand.FilterInternshipDescriptor descriptor = new FindCommand.FilterInternshipDescriptor();
        FindCommand findCommand = new FindCommand(descriptor);

        assertCommandFailure(findCommand, model, String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }


}
