package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTanks.getTypicalTanks;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.fish.FishAddCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTankList;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TankList;
import seedu.address.model.fish.Fish;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.readings.AmmoniaLevel;
import seedu.address.model.tank.readings.PH;
import seedu.address.model.tank.readings.ReadOnlyReadingLevels;
import seedu.address.model.tank.readings.Reading;
import seedu.address.model.tank.readings.Temperature;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskFeedingReminder;
import seedu.address.testutil.FishBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullFish_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FishAddCommand(null, Index.fromOneBased(1)));
    }

    @Test
    public void execute_fishAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFishAdded modelStub = new ModelStubAcceptingFishAdded();
        Fish validFish = new FishBuilder().build();

        CommandResult commandResult = new FishAddCommand(validFish, Index.fromOneBased(1)).execute(modelStub);

        assertEquals(String.format(FishAddCommand.MESSAGE_SUCCESS, validFish), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validFish), modelStub.fishesAdded);
    }

    @Test
    public void execute_duplicateFish_throwsCommandException() {
        Fish validFish = new FishBuilder().build();
        FishAddCommand addCommand = new FishAddCommand(validFish, Index.fromOneBased(1));
        ModelStub modelStub = new ModelStubWithFish(validFish);

        assertThrows(CommandException.class,
                FishAddCommand.MESSAGE_DUPLICATE_FISH, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Fish alice = new FishBuilder().withName("Alice").build();
        Fish bob = new FishBuilder().withName("Bob").build();
        FishAddCommand addAliceCommand = new FishAddCommand(alice, Index.fromOneBased(1));
        FishAddCommand addBobCommand = new FishAddCommand(bob, Index.fromOneBased(1));

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        FishAddCommand addAliceCommandCopy = new FishAddCommand(alice, Index.fromOneBased(1));
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different fish -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        private TankList tankList;

        private ModelStub() {
            tankList = new TankList();
            tankList.setTanks(getTypicalTanks());
        }

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
        public void setGuiMode(GuiSettings.GuiMode newMode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFish(Fish fish) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasFish(Fish fish) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFish(Fish target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFish(Fish target, Fish editedFish) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Fish> getFilteredFishList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Fish> getSortedFishList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFishList(Predicate<Fish> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredFishList(Comparator<Fish> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        //=========== TaskList =============================================================
        @Override
        public void setTaskList(ReadOnlyTaskList taskList) {}

        @Override
        public ReadOnlyTaskList getTaskList() {
            return null;
        }

        @Override
        public Path getTaskListFilePath() {
            return null;
        }

        @Override
        public void setTaskListFilePath(Path taskListFilePath) {}

        @Override
        public boolean hasTask(Task task) {
            return false;
        }

        @Override
        public void addTask(Task task) {}

        @Override
        public void deleteTask(Task task) {}

        @Override
        public void setTask(Task target, Task editedTask) {}

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return null;
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {}

        //=========== TankList =============================================================
        @Override
        public void setTankList(ReadOnlyTankList tankList) {
            this.tankList.resetData(tankList);
        }

        public ReadOnlyTankList getTankList() {
            return tankList;
        }

        public Path getTankListFilePath() {
            return null;
        }

        public void setTankListFilePath(Path tankListFilePath) {}

        public boolean hasTank(Tank tank) {
            return false;
        }

        public void addTank(Tank tank) {}

        public void deleteTank(Tank tank) {}

        public void setTank(Tank target, Tank editedTank) {}

        public ObservableList<Tank> getFilteredTankList() {
            FilteredList<Tank> ret = new FilteredList<>(tankList.getTankList());
            return ret;
        }

        public void updateFilteredTankList(Predicate<Tank> predicate) {}

        @Override
        public void setLastFedDateTimeFishes(Tank tankToFeed, String formattedDate) {
            //TODO tests
        }

        @Override
        public Tank getTankFromIndex(Index index) {
            return null;
        }

        @Override
        public ArrayList<TaskFeedingReminder> executeFeedingReminderInitModel() {
            //TODO tests
            return null;
        }

        @Override
        public void addReadingsToIndividualReadingLevels(AmmoniaLevel a, PH ph, Temperature temp, Tank t) {

        }

        @Override
        public void setFullReadingLevels(ReadOnlyReadingLevels ammoniaLevels) {

        }

        @Override
        public ReadOnlyReadingLevels getFullReadingLevels() {
            return null;
        }

        @Override
        public boolean hasIndividualReadingLevels(UniqueIndividualReadingLevels ammoniaLevels) {
            return false;
        }

        @Override
        public void deleteIndividualReadingLevels(UniqueIndividualReadingLevels target) {

        }

        @Override
        public Reading[] deleteLastEntryFromIndividualReadings(UniqueIndividualReadingLevels target) {
            return new Reading[0];
        }

        @Override
        public void addIndividualReadingLevels(UniqueIndividualReadingLevels ammoniaLevels) {

        }

        @Override
        public void setIndividualReadingLevels(UniqueIndividualReadingLevels target,
                                               UniqueIndividualReadingLevels editedList) {

        }

        @Override
        public ObservableList<UniqueIndividualReadingLevels> getFilteredReadingLevels() {
            return null;
        }

        @Override
        public void updateFilteredReadingLevels(Predicate<UniqueIndividualReadingLevels> predicate) {

        }

        @Override
        public Index getTankIndex(Tank tank) {
            return null;
        }
    }

    /**
     * A Model stub that contains a single fish.
     */
    private class ModelStubWithFish extends ModelStub {
        private final Fish fish;

        ModelStubWithFish(Fish fish) {
            requireNonNull(fish);
            this.fish = fish;
        }

        @Override
        public boolean hasFish(Fish fish) {
            requireNonNull(fish);
            return this.fish.isSameFish(fish);
        }
    }

    /**
     * A Model stub that always accept the fish being added.
     */
    private class ModelStubAcceptingFishAdded extends ModelStub {
        final ArrayList<Fish> fishesAdded = new ArrayList<>();

        ModelStubAcceptingFishAdded() {
            super();
        }

        @Override
        public boolean hasFish(Fish fish) {
            requireNonNull(fish);
            return fishesAdded.stream().anyMatch(fish::isSameFish);
        }

        @Override
        public void addFish(Fish fish) {
            requireNonNull(fish);
            fishesAdded.add(fish);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
