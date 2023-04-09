package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDeliveryJobSystem;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliveryList;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.stats.WeeklyStats;
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
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public ObservableList<DeliveryJob> getDeliveryJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDeliveryJobList(Predicate<DeliveryJob> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedDeliveryJobList(Comparator<DeliveryJob> sorter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedDeliveryJobListByDate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateWeekDeliveryJobList(LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFocusDate(LocalDate jobDate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<DeliveryJob> getSortedDeliveryJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Map<LocalDate, DeliveryList> getSortedDeliveryJobListByDate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Map<LocalDate, DeliveryList> getWeekDeliveryJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public DeliveryList getDayOfWeekJob(int dayOfWeek) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<DeliveryJob> getUnscheduledDeliveryJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<DeliveryJob> getCompletedDeliveryJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public LocalDate getFocusDate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeliveryJobSystem(ReadOnlyDeliveryJobSystem jobSystem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyDeliveryJobSystem getDeliveryJobSystem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDeliveryJob(DeliveryJob job) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDeliveryJob(DeliveryJob target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDeliveryJob(DeliveryJob job) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeliveryJob(DeliveryJob target, DeliveryJob editedJob) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteReminder(int i) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addReminder(Reminder reminder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Reminder> getReminderList() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void sortReminderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHasShown(int i, boolean b) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean sameWeek(DeliveryJob job, WeeklyStats weeklyStats) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<DeliveryJob> weekJobsList(ObservableList<DeliveryJob> list, LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public double getTotalEarnings(ObservableList<DeliveryJob> list) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getTotalCompleted(ObservableList<DeliveryJob> list) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getTotalPending(ObservableList<DeliveryJob> list) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Person> getPersonById(String id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getDeliveryJobSystemFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeliveryJobSystemFilePath(Path deliveryJobSystemFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        public ObservableList<DeliveryJob> getFilteredDeliveryJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedDeliveryJobListByComparator(Comparator<DeliveryJob> sorter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<DeliveryJob> getSortedDeliveryJobListByComparator() {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
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
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

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
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
