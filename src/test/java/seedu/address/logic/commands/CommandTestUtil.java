package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_PREMIUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_START_DATE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditClientDescriptorBuilder;
import seedu.address.testutil.EditPolicyDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_APPOINTMENT_NAME = "Discussion";
    public static final String VALID_APPOINTMENT_DATE = "05.05.2040";
    public static final String VALID_POLICY_NAME_AMY = "Life Insurance";
    public static final String VALID_POLICY_NAME_BOB = "Health Insurance";
    public static final String VALID_POLICY_DATE_AMY = "01.01.2020";
    public static final String VALID_POLICY_DATE_BOB = "03.03.2023";
    public static final String VALID_POLICY_PREMIUM_AMY = "1000";
    public static final String VALID_POLICY_PREMIUM_BOB = "2000";
    public static final String VALID_POLICY_FREQUENCY_AMY = "monthly";
    public static final String VALID_POLICY_FREQUENCY_BOB = "yearly";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;

    public static final String POLICY_NAME_AMY = " " + PREFIX_POLICY_NAME + VALID_POLICY_NAME_AMY;
    public static final String POLICY_NAME_BOB = " " + PREFIX_POLICY_NAME + VALID_POLICY_NAME_BOB;
    public static final String POLICY_DATE_AMY = " " + PREFIX_POLICY_START_DATE + VALID_POLICY_DATE_AMY;
    public static final String POLICY_DATE_BOB = " " + PREFIX_POLICY_START_DATE + VALID_POLICY_DATE_BOB;
    public static final String POLICY_PREMIUM_AMY = " " + PREFIX_POLICY_PREMIUM + VALID_POLICY_PREMIUM_AMY;
    public static final String POLICY_PREMIUM_BOB = " " + PREFIX_POLICY_PREMIUM + VALID_POLICY_PREMIUM_BOB;
    public static final String POLICY_FREQUENCY_AMY = " " + PREFIX_POLICY_FREQUENCY + VALID_POLICY_FREQUENCY_AMY;
    public static final String POLICY_FREQUENCY_BOB = " " + PREFIX_POLICY_FREQUENCY + VALID_POLICY_FREQUENCY_BOB;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_POLICY_NAME = " " + PREFIX_POLICY_NAME
            + "Life Insurance*"; // '*' not allowed in policy names
    public static final String INVALID_POLICY_DATE = " " + PREFIX_POLICY_START_DATE
            + "01/01/2020"; // '/' not allowed in policy dates
    public static final String INVALID_POLICY_PREMIUM = " " + PREFIX_POLICY_PREMIUM
            + "$203"; // '$' not allowed in policy premiums
    public static final String INVALID_POLICY_FREQUENCY = " " + PREFIX_POLICY_FREQUENCY
            + "daily"; // 'weekly' not allowed in policy frequencies
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditClientDescriptor DESC_AMY;
    public static final EditCommand.EditClientDescriptor DESC_BOB;

    public static final EditPolicyCommand.EditPolicyDescriptor DESC_POLICY_AMY;

    public static final EditPolicyCommand.EditPolicyDescriptor DESC_POLICY_BOB;

    static {
        DESC_AMY = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).build();
        DESC_BOB = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).build();
        DESC_POLICY_AMY = new EditPolicyDescriptorBuilder().withPolicyName(VALID_POLICY_NAME_AMY)
                .withStartDate(VALID_POLICY_DATE_AMY).withPremium(VALID_POLICY_PREMIUM_AMY)
                .withFrequency(VALID_POLICY_FREQUENCY_AMY).build();
        DESC_POLICY_BOB = new EditPolicyDescriptorBuilder().withPolicyName(VALID_POLICY_NAME_BOB)
                .withStartDate(VALID_POLICY_DATE_BOB).withPremium(VALID_POLICY_PREMIUM_BOB)
                .withFrequency(VALID_POLICY_FREQUENCY_BOB).build();

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
     * - the address book, filtered client list and selected client in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Client> expectedFilteredList = new ArrayList<>(actualModel.getFilteredClientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredClientList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showClientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().fullName.split("\\s+");
        model.updateFilteredClientList(new NameContainsKeywordsPredicate(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }

}
