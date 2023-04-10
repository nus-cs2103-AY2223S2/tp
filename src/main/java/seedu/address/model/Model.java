package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.exceptions.ModifyFrozenStateException;
import seedu.address.model.history.InputHistory;
import seedu.address.model.person.Person;
import seedu.address.model.person.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' E-Lister file path.
     */
    Path getElisterFilePath();

    /**
     * Sets the user prefs' E-Lister file path.
     */
    void setElisterFilePath(Path elisterFilePath);

    /**
     * Replaces E-Lister data with the data in {@code elister}.
     */
    void setElister(ReadOnlyElister elister);

    /** Returns the Elister */
    ReadOnlyElister getElister();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the E-Lister.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the E-Lister.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the E-Lister.
     */
    void addPerson(Person person);

    /**
     * Adds all Persons from a given E-Lister.
     * @param newElister E-Lister containing persons to be added.
     * @return The feedback from this operation.
     */
    String addPersonsFromElister(ReadOnlyElister newElister);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the E-Lister.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the E-Lister.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifable view of the list of applying filters */
    ObservableList<Filter> getApplyingFilterList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<? super Person> predicate);

    /**
     * Updates the filter of the fltered person list to filter by the given {@code predicate} and
     * the current applying filters.
     *
     * @param predicate The predicate to update
     * @param filtersFromPredicate The filters come from the argument predicate.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<? super Person> predicate, Stream<Filter> filtersFromPredicate);

    /**
     * Refreshes the filtered person list to push any changes within contained Persons to the UI,
     * if the appropriate observers are not otherwise triggered.
     */
    void refreshFilteredPersonList();

    /**
     * Freezes the visible list of filtered persons, temporarily halting reactive updates
     * from Predicate checks.
     * @throws ModifyFrozenStateException if this Model is already frozen
     */
    void freezeFilteredPersonList() throws ModifyFrozenStateException;

    /**
     * Unfreezes the visible list of filtered persons, resuming reactive updates
     * from Predicate checks.
     * @throws ModifyFrozenStateException if this Model is not frozen
     */
    void unfreezeFilteredPersonList() throws ModifyFrozenStateException;

    /**
     * Freezes the visible list of filtered persons,
     * and changes the visible list to show only persons currently equal to
     * at least one element of {@code frozenPersons}.
     */
    void freezeWith(List<Person> frozenPersons);

    /**
     * Returns {@code true} if this Model is currently frozen.
     */
    boolean isFrozen();

    /**
     * Returns the Predicate used to filter the visible list (ignoring freezing).
     */
    Predicate<? super Person> getPredicate();

    /**
     * Changes this Model's state into a copy of that of {@code other}.
     *
     * @param other The model to copy from.
     */
    void replicateStateOf(Model other);

    /**
     * Returns a state-detached copy of this Model.
     * The copy (and its composition-descendant Objects) shall not be affected
     * by {@code Commands} applied to this Model Object.
     */
    Model stateDetachedCopy();

    /**
     * Adds the given tag to the person.
     * {@code person} must already exist in the E-Lister
     */
    void addTag(Person person, Tag tag);

    /**
     * Deletes the tag with the given name from the person
     * specified by input index according to the E-Lister list.
     *
     * @param person The person to delete tag from.
     * @param tag The tag to delete.
     */
    void deleteTag(Person person, Tag tag);

    /**
     * Returns the user prefs' history storage file path.
     */
    Path getInputHistoryStoragePath();

    /**
     * Sets the user prefs' history storage file path.
     */
    void setInputHistoryStoragePath(Path filePath);

    /**
     * Sets new {@code History} object to the model.
     */
    void setInputHistory(InputHistory inputHistory);

    /** Returns the {@code History}*/
    InputHistory getInputHistory();

    /**
     * Returns the primary Stage.
     */
    Stage getPrimaryStage();

    /**
     * Sets the primary Stage.
     */
    void setPrimaryStage(Stage primaryStage);
}
