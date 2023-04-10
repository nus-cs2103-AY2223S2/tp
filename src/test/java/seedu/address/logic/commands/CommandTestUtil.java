package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDING_INTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_FED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TANK;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.fish.FishEditCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.NameContainsKeywordsPredicate;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankNameContainsKeywordsPredicate;
import seedu.address.testutil.EditFishDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    /* Sorting */
    public static final String SORT_NAME = " " + PREFIX_SORT_BY + "n";
    public static final String SORT_LAST_FED_DATE = " " + PREFIX_SORT_BY + "lfd";
    public static final String SORT_SPECIES = " " + PREFIX_SORT_BY + "s";
    public static final String SORT_FEEDING = " " + PREFIX_SORT_BY + "fi";
    public static final String SORT_TANK = " " + PREFIX_SORT_BY + "tk";

    /* Tank additions */
    public static final String VALID_TANK_INDEX = "1";
    public static final String TANK_DESC = " " + PREFIX_TANK + VALID_TANK_INDEX;

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_LAST_FED_DATE_AMY = "01/01/2000 00:00";
    public static final String VALID_LAST_FED_DATE_BOB = "02/01/2000 00:00";
    public static final String VALID_SPECIES_AMY = "Guppy";
    public static final String VALID_SPECIES_BOB = "Tetra";
    public static final String VALID_FEEDING_INTERVAL_AMY = "0d15h";
    public static final String VALID_FEEDING_INTERVAL_BOB = "2d0h";
    public static final String VALID_TANK_AMY = "1";
    public static final String VALID_TANK_BOB = "2";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String LAST_FED_DATE_DESC_AMY = " " + PREFIX_LAST_FED_DATE + VALID_LAST_FED_DATE_AMY;
    public static final String LAST_FED_DATE_DESC_BOB = " " + PREFIX_LAST_FED_DATE + VALID_LAST_FED_DATE_BOB;
    public static final String SPECIES_DESC_AMY = " " + PREFIX_SPECIES + VALID_SPECIES_AMY;
    public static final String SPECIES_DESC_BOB = " " + PREFIX_SPECIES + VALID_SPECIES_BOB;
    public static final String FEEDING_INTERVAL_DESC_AMY = " " + PREFIX_FEEDING_INTERVAL + VALID_FEEDING_INTERVAL_AMY;
    public static final String FEEDING_INTERVAL_DESC_BOB = " " + PREFIX_FEEDING_INTERVAL + VALID_FEEDING_INTERVAL_BOB;
    public static final String TANK_DESC_AMY = " " + PREFIX_TANK + VALID_TANK_AMY;
    public static final String TANK_DESC_BOB = " " + PREFIX_TANK + VALID_TANK_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_LAST_FED_DATE_DESC =
            " " + PREFIX_LAST_FED_DATE + "11 Mar 2000 00:00"; //not dd/mm/yyyy
    public static final String INVALID_SPECIES_DESC = " " + PREFIX_SPECIES + "guppy!"; // '!' not allowed in species
    public static final String INVALID_FEEDING_INTERVAL_DESC = " "
            + PREFIX_FEEDING_INTERVAL; // empty string not allowed for feeding intervals
    public static final String INVALID_TANK_DESC = " " + PREFIX_TANK; // empty string not allowed for tanks
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final FishEditCommand.EditFishDescriptor DESC_AMY;
    public static final FishEditCommand.EditFishDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditFishDescriptorBuilder().withName(VALID_NAME_AMY)
                .withLastFedDate(VALID_LAST_FED_DATE_AMY).withSpecies(VALID_SPECIES_AMY)
                .withFeedingInterval(VALID_FEEDING_INTERVAL_AMY).withTank(VALID_TANK_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditFishDescriptorBuilder().withName(VALID_NAME_BOB)
                .withLastFedDate(VALID_LAST_FED_DATE_BOB).withSpecies(VALID_SPECIES_BOB)
                .withFeedingInterval(VALID_FEEDING_INTERVAL_BOB).withTank(VALID_TANK_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered fish list and selected fish in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Fish> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFishList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredFishList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the fish at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showFishAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFishList().size());

        Fish fish = model.getFilteredFishList().get(targetIndex.getZeroBased());
        final String[] splitName = fish.getName().fullName.split("\\s+");
        model.updateFilteredFishList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredFishList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the fish at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTankAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTankList().size());

        Tank fish = model.getFilteredTankList().get(targetIndex.getZeroBased());
        final String[] splitName = fish.getTankName().fullTankName.split("\\s+");
        model.updateFilteredTankList(new TankNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTankList().size());
    }

}
