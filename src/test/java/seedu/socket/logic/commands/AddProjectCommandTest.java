package seedu.socket.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.socket.commons.core.GuiSettings;
import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.model.Model;
import seedu.socket.model.ReadOnlySocket;
import seedu.socket.model.ReadOnlyUserPrefs;
import seedu.socket.model.Socket;
import seedu.socket.model.person.Person;
import seedu.socket.model.project.Project;
import seedu.socket.testutil.ProjectBuilder;

public class AddProjectCommandTest {

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProjectCommand(null));
    }

    @Test
    public void execute_projectAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingProjectAdded modelStub = new ModelStubAcceptingProjectAdded();
        Project validProject = new ProjectBuilder().build();

        CommandResult commandResult = new AddProjectCommand(validProject).execute(modelStub);

        assertEquals(String.format(AddProjectCommand.MESSAGE_SUCCESS, validProject), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProject), modelStub.projectsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Project validProject = new ProjectBuilder().build();
        AddProjectCommand addProjectCommand = new AddProjectCommand(validProject);
        ModelStub modelStub = new ModelStubWithProject(validProject);

        assertThrows(CommandException.class,
                AddProjectCommand.MESSAGE_DUPLICATE_PROJECT, () -> addProjectCommand.execute(modelStub));
    }

    @Test
    public void testEquals() {
        Project project1 = new ProjectBuilder().withName("project1").build();
        Project project2 = new ProjectBuilder().withName("project2").build();
        AddProjectCommand addProject1Command = new AddProjectCommand(project1);
        AddProjectCommand addProject2Command = new AddProjectCommand(project2);

        // same object -> returns true
        assertTrue(addProject1Command.equals(addProject1Command));

        // same values -> returns true
        AddProjectCommand addProject1CommandCopy = new AddProjectCommand(project1);
        assertTrue(addProject1Command.equals(addProject1CommandCopy));

        // different types -> returns false
        assertFalse(addProject1Command.equals(1));

        // null -> returns false
        assertFalse(addProject1Command.equals(null));

        // different person -> returns false
        assertFalse(addProject1Command.equals(addProject2Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getViewedPerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateViewedPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public ObservableList<Project> getViewedProject() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateViewedProject(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getSocketFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSocketFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSocket(ReadOnlySocket newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySocket getSocket() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDeleteMultiplePerson(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortPersonList(String category) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProject(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProject(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProject(Project target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProject(Project target, Project editedProject) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProjects(List<Project> projects) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Project> getFilteredProjectList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProjectList(Predicate<Project> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortProjectList(String category) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitSocket() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoSocket() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoSocket() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoSocket() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoSocket() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single project.
     */
    private class ModelStubWithProject extends ModelStub {
        private final Project project;

        ModelStubWithProject(Project project) {
            requireNonNull(project);
            this.project = project;
        }

        @Override
        public boolean hasProject(Project project) {
            requireNonNull(project);
            return this.project.isSameProject(project);
        }
    }

    /**
     * A Model stub that always accept the project being added.
     */
    private class ModelStubAcceptingProjectAdded extends ModelStub {
        final ArrayList<Project> projectsAdded = new ArrayList<>();

        @Override
        public boolean hasProject(Project project) {
            requireNonNull(project);
            return projectsAdded.stream().anyMatch(project::isSameProject);
        }

        @Override
        public void addProject(Project project) {
            requireNonNull(project);
            projectsAdded.add(project);
        }

        @Override
        public ReadOnlySocket getSocket() {
            return new Socket();
        }

        // AddProjectCommand#execute calls commitSocket()
        @Override
        public void commitSocket() {
        }
    }

}

