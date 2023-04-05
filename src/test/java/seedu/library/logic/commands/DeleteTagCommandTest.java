package seedu.library.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.library.testutil.Assert.assertThrows;
import static seedu.library.testutil.TypicalBookmarks.getTypicalLibrary;

import org.junit.jupiter.api.Test;

import seedu.library.model.Model;
import seedu.library.model.ModelManager;
import seedu.library.model.UserPrefs;
import seedu.library.model.tag.Tag;
import seedu.library.testutil.TagsBuilder;

public class DeleteTagCommandTest {

    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs(), new TagsBuilder().getTypicalTags());

    @Test
    public void execute_deleteTag_success() {
        Tag tagToDelete = new Tag("plant");
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(tagToDelete);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagToDelete);

        ModelManager expectedModel = new ModelManager(model.getLibrary(), new UserPrefs(), model.getTags());
        expectedModel.deleteTag(tagToDelete);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteInvalidTag_throwsCommandException() {
        assertThrows(IllegalArgumentException.class, Tag.MESSAGE_CONSTRAINTS, () ->
                new DeleteTagCommand(new Tag("abc 123 abc")).execute(model));
    }

    @Test
    public void equals() {
        DeleteTagCommand deletePlantCommand = new DeleteTagCommand(new Tag("plant"));
        DeleteTagCommand deleteNovelCommand = new DeleteTagCommand(new Tag("novel"));

        // same object -> returns true
        assertTrue(deletePlantCommand.equals(deletePlantCommand));

        // same values -> returns true
        DeleteTagCommand deletePlantCommandCopy = new DeleteTagCommand(new Tag("plant"));
        assertTrue(deletePlantCommand.equals(deletePlantCommandCopy));

        // different types -> returns false
        assertFalse(deletePlantCommand.equals(1));

        // null -> returns false
        assertFalse(deletePlantCommand.equals(null));

        // different bookmark -> returns false
        assertFalse(deletePlantCommand.equals(deleteNovelCommand));
    }
}
