package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_MEETING_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_MEETING_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_NAME_BRAVO;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.EditProjectCommand.EditProjectDescriptor;
import seedu.socket.testutil.EditProjectDescriptorBuilder;

public class EditProjectDescriptorTest {

    final EditProjectDescriptor descAlpha = new EditProjectDescriptorBuilder()
            .withName(VALID_PROJECT_NAME_ALPHA)
            .withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA)
            .withMeeting(VALID_PROJECT_MEETING_ALPHA).build();

    final EditProjectDescriptor descBravo = new EditProjectDescriptorBuilder()
            .withName(VALID_PROJECT_NAME_BRAVO)
            .withRepoHost(VALID_PROJECT_REPO_HOST_BRAVO)
            .withMeeting(VALID_PROJECT_MEETING_BRAVO).build();
    @Test
    public void equals() {
        // same values -> returns true
        EditProjectDescriptor descriptorWithSameValues = new EditProjectDescriptor(descAlpha);
        assertTrue(descAlpha.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(descAlpha.equals(descAlpha));

        // null -> returns false
        assertFalse(descAlpha.equals(null));

        // different types -> returns false
        assertFalse(descAlpha.equals(5));

        // different values -> returns false
        assertFalse(descAlpha.equals(descBravo));

        // different name -> returns false
        EditProjectDescriptor editedAlpha = new EditProjectDescriptorBuilder(descAlpha)
                .withName(VALID_PROJECT_NAME_BRAVO).build();
        assertFalse(descAlpha.equals(editedAlpha));

        // different repo host -> returns false
        editedAlpha = new EditProjectDescriptorBuilder(descAlpha).withRepoHost(VALID_PROJECT_REPO_HOST_BRAVO).build();
        assertFalse(descAlpha.equals(editedAlpha));

        // different repo name -> returns false
        editedAlpha = new EditProjectDescriptorBuilder(descAlpha).withRepoName(VALID_PROJECT_REPO_NAME_BRAVO).build();
        assertFalse(descAlpha.equals(editedAlpha));

        // different deadline -> returns false
        editedAlpha = new EditProjectDescriptorBuilder(descAlpha).withDeadline(VALID_PROJECT_DEADLINE_BRAVO).build();
        assertFalse(descAlpha.equals(editedAlpha));

        // different meeting -> returns false
        editedAlpha = new EditProjectDescriptorBuilder(descAlpha).withMeeting(VALID_PROJECT_MEETING_BRAVO).build();
        assertFalse(descAlpha.equals(editedAlpha));
    }
}
