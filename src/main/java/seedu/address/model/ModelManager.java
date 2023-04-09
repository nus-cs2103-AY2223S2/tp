package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
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
    public boolean hasPerson(Student person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Student target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Student person) {
        addressBook.addStudent(person);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
                && filteredPersons.equals(other.filteredPersons);
    }

    /**
     * Returns true if the model has a student whose name is part of the input name.
     */
    @Override
    public boolean hasDuplicateName(String name) {
        int count = 0;
        for (Student s : filteredPersons) {
            if (s.getName().toString().toLowerCase().contains(name.toLowerCase())) {
                count++;
            }
        }
        return count >= 2;
    }

    @Override
    public boolean hasDuplicateNameAdd(String name) {
        int count = 0;
        for (Student s : filteredPersons) {
            if (s.getName().toString().toLowerCase().contains(name.toLowerCase())) {
                count++;
            }
        }
        return count >= 1;
    }

    @Override
    public boolean hasDuplicateNameEdit(String name, Integer index) {
        int count = 0;
        for (int i = 0; i < filteredPersons.size(); i++) {
            if (filteredPersons.get(i).getName().toString().toLowerCase().contains(name.toLowerCase()) && i != index) {
                count++;
            }
        }
        return count >= 1;
    }

    /**
     * Returns true if the model has a student whose name is part of the input name.
     */
    @Override
    public boolean hasExtendedName(String name) {
        int count = 0;
        for (Student s : filteredPersons) {
            if (name.toLowerCase().contains(s.getName().toString().toLowerCase())) {
                count++;
            }
        }
        return count >= 1;
    }

    @Override
    public boolean hasExtendedNameEdit(String name, Integer index) {
        int count = 0;
        for (int i = 0; i < filteredPersons.size(); i++) {
            if (name.toLowerCase().contains(filteredPersons.get(i).getName().toString().toLowerCase()) && i != index) {
                count++;
            }
        }
        return count >= 1;
    }

    @Override
    public boolean noSuchStudent(String name) {
        for (Student s : filteredPersons) {
            if (s.getName().toString().toLowerCase().contains(name.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasConflictingLessonTime(Lesson lesson) {
        for (Student s : filteredPersons) {
            if (s.hasConflictingLessonTime(lesson)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasConflictingExamTime(Lesson lesson) {
        for (Student s : filteredPersons) {
            if (s.hasConflictingExamTime(lesson)) {
                return true;
            }
        }
        return false;
    }
}
