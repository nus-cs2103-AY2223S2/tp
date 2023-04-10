package seedu.quickcontacts.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.quickcontacts.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.quickcontacts.commons.core.index.Index;
import seedu.quickcontacts.logic.commands.exceptions.CommandException;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.person.NameContainsKeywordsPredicate;
import seedu.quickcontacts.model.person.Person;
import seedu.quickcontacts.testutil.EditMeetingDescriptorBuilder;
import seedu.quickcontacts.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_ALICE = "Alice Pauline";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_ALICE = " " + PREFIX_NAME + VALID_NAME_ALICE;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_MEETING_TITLE = "Welfare call";
    public static final String VALID_MEETING_DATETIME = "01/01/2021 11:11";
    public static final String VALID_MEETING_LOCATION = "In school";
    public static final String VALID_MEETING_DESCRIPTION = "Finish up school project (computer science)";
    public static final String VALID_MEETING_TITLE_2 = "Project discussion";
    public static final String VALID_MEETING_DATETIME_2 = "02/01/2023 12:00";
    public static final String VALID_MEETING_LOCATION_2 = "Central Library";
    public static final String VALID_MEETING_DESCRIPTION_2 = "URGENT";

    public static final String MEETING_TITLE_DESC = " " + PREFIX_MEETING_TITLE + VALID_MEETING_TITLE;
    public static final String MEETING_PERSON_ALICE_DESC = " " + PREFIX_PERSON + "Alice Pauline";
    public static final String MEETING_PERSON_BENSON_DESC = " " + PREFIX_PERSON + "Benson Meier";
    public static final String MEETING_DATETIME_DESC = " " + PREFIX_DATETIME + VALID_MEETING_DATETIME;
    public static final String MEETING_LOCATION_DESC = " " + PREFIX_LOCATION + VALID_MEETING_LOCATION;
    public static final String MEETING_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + VALID_MEETING_DESCRIPTION;

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    public static final EditMeetingsCommand.EditMeetingDescriptor DESC_MEETING_A;
    public static final EditMeetingsCommand.EditMeetingDescriptor DESC_MEETING_B;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_MEETING_A = new EditMeetingDescriptorBuilder().withTitle(VALID_MEETING_TITLE)
            .withDateTime(VALID_MEETING_DATETIME).withAttendees().withLocation(VALID_MEETING_LOCATION)
            .withDescription(VALID_MEETING_DESCRIPTION).build();
        DESC_MEETING_B = new EditMeetingDescriptorBuilder().withTitle(VALID_MEETING_TITLE_2)
                .withDateTime(VALID_MEETING_DATETIME_2).withAttendees().withLocation(VALID_MEETING_LOCATION_2)
                .withDescription(VALID_MEETING_DESCRIPTION_2).build();
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
     * - the quick book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        QuickBook expectedQuickBook = new QuickBook(actualModel.getQuickBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedQuickBook, actualModel.getQuickBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s quick book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
