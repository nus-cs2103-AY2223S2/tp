package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Note;
import seedu.address.model.event.Tutorial;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different person but same email and same phone number -> returns true
        assertEquals(addAliceCommand, addBobCommand);
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private static class ModelStub implements Model {
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTutorial(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLab(Lab lab) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addConsultation(Consultation consultation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudentToTutorial(Index toAdd, Index tutorialIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudentToLab(Index toAdd, Index labIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudentToConsultation(Index toAdd, Index labIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudentFromEvent(Index index, Index eventIndex, String eventType) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
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
        public boolean hasTutorial(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTutorial(Tutorial target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutorial(Tutorial target, Tutorial editedTutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tutorial> getFilteredTutorialList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTutorialList(Predicate<Tutorial> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLab(Lab lab) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLab(Lab target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLab(Lab target, Lab editedLab) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lab> getFilteredLabList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredLabList(Predicate<Lab> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasConsultation(Consultation lab) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteConsultation(Consultation target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setConsultation(Consultation target, Consultation editedConsultation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Consultation> getFilteredConsultationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredConsultationList(Predicate<Consultation> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortAllPersonList(String metric, boolean increasingOrder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortTutorialPersonList(String metric, boolean isIncreasing) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortLabPersonList(String metric, boolean isIncreasing) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortConsultationPersonList(String metric, boolean isIncreasing) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean addNoteToTutorial(Note note, String nameOfEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean addNoteToLab(Note note, String nameOfEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean addNoteToConsultation(Note note, String nameOfEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean removeNoteFromConsultation(Index index, String nameOfEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean removeNoteFromLab(Index index, String nameOfEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean removeNoteFromTutorial(Index index, String nameOfEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean editNoteFromTutorial(Index index, Note note, String nameOfEvent) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean editNoteFromLab(Index index, Note note, String nameOfEvent) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean editNoteFromConsultation(Index index, Note note, String nameOfEvent) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private static class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private static class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        final ArrayList<Tutorial> tutorialsAdded = new ArrayList<>();
        final ArrayList<Lab> labsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public boolean hasTutorial(Tutorial tutorial) {
            requireNonNull(tutorial);
            return tutorialsAdded.stream().anyMatch(tutorial::isSameTutorial);
        }

        @Override
        public void addTutorial(Tutorial tutorial) {
            requireNonNull(tutorial);
            tutorialsAdded.add(tutorial);
        }

        @Override
        public boolean hasLab(Lab lab) {
            requireNonNull(lab);
            return labsAdded.stream().anyMatch(lab::isSameLab);
        }

        @Override
        public void addLab(Lab lab) {
            requireNonNull(lab);
            labsAdded.add(lab);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
