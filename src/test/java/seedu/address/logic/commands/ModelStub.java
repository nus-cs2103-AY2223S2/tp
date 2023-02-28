package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyFriendlyLink;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Nric;

/**
 * A default model stub that have all the methods failing.
 */
class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getFriendlyLinkFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFriendlyLinkFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFriendlyLink(ReadOnlyFriendlyLink newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyFriendlyLink getFriendlyLink() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPair(Pair pair) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPair(Pair pair) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePair(Pair target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPair(Pair target, Pair editedPair) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addElderly(Elderly elderly) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasElderly(Elderly elderly) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteElderly(Elderly target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setElderly(Elderly target, Elderly editedElderly) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Elderly getElderly(Nric nric) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addVolunteer(Volunteer volunteer) {
        throw new AssertionError("This method should not be called.");
    }
    @Override
    public boolean hasVolunteer(Volunteer volunteer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteVolunteer(Volunteer target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setVolunteer(Volunteer target, Volunteer editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Volunteer getVolunteer(Nric nric) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Volunteer getVolunteerByNric(Nric nric) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Pair> getFilteredPairList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPairList(Predicate<Pair> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns an unmodifiable view of the filtered elderly list
     */
    @Override
    public ObservableList<Elderly> getFilteredElderlyList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredElderlyList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns an unmodifiable view of the filtered volunteers list
     */
    @Override
    public ObservableList<Volunteer> getFilteredVolunteerList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredVolunteerList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
