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

public class UndoCommandIntegrationTest {
    @Test
    public void constructor_noNull() {
        UndoCommand undoer = new UndoCommand();
        assertTrue(undoer != null);
    }

    @Test
    public void execute_undoSuccessFul_successMessage() throws CommandException {
        Model stub = new ModelStub();
        HistoryUtil history = HistoryUtil.getInstance();
        history.storePast(new FilledMomento(), FilledMomento.DESC);
        CommandResult commandResult = new UndoCommand().execute(stub);
        assertEquals(UndoCommand.MESSAGE_SUCCESS + FilledMomento.DESC, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_undoUnsuccessful_failureMessage() throws CommandException {
        Model stub = new ModelStub();
        HistoryUtil history = HistoryUtil.getInstance();
        for (int i = 0; i < 11; i++) {
            history.undo();
        };
        CommandResult commandResult = new UndoCommand().execute(stub);
        assertEquals(UndoCommand.MESSAGE_FAILURE, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_restoreUnsuccessful_failureMessage() throws CommandException {
        Model stub = new EmptyModelStub();
        HistoryUtil history = HistoryUtil.getInstance();
        history.storePast(stub.save(), EmptyMomentoStub.DESC);

        CommandResult commandResult = new UndoCommand().execute(stub);
        assertEquals(UndoCommand.MESSAGE_FAILURE, commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        UndoCommand one = new UndoCommand();
        UndoCommand ptrOne = one;
        UndoCommand two = new UndoCommand();
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
            return new Memento() {
                @Override
                public boolean restore() {
                    return true;
                }

                @Override
                public Memento getUpdatedMemento() {
                    return this;
                }
            };
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
            throw new AssertionError("Unimplemented method 'hasTeam'");
        }

        @Override
        public void deleteTeam(Team target) {
            throw new AssertionError("Unimplemented method 'deleteTeam'");
        }

        @Override
        public void addTeam(Team team) {
            throw new AssertionError("Unimplemented method 'addTeam'");
        }

        @Override
        public void updatePersonInTeams(Person person) {
            throw new AssertionError("Unimplemented method 'updatePersonInTeams'");
        }

        @Override
        public void removeFromAllTeams(Person person) {
            throw new AssertionError("Unimplemented method 'removeFromAllTeams'");
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
        public ObservableList<Person> getSortedPersonList() {

            throw new UnsupportedOperationException("Unimplemented method 'getSortedPersonList'");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new UnsupportedOperationException("Unimplemented method 'updateFilteredPersonList'");
        }

        @Override
        public void updateFilteredTeamList(Predicate<Team> predicate) {

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
            throw new AssertionError("Unimplemented method 'getTeamList'");
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
