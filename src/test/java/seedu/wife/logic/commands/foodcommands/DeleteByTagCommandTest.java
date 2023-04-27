package seedu.wife.logic.commands.foodcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_CHOCOLATE;
import static seedu.wife.testutil.TypicalWife.getTypicalWife;
import static seedu.wife.testutil.TypicalWife.getTypicalWifeWithoutFoodTag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;
import seedu.wife.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteByTagCommand}.
 */
public class DeleteByTagCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWife(), new UserPrefs());
        expectedModel = new ModelManager(model.getWife(), new UserPrefs());
    }

    @Test
    public void execute_validTag_success() {
        DeleteByTagCommand deleteByTagCommand = new DeleteByTagCommand(new Tag(VALID_TAG_CHOCOLATE));

        ModelManager expectedModel = new ModelManager(model.getWife(), new UserPrefs());
        assertEquals(deleteByTagCommand.execute(expectedModel), deleteByTagCommand.execute(model));
    }

    @Test
    public void execute_invalidTag_deletesNothing() {
        this.model = new ModelManager(getTypicalWifeWithoutFoodTag(), new UserPrefs());
        this.expectedModel = new ModelManager(model.getWife(), new UserPrefs());

        DeleteByTagCommand deleteByTagCommand = new DeleteByTagCommand(new Tag(VALID_TAG_CHOCOLATE));

        assertEquals(deleteByTagCommand.execute(expectedModel), deleteByTagCommand.execute(model));
    }

}
