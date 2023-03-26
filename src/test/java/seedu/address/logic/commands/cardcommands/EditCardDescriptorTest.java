package seedu.address.logic.commands.cardcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GRAVITY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HARD;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditCardDescriptorBuilder;

public class EditCardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditCardDescriptor descriptorWithSameValues = new EditCommand.EditCardDescriptor(DESC_GRAVITY);
        assertTrue(DESC_GRAVITY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_GRAVITY.equals(DESC_GRAVITY));

        // null -> returns false
        assertFalse(DESC_GRAVITY.equals(null));

        // different types -> returns false
        assertFalse(DESC_GRAVITY.equals(5));

        // different values -> returns false
        assertFalse(DESC_GRAVITY.equals(DESC_PHOTOSYNTHESIS));

        // different name -> returns false
        EditCommand.EditCardDescriptor editedGravity = new EditCardDescriptorBuilder(DESC_GRAVITY)
                .withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build();
        assertFalse(DESC_GRAVITY.equals(editedGravity));

        // different address -> returns false
        editedGravity = new EditCardDescriptorBuilder(DESC_GRAVITY).withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).build();
        assertFalse(DESC_GRAVITY.equals(editedGravity));

        // different tags -> returns false
        editedGravity = new EditCardDescriptorBuilder(DESC_GRAVITY).withTag(VALID_TAG_HARD).build();
        assertFalse(DESC_GRAVITY.equals(editedGravity));
    }
}
