package seedu.internship.logic.commands;

import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.testutil.TypicalEvents.getTypicalEventCatalogue;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternshipCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.internship.Statistics;

public class StatsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipCatalogue(), getTypicalEventCatalogue(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternshipCatalogue(), model.getEventCatalogue(), new UserPrefs());
    }
    @Test
    public void execute_stats_success() {
        Statistics expectedStatistics = new Statistics(expectedModel.getFilteredInternshipList(),
                expectedModel.getFilteredEventList());
        CommandResult expectedCommandResult = new CommandResult(StatsCommand.SHOWING_STATS_MESSAGE,
                ResultType.STATS, expectedStatistics);
        assertCommandSuccess(new StatsCommand(), model, expectedCommandResult, expectedModel);
    }
}
