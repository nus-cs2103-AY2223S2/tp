package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVER_LETTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESUME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contact.EditContactCommand;
import seedu.address.logic.commands.documents.EditDocumentsCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditContactDescriptorBuilder;
import seedu.address.testutil.EditDocumentsDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BENSON = "Benson Meier";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_COMPANY_NAME_GOOGLE = "Google";
    public static final String VALID_COMPANY_NAME_NETFLIX = "Netflix";
    public static final String VALID_COMPANY_NAME_ORACLE = "Oracle";
    public static final String VALID_JOB_TITLE_PRODUCT_MANAGER = "Product Manager";
    public static final String VALID_JOB_TITLE_NETWORK_ENGINEER = "Network Engineer";
    public static final String VALID_JOB_TITLE_DATA_ENGINEER = "Data Engineer";
    public static final String VALID_PHONE_COMPANY_A = "33333333";
    public static final String VALID_PHONE_COMPANY_B = "55555555";
    public static final String VALID_COMPANY_NAME_BANK_OF_AMERICA = "Bank of America";
    public static final String VALID_COMPANY_NAME_CARL_KURZ = "Carl Kurz";
    public static final String VALID_COMPANY_NAME_DEUTSCHE_BANK = "Deutsche Bank";
    public static final String VALID_COMPANY_NAME_META = "Meta";
    public static final String VALID_JOB_TITLE_SOFTWARE_ENGINEER = "Software Engineer";
    public static final String VALID_JOB_TITLE_SOFTWARE_TESTER = "Software Tester";
    public static final String VALID_JOB_TITLE_CLOUD_ENGINEER = "Cloud Engineer";
    public static final String VALID_JOB_TITLE_WEB_DEVELOPER = "Web Developer";
    public static final String VALID_COMPANY_NAME_AMAZON = "Amazon";
    public static final String VALID_JOB_TITLE_META = "Software Tester";
    public static final String VALID_PHONE_BANK_OF_AMERICA = "33333333";
    public static final String VALID_PHONE_META = "55555555";
    public static final String VALID_PHONE_AMAZON = "66666666";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_BANK_OF_AMERICA = "example@bankofamerica.com";
    public static final String VALID_EMAIL_META = "meta@example.com";
    public static final String VALID_EMAIL_AMAZON = "example@amazon.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_RESUME_LINK_GOOGLE = "https://drive.example.com/resume_google";
    public static final String VALID_RESUME_LINK_NETFLIX = "https://drive.example.com/resume_netflix";
    public static final String VALID_RESUME_LINK_ORACLE = "https://drive.example.com/resume_oracle";
    public static final String VALID_COVER_LETTER_LINK_GOOGLE = "https://drive.example.com/coverletter_google";
    public static final String VALID_COVER_LETTER_LINK_NETFLIX = "https://drive.example.com/coverletter_netflix";
    public static final String VALID_COVER_LETTER_LINK_ORACLE = "https://drive.example.com/coverletter_oracle";
    public static final String VALID_STATUS_PENDING = "PENDING";
    public static final String VALID_STATUS_ACCEPTED = "ACCEPTED";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String PHONE_DESC_PLACEHOLDER = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_BANK_OF_AMERICA;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String EMAIL_DESC_PLACEHOLDER = " " + PREFIX_EMAIL + VALID_EMAIL_BANK_OF_AMERICA;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String PHONE_DESC_COMPANY_A = " " + PREFIX_PHONE + VALID_PHONE_COMPANY_A;
    public static final String PHONE_DESC_COMPANY_B = " " + PREFIX_PHONE + VALID_PHONE_COMPANY_B;
    public static final String RESUME_DESC_GOOGLE = " " + PREFIX_RESUME + VALID_RESUME_LINK_GOOGLE;
    public static final String RESUME_DESC_NETFLIX = " " + PREFIX_RESUME + VALID_RESUME_LINK_NETFLIX;
    public static final String COVER_LETTER_DESC_GOOGLE = " " + PREFIX_COVER_LETTER + VALID_COVER_LETTER_LINK_GOOGLE;
    public static final String COVER_LETTER_DESC_NETFLIX = " " + PREFIX_COVER_LETTER + VALID_COVER_LETTER_LINK_NETFLIX;
    public static final String PHONE_DESC_BANK_OF_AMERICA = " " + PREFIX_PHONE + VALID_PHONE_BANK_OF_AMERICA;
    public static final String EMAIL_DESC_BANK_OF_AMERICA = " " + PREFIX_EMAIL + VALID_EMAIL_BANK_OF_AMERICA;
    public static final String PHONE_DESC_META = " " + PREFIX_PHONE + VALID_PHONE_META;
    public static final String EMAIL_DESC_META = " " + PREFIX_EMAIL + VALID_EMAIL_META;
    public static final String STATUS_DESC_PENDING = " " + PREFIX_STATUS + VALID_STATUS_PENDING;
    public static final String STATUS_DESC_ACCEPTED = " " + PREFIX_STATUS + VALID_STATUS_ACCEPTED;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    // protocol missing in URL
    public static final String INVALID_RESUME_DESC = " " + PREFIX_RESUME + "example.com/resume_google";
    // missing domain name
    public static final String INVALID_COVER_LETTER_DESC = " " + PREFIX_COVER_LETTER + "https://-/cover_letter_google";
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "DELETED";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditDocumentsCommand.EditDocumentsDescriptor DESC_DOCUMENTS_GOOGLE;
    public static final EditDocumentsCommand.EditDocumentsDescriptor DESC_DOCUMENTS_NETFLIX;

    public static final EditContactCommand.EditContactDescriptor DESC_BANK_OF_AMERICA_CONTACT;
    public static final EditContactCommand.EditContactDescriptor DESC_META_CONTACT;

    static {
        DESC_DOCUMENTS_GOOGLE = new EditDocumentsDescriptorBuilder()
                .withResumeLink(VALID_RESUME_LINK_GOOGLE)
                .withCoverLetterLink(VALID_COVER_LETTER_LINK_GOOGLE)
                .build();
        DESC_DOCUMENTS_NETFLIX = new EditDocumentsDescriptorBuilder()
                .withResumeLink(VALID_RESUME_LINK_NETFLIX)
                .withCoverLetterLink(VALID_COVER_LETTER_LINK_NETFLIX)
                .build();
        DESC_BANK_OF_AMERICA_CONTACT = new EditContactDescriptorBuilder().withPhone(VALID_PHONE_BANK_OF_AMERICA)
                .withEmail(VALID_EMAIL_BANK_OF_AMERICA).build();
        DESC_META_CONTACT = new EditContactDescriptorBuilder().withPhone(VALID_PHONE_META)
                .withEmail(VALID_EMAIL_META).build();
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
     * Compares the given {@code result}, confirms that <br>
     * - the {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */

    public static void assertCommandSuccess(CommandResult result, Model actualModel,
        CommandResult expectedCommandResult, Model expectedModel) {
        assertEquals(expectedCommandResult, result);
        assertEquals(expectedModel, actualModel);
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(CommandResult, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */

    public static void assertCommandSuccess(CommandResult result, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(result, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered internship list and selected internship in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<InternshipApplication> expectedFilteredList = new ArrayList<>(
                actualModel.getSortedFilteredInternshipList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getSortedFilteredInternshipList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the internship at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showInternshipAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getSortedFilteredInternshipList().size());

        InternshipApplication internship = model.getSortedFilteredInternshipList().get(targetIndex.getZeroBased());
        final String[] splitName = internship.getCompanyName().fullName.split("\\s+");
        model.updateFilteredInternshipList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getSortedFilteredInternshipList().size());
    }
}

