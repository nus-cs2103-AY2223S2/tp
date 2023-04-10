package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALEX;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.nio.file.Path;
//import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.parser.IndexHandler;
import seedu.address.model.EduMate;
import seedu.address.model.EduMateHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyEduMate;
import seedu.address.model.ReadOnlyEduMateHistory;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.meetup.MeetUpIndex;
import seedu.address.model.meetup.Participants;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalUser;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    //    @Test
    //    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
    //        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
    //
    //        Person validPerson = new PersonBuilder().build();
    //        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);
    //        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
    //        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    //    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);
        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person albert = new PersonBuilder().withName("Albert").build();
        Person bart = new PersonBuilder().withName("Bart").build();
        AddCommand addAlbertCommand = new AddCommand(albert);
        AddCommand addBartCommand = new AddCommand(bart);

        // same object -> returns true
        assertTrue(addAlbertCommand.equals(addAlbertCommand));

        // same values -> returns true
        AddCommand addAlbertCommandCopy = new AddCommand(albert);
        assertTrue(addAlbertCommand.equals(addAlbertCommandCopy));

        // different types -> returns false
        assertFalse(addAlbertCommand.equals(1));

        // null -> returns false
        assertFalse(addAlbertCommand.equals(null));

        // different person -> returns false
        assertFalse(addAlbertCommand.equals(addBartCommand));
    }

    @Test
    public void checkAssignedIndex_emptyModel_assign1() throws CommandException {
        Model model = new ModelManager();
        assertTrue(model.getObservablePersonList().isEmpty());
        Person validPerson = new PersonBuilder().build();
        new AddCommand(validPerson).execute(model);
        assertEquals(1, model.getObservablePersonList().size());
        assertEquals(new ContactIndex(1), validPerson.getContactIndex());
    }

    @Test
    public void checkAssignedIndex_gapsInContactIndexSequence_assignLowestAvailableIndex() throws CommandException {
        Model model = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());
        IndexHandler indexHandler = new IndexHandler(model);
        int[] indicesToDelete = new int[] {3, 6, 10};
        for (int idx : indicesToDelete) {
            new DeleteCommand(new ContactIndex(idx)).execute(model);
        }

        Person validPerson = new PersonBuilder().build();
        new AddCommand(validPerson).execute(model);
        assertEquals(validPerson,
                indexHandler.getPersonByIndex(new ContactIndex(3)).orElse(ALEX));
    }

    @Test
    public void checkAssignedIndex_consecutiveIndexPresent_assignLastIndex() throws CommandException {
        Model model = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());
        IndexHandler indexHandler = new IndexHandler(model);
        int size = model.getObservablePersonList().size();
        Person validPerson = new PersonBuilder().build();
        new AddCommand(validPerson).execute(model);
        assertEquals(validPerson,
                indexHandler.getPersonByIndex(new ContactIndex(size + 1)).orElse(ALEX));
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
        public Path getEduMateFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEduMateFilePath(Path eduMateFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEduMate(ReadOnlyEduMate newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEduMate getEduMate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEduMateHistory getEduMateHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEduMateHistory(String command) {
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
        public void resetPersons() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRecommendation(Recommendation recommendation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRecommendation(Recommendation target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Recommendation addRecommendation(Recommendation recommendation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecommendation(Recommendation target, Recommendation editedRecommendation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecommendations(List<Recommendation> recommendations) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetRecommendations() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public User getUser() {
            return TypicalUser.LINUS;
        }

        @Override
        public void setUser(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Person> getPersonByIndex(ContactIndex index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getObservablePersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateObservablePersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateObservablePersonList(Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateObservablePersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Recommendation> getRecommendationByIndex(ContactIndex contactIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeetUp(MeetUp meetUp) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public MeetUpIndex getMeetUpIndex() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<MeetUp> getObservableMeetUpList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeEmptyMeetUps() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Recommendation> getObservableRecommendationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateObservableRecommendationList(Predicate<Recommendation> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateObservableRecommendationList(Comparator<Recommendation> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateObservableRecommendationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Participants getParticipants() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateObservableMeetUpList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateObservableMeetUpList(Comparator<MeetUp> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<MeetUp> getMeetUpByIndex(MeetUpIndex meetUpIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeetUp(MeetUpIndex meetUpIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setParticipants(Set<ContactIndex> indices) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     *  A Model stub that contains a single person.
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
        final EduMate eduMate = new EduMate();
        final FilteredList<Person> filteredList = new FilteredList<>(eduMate.getPersonList());

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public Person addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
            return person;
        }

        @Override
        public ReadOnlyEduMate getEduMate() {
            return new EduMate();
        }

        @Override
        public ObservableList<Person> getObservablePersonList() {
            return filteredList;
        }
    }

}
