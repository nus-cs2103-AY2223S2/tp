package teambuilder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import teambuilder.commons.core.GuiSettings;
import teambuilder.commons.core.Memento;
import teambuilder.commons.util.HistoryUtil;
import teambuilder.logic.commands.exceptions.CommandException;
import teambuilder.model.Model;
import teambuilder.model.ReadOnlyTeamBuilder;
import teambuilder.model.ReadOnlyUserPrefs;
import teambuilder.model.person.Person;
import teambuilder.model.team.Team;

public class RedoCommandIntegrationTest {
    @Test
    public void constructor_noNull() {
        RedoCommand undoer = new RedoCommand();
        assertTrue(undoer != null);
    }

    @Test
    public void execute_redoUnsuccessful_failureMessage() throws CommandException {
        Model stub = new ModelStub();
        HistoryUtil history = HistoryUtil.getInstance();
        while (history.redo().isPresent())
            ;
        CommandResult commandResult = new RedoCommand().execute(stub);
        assertEquals(RedoCommand.MESSAGE_FAILURE, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_restoreUnsuccessful_failureMessage() throws CommandException {
        Model stub = new EmptyModelStub();
        HistoryUtil history = HistoryUtil.getInstance();
        history.storePast(stub.save(), EmptyMomentoStub.DESC);
        history.undo();

        CommandResult commandResult = new RedoCommand().execute(stub);
        assertEquals(RedoCommand.MESSAGE_FAILURE, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_restoreSuccessFul_successMessage() throws CommandException {
        Model stub = new ModelStub();
        HistoryUtil history = HistoryUtil.getInstance();
        history.storePast(new FilledMomento(), FilledMomento.DESC);
        history.undo();

        CommandResult commandResult = new RedoCommand().execute(stub);
        assertEquals(RedoCommand.MESSAGE_SUCCESS + FilledMomento.DESC, commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        RedoCommand one = new RedoCommand();
        RedoCommand ptrOne = one;
        RedoCommand two = new RedoCommand();
        Object obj = new Object();
        String str = "Hello!";

        assertTrue(one.equals(ptrOne));
        assertTrue(ptrOne.equals(one));

        assertFalse(two.equals(one));
        assertFalse(one.equals(two));

        assertFalse(one.equals(str));
        assertFalse(one.equals(obj));

    }

    private class FilledMomento implements Memento {
        private static final String DESC = "Everything";

        @Override
        public boolean restore() {
            return true;
        }

        @Override
        public Memento getUpdatedMemento() {
            return new FilledMomento();
        }
    }

    private class ModelStub implements Model {

        @Override
        public Memento save() {
            return new FilledMomento();
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new UnsupportedOperationException("Unimplemented method 'setUserPrefs'");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new UnsupportedOperationException("Unimplemented method 'getUserPrefs'");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new UnsupportedOperationException("Unimplemented method 'getGuiSettings'");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new UnsupportedOperationException("Unimplemented method 'setGuiSettings'");
        }

        @Override
        public Path getAddressBookFilePath() {

            throw new UnsupportedOperationException("Unimplemented method 'getAddressBookFilePath'");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new UnsupportedOperationException("Unimplemented method 'setAddressBookFilePath'");
        }

        @Override
        public void setTeamBuilder(ReadOnlyTeamBuilder teamBuilder) {
            throw new UnsupportedOperationException("Unimplemented method 'setAddressBook'");
        }

        @Override
        public ReadOnlyTeamBuilder getTeamBuilder() {
            throw new UnsupportedOperationException("Unimplemented method 'getAddressBook'");
        }

        @Override
        public boolean hasPerson(Person person) {

            throw new UnsupportedOperationException("Unimplemented method 'hasPerson'");
        }

        @Override
        public void deletePerson(Person target) {

            throw new UnsupportedOperationException("Unimplemented method 'deletePerson'");
        }

        @Override
        public void addPerson(Person person) {

            throw new UnsupportedOperationException("Unimplemented method 'addPerson'");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {

            throw new UnsupportedOperationException("Unimplemented method 'setPerson'");
        }

        @Override
        public boolean hasTeam(Team team) {
            throw new UnsupportedOperationException("Unimplemented method 'hasTeam'");
        }

        @Override
        public void deleteTeam(Team target) {
            throw new UnsupportedOperationException("Unimplemented method 'deleteTeam'");
        }

        @Override
        public void addTeam(Team team) {
            throw new UnsupportedOperationException("Unimplemented method 'addTeam'");
        }

        @Override
        public void updatePersonInTeams(Person person) {
            throw new UnsupportedOperationException("Unimplemented method 'updatePersonInTeams'");
        }

        @Override
        public void removeFromAllTeams(Person person) {
            throw new UnsupportedOperationException("Unimplemented method 'removeFromAllTeams'");
        }

        @Override
        public ObservableList<Person> getSortedPersonList() {
            throw new UnsupportedOperationException("Unimplemented method 'getSortedPersonList'");
        }

        @Override
        public void setTeam(Team team, Team editedTeam) {
            throw new UnsupportedOperationException("Unimplemented method 'setTeam'");
        }

        @Override
        public ObservableList<Team> getSortedTeamList() {
            throw new UnsupportedOperationException("Unimplemented method 'getSortedTeamList'");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new UnsupportedOperationException("Unimplemented method 'updateFilteredPersonList'");
        }

        @Override
        public void updateFilteredTeamList(Predicate<Team> predicate) {
            throw new UnsupportedOperationException("Unimplemented method 'updateFilteredTeamList'");
        }

        @Override
        public void updateSortPerson(Comparator<Person> comparator) {
            throw new UnsupportedOperationException("Unimplemented method 'updateSortPerson'");
        }

        @Override
        public void updateSortTeam(Comparator<Team> comparator) {
            throw new UnsupportedOperationException("Unimplemented method 'updateSortTeam'");
        }

        @Override
        public ObservableList<Team> getTeamList() {
            throw new AssertionError("This method should not be called.");
        }

    }

    private class EmptyMomentoStub implements Memento {
        private static final String DESC = "This should not matter";

        @Override
        public boolean restore() {
            return false;
        }

        @Override
        public Memento getUpdatedMemento() {
            return new EmptyMomentoStub();
        }
    }

    private class EmptyModelStub extends ModelStub {

        @Override
        public Memento save() {
            return new EmptyMomentoStub();
        }
    }
}
