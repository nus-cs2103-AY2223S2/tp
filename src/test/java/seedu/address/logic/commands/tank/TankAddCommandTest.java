package seedu.address.logic.commands.tank;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTankList;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.fish.Fish;
import seedu.address.model.tank.Tank;
import seedu.address.model.task.Task;
import seedu.address.testutil.TankBuilder;




public class TankAddCommandTest {
    @Test
    public void constructor_nullTank_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TankAddCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Tank validTank = new TankBuilder().build();
        assertThrows(NullPointerException.class, () -> new TankAddCommand(validTank).execute(null));
    }

    @Test
    public void execute_duplicateFish_throwsCommandException() {
        Tank validTank = new TankBuilder().build();
        ModelStub modelStub = new ModelStubWithTank(validTank);
        TankAddCommand addCommand = new TankAddCommand(validTank);

        assertThrows(CommandException.class, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tank saltwaterTank = new TankBuilder().withTankName("Saltwater Tank 1").build();
        Tank freshwaterTank = new TankBuilder().withTankName("Freshwater Tank 3").build();
        TankAddCommand addSaltwaterCommand = new TankAddCommand(saltwaterTank);
        TankAddCommand addFreshwaterCommand = new TankAddCommand(freshwaterTank);

        // same object -> returns true
        assertTrue(addSaltwaterCommand.equals(addSaltwaterCommand));

        // same values -> returns true
        TankAddCommand addSaltwaterCommandCopy = new TankAddCommand(saltwaterTank);
        assertTrue(addSaltwaterCommand.equals(addSaltwaterCommandCopy));

        // different types -> returns false
        assertFalse(addSaltwaterCommand.equals(1));

        // null -> returns false
        assertFalse(addSaltwaterCommand.equals(null));

        // different fish -> returns false
        assertFalse(addSaltwaterCommand.equals(addFreshwaterCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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
        public void updateFilteredFishList(Predicate<Fish> predicate) {
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
        public void setTankList(ReadOnlyTankList tankList) {}

        public ReadOnlyTankList getTankList() {
            return null;
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
            return null;
        }

        public void updateFilteredTankList(Predicate<Tank> predicate) {}
    }

    /**
     * A Model stub that contains a single tank.
     */
    private class ModelStubWithTank extends ModelStub {
        private final Tank tank;

        ModelStubWithTank(Tank tank) {
            requireNonNull(tank);
            this.tank = tank;
        }

        @Override
        public boolean hasTank(Tank tank) {
            requireNonNull(tank);
            return this.tank.isSameTank(tank);
        }
    }

    /**
     * A Model stub that always accept the tank being added.
     */
    private class ModelStubAcceptingTankAdded extends ModelStub {
        final ArrayList<Tank> tanksAdded = new ArrayList<>();

        @Override
        public boolean hasTank(Tank tank) {
            requireNonNull(tank);
            return tanksAdded.stream().anyMatch(tank::isSameTank);
        }

        @Override
        public void addTank(Tank tank) {
            requireNonNull(tank);
            tanksAdded.add(tank);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
