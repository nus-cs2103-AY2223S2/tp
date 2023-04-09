package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.socket.testutil.TypicalPersons.ALICE;
import static seedu.socket.testutil.TypicalPersons.BENSON;
import static seedu.socket.testutil.TypicalPersons.CARL;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.socket.commons.core.Messages;
import seedu.socket.commons.core.index.Index;
import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.UserPrefs;
import seedu.socket.model.person.Name;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;
import seedu.socket.testutil.ProjectBuilder;
import seedu.socket.testutil.TypicalProjects;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UnassignCommand.
 */
public class UnassignCommandTest {
    @Test
    public void execute_unassign_success() {
        Model model = new ModelManager(TypicalProjects.getTypicalSocket(), new UserPrefs());
        Person targetMember = ALICE;
        Name name = targetMember.getName();
        Project targetProject = model.getFilteredProjectList().get(0);
        Set<Person> newMembers = new HashSet<>();
        newMembers.add(BENSON);
        Project editedProject = new ProjectBuilder(targetProject).withMembers(newMembers).build();

        Model expectedModel = new ModelManager(TypicalProjects.getTypicalSocket(), new UserPrefs());
        expectedModel.setProject(targetProject, editedProject);
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PROJECT, name);
        String expectedMessage = String.format(UnassignCommand.MESSAGE_UNASSIGN_SUCCESS, name,
                model.getFilteredProjectList().get(0).getName());
        assertCommandSuccess(unassignCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_invalidIndex_failure() {
        Model model = new ModelManager(TypicalProjects.getTypicalSocket(), new UserPrefs());
        Person targetMember = ALICE;
        Name name = targetMember.getName();
        UnassignCommand unassignCommand = new UnassignCommand(Index.fromOneBased(4), name);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, name);
        assertCommandFailure(unassignCommand, model, expectedMessage);
    }

    @Test
    public void execute_memberNotInProject_failure() {
        Model model = new ModelManager(TypicalProjects.getTypicalSocket(), new UserPrefs());
        Person targetMember = CARL;
        Name name = targetMember.getName();
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PROJECT, name);
        String expectedMessage = String.format(UnassignCommand.MESSAGE_NOT_IN_PROJECT, name);
        assertCommandFailure(unassignCommand, model, expectedMessage);
    }
    @Test
    public void testEquals() {
        Name name = new Name(VALID_NAME_AMY);
        UnassignCommand unassignFirstCommand = new UnassignCommand(INDEX_FIRST_PROJECT, name);
        UnassignCommand unassignSecondCommand = new UnassignCommand(INDEX_FIRST_PROJECT, name);
        // same object -> returns true
        assertTrue(unassignFirstCommand.equals(unassignFirstCommand));
        // same values -> returns true
        assertTrue(unassignFirstCommand.equals(unassignSecondCommand));
        // different types -> returns false
        assertFalse(unassignFirstCommand.equals(1));
        // null -> returns false
        assertFalse(unassignFirstCommand.equals(null));
        // different name -> returns false
        assertFalse(unassignFirstCommand.equals(new UnassignCommand(INDEX_FIRST_PROJECT, new Name("Bob"))));
        // different index -> returns false
        assertFalse(unassignFirstCommand.equals(new UnassignCommand(Index.fromOneBased(2), name)));
    }
}
