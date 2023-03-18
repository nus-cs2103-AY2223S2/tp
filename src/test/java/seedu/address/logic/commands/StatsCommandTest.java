package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;
import static seedu.address.testutil.TestUtil.getTypicalModelManager;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Summary;
import seedu.address.logic.aggregatefunction.Count;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.testutil.ModelManagerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class StatsCommandTest {

    private Model model;
    private Model expectedModel;

    @Test
    public void execute_emptyModel() {
        model = new ModelManagerBuilder().build();

        expectedModel = new ModelManagerBuilder().build();
        Summary summary = new Summary();
        summary.describe(new Count<>(new ArrayList<>(), Elderly.class));
        summary.describe(new Count<>(new ArrayList<>(), Volunteer.class));
        summary.describe(new Count<>(new ArrayList<>(), Pair.class));
        String expectedMessage = summary.toString();

        assertCommandSuccess(new StatsCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_typicalModel() {
        model = getTypicalModelManager();

        FriendlyLink friendlyLink = getTypicalFriendlyLink();
        expectedModel = getTypicalModelManager();
        Summary summary = new Summary();
        summary.describe(new Count<>(friendlyLink.getElderlyList(), Elderly.class));
        summary.describe(new Count<>(friendlyLink.getVolunteerList(), Volunteer.class));
        summary.describe(new Count<>(friendlyLink.getPairList(), Pair.class));
        String expectedMessage = summary.toString();

        assertCommandSuccess(new StatsCommand(), model, expectedMessage, expectedModel);

    }
}
