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
import teambuilder.commons.core.Momento;
import teambuilder.commons.util.HistoryUtil;
import teambuilder.logic.commands.exceptions.CommandException;
import teambuilder.model.Model;
import teambuilder.model.ReadOnlyTeamBuilder;
import teambuilder.model.ReadOnlyUserPrefs;
import teambuilder.model.person.Person;

public class UndoCommandTest {
    @Test
    public void constructor_noNull() {
        UndoCommand undoer = new UndoCommand();
        assertTrue(undoer != null);
    }

    @Test
    public void execute_undoUnsuccessful_failureMessage() throws CommandException {
        Model stub = new ModelStub();
        HistoryUtil history = HistoryUtil.getInstance();
        while (history.undo());
        CommandResult commandResult = new UndoCommand().execute(stub);
        assertEquals(UndoCommand.MESSAGE_FAILURE, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_undoSuccessFul_successMessage() throws CommandException {
        Model stub = new ModelStub();
        HistoryUtil history = HistoryUtil.getInstance();
        history.store(new FilledMomento());
        CommandResult commandResult = new UndoCommand().execute(stub);
        assertEquals(UndoCommand.MESSAGE_SUCCESS + "Everything", commandResult.getFeedbackToUser());
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

    private class FilledMomento implements Momento {
        private String desc = "Everything";

        @Override
        public boolean restore() {
            return true;
        }

        @Override
        public void setDescription(String desc) {
            throw new UnsupportedOperationException("Unimplemented method 'setDescription'");
        }

        @Override
        public String toString() {
            return desc;
        }
    }

    private class ModelStub implements Model {

        @Override
        public Momento save() {
            throw new UnsupportedOperationException("Unimplemented method 'save'");
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
        public void setAddressBook(ReadOnlyTeamBuilder addressBook) {
            throw new UnsupportedOperationException("Unimplemented method 'setAddressBook'");
        }

        @Override
        public ReadOnlyTeamBuilder getAddressBook() {
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
        public ObservableList<Person> getSortedPersonList() {

            throw new UnsupportedOperationException("Unimplemented method 'getSortedPersonList'");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {

            throw new UnsupportedOperationException("Unimplemented method 'updateFilteredPersonList'");
        }

        @Override
        public void updateSort(Comparator<Person> comparator) {
            throw new UnsupportedOperationException("Unimplemented method 'updateSort'");
        }

    }
}
