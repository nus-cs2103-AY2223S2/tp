package seedu.dengue.model;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.dengue.commons.core.GuiSettings;
import seedu.dengue.commons.core.LogsCenter;
import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.commons.util.CsvUtil;
import seedu.dengue.logic.analyst.DataBin;
import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.overview.Overview;
import seedu.dengue.model.overview.PostalOverview;
import seedu.dengue.model.person.Person;
import seedu.dengue.storage.CsvDengueHotspotStorage;
import seedu.dengue.storage.temporary.TemporaryMemory;

/**
 * Represents the in-memory model of the Dengue Hotspot Tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final DengueHotspotTracker dengueHotspotTracker;
    private TemporaryMemory memory;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private Overview overview;

    /**
     * Initializes a ModelManager with the given dengueHotspotTracker and userPrefs.
     */
    public ModelManager(ReadOnlyDengueHotspotTracker dengueHotspotTracker, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(dengueHotspotTracker, userPrefs);

        logger.fine("Initializing with Dengue Hotspot Tracker: " + dengueHotspotTracker
                + " and user prefs " + userPrefs);

        this.dengueHotspotTracker = new DengueHotspotTracker(dengueHotspotTracker)
                .generateDeepCopy();
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.dengueHotspotTracker.getPersonList());
        this.overview = new PostalOverview();
        this.memory = new TemporaryMemory(this.dengueHotspotTracker);
    }

    public ModelManager() {
        this(new DengueHotspotTracker(), new UserPrefs());
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
    public Path getDengueHotspotTrackerFilePath() {
        return userPrefs.getDengueHotspotTrackerFilePath();
    }

    @Override
    public void setDengueHotspotTrackerFilePath(Path dengueHotspotTrackerFilePath) {
        requireNonNull(dengueHotspotTrackerFilePath);
        userPrefs.setDengueHotspotTrackerFilePath(dengueHotspotTrackerFilePath);
    }

    //=========== DengueHotspotTracker ================================================================================

    // Access Temporary Memory for Undo Redo

    @Override
    public void updateFromMemoryStack() {
        setDengueHotspotTracker(memory.loadCurrent());
    }

    /**
     * Saves changes.
     * @param tracker The {@code ReadOnlyDengueHotspotTracker} to save.
     */
    public void saveChanges(ReadOnlyDengueHotspotTracker tracker) {
        memory.saveNewLatest(tracker);
        updateFromMemoryStack();
    }

    @Override
    public void undo() throws CommandException {

        try {
            memory.undo();
            updateFromMemoryStack();
        } catch (NullPointerException err) {
            throw new CommandException("Cannot undo any further!");
        }
    }
    @Override
    public void redo() throws CommandException {
        memory.redo();
        updateFromMemoryStack();
    }

    @Override
    public void setDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker) {
        this.dengueHotspotTracker.resetData(dengueHotspotTracker);
    }

    @Override
    public ReadOnlyDengueHotspotTracker getDengueHotspotTracker() {
        return dengueHotspotTracker;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return dengueHotspotTracker.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        dengueHotspotTracker.removePerson(target);
        saveChanges(this.dengueHotspotTracker);
    }

    @Override
    public void addPerson(Person person) {
        dengueHotspotTracker.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        saveChanges(this.dengueHotspotTracker);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        dengueHotspotTracker.setPerson(target, editedPerson);
        saveChanges(this.dengueHotspotTracker);
    }

    @Override
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        dengueHotspotTracker.setPersons(persons);
        saveChanges(this.dengueHotspotTracker);
    }


    //=========== Filtered Person List Accessors =============================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedDengueHotspotTracker}
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

    //=========== Overview Stuff =============================================================

    @Override
    public Overview getOverview() {
        this.overview.update(filteredPersons);
        return this.overview;
    }

    @Override
    public void setOverview(Overview newOverview) {
        this.overview = newOverview;
    }

    //=========== Sort Stuff =================================================================

    @Override
    public void sort(List<Person> sortedList) {
        dengueHotspotTracker.setPersons(sortedList);
    }

    //=========== Import/Export Csv Stuff ====================================================

    @Override
    public void importCsv(Path filePath) throws DataConversionException, IOException {
        CsvDengueHotspotStorage toRead = new CsvDengueHotspotStorage(filePath);
        Optional<ReadOnlyDengueHotspotTracker> dengueHotspotTrackerOptional;
        ReadOnlyDengueHotspotTracker importedData;
        try {
            dengueHotspotTrackerOptional = toRead.readDengueHotspotTracker();
            if (!dengueHotspotTrackerOptional.isPresent()) {
                logger.info("Data file not found. No change to data in DengueHotspotTracker");
                throw new IOException();
            } else {
                importedData = dengueHotspotTrackerOptional.get();
                dengueHotspotTracker.setPersons(importedData.getPersonList());
            }
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. No change to data in DengueHotspotTracker");
            throw e;
        }
    }

    @Override
    public void exportCsv(Path filePath) throws IOException {
        DengueHotspotTracker tempDht = new DengueHotspotTracker();
        tempDht.setPersons(filteredPersons);
        CsvDengueHotspotStorage toSave = new CsvDengueHotspotStorage(filePath);
        try {
            toSave.saveDengueHotspotTracker(tempDht);
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void checkout(Path filePath) throws IOException {
        ObservableList<DataBin> tempList = FXCollections.observableArrayList();
        tempList.setAll(overview.getOverviewContent());
        String[] headers = Overview.getHeaders();
        List<String[]> data = Overview.enumList(tempList
                        .stream()
                        .map(DataBin::toCsvString)
                        .collect(Collectors.toList()));
        try {
            CsvUtil.saveCsvFile(data, filePath, headers);
        } catch (IOException e) {
            throw e;
        }
    }

    //=========== Misc Stuff =================================================================

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
        return dengueHotspotTracker.equals(other.dengueHotspotTracker)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && overview.equals(other.overview);
    }
}
