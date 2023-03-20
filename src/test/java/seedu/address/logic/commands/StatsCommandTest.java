package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getElderly;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;
import static seedu.address.testutil.TestUtil.getTypicalModelManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Summary;
import seedu.address.logic.aggregatefunction.Count;
import seedu.address.logic.aggregatefunction.MaxCount;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.testutil.ModelManagerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for StatsCommand.
 */
public class StatsCommandTest {

    private Model model;
    private Model expectedModel;

    @Test
    public void execute_emptyModel() {
        model = new ModelManagerBuilder().build();

        expectedModel = new ModelManagerBuilder().build();
        Summary summary = new Summary();
        summary.describe(new Count<>(new ArrayList<>(), StatsCommand.ELDERLY_COUNT));
        summary.describe(new Count<>(new ArrayList<>(), StatsCommand.UNPAIRED_ELDERLY_COUNT));
        summary.describe(new Count<>(new ArrayList<>(), StatsCommand.VOLUNTEER_COUNT));
        summary.describe(new Count<>(new ArrayList<>(), StatsCommand.UNPAIRED_VOLUNTEER_COUNT));
        summary.describe(new Count<>(new ArrayList<>(), StatsCommand.PAIR_COUNT));
        summary.describe(new Count<>(new ArrayList<>(), StatsCommand.MAX_VOLUNTEER_PER_ELDERLY));
        summary.describe(new Count<>(new ArrayList<>(), StatsCommand.MAX_ELDERLY_PER_VOLUNTEER));
        String expectedMessage = summary.toString();

        assertCommandSuccess(new StatsCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_typicalModel() {
        model = getTypicalModelManager();

        FriendlyLink friendlyLink = getTypicalFriendlyLink();
        expectedModel = getTypicalModelManager();

        List<Elderly> pairedElderly = model.getFilteredPairList().stream()
                .map(pair -> pair.getElderly()).collect(Collectors.toList());
        List<Volunteer> pairedVolunteers = model.getFilteredPairList().stream()
                .map(pair -> pair.getVolunteer()).collect(Collectors.toList());
        List<Elderly> unpairedElderly = model.getFilteredElderlyList().stream()
                .filter(elderly -> !pairedElderly.contains(elderly)).collect(Collectors.toList());
        List<Volunteer> unpairedVolunteers = model.getFilteredVolunteerList().stream()
                .filter(volunteer -> !pairedVolunteers.contains(volunteer)).collect(Collectors.toList());

        Summary summary = new Summary();
        summary.describe(new Count<>(friendlyLink.getElderlyList(), StatsCommand.ELDERLY_COUNT));
        summary.describe(new Count<>(unpairedElderly, StatsCommand.UNPAIRED_ELDERLY_COUNT));
        summary.describe(new Count<>(friendlyLink.getVolunteerList(), StatsCommand.VOLUNTEER_COUNT));
        summary.describe(new Count<>(unpairedVolunteers, StatsCommand.UNPAIRED_VOLUNTEER_COUNT));
        summary.describe(new Count<>(friendlyLink.getPairList(), StatsCommand.PAIR_COUNT));
        summary.describe(new MaxCount<>(friendlyLink.getPairList(),
                StatsCommand.MAX_VOLUNTEER_PER_ELDERLY, pair -> pair.getElderly()));
        summary.describe(new MaxCount<>(friendlyLink.getPairList(),
                StatsCommand.MAX_ELDERLY_PER_VOLUNTEER, pair -> pair.getVolunteer()));
        String expectedMessage = summary.toString();

        assertCommandSuccess(new StatsCommand(), model, expectedMessage, expectedModel);

    }
}
