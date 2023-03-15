package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.MEETING_A;
import static seedu.address.testutil.TypicalMeetings.MEETING_B;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingBuilder;

public class AddMeetingCommandTest {

    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null, null,
            null, null, null));
    }

    @Test
    public void execute_meetingAcceptedByModel_addSuccessful() throws Exception {
        ModelStubWithPersons modelStub = new ModelStubWithPersons(BENSON, CARL);
        Meeting validMeeting = new MeetingBuilder(MEETING_B).build();

        CommandResult commandResult = new AddMeetingCommand(MEETING_B.getTitle(), MEETING_B.getDateTime(),
            new HashSet<>(Arrays.asList(BENSON.getName(), CARL.getName())),
            MEETING_B.getLocation(), MEETING_B.getDescription())
            .execute(modelStub);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(MEETING_A.getTitle(), MEETING_A.getDateTime(),
            new HashSet<>(Collections.singletonList(ALICE.getName())), MEETING_A.getLocation(),
            MEETING_A.getDescription());
        ModelStub modelStub = new ModelStubWithMeeting(ALICE, CARL, MEETING_A);

        assertThrows(CommandException.class, AddMeetingCommand.MESSAGE_DUPLICATE_MEETING, () ->
            addMeetingCommand.execute(modelStub));
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private static class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
        public void updateFilteredMeetingList(Predicate<Meeting> meetingPredicate) {
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
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
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
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPersonByName(Name personName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getMeetingsList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(Meeting target, Meeting editedMeeting) {
            throw new AssertionError("This method should not be called.");
        }
        
        @Override
        public void removeMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private static class ModelStubWithPersons extends AddMeetingCommandTest.ModelStub {
        private final ArrayList<Meeting> meetingsAdded = new ArrayList<>();
        private final Person person1;
        private final Person person2;

        ModelStubWithPersons(Person person1, Person person2) {
            this.person1 = person1;
            this.person2 = person2;
        }

        @Override
        public Person getPersonByName(Name personName) {
            return person1.getName().equals(personName) ? person1 : person2;
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting::isSameMeeting);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person1.isSamePerson(person) || this.person2.isSamePerson(person);
        }
    }
    private static class ModelStubWithMeeting extends AddMeetingCommandTest.ModelStub {
        private final ArrayList<Meeting> meetingsAdded = new ArrayList<>();
        private final Person person1;
        private final Person person2;

        ModelStubWithMeeting(Person person1, Person person2, Meeting meeting) {
            this.person1 = person1;
            this.person2 = person2;
            meetingsAdded.add(meeting);
        }

        @Override
        public Person getPersonByName(Name personName) {
            return person1.getName().equals(personName) ? person1 : person2;
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting::isSameMeeting);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person1.isSamePerson(person) || this.person2.isSamePerson(person);
        }
    }
}
