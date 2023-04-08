package seedu.dengue.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.UserPrefs;
import seedu.dengue.model.overview.AgeOverview;
import seedu.dengue.model.overview.PostalOverview;
import seedu.dengue.model.overview.VariantOverview;
import seedu.dengue.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class OverviewCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void resetModels() {
        model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
    }

    @Test
    public void execute_postalOverview_successBeforeGet() {
        OverviewCommand overviewCommand = new OverviewCommand(new PostalOverview(), "POSTAL");
        String expectedMessage = String.format(OverviewCommand.MESSAGE_SUCCESS, "POSTAL");

        expectedModel.setOverview(new PostalOverview());

        assertCommandSuccess(overviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_postalOverview_successAfterGet() {
        OverviewCommand overviewCommand = new OverviewCommand(new PostalOverview(), "POSTAL");
        overviewCommand.execute(model);
        model.getOverview();

        List<Person> personList = expectedModel.getDengueHotspotTracker().getPersonList();
        expectedModel.setOverview(new PostalOverview(personList));

        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_ageOverview_successBeforeGet() {
        OverviewCommand overviewCommand = new OverviewCommand(new AgeOverview(), "AGE");
        String expectedMessage = String.format(OverviewCommand.MESSAGE_SUCCESS, "AGE");

        expectedModel.setOverview(new AgeOverview());

        assertCommandSuccess(overviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_ageOverview_successAfterGet() {
        OverviewCommand overviewCommand = new OverviewCommand(new AgeOverview(), "AGE");
        overviewCommand.execute(model);
        model.getOverview();

        List<Person> personList = expectedModel.getDengueHotspotTracker().getPersonList();
        expectedModel.setOverview(new AgeOverview(personList));

        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_variantOverview_successBeforeGet() {
        OverviewCommand overviewCommand = new OverviewCommand(new VariantOverview(), "VARIANT");
        String expectedMessage = String.format(OverviewCommand.MESSAGE_SUCCESS, "VARIANT");

        expectedModel.setOverview(new VariantOverview());

        assertCommandSuccess(overviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_variantOverview_successAfterGet() {
        OverviewCommand overviewCommand = new OverviewCommand(new VariantOverview(), "VARIANT");
        overviewCommand.execute(model);
        model.getOverview();

        List<Person> personList = expectedModel.getDengueHotspotTracker().getPersonList();
        expectedModel.setOverview(new VariantOverview(personList));

        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_postalOverviewFromAge_successBeforeGet() {
        model.setOverview(new AgeOverview());
        model.getOverview();

        OverviewCommand overviewCommand = new OverviewCommand(new PostalOverview(), "POSTAL");
        String expectedMessage = String.format(OverviewCommand.MESSAGE_SUCCESS, "POSTAL");

        assertCommandSuccess(overviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_postalOverviewFromAge_successAfterGet() {
        model.setOverview(new AgeOverview());
        model.getOverview();

        OverviewCommand overviewCommand = new OverviewCommand(new PostalOverview(), "POSTAL");
        overviewCommand.execute(model);
        model.getOverview();
        expectedModel.getOverview();

        assertEquals(expectedModel, model);
    }
}
