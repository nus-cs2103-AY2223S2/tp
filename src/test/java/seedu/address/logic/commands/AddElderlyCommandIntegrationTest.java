package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Elderly;
import seedu.address.testutil.ElderlyBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddElderlyCommand}.
 */
public class AddElderlyCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFriendlyLink(), new UserPrefs());
    }

    @Test
    public void execute_newElderly_success() {
        Elderly validElderly = new ElderlyBuilder().build();

        Model expectedModel = new ModelManager(model.getFriendlyLink(), new UserPrefs());
        expectedModel.addElderly(validElderly);

        assertCommandSuccess(new AddElderlyCommand(validElderly), model,
                String.format(AddElderlyCommand.MESSAGE_SUCCESS, validElderly), expectedModel);
    }

    @Test
    public void execute_duplicateElderly_throwsCommandException() {
        Elderly elderlyInList = model.getFriendlyLink().getElderlyList().get(0);
        assertCommandFailure(new AddElderlyCommand(elderlyInList), model,
                Messages.MESSAGE_DUPLICATE_ELDERLY);
    }

}
