package seedu.internship.logic.commands;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.internship.testutil.TypicalEvents.getTypicalEventCatalogue;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternshipCatalogue;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.internship.model.EventCatalogue;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Position;
import seedu.internship.model.internship.Status;
import seedu.internship.testutil.FilterInternshipDescriptorBuilder;
import seedu.internship.testutil.InternshipBuilder;



/**
 * Contains integration tests (interaction with the Model) and unit tests for FindCommand.
 */
public class FindCommandTest {

    private Model model = new ModelManager(getTypicalInternshipCatalogue(),
            new EventCatalogue(getTypicalEventCatalogue()), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Internship findInternship = new InternshipBuilder().buildForFind();
        FindCommand.FilterInternshipDescriptor descriptor =
                new FilterInternshipDescriptorBuilder(findInternship).build();
        FindCommand findCommand = new FindCommand(descriptor);

        Model expectedModel = new ModelManager(new InternshipCatalogue(model.getInternshipCatalogue()),
                new EventCatalogue(model.getEventCatalogue()), new UserPrefs());

        expectedModel.updateFilteredInternshipList(x -> x.equals(findInternship));

        CommandResult expectedCommandResult = new CommandResult(
                String.format(FindCommand.MESSAGE_SUCCESS, expectedModel.getFilteredInternshipList().size()),
                ResultType.FIND);

        assertCommandSuccess(findCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {

        FindCommand.FilterInternshipDescriptor descriptor = new FindCommand.FilterInternshipDescriptor();
        descriptor.setPosition(new Position("Engineer"));
        descriptor.setStatus(new Status(1));
        FindCommand findCommand = new FindCommand(descriptor);

        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        Model expectedModel = new ModelManager(new InternshipCatalogue(model.getInternshipCatalogue()),
                new EventCatalogue(model.getEventCatalogue()), new UserPrefs());

        Predicate<Internship> finalFilterPos = x -> x.getPosition().positionName.toLowerCase().contains(descriptor
                .getPosition().get().positionName.toLowerCase());
        Predicate<Internship> finalFilterStat = x -> x.getStatus().equals(descriptor.getStatus().get());

        expectedModel.updateFilteredInternshipList(x -> finalFilterPos.test(x) && finalFilterStat.test(x));

        CommandResult expectedCommandResult = new CommandResult(
                String.format(FindCommand.MESSAGE_SUCCESS, 3),
                ResultType.FIND);

        assertCommandSuccess(findCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        FindCommand.FilterInternshipDescriptor descriptor = new FindCommand.FilterInternshipDescriptor();
        FindCommand findCommand = new FindCommand(descriptor);


        assertCommandFailure(findCommand, model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }


}
