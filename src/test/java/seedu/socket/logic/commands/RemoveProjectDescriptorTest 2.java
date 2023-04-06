package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_MEETING_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_MEETING_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_ALPHA;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_NAME_BRAVO;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.RemoveProjectCommand.RemoveProjectDescriptor;
import seedu.socket.testutil.RemoveProjectDescriptorBuilder;

public class RemoveProjectDescriptorTest {

    final RemoveProjectCommand.RemoveProjectDescriptor descAlpha = new RemoveProjectDescriptorBuilder()
            .withRepoHost(VALID_PROJECT_REPO_HOST_ALPHA)
            .withMeeting(VALID_PROJECT_MEETING_ALPHA).build();

    final RemoveProjectCommand.RemoveProjectDescriptor descBravo = new RemoveProjectDescriptorBuilder()
            .withRepoHost(VALID_PROJECT_REPO_HOST_BRAVO)
            .withMeeting(VALID_PROJECT_MEETING_BRAVO).build();
    @Test
    public void equals() {

        // same values -> returns true
        RemoveProjectDescriptor descriptorWithSameValues = new RemoveProjectDescriptor(descAlpha);
        assertTrue(descAlpha.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(descAlpha.equals(descAlpha));

        // null -> returns false
        assertFalse(descAlpha.equals(null));

        // different types -> returns false
        assertFalse(descAlpha.equals(5));

        // different values but same fields -> returns true
        assertTrue(descAlpha.equals(descBravo));

        // different repo host -> returns true
        RemoveProjectDescriptor removedAlpha = new RemoveProjectDescriptorBuilder(descAlpha)
                .withRepoHost(VALID_PROJECT_REPO_HOST_BRAVO).build();
        assertTrue(descAlpha.equals(removedAlpha));

        // add repo name -> returns true
        removedAlpha = new RemoveProjectDescriptorBuilder(descAlpha)
                .withRepoName(VALID_PROJECT_REPO_NAME_BRAVO).build();
        assertTrue(descAlpha.equals(removedAlpha));

        // different deadline -> returns true
        removedAlpha = new RemoveProjectDescriptorBuilder(descAlpha).withDeadline(VALID_PROJECT_DEADLINE_BRAVO).build();
        assertTrue(descAlpha.equals(removedAlpha));

        // different meeting -> returns true
        removedAlpha = new RemoveProjectDescriptorBuilder(descAlpha).withMeeting(VALID_PROJECT_MEETING_BRAVO).build();
        assertTrue(descBravo.equals(removedAlpha));
    }
}
