package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalOpenings.getTypicalUltron;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.OpeningBuilder;
import seedu.ultron.logic.commands.AddCommand;
import seedu.ultron.model.Model;
import seedu.ultron.model.ModelManager;
import seedu.ultron.model.UserPrefs;
import seedu.ultron.model.opening.Opening;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalUltron(), new UserPrefs());
    }

    @Test
    public void execute_newOpening_success() {
        Opening validOpening = new OpeningBuilder().build();

        Model expectedModel = new ModelManager(model.getUltron(), new UserPrefs());
        expectedModel.addOpening(validOpening);

        assertCommandSuccess(new AddCommand(validOpening), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validOpening), expectedModel);
    }

    @Test
    public void execute_duplicateOpening_throwsCommandException() {
        Opening openingInList = model.getUltron().getOpeningList().get(0);
        assertCommandFailure(new AddCommand(openingInList), model, AddCommand.MESSAGE_DUPLICATE_OPENING);
    }

}
