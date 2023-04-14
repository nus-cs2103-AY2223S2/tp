package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESOURCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEACHER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModuleTracker;
import seedu.address.model.module.Module;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditModuleDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_CS3230 = "CS3230";
    public static final String VALID_NAME_CS3219 = "CS3219";
    public static final String VALID_RESOURCE_CS3230 = "www.cs3230.com";
    public static final String VALID_RESOURCE_CS3219 = "www.cs3219.com";
    public static final String VALID_TIMESLOT_CS3230 = "Tuesday 14:00 16:00";
    public static final String VALID_TIMESLOT_CS3219 = "Tuesday 12:00 14:00";
    public static final String VALID_ADDRESS_CS3230 = "COM1 02-10";
    public static final String VALID_ADDRESS_CS3219 = "COM1 02-12";
    public static final String VALID_TAG_CS3219 = "Lecture";
    public static final String VALID_TAG_CS3230 = "Tutorial";
    public static final String VALID_DEADLINE_CS3219 = "270223 14:00";
    public static final String VALID_DEADLINE_CS3230 = "300523 12:00";
    public static final String VALID_REMARK_CS3219 = "Hybrid";
    public static final String VALID_REMARK_CS3230 = "Zoom";
    public static final String VALID_TEACHER_CS3219 = "Prof. Z";
    public static final String VALID_TEACHER_CS3230 = "Prof. X";

    public static final String NAME_DESC_CS3230 = " " + PREFIX_NAME + VALID_NAME_CS3230;
    public static final String NAME_DESC_CS3219 = " " + PREFIX_NAME + VALID_NAME_CS3219;
    public static final String TAG_DESC_CS3230 = " " + PREFIX_TAG + VALID_TAG_CS3230;
    public static final String TAG_DESC_CS3219 = " " + PREFIX_TAG + VALID_TAG_CS3219;
    public static final String TAG_DESC_CS3230_MULTIPLE_TAGS = TAG_DESC_CS3230 + TAG_DESC_CS3219;
    public static final String TIMESLOT_DESC_CS3230 = " " + PREFIX_TIMESLOT + VALID_TIMESLOT_CS3230;
    public static final String TIMESLOT_DESC_CS3219 = " " + PREFIX_TIMESLOT + VALID_TIMESLOT_CS3219;
    public static final String ADDRESS_DESC_CS3230 = " " + PREFIX_ADDRESS + VALID_ADDRESS_CS3230;
    public static final String ADDRESS_DESC_CS3219 = " " + PREFIX_ADDRESS + VALID_ADDRESS_CS3219;
    public static final String TAG_DESC_TUTORIAL = " " + PREFIX_TAG + VALID_TAG_CS3230;
    public static final String TAG_DESC_LECTURE = " " + PREFIX_TAG + VALID_TAG_CS3219;
    public static final String DEADLINE_DESC_CS3219 = " " + PREFIX_DEADLINE + VALID_DEADLINE_CS3219;
    public static final String DEADLINE_DESC_CS3230 = " " + PREFIX_DEADLINE + VALID_DEADLINE_CS3230;
    public static final String REMARK_DESC_CS3219 = " " + PREFIX_REMARK + VALID_REMARK_CS3219;
    public static final String REMARK_DESC_CS3230 = " " + PREFIX_REMARK + VALID_REMARK_CS3230;
    public static final String TEACHER_DESC_CS3219 = " " + PREFIX_TEACHER + VALID_TEACHER_CS3219;
    public static final String TEACHER_DESC_CS3230 = " " + PREFIX_TEACHER + VALID_TEACHER_CS3230;

    public static final String RESOURCE_DESC_CS3230 = " " + PREFIX_RESOURCE + VALID_RESOURCE_CS3230;

    public static final String RESOURCE_DESC_CS3219 = " " + PREFIX_RESOURCE + VALID_RESOURCE_CS3219;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "ST2334&"; // '&' not allowed in names
    public static final String INVALID_TYPE_DESC = " " + PREFIX_TAG + "911a"; // 'a' not allowed in types
    public static final String INVALID_TIMESLOT_DESC = " " + PREFIX_TIMESLOT + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    // public static final String PREAMBLE_WHITESPACE = " \n  \t ";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditModuleDescriptor DESC_CS3230;
    public static final EditCommand.EditModuleDescriptor DESC_CS3219;

    static {
        DESC_CS3230 = new EditModuleDescriptorBuilder().withName(VALID_NAME_CS3230)
                .withResource(VALID_RESOURCE_CS3230).withTimeSlot(VALID_TIMESLOT_CS3230)
                .withAddress(VALID_ADDRESS_CS3230)
                .withTags(VALID_TAG_CS3230).build();
        DESC_CS3219 = new EditModuleDescriptorBuilder().withName(VALID_NAME_CS3219)
                .withResource(VALID_RESOURCE_CS3219).withTimeSlot(VALID_TIMESLOT_CS3219)
                .withAddress(VALID_ADDRESS_CS3219)
                .withTags(VALID_TAG_CS3219, VALID_TAG_CS3230).build();
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
     * - the module tracker, filtered module list and selected module in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ModuleTracker expectedModuleTracker = new ModuleTracker(actualModel.getModuleTracker());
        List<Module> expectedFilteredList = new ArrayList<>(actualModel.getDisplayedModuleList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedModuleTracker, actualModel.getModuleTracker());
        assertEquals(expectedFilteredList, actualModel.getDisplayedModuleList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the module at the given {@code targetIndex} in the
     * {@code model}'s module tracker.
     */
    public static void showModuleAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getDisplayedModuleList().size());

        Module module = model.getDisplayedModuleList().get(targetIndex.getZeroBased());
        final String[] splitName = module.getName().fullName.split("\\s+");
        model.updateFilteredModuleList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getDisplayedModuleList().size());
    }

}
