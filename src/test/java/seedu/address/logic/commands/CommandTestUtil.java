package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.EduMate;
import seedu.address.model.Model;
import seedu.address.model.person.ContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String NAME_ALEX = "Alex Quinn";
    public static final String NAME_BEN = "Benjamin Khoo";
    public static final String PHONE_ALEX = "94587551";
    public static final String PHONE_BEN = "92552446";
    public static final String EMAIL_ALEX = "alexquinn@gmail.com";
    public static final String EMAIL_BEN = "benjaminkhoo@gmail.com";
    public static final String STATION_ALEX = "Queenstown";
    public static final String STATION_BEN = "Bencoolen";
    public static final String TELEGRAM_HANDLE_ALEX = "@alexquinn";
    public static final String TELEGRAM_HANDLE_BEN = "@benjaminkhoo";
    public static final Integer INDEX_ALEX = 16;
    public static final Integer INDEX_BEN = 17;

    public static final String VALID_GROUP_1 = "Groupmate";
    public static final String VALID_GROUP_2 = "TA";
    public static final String VALID_MODULE_1 = "CS2100";
    public static final String VALID_MODULE_2 = "GEA1000";

    public static final String NAME_DESC_ALEX = " " + Prefix.NAME + NAME_ALEX;
    public static final String NAME_DESC_BEN = " " + Prefix.NAME + NAME_BEN;
    public static final String PHONE_DESC_ALEX = " " + Prefix.PHONE + PHONE_ALEX;
    public static final String PHONE_DESC_BEN = " " + Prefix.PHONE + PHONE_BEN;
    public static final String EMAIL_DESC_ALEX = " " + Prefix.EMAIL + EMAIL_ALEX;
    public static final String EMAIL_DESC_BEN = " " + Prefix.EMAIL + EMAIL_BEN;
    public static final String TELEGRAM_DESC_ALEX = " " + Prefix.TELEGRAM_HANDLE + TELEGRAM_HANDLE_ALEX;
    public static final String TELEGRAM_DESC_BEN = " " + Prefix.TELEGRAM_HANDLE + TELEGRAM_HANDLE_BEN;

    public static final String STATION_DESC_ALEX = " " + Prefix.STATION + STATION_ALEX;
    public static final String STATION_DESC_BEN = " " + Prefix.STATION + STATION_BEN;
    public static final String VALID_GROUP_2_DESC = " " + Prefix.GROUP_TAG + VALID_GROUP_2;
    public static final String VALID_GROUP_1_DESC = " " + Prefix.GROUP_TAG + VALID_GROUP_1;
    public static final String VALID_MODULE_1_DESC = " " + Prefix.MODULE_TAG + VALID_MODULE_1;
    public static final String VALID_MODULE_2_DESC = " " + Prefix.MODULE_TAG + VALID_MODULE_2;

    public static final String INVALID_NAME_DESC = " " + Prefix.NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + Prefix.PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + Prefix.EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_STATION_DESC = " " + Prefix.STATION; // empty string not allowed for stations
    public static final String INVALID_TELEGRAM_DESC = " " + Prefix.TELEGRAM_HANDLE
            + "hellothere"; // missing '@' symbol
    public static final String INVALID_GROUP_DESC = " " + Prefix.GROUP_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_MODULE_1_DESC = " " + Prefix.MODULE_TAG + "c1101"; // first 2 or 3 not alphabets
    public static final String INVALID_MODULE_2_DESC = " " + Prefix.MODULE_TAG + "cfg111"; // not 4 digit module code

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_ALEX;
    public static final EditCommand.EditPersonDescriptor DESC_BEN;

    static {
        DESC_ALEX = new EditPersonDescriptorBuilder().withName(NAME_ALEX)
                .withPhone(PHONE_ALEX).withEmail(EMAIL_ALEX).withStation(STATION_ALEX)
                .withTelegramHandle(TELEGRAM_HANDLE_ALEX)
                .withContactIndex(INDEX_ALEX)
                .withGroupTags(VALID_GROUP_2)
                .withModuleTags(VALID_MODULE_1, VALID_MODULE_2).build();
        DESC_BEN = new EditPersonDescriptorBuilder().withName(NAME_BEN)
                .withPhone(PHONE_BEN).withEmail(EMAIL_BEN)
                .withStation(STATION_BEN)
                .withContactIndex(INDEX_BEN)
                .withTelegramHandle(TELEGRAM_HANDLE_BEN)
                .withGroupTags(VALID_GROUP_1, VALID_GROUP_2)
                .withModuleTags(VALID_MODULE_2).build();
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        EduMate expectedEduMate = new EduMate(actualModel.getEduMate());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getObservablePersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedEduMate, actualModel.getEduMate());
        assertEquals(expectedFilteredList, actualModel.getObservablePersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getObservablePersonList().size());

        Person person = model.getObservablePersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().getValue().split("\\s+");
        model.updateObservablePersonList(
                new ContainsKeywordsPredicate(Arrays.asList(splitName[0]), Prefix.NAME));

        assertEquals(1, model.getObservablePersonList().size());
    }

}
