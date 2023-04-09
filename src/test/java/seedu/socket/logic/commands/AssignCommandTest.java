package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.testutil.Assert.assertThrows;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.socket.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.socket.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.socket.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.socket.testutil.TypicalPersons.ALICE;
import static seedu.socket.testutil.TypicalPersons.BENSON;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.socket.commons.core.Messages;
import seedu.socket.commons.core.index.Index;
import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.UserPrefs;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;
import seedu.socket.testutil.ProjectBuilder;
import seedu.socket.testutil.TypicalProjects;

public class AssignCommandTest {
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand(INDEX_FIRST_PERSON, null));
        assertThrows(NullPointerException.class, () -> new AssignCommand(null, INDEX_FIRST_PROJECT));
        assertThrows(NullPointerException.class, () -> new AssignCommand(null, null));
    }

    @Test
    public void execute_assign_success() {
        Model model = new ModelManager(TypicalProjects.getTypicalSocket(), new UserPrefs());
        // Index 0 & 1 Person are already members of target Project, hence get Index 2 is used.
        Person targetMember = model.getFilteredPersonList().get(2);
        Project targetProject = model.getFilteredProjectList().get(0);
        Set<Person> newMembers = new HashSet<>();
        newMembers.add(ALICE);
        newMembers.add(BENSON);
        newMembers.add(targetMember);
        Project editedProject = new ProjectBuilder(targetProject).withMembers(newMembers).build();

        Model expectedModel = new ModelManager(TypicalProjects.getTypicalSocket(), new UserPrefs());
        expectedModel.setProject(targetProject, editedProject);
        AssignCommand assignCommand = new AssignCommand(INDEX_THIRD_PERSON, INDEX_FIRST_PROJECT);
        String expectedMessage = String.format(AssignCommand.MESSAGE_ASSIGN_SUCCESS, targetMember.getName(),
                targetProject.getName());
        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMember_failure() {
        Model model = new ModelManager(TypicalProjects.getTypicalSocket(), new UserPrefs());
        Person targetMember = ALICE;
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PROJECT);
        String expectedMessage = String.format(AssignCommand.MESSAGE_ALREADY_IN_PROJECT, targetMember.getName());
        assertCommandFailure(assignCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Model model = new ModelManager(TypicalProjects.getTypicalSocket(), new UserPrefs());

        // One based index, hence must add 2 to offset index 0 & reach out of bound.
        int outOfBoundPerson = model.getFilteredPersonList().size() + 2;
        int outOfBoundProject = model.getFilteredProjectList().size() + 2;

        // AssignCommand to be tested.
        AssignCommand personIndexWrongAssignCommand = new AssignCommand(
                Index.fromOneBased(outOfBoundPerson), INDEX_FIRST_PROJECT);
        AssignCommand projectIndexWrongAssignCommand = new AssignCommand(
                INDEX_FIRST_PERSON, Index.fromOneBased(outOfBoundProject));
        AssignCommand bothIndexWrongAssignCommand = new AssignCommand(
                Index.fromOneBased(outOfBoundPerson), Index.fromOneBased(outOfBoundProject));

        // Message expected when AssignCommand is executed.
        String expectedPersonMessage = String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        String expectedProjectMessage = String.format(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);

        // Test cases.
        assertCommandFailure(personIndexWrongAssignCommand, model, expectedPersonMessage);
        assertCommandFailure(projectIndexWrongAssignCommand, model, expectedProjectMessage);
        assertCommandFailure(bothIndexWrongAssignCommand, model, expectedPersonMessage);
    }

    @Test
    public void testEquals() {
        AssignCommand assignFirstCommand = new AssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PROJECT);

        // Same object -> returns true
        assertTrue(assignFirstCommand.equals(assignFirstCommand));

        // Same values -> returns true
        AssignCommand assignFirstPersonCommandCopy = new AssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PROJECT);
        assertTrue(assignFirstCommand.equals(assignFirstPersonCommandCopy));

        // Different types -> returns false
        assertFalse(assignFirstCommand.equals(1));

        // Null -> returns false
        assertFalse(assignFirstCommand.equals(null));

        // Different person Index -> returns false
        AssignCommand assignSecondPersonCommand = new AssignCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PROJECT);
        assertFalse(assignFirstCommand.equals(assignSecondPersonCommand));

        // Different project Index -> returns false
        AssignCommand assignSecondProjectCommand = new AssignCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PROJECT);
        assertFalse(assignFirstCommand.equals(assignSecondProjectCommand));

        // Different person & project Index -> returns false
        AssignCommand assignSecondCommand = new AssignCommand(INDEX_SECOND_PERSON, INDEX_SECOND_PROJECT);
        assertFalse(assignFirstCommand.equals(assignSecondCommand));
    }
}
