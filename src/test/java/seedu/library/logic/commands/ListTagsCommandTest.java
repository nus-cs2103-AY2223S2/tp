package seedu.library.logic.commands;

import static seedu.library.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.library.model.Library;
import seedu.library.model.Model;
import seedu.library.model.ModelManager;
import seedu.library.model.UserPrefs;
import seedu.library.testutil.TagsBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTagCommand.
 */

public class ListTagsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new Library(), new UserPrefs(), new TagsBuilder().getTypicalTags());
        expectedModel = new ModelManager(new Library(), new UserPrefs(), model.getTags());
    }

    @Test
    public void execute_listTagIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListTagsCommand(), model,
                ListTagsCommand.MESSAGE_SUCCESS + model.tagListToString(), expectedModel);
    }

}
