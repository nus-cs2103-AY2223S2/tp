package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.comparators.AddressComparator;
import seedu.address.logic.comparators.EmailComparator;
import seedu.address.logic.comparators.NameComparator;
import seedu.address.logic.comparators.PerformanceComparator;
import seedu.address.logic.comparators.RemarkComparator;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Note;
import seedu.address.model.event.Tutorial;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private FilteredList<Person> filteredPersons;
    private FilteredList<Lab> filteredLabs;
    private FilteredList<Tutorial> filteredTutorials;
    private FilteredList<Consultation> filteredConsultations;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredLabs = new FilteredList<>(this.addressBook.getLabList());
        filteredTutorials = new FilteredList<>(this.addressBook.getTutorialList());
        filteredConsultations = new FilteredList<>(this.addressBook.getConsultationList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasTutorial(Tutorial tutorial) {
        requireNonNull(tutorial);
        return addressBook.hasTutorial(tutorial);
    }

    @Override
    public void deleteTutorial(Tutorial target) {
        addressBook.removeTutorial(target);
    }

    @Override
    public void addTutorial(Tutorial tutorial) {
        addressBook.addTutorial(tutorial);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setTutorial(Tutorial target, Tutorial editedTutorial) {
        requireAllNonNull(target, editedTutorial);

        addressBook.setTutorial(target, editedTutorial);
    }

    @Override
    public void addStudentToTutorial(Index toAdd, Index tutIndex) throws CommandException {
        Person toAddPerson = this.getFilteredPersonList().get(toAdd.getZeroBased());
        addressBook.addStudentToTutorial(toAddPerson, tutIndex);
    }

    @Override
    public void deleteStudentFromEvent(Index toDel, Index eventIndex, String eventType) throws CommandException {
        addressBook.deleteStudentFromEvent(toDel, eventIndex, eventType);
    }

    @Override
    public boolean hasLab(Lab lab) {
        requireNonNull(lab);
        return addressBook.hasLab(lab);
    }

    @Override
    public void deleteLab(Lab target) {
        addressBook.removeLab(target);
    }

    @Override
    public void addLab(Lab lab) {
        addressBook.addLab(lab);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setLab(Lab target, Lab editedLab) {
        requireAllNonNull(target, editedLab);

        addressBook.setLab(target, editedLab);
    }

    @Override
    public void addStudentToLab(Index toAdd, Index labIndex) throws CommandException {
        Person toAddPerson = this.getFilteredPersonList().get(toAdd.getZeroBased());
        addressBook.addStudentToLab(toAddPerson, labIndex);
    }

    @Override
    public boolean hasConsultation(Consultation consultation) {
        requireNonNull(consultation);
        return addressBook.hasConsultation(consultation);
    }

    @Override
    public void deleteConsultation(Consultation target) {
        addressBook.removeConsultation(target);
    }

    @Override
    public void addConsultation(Consultation consultation) {
        addressBook.addConsultation(consultation);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setConsultation(Consultation target, Consultation editedConsultation) {
        requireAllNonNull(target, editedConsultation);
        addressBook.setConsultation(target, editedConsultation);
    }

    @Override
    public void addStudentToConsultation(Index toAdd, Index consultationIndex) throws CommandException {
        Person toAddPerson = this.getFilteredPersonList().get(toAdd.getZeroBased());
        addressBook.addStudentToConsultation(toAddPerson, consultationIndex);
    }

    @Override
    public boolean addNoteToTutorial(Note note, String nameOfEvent) {
        requireNonNull(note);
        return addressBook.addNoteToTutorial(note, nameOfEvent);
    }

    @Override
    public boolean addNoteToLab(Note note, String nameOfEvent) {
        requireNonNull(note);
        return addressBook.addNoteToLab(note, nameOfEvent);
    }

    @Override
    public boolean addNoteToConsultation(Note note, String nameOfEvent) {
        requireNonNull(note);
        return addressBook.addNoteToConsultation(note, nameOfEvent);
    }

    @Override
    public boolean removeNoteFromTutorial(Index index, String nameOfEvent) {
        requireNonNull(index);
        return addressBook.removeNoteFromTutorial(index, nameOfEvent);
    }

    @Override
    public boolean removeNoteFromLab(Index index, String nameOfEvent) {
        requireNonNull(index);
        return addressBook.removeNoteFromLab(index, nameOfEvent);
    }

    @Override
    public boolean removeNoteFromConsultation(Index index, String nameOfEvent) {
        requireNonNull(index);
        return addressBook.removeNoteFromConsultation(index, nameOfEvent);
    }

    @Override
    public boolean editNoteFromTutorial(Index index, Note note, String nameOfEvent) {
        requireNonNull(index);
        requireNonNull(note);
        return addressBook.editNoteFromTutorial(index, note, nameOfEvent);
    }
    @Override
    public boolean editNoteFromLab(Index index, Note note, String nameOfEvent) {
        requireNonNull(index);
        requireNonNull(note);
        return addressBook.editNoteFromLab(index, note, nameOfEvent);
    }
    @Override
    public boolean editNoteFromConsultation(Index index, Note note, String nameOfEvent) {
        requireNonNull(index);
        requireNonNull(note);
        return addressBook.editNoteFromConsultation(index, note, nameOfEvent);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    /**
     * Updates the filterPersons list with the sorted list
     * @param metric the sorting metric used
     * @param isIncreasing a boolean to indicate the sorting order
     */
    public void updateSortAllPersonList(String metric, boolean isIncreasing) {
        requireNonNull(metric);
        SortedList<Person> sortedData = new SortedList<>(filteredPersons);
        Comparator<Person> comparator;
        switch (metric) {
        case "performance":
            comparator = new PerformanceComparator(isIncreasing);
            break;
        case "email":
            comparator = new EmailComparator(isIncreasing);
            break;
        case "name":
            comparator = new NameComparator(isIncreasing);
            break;
        case "address":
            comparator = new AddressComparator(isIncreasing);
            break;
        default:
            comparator = new RemarkComparator(isIncreasing);
        }
        sortedData.setComparator(comparator);

        filteredPersons = new FilteredList<>(sortedData);
    }

    /**
     * Updates the list of students in the tutorial if that is the group of students the TA wishes to sort
     * @param metric Type of indicator to sort by
     * @param isIncreasing Sort in ascending or descending order
     */
    public void updateSortTutorialPersonList(String metric, boolean isIncreasing) {
        requireNonNull(metric);
        Comparator<Person> comparator;
        switch (metric) {
        case "performance":
            comparator = new PerformanceComparator(isIncreasing);
            break;
        case "email":
            comparator = new EmailComparator(isIncreasing);
            break;
        case "name":
            comparator = new NameComparator(isIncreasing);
            break;
        case "address":
            comparator = new AddressComparator(isIncreasing);
            break;
        default:
            comparator = new RemarkComparator(isIncreasing);
        }

        for (Tutorial tutorial : filteredTutorials) {
            Collections.sort(tutorial.getStudents(), comparator);
        }
    }

    /**
     * Updates the list of students in the lab if that is the group of students the TA wishes to sort
     * @param metric Type of indicator to sort by
     * @param isIncreasing Sort in ascending or descending order
     */
    public void updateSortLabPersonList(String metric, boolean isIncreasing) {
        requireNonNull(metric);
        Comparator<Person> comparator;
        switch (metric) {
        case "performance":
            comparator = new PerformanceComparator(isIncreasing);
            break;
        case "email":
            comparator = new EmailComparator(isIncreasing);
            break;
        case "name":
            comparator = new NameComparator(isIncreasing);
            break;
        case "address":
            comparator = new AddressComparator(isIncreasing);
            break;
        default:
            comparator = new RemarkComparator(isIncreasing);
        }

        for (Lab lab : filteredLabs) {
            Collections.sort(lab.getStudents(), comparator);
        }
    }

    /**
     * Updates the list of students in the consultation if that is the group of students the TA wishes to sort
     * @param metric Type of indicator to sort by
     * @param isIncreasing Sort in ascending or descending order
     */
    public void updateSortConsultationPersonList(String metric, boolean isIncreasing) {
        requireNonNull(metric);
        Comparator<Person> comparator;
        switch (metric) {
        case "performance":
            comparator = new PerformanceComparator(isIncreasing);
            break;
        case "email":
            comparator = new EmailComparator(isIncreasing);
            break;
        case "name":
            comparator = new NameComparator(isIncreasing);
            break;
        case "address":
            comparator = new AddressComparator(isIncreasing);
            break;
        default:
            comparator = new RemarkComparator(isIncreasing);
        }

        for (Consultation consultation : filteredConsultations) {
            Collections.sort(consultation.getStudents(), comparator);
        }
    }
    //=========== Filtered Tutorial List Accessors =============================================================

    @Override
    public ObservableList<Tutorial> getFilteredTutorialList() {
        return filteredTutorials;
    }

    @Override
    public void updateFilteredTutorialList(Predicate<Tutorial> predicate) {
        requireNonNull(predicate);
        filteredTutorials.setPredicate(predicate);
    }

    //=========== Filtered Lab List Accessors =============================================================

    @Override
    public ObservableList<Lab> getFilteredLabList() {
        return filteredLabs;
    }

    @Override
    public void updateFilteredLabList(Predicate<Lab> predicate) {
        requireNonNull(predicate);
        filteredLabs.setPredicate(predicate);
    }

    //=========== Filtered Consultation List Accessors =============================================================

    @Override
    public ObservableList<Consultation> getFilteredConsultationList() {
        return filteredConsultations;
    }

    @Override
    public void updateFilteredConsultationList(Predicate<Consultation> predicate) {
        requireNonNull(predicate);
        filteredConsultations.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTutorials.equals(other.filteredTutorials)
                && filteredLabs.equals(other.filteredLabs)
                && filteredConsultations.equals(other.filteredConsultations);
    }
}
