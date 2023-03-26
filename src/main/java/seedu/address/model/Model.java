package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Nric;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<?> PREDICATE_SHOW_ALL = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     *
     * @param userPrefs User preference to be set.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     *
     * @return Unmodifiable user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     *
     * @return GUI settings of FriendlyLink.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     *
     * @param guiSettings GUI settings to be set.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' elderly database file path.
     *
     * @return elderly database file path.
     */
    Path getElderlyFilePath();

    /**
     * Sets the user prefs' elderly database file path.
     *
     * @param elderlyFilePath elderly database file path to be set.
     */
    void setElderlyFilePath(Path elderlyFilePath);

    /**
     * Returns the user prefs' volunteer database file path.
     *
     * @return volunteer database file path.
     */
    Path getVolunteerFilePath();

    /**
     * Sets the user prefs' volunteer database file path.
     *
     * @param volunteerFilePath volunteer database file path to be set.
     */
    void setVolunteerFilePath(Path volunteerFilePath);

    /**
     * Returns the user prefs' pair database file path.
     *
     * @return pair database file path.
     */
    Path getPairFilePath();

    /**
     * Sets the user prefs' pair database file path.
     *
     * @param pairFilePath pair database file path to be set.
     */
    void setPairFilePath(Path pairFilePath);

    /**
     * Replaces FriendlyLink database data with the data in {@code friendlyLink}.
     *
     * @param friendlyLink Data to set.
     */
    void setFriendlyLink(FriendlyLink friendlyLink);

    /**
     * Returns the FriendlyLink.
     *
     * @return {@code FriendlyLink} object.
     */
    FriendlyLink getFriendlyLink();

    /**
     * Retrieves the elderly with the given Nric.
     * Elderly of that Nric must exist in the FriendlyLink database.
     *
     * @param nric Nric of the elderly.
     * @return Elderly with that Nric.
     */
    Elderly getElderly(Nric nric);

    /**
     * Retrieves the volunteer with the given Nric.
     * Volunteer of that Nric must exist in the FriendlyLink database.
     *
     * @param nric Nric of the volunteer.
     * @return Volunteer with that Nric.
     */
    Volunteer getVolunteer(Nric nric);

    /**
     * Returns true if an elderly with the same {@code nric} exists in the friendly link database.
     */
    boolean hasElderly(Nric nric);

    /**
     * Deletes the given elderly.
     * The elderly must exist in the friendly link database.
     *
     * @param target Elderly to delete.
     */
    void deleteElderly(Elderly target);

    /**
     * Adds the given elderly.
     * {@code elderly} must not already exist in the friendly link database.
     *
     * @param elderly Elderly to add.
     */
    void addElderly(Elderly elderly);

    /**
     * Replaces the given elderly {@code target} with {@code editedElderly}.
     * {@code target} must exist in the friendly link database.
     * The elderly identity of {@code editedElderly} must not be the same as another existing elderly in the
     * friendly link database.
     *
     * @param target Elderly to replace.
     * @param editedElderly Replacement elderly.
     */
    void setElderly(Elderly target, Elderly editedElderly);

    /**
     * Returns true if an volunteer with the same {@code nric} exists in the friendly link database.
     */
    boolean hasVolunteer(Nric nric);

    /**
     * Deletes the given volunteer.
     * The volunteer must exist in the friendly link database.
     *
     * @param target Volunteer to delete.
     */
    void deleteVolunteer(Volunteer target);

    /**
     * Adds the given volunteer.
     * {@code volunteer} must not already exist in the friendly link database.
     *
     * @param volunteer Volunteer to add.
     */
    void addVolunteer(Volunteer volunteer);

    /**
     * Replaces the given volunteer {@code target} with {@code editedVolunteer}.
     * {@code target} must exist in the friendly link database.
     * The volunteer identity of {@code editedVolunteer} must not be the same as another existing volunteer in the
     * friendly link database.
     *
     * @param target Volunteer to replace.
     * @param editedVolunteer Replacement volunteer.
     */
    void setVolunteer(Volunteer target, Volunteer editedVolunteer);

    /**
     * Adds the given pair.
     * {@code pair} must not already exist in FriendlyLink.
     *
     * @param pair Pair to add.
     */
    void addPair(Pair pair);

    /**
     * Adds the given pair.
     * The pair consisting of elderly with {@code elderlyNric} and volunteer with {@code volunteerNric}
     * must not already exist in FriendlyLink.
     *
     * @param elderlyNric Nric of the elderly of the pair to add.
     * @param volunteerNric Nric of the volunteer of the pair to add.
     */
    boolean addPair(Nric elderlyNric, Nric volunteerNric);

    /**
     * Returns true if a pair with the same identity as {@code pair} exists in FriendlyLink.
     *
     * @param pair Pair to be checked.
     * @return True if {@code pair} exists in FriendlyLink, false otherwise.
     */
    boolean hasPair(Pair pair);

    /**
     * Deletes the given pair.
     * The pair must exist in FriendlyLink.
     *
     * @param target Pair to delete.
     */
    void deletePair(Pair target);

    /**
     * Deletes the given pair.
     * The pair consisting of elderly with {@code elderlyNric} and volunteer with {@code volunteerNric}
     * must exist in FriendlyLink.
     *
     * @param elderlyNric Nric of the elderly of the pair to delete.
     * @param volunteerNric Nric of the volunteer of the pair to delete.
     */
    void deletePair(Nric elderlyNric, Nric volunteerNric);

    /**
     * Replaces the given pair {@code target} with {@code editedPair}.
     * {@code target} must exist in FriendlyLink.
     * The pair identity of {@code editedPair} must not be the same as another existing pair in FriendlyLink.
     *
     * @param target Pair to replace.
     * @param editedPair Replacement pair.
     */
    void setPair(Pair target, Pair editedPair);

    /**
     * Returns an unmodifiable view of the filtered elderly list.
     *
     * @return Unmodifiable filtered elderly list.
     */
    ObservableList<Elderly> getFilteredElderlyList();

    /**
     * Updates the filter of the filtered elderly list to filter by the given {@code predicate}.
     *
     * @param predicate Predicate to filter the elderly list.
     */
    void updateFilteredElderlyList(Predicate<Elderly> predicate);


    /**
     * Returns an unmodifiable view of the filtered volunteer list.
     *
     * @return Unmodifiable filtered volunteer list.
     */
    ObservableList<Volunteer> getFilteredVolunteerList();

    /**
     * Updates the filter of the filtered volunteer list to filter by the given {@code predicate}.
     *
     * @param predicate Predicate to filter the volunteer list.
     */
    void updateFilteredVolunteerList(Predicate<Volunteer> predicate);

    /**
     * Returns an unmodifiable view of the filtered pair list.
     *
     * @return Unmodifiable filtered pair list.
     */
    ObservableList<Pair> getFilteredPairList();

    /**
     * Updates the filter of the filtered pair list to filter by the given {@code predicate}.
     *
     * @param predicate Predicate to filter the pair list.
     */
    void updateFilteredPairList(Predicate<Pair> predicate);

    /**
     * Refreshes all filtered lists to display everything.
     */
    void refreshAllFilteredLists();

    /**
     * updates all filtered lists with the given predicates.
     */
    void updateAllFilteredLists(Predicate<Elderly> elderlyPredicate,
            Predicate<Volunteer> pairPredicate, Predicate<Pair> volunteerPredicate);

}
