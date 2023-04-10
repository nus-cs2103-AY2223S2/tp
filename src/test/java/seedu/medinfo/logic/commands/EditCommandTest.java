package seedu.medinfo.logic.commands;

import static seedu.medinfo.testutil.TypicalPatients.getTypicalMedInfo;

import seedu.medinfo.model.Model;
import seedu.medinfo.model.ModelManager;
import seedu.medinfo.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalMedInfo(), new UserPrefs());

}
