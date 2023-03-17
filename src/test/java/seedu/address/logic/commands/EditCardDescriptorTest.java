package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HARD;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditCardDescriptorBuilder;

public class EditCardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditCardDescriptor descriptorWithSameValues = new EditCommand.EditCardDescriptor(DESC_AMY);
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
        EditCommand.EditCardDescriptor editedAmy = new EditCardDescriptorBuilder(DESC_AMY)
                .withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditCardDescriptorBuilder(DESC_AMY).withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditCardDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HARD).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
