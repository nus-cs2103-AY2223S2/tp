package seedu.wife.logic.commands.foodcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_DAIRY;
import static seedu.wife.testutil.TypicalWife.getTypicalWife;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;
import seedu.wife.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListByTagCommand.
 */
public class ListByTagCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWife(), new UserPrefs());
        expectedModel = new ModelManager(model.getWife(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsFoodThatMatchesTagDairy() {
        ListByTagCommand command = new ListByTagCommand(new Tag(VALID_TAG_DAIRY));

        assertEquals(command.execute(expectedModel), command.execute(model));
    }

    @Test
    public void execute_listIsNotFiltered_showsFoodThatMatchesTagDairyAndNotWithTest() {
        HashSet<Tag> testHashSet = new HashSet<Tag>();
        testHashSet.add(new Tag(VALID_TAG_DAIRY));
        testHashSet.add(new Tag("Test"));
        ListByTagCommand command = new ListByTagCommand(testHashSet);

        assertEquals(command.execute(expectedModel), command.execute(model));
    }

}
