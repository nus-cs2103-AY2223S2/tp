package seedu.library.logic.commands;

import static seedu.library.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.library.testutil.TypicalBookmarks.getTypicalLibrary;

import org.junit.jupiter.api.Test;

import seedu.library.model.Model;
import seedu.library.model.ModelManager;
import seedu.library.model.UserPrefs;
import seedu.library.testutil.TagsBuilder;

public class SortCommandTest {

    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs(), new TagsBuilder().getTypicalTags());

    @Test
    public void execute_sortByDescendingOrder() {
        String order = "desc";
        SortCommand sortCommand = new SortCommand(order);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS_DESC;

        ModelManager expectedModel = new ModelManager(model.getLibrary(), new UserPrefs(), model.getTags());
        expectedModel.updateSortedBookmarkList(order);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByAscendingOrder() {
        String order = "asc";
        SortCommand sortCommand = new SortCommand(order);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS_ASC;

        ModelManager expectedModel = new ModelManager(model.getLibrary(), new UserPrefs(), model.getTags());
        expectedModel.updateSortedBookmarkList(order);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

}
