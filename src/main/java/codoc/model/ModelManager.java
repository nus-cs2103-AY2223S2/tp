package codoc.model;

import static codoc.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import codoc.commons.core.GuiSettings;
import codoc.commons.core.LogsCenter;
import codoc.model.person.Person;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of CoDoc data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Codoc codoc;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private Person protagonist;
    private String currentTab;

    /**
     * Initializes a ModelManager with the given codoc and userPrefs.
     */
    public ModelManager(ReadOnlyCodoc codoc, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(codoc, userPrefs);

        logger.fine("Initializing with CoDoc: " + codoc + " and user prefs " + userPrefs);

        this.codoc = new Codoc(codoc);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.codoc.getPersonList());
        if (filteredPersons.size() == 0) { // fresh database
            protagonist = null;
            currentTab = null;
        } else {
            protagonist = filteredPersons.get(0);
            currentTab = "c";
        }
    }

    public ModelManager() {
        this(new Codoc(), new UserPrefs());
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
    public Path getCodocFilePath() {
        return userPrefs.getCodocFilePath();
    }

    @Override
    public void setCodocFilePath(Path codocFilePath) {
        requireNonNull(codocFilePath);
        userPrefs.setCodocFilePath(codocFilePath);
    }

    //=========== Codoc ================================================================================

    @Override
    public void setCodoc(ReadOnlyCodoc codoc) {
        this.codoc.resetData(codoc);
    }

    @Override
    public ReadOnlyCodoc getCodoc() {
        return codoc;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return codoc.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        codoc.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        codoc.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        codoc.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedCodoc}
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

    //=========== Protagonist ================================================================================

    @Override
    public Person getProtagonist() {
        return protagonist;
    }

    @Override
    public void setProtagonist(Person protagonist) {
        this.protagonist = protagonist;
    }

    //=========== InfoTab ====================================================================================

    @Override
    public String getCurrentTab() {
        return currentTab;
    }

    @Override
    public void setCurrentTab(String tab) {
        currentTab = tab;
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
        return codoc.equals(other.codoc)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
