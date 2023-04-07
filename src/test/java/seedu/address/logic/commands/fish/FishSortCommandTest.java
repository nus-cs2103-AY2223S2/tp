package seedu.address.logic.commands.fish;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalReadings.getTypicalFullReadingLevels;
import static seedu.address.testutil.TypicalTanks.getTypicalTankList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.fish.FishSortCommandParser;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.fish.Fish;
import seedu.address.model.tank.Tank;
import seedu.address.testutil.FishBuilder;

public class FishSortCommandTest {

    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalTaskList(),
            getTypicalTankList(), getTypicalFullReadingLevels());
    private final String[] unsortedNames = {"Amy Bee", "Zebra", "Benson"};
    private final String[] sortedNames = {"Amy Bee", "Benson", "Zebra"};
    private final String[] unsortedDates = {"10/06/2003 00:00", "15/06/2003 00:00", "10/06/2003 12:00"};
    private final String[] sortedDates = {"10/06/2003 00:00", "10/06/2003 12:00", "15/06/2003 00:00"};
    private final String[] unsortedIntervals = {"2d1h", "12d20h", "0d5h"};
    private final String[] sortedIntervals = {"0d5h", "2d1h", "12d20h"};
    private final String[] unsortedSpecies = {"Tetra", "Beta", "Parrot"};
    private final String[] sortedSpecies = {"Beta", "Parrot", "Tetra"};
    private final int[] unsortedTankIndexes = {1, 2, 1};
    private final int[] sortedTankIndexes = {2, 1, 1};

    @Test
    public void constructor_nullComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FishSortCommand(null, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Comparator<Fish> validComp = (o1, o2) -> 0;
        assertThrows(NullPointerException.class, () -> new FishSortCommand(validComp, null).execute(null));
    }

    @Test
    public void execute_invalidTankIndex_throwsCommandException() {
        Comparator<Fish> validComp = (o1, o2) -> 0;
        assertThrows(CommandException.class, () -> new FishSortCommand(validComp, Index.fromOneBased(5))
                .execute(model));
    }

    @Test
    public void execute_sortNameCommand_success() {
        FishBuilder fishBuilder = new FishBuilder();
        for (int i = 0; i < 3; i++) {
            fishBuilder.withName(unsortedNames[i]);
            Fish fish = fishBuilder.build();
            model.addFish(fish);
        }
        try {
            new FishSortCommand(FishSortCommandParser.NAME_COMPARATOR, null).execute(model);
        } catch (CommandException e) {
            fail("Exception in sortNameCommandTest");
        }
        ObservableList<Fish> list = model.getFilteredFishList();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(sortedNames[i], list.get(i).getName().toString());
        }
    }

    @Test
    public void execute_sortLastFedDateCommand_success() {
        FishBuilder fishBuilder = new FishBuilder();
        for (int i = 0; i < 3; i++) {
            fishBuilder.withName(unsortedNames[i]);
            fishBuilder.withLastFedDate(unsortedDates[i]);
            Fish fish = fishBuilder.build();
            model.addFish(fish);
        }
        try {
            new FishSortCommand(FishSortCommandParser.LAST_FED_COMPARATOR, null).execute(model);
        } catch (CommandException e) {
            fail("Exception in sortLastFedDateCommandTest");
        }
        ObservableList<Fish> list = model.getFilteredFishList();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(sortedDates[i], list.get(i).getLastFedDateTime().toString());
        }
    }

    @Test
    public void execute_sortFeedingIntervalCommand_success() {
        FishBuilder fishBuilder = new FishBuilder();
        for (int i = 0; i < 3; i++) {
            fishBuilder.withName(unsortedNames[i]);
            fishBuilder.withFeedingInterval(unsortedIntervals[i]);
            Fish fish = fishBuilder.build();
            model.addFish(fish);
        }
        try {
            new FishSortCommand(FishSortCommandParser.FEEDING_COMPARATOR, null).execute(model);
        } catch (CommandException e) {
            fail("Exception in sortFeedingIntervalCommandTest");
        }
        ObservableList<Fish> list = model.getFilteredFishList();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(sortedIntervals[i], list.get(i).getFeedingInterval().value);
        }
    }

    @Test
    public void execute_sortSpeciesCommand_success() {
        FishBuilder fishBuilder = new FishBuilder();
        for (int i = 0; i < 3; i++) {
            fishBuilder.withName(unsortedNames[i]);
            fishBuilder.withSpecies(unsortedSpecies[i]);
            Fish fish = fishBuilder.build();
            model.addFish(fish);
        }
        try {
            new FishSortCommand(FishSortCommandParser.SPECIES_COMPARATOR, null).execute(model);
        } catch (CommandException e) {
            fail("Exception in sortFeedingIntervalCommandTest");
        }
        ObservableList<Fish> list = model.getFilteredFishList();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(sortedSpecies[i], list.get(i).getSpecies().toString());
        }
    }

    @Test
    public void execute_sortTankCommand_success() {
        FishBuilder fishBuilder = new FishBuilder();
        ObservableList<Tank> tankList = model.getFilteredTankList();
        for (int i = 0; i < 3; i++) {
            fishBuilder.withName(unsortedNames[i]);
            Fish fish = fishBuilder.build();
            fish.setTank(tankList.get(unsortedTankIndexes[i] - 1));
            model.addFish(fish);
        }
        try {
            new FishSortCommand(FishSortCommandParser.TANK_COMPARATOR, null).execute(model);
        } catch (CommandException e) {
            fail("Exception in sortFeedingIntervalCommandTest");
        }
        ObservableList<Fish> list = model.getFilteredFishList();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(tankList.get(sortedTankIndexes[i] - 1), list.get(i).getTank());
        }
    }
}
