package seedu.library.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.library.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_AUTHOR_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_GENRE_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_PROGRESS_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_RATING_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_URL_BOB;

import org.junit.jupiter.api.Test;

import seedu.library.logic.commands.EditCommand.EditBookmarkDescriptor;
import seedu.library.testutil.EditBookmarkDescriptorBuilder;

public class EditBookmarkDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditBookmarkDescriptor descriptorWithSameValues = new EditBookmarkDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditBookmarkDescriptor editedAmy = new EditBookmarkDescriptorBuilder(DESC_AMY)
                .withTitle(VALID_TITLE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different progress -> returns false
        editedAmy = new EditBookmarkDescriptorBuilder(DESC_AMY).withProgress(VALID_PROGRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different genre -> returns false
        editedAmy = new EditBookmarkDescriptorBuilder(DESC_AMY).withGenre(VALID_GENRE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different author -> returns false
        editedAmy = new EditBookmarkDescriptorBuilder(DESC_AMY).withAuthor(VALID_AUTHOR_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditBookmarkDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different rating -> returns false
        editedAmy = new EditBookmarkDescriptorBuilder(DESC_AMY).withRating(VALID_RATING_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different url -> returns false
        editedAmy = new EditBookmarkDescriptorBuilder(DESC_AMY).withUrl(VALID_URL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
