package codoc.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import codoc.commons.core.GuiSettings;
import codoc.model.person.Person;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** User input that corrospond to show all person predicate */
    String PREDICATE_SHOW_ALL_PERSONS_INPUT = "";

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
     * Returns the user prefs' CoDoc file path.
     */
    Path getCodocFilePath();

    /**
     * Sets the user prefs' CoDoc file path.
     */
    void setCodocFilePath(Path codocFilePath);

    /**
     * Replaces CoDoc data with the data in {@code codoc}.
     */
    void setCodoc(ReadOnlyCodoc codoc);

    /** Returns the Codoc */
    ReadOnlyCodoc getCodoc();

    /**
     * Returns true if a person with the same identity as {@code person} exists in CoDoc.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in CoDoc.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in CoDoc.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in CoDoc.
     * The person identity of {@code editedPerson} must not be the same as another existing person in CoDoc.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    String updateFilteredPersonList(Predicate<Person> predicate, String userInput);

    Person getProtagonist();

    void setProtagonist(Person protagonist);

    String getCurrentTab();

    void setCurrentTab(String tab);

}
