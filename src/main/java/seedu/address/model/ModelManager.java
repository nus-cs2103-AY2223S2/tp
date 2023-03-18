package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Nric;

/**
 * Represents the in-memory model of the FriendlyLink data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FriendlyLink friendlyLink;
    private final UserPrefs userPrefs;
    private final FilteredList<Elderly> filteredElderly;
    private final FilteredList<Volunteer> filteredVolunteers;
    private final FilteredList<Pair> filteredPairs;

    /**
     * Initializes a ModelManager with the given friendlyLink and userPrefs.
     */
    public ModelManager(ReadOnlyFriendlyLink friendlyLink, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(friendlyLink, userPrefs);
        logger.fine("Initializing with FriendlyLink: " + friendlyLink + " and user prefs " + userPrefs);
        this.friendlyLink = new FriendlyLink(friendlyLink);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredElderly = new FilteredList<>(this.friendlyLink.getElderlyList());
        filteredVolunteers = new FilteredList<>(this.friendlyLink.getVolunteerList());
        filteredPairs = new FilteredList<>(this.friendlyLink.getPairList());
    }

    public ModelManager() {
        this(new FriendlyLink(), new UserPrefs());
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
    public Path getFriendlyLinkFilePath() {
        return userPrefs.getFriendlyLinkFilePath();
    }

    @Override
    public void setFriendlyLinkFilePath(Path friendlyLinkFilePath) {
        requireNonNull(friendlyLinkFilePath);
        userPrefs.setFriendlyLinkFilePath(friendlyLinkFilePath);
    }

    //=========== FriendlyLink ================================================================================

    @Override
    public void setFriendlyLink(FriendlyLink friendlyLink) {
        this.friendlyLink.resetFriendlyLinkData(friendlyLink);
    }

    @Override
    public FriendlyLink getFriendlyLink() {
        return friendlyLink;
    }

    //=========== FriendlyLink Elderly  ======================================================================

    public Elderly getElderly(Nric nric) {
        requireNonNull(nric);
        return friendlyLink.getElderly(nric);
    }

    @Override
    public boolean hasElderly(Elderly elderly) {
        requireNonNull(elderly);
        return friendlyLink.hasElderly(elderly);
    }

    @Override
    public void deleteElderly(Elderly target) {
        friendlyLink.removeElderly(target);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addElderly(Elderly elderly) {
        friendlyLink.addElderly(elderly);
        updateFilteredElderlyList((Predicate<Elderly>) PREDICATE_SHOW_ALL);
    }

    @Override
    public void setElderly(Elderly target, Elderly editedElderly) {
        requireAllNonNull(target, editedElderly);
        friendlyLink.setElderly(target, editedElderly);
    }

    //=========== FriendlyLink Volunteers ======================================================================

    @Override
    public Volunteer getVolunteer(Nric nric) {
        requireNonNull(nric);
        return friendlyLink.getVolunteer(nric);
    }

    @Override
    public boolean hasVolunteer(Volunteer volunteer) {
        requireNonNull(volunteer);
        return friendlyLink.hasVolunteer(volunteer);
    }

    @Override
    public void deleteVolunteer(Volunteer target) {
        friendlyLink.removeVolunteer(target);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addVolunteer(Volunteer volunteer) {
        friendlyLink.addVolunteer(volunteer);
        updateFilteredVolunteerList((Predicate<Volunteer>) PREDICATE_SHOW_ALL);
    }

    @Override
    public void setVolunteer(Volunteer target, Volunteer editedVolunteer) {
        requireAllNonNull(target, editedVolunteer);
        friendlyLink.setVolunteer(target, editedVolunteer);
    }

    //=========== FriendlyLink Pairs ======================================================================
    @Override
    public boolean hasPair(Pair pair) {
        requireNonNull(pair);
        return friendlyLink.hasPair(pair);
    }

    @Override
    public void addPair(Nric elderlyNric, Nric volunteerNric) {
        friendlyLink.addPair(elderlyNric, volunteerNric);
        // TODO: implement updateFilteredPersonList(PREDICATE_SHOW_ALL_PAIRS);
    }

    @Override
    public void addPair(Pair pair) {
        friendlyLink.addPair(pair);
        // TODO: implement updateFilteredPersonList(PREDICATE_SHOW_ALL_PAIRS);
    }

    @Override
    public void deletePair(Pair target) {
        friendlyLink.removePair(target);
    }

    @Override
    public void deletePair(Nric elderlyNric, Nric volunteerNric) {
        friendlyLink.removePair(elderlyNric, volunteerNric);
    }

    @Override
    public void setPair(Pair target, Pair editedPair) {
        requireAllNonNull(target, editedPair);
        // TODO: implement friendlyLink.setPair(target, editedPair);
    }

    //=========== Filtered Elderly List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Elderly} backed by the internal list of
     * {@code versionedFriendlyLink}
     */
    @Override
    public ObservableList<Elderly> getFilteredElderlyList() {
        return filteredElderly;
    }

    @Override
    public void updateFilteredElderlyList(Predicate<Elderly> predicate) {
        requireNonNull(predicate);
        filteredElderly.setPredicate(predicate);
    }

    //=========== Filtered Volunteer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Volunteer} backed by the internal list of
     * {@code versionedFriendlyLink}
     */
    @Override
    public ObservableList<Volunteer> getFilteredVolunteerList() {
        return filteredVolunteers;
    }

    @Override
    public void updateFilteredVolunteerList(Predicate<Volunteer> predicate) {
        requireNonNull(predicate);
        filteredVolunteers.setPredicate(predicate);
    }

    //=========== Filtered Pair List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Pair} backed by the internal list of
     * {@code versionedFriendlyLink}
     */
    @Override
    public ObservableList<Pair> getFilteredPairList() {
        return filteredPairs;
    }

    @Override
    public void updateFilteredPairList(Predicate<Pair> predicate) {
        requireNonNull(predicate);
        // TODO: implement filteredPairs.setPredicate(predicate);
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
        return friendlyLink.equals(other.friendlyLink)
                && userPrefs.equals(other.userPrefs)
                && filteredElderly.equals(other.filteredElderly)
                && filteredVolunteers.equals(other.filteredVolunteers)
                && filteredPairs.equals(other.filteredPairs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friendlyLink, userPrefs, filteredElderly,
                filteredVolunteers, filteredPairs);
    }
}
