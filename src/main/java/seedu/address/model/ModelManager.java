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
import seedu.address.model.pet.Pet;

/**
 * Represents the in-memory model of the PetPal data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PetPal petPal;
    private final PetPal archiveStorage;
    private PetPal petPalCache;
    private PetPal archiveCache;
    private final UserPrefs userPrefs;
    private final FilteredList<Pet> filteredPets;

    /**
     * Initializes a ModelManager with the given PetPal and userPrefs.
     */
    public ModelManager(ReadOnlyPetPal petPal, ReadOnlyPetPal petPalArchive, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(petPal, petPalArchive, userPrefs);

        logger.info("Initializing with PetPal: " + petPal + " and user prefs " + userPrefs);

        this.petPalCache = new PetPal(petPal);
        this.petPal = new PetPal(petPal);
        this.archiveStorage = new PetPal(petPalArchive);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPets = new FilteredList<>(this.petPal.getPetList());
    }

    public ModelManager() {
        this(new PetPal(), new PetPal(), new UserPrefs());
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
    public Path getPetPalFilePath() {
        return userPrefs.getPetPalFilePath();
    }

    @Override
    public void setPetPalFilePath(Path petPalFilePath) {
        requireNonNull(petPalFilePath);
        userPrefs.setPetPalFilePath(petPalFilePath);
    }

    public Path getPetPalArchiveFilePath() {
        return userPrefs.getPetPalArchiveFilePath();
    }

    public void setPetPalArchiveFilePath(Path petPalArchiveFilePath) {
        requireNonNull(petPalArchiveFilePath);
        userPrefs.setPetPalArchiveFilePath(petPalArchiveFilePath);
    }

    //===================================== PetPal ======================================================

    @Override
    public void setPetPal(ReadOnlyPetPal petPal) {
        this.petPal.resetData(petPal);
    }

    @Override
    public ReadOnlyPetPal getPetPal() {
        return petPal;
    }

    public ReadOnlyPetPal getPetPalArchive() {
        return archiveStorage;
    }

    @Override
    public boolean hasPet(Pet pet) {
        requireNonNull(pet);
        return petPal.hasPet(pet);
    }

    @Override
    public void deletePet(Pet target) {
        this.petPalCache = new PetPal(petPal);
        petPal.removePet(target);
    }

    @Override
    public void addPet(Pet pet) {
        this.petPalCache = new PetPal(petPal);
        petPal.addPet(pet);
        updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);
    }

    @Override
    public void markPet(Pet target) {
        this.petPalCache = new PetPal(petPal);
        petPal.markPet(target);
    }

    @Override
    public void undo() {
        PetPal temp = new PetPal(petPalCache);
        petPal.setPets(temp.getPetList());

        PetPal archiveTemp = new PetPal(archiveCache);
        archiveStorage.setPets(archiveTemp.getPetList());
    }

    @Override
    public void setPet(Pet target, Pet editedPet) {
        requireAllNonNull(target, editedPet);

        petPal.setPet(target, editedPet);
    }

    @Override
    public void archivePet(Pet petToArchive) {
        this.petPalCache = new PetPal(petPal);
        this.archiveCache = new PetPal(archiveStorage);

        requireNonNull(petToArchive);
        archiveStorage.archivePet(petToArchive);
        petPal.removePet(petToArchive);
        updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);
    }

    @Override
    public boolean hasArchivePet(Pet p) {
        requireNonNull(p);
        return archiveStorage.hasPet(p);
    }

    //=========== Filtered Pet List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Pet} backed by the internal list of
     * {@code versionedPetPal}
     */
    @Override
    public ObservableList<Pet> getFilteredPetList() {
        return filteredPets;
    }

    @Override
    public void updateFilteredPetList(Predicate<Pet> predicate) {
        requireNonNull(predicate);
        filteredPets.setPredicate(predicate);
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
        return petPal.equals(other.petPal)
                && userPrefs.equals(other.userPrefs)
                && filteredPets.equals(other.filteredPets);
    }
}
