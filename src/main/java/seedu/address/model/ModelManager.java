package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.exceptions.ModifyFrozenStateException;
import seedu.address.model.history.InputHistory;
import seedu.address.model.person.ParticularPersonsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final InputHistory inputHistory;
    private final FilteredList<Person> filteredPersons;
    private final List<Person> frozenPersons;

    private Predicate<? super Person> frozenPredicate = null;
    private boolean isFrozen = false;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, InputHistory inputHistory) {
        requireAllNonNull(addressBook, userPrefs, inputHistory);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.inputHistory = new InputHistory(inputHistory);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredPersons.setPredicate(PREDICATE_SHOW_ALL_PERSONS);
        frozenPersons = new ArrayList<>();
    }

    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        this(addressBook, userPrefs, new InputHistory());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new InputHistory());
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

    @Override
    public ModelManager stateDetachedCopy() {
        ModelManager copy = new ModelManager(addressBook.deepCopy(), userPrefs);
        copy.updateFilteredPersonList(getPredicate());
        if (isFrozen) {
            copy.freezeWith(filteredPersons);
        }
        return copy;
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
    public String addPersonsFromAddressBook(ReadOnlyAddressBook newAddressBook) {
        String feedback = "Success!";

        for (Person person: newAddressBook.getPersonList()) {
            try {
                addressBook.addPerson(person);
            } catch (DuplicatePersonException e) {
                feedback = "Warning: Some contacts already exist! Those contacts will be ignored.";
            }
        }
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return feedback;
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void addTag(Person person, Tag tag) {
        addressBook.addTag(person, tag);
    }

    @Override
    public void deleteTag(Person person, Tag tag) {
        addressBook.deleteTag(person, tag);
    }

    //=========== History ================================================================================

    @Override
    public Path getInputHistoryStoragePath() {
        return inputHistory.getHistoryStoragePath();
    }

    @Override
    public void setInputHistoryStoragePath(Path newPath) {
        inputHistory.setHistoryStoragePath(newPath);
    }

    @Override
    public InputHistory getInputHistory() {
        return inputHistory;
    }

    @Override
    public void setInputHistory(InputHistory newInputHistory) {
        inputHistory.resetData(newInputHistory);
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
    public void updateFilteredPersonList(Predicate<? super Person> predicate) {
        requireNonNull(predicate);
        try {
            unfreezeFilteredPersonList();
        } catch (ModifyFrozenStateException ex) {
            // do nothing
        }
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void refreshFilteredPersonList() {
        Predicate<? super Person> predicate = filteredPersons.getPredicate();
        filteredPersons.setPredicate(null);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void freezeFilteredPersonList() throws ModifyFrozenStateException {
        if (isFrozen) {
            throw new ModifyFrozenStateException("Model is already frozen");
        }
        frozenPersons.clear();
        frozenPersons.addAll(filteredPersons);
        frozenPredicate = filteredPersons.getPredicate();
        filteredPersons.setPredicate(new ParticularPersonsPredicate(frozenPersons));
        isFrozen = true;
    }

    @Override
    public void unfreezeFilteredPersonList() throws ModifyFrozenStateException {
        if (!isFrozen) {
            throw new ModifyFrozenStateException("Model is not frozen");
        }
        filteredPersons.setPredicate(frozenPredicate);
        isFrozen = false;
    }

    @Override
    public void freezeWith(List<Person> frozenPersons) {
        requireNonNull(frozenPersons);
        try {
            unfreezeFilteredPersonList();
        } catch (ModifyFrozenStateException ex) {
            // do nothing
        }
        this.frozenPersons.clear();
        for (Person p: addressBook.getPersonList()) {
            if (frozenPersons.contains(p)) {
                this.frozenPersons.add(p);
            }
        }
        frozenPredicate = filteredPersons.getPredicate();
        filteredPersons.setPredicate(new ParticularPersonsPredicate(this.frozenPersons));
        isFrozen = true;
    }

    @Override
    public boolean isFrozen() {
        return isFrozen;
    }

    @Override
    public void replicateStateOf(Model other) {
        setAddressBook(other.getAddressBook());
        updateFilteredPersonList(other.getPredicate());
        if (other.isFrozen()) {
            freezeWith(other.getFilteredPersonList());
        }
    }

    @Override
    public Predicate<? super Person> getPredicate() {
        if (isFrozen) {
            return frozenPredicate;
        }
        return filteredPersons.getPredicate();
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
                && isFrozen == other.isFrozen
                && (!isFrozen || Objects.equals(frozenPredicate, other.frozenPredicate));
    }

}
