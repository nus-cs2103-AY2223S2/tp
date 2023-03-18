package seedu.socket.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_DEADLINE_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_HOST_BRAVO;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PROJECT_REPO_NAME_BRAVO;
import static seedu.socket.testutil.Assert.assertThrows;
import static seedu.socket.testutil.TypicalPersons.ALICE;
import static seedu.socket.testutil.TypicalPersons.BOB;
import static seedu.socket.testutil.TypicalPersons.CARL;
import static seedu.socket.testutil.TypicalPersons.DANIEL;
import static seedu.socket.testutil.TypicalProjects.ALPHA;
import static seedu.socket.testutil.TypicalProjects.BRAVO;

import org.junit.jupiter.api.Test;

import seedu.socket.testutil.ProjectBuilder;

public class ProjectTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Project project = new ProjectBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> project.getMembers().remove(0));
    }

    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(ALPHA.isSameProject(ALPHA));

        // null -> returns false
        assertFalse(ALPHA.isSameProject(null));

        // same name, all other attributes different -> returns true
        Project editedAlpha = new ProjectBuilder(ALPHA).withRepoHost(VALID_PROJECT_REPO_HOST_BRAVO)
            .withRepoName(VALID_PROJECT_REPO_NAME_BRAVO).withProjectDeadline(VALID_PROJECT_DEADLINE_BRAVO)
            .withMembers(ALICE, BOB).build();
        assertTrue(ALPHA.isSameProject(editedAlpha));

        // different name, all other attributes same -> returns false
        editedAlpha = new ProjectBuilder(ALPHA).withName(VALID_PROJECT_NAME_BRAVO).build();
        assertFalse(ALPHA.isSameProject(editedAlpha));

        // name differs in case, all other attributes same -> returns false
        Project editedBravo = new ProjectBuilder(BRAVO).withName(VALID_PROJECT_NAME_BRAVO.toLowerCase()).build();
        assertFalse(BRAVO.isSameProject(editedBravo));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_PROJECT_NAME_BRAVO + " ";
        editedBravo = new ProjectBuilder(BRAVO).withName(nameWithTrailingSpaces).build();
        assertFalse(BRAVO.isSameProject(editedBravo));
    }

    @Test
    public void testEquals() {
        // same values -> returns true
        Project alphaCopy = new ProjectBuilder(ALPHA).build();
        assertTrue(ALPHA.equals(alphaCopy));

        // same object -> returns true
        assertTrue(ALPHA.equals(ALPHA));

        // null -> returns false
        assertFalse(ALPHA.equals(null));

        // different type -> returns false
        assertFalse(ALPHA.equals(5));

        // different project -> returns false
        assertFalse(ALPHA.equals(BRAVO));

        // different name -> returns false
        Project editedAlpha = new ProjectBuilder(ALPHA).withName(VALID_PROJECT_NAME_BRAVO).build();
        assertFalse(ALPHA.equals(editedAlpha));

        // different repo host -> returns false
        editedAlpha = new ProjectBuilder(ALPHA).withRepoHost(VALID_PROJECT_REPO_HOST_BRAVO).build();
        assertFalse(ALPHA.equals(editedAlpha));

        // different repo name -> returns false
        editedAlpha = new ProjectBuilder(ALPHA).withRepoName(VALID_PROJECT_REPO_NAME_BRAVO).build();
        assertFalse(ALPHA.equals(editedAlpha));

        // different deadline -> returns false
        editedAlpha = new ProjectBuilder(ALPHA).withProjectDeadline(VALID_PROJECT_DEADLINE_BRAVO).build();
        assertFalse(ALPHA.equals(editedAlpha));

        // different members -> returns false
        editedAlpha = new ProjectBuilder(ALPHA).withMembers(CARL, DANIEL).build();
        assertFalse(ALPHA.equals(editedAlpha));
    }
}
