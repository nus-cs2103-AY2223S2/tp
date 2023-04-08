package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalOpenings.getTypicalUltron;
import static seedu.ultron.model.Model.PREDICATE_SHOW_ALL_OPENINGS;

import org.junit.jupiter.api.Test;

import seedu.ultron.logic.commands.ClearCommand;
import seedu.ultron.model.Model;
import seedu.ultron.model.ModelManager;
import seedu.ultron.model.Ultron;
import seedu.ultron.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyUltron_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyUltron_success() {
        Model model = new ModelManager(getTypicalUltron(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalUltron(), new UserPrefs());
        Ultron emptyUltron = new Ultron();
        expectedModel.setUltron(emptyUltron);
        expectedModel.updateFilteredOpeningList(PREDICATE_SHOW_ALL_OPENINGS);
        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
