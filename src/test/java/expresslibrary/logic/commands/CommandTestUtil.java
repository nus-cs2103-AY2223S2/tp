package expresslibrary.logic.commands;

import static expresslibrary.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_BORROW_DATE;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_DUE_DATE;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_EMAIL;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_ISBN;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_NAME;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_PHONE;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_TAG;
import static expresslibrary.logic.parser.CliSyntax.PREFIX_TITLE;
import static expresslibrary.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import expresslibrary.commons.core.index.Index;
import expresslibrary.logic.commands.exceptions.CommandException;
import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.Model;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.TitleContainsKeywordsPredicate;
import expresslibrary.model.person.NameContainsKeywordsPredicate;
import expresslibrary.model.person.Person;
import expresslibrary.testutil.EditBookDescriptorBuilder;
import expresslibrary.testutil.EditPersonDescriptorBuilder;
import expresslibrary.testutil.TypicalPersons;

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
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_AUTHOR_ROWLING = "JK Rowling";
    public static final LocalDate VALID_BORROW_DATE = LocalDate.parse("30/03/2023");
    public static final Person VALID_BORROWER_ALICE = TypicalPersons.ALICE;
    public static final LocalDate VALID_DUE_DATE = LocalDate.parse("06/04/2023");
    public static final String VALID_ISBN_HARRY = "9780747532743";
    public static final String VALID_TITLE_ANIMAL = "Animal Farm";
    public static final String VALID_AUTHOR_ALEX = "Alexandra Harris";
    public static final String VALID_TITLE_HARRY = "Harry Potter";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String TITLE_DESC_HARRY = " " + PREFIX_TITLE + VALID_TITLE_HARRY;
    public static final String AUTHOR_DESC_ROWLING = " " + PREFIX_AUTHOR + VALID_AUTHOR_ROWLING;
    public static final String ISBN_DESC_HARRY = " " + PREFIX_ISBN + VALID_ISBN_HARRY;
    public static final String BORROW_DATE_DESC = " " + PREFIX_BORROW_DATE + VALID_BORROW_DATE;
    public static final String DUE_DATE_DESC = " " + PREFIX_DUE_DATE + VALID_DUE_DATE;
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
    public static final String INVALID_TITLE_DESC = " " + PREFIX_NAME; // empty string not allowed for titles
    public static final String INVALID_AUTHOR_DESC = " " + PREFIX_NAME + "Rowling&"; // '&' not allowed in authors
    public static final String INVALID_ISBN_DESC = " " + PREFIX_ISBN + "123a"; // 'a' not allowed in isbns
    public static final String INVALID_BORROW_DATE = " " + PREFIX_BORROW_DATE
            + "30/03/2023a"; // 'a' not allowed in borrow date
    public static final String INVALID_DUE_DATE = " " + PREFIX_DUE_DATE
            + "06/04/2023a"; // 'a' not allowed in due date


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPersonCommand.EditPersonDescriptor DESC_AMY;
    public static final EditPersonCommand.EditPersonDescriptor DESC_BOB;
    public static final EditBookCommand.EditBookDescriptor DESC_HARRY;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_HARRY = new EditBookDescriptorBuilder().withTitle(VALID_TITLE_HARRY)
                .withAuthor(VALID_AUTHOR_ROWLING).withIsbn(VALID_ISBN_HARRY).withBorrower(VALID_BORROWER_ALICE)
                .withBorrowDate(VALID_BORROW_DATE).withDueDate(VALID_DUE_DATE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult}
     * <br>
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
     * Convenience wrapper to
     * {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
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
     * - the express library, filtered person list and selected person in
     * {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ExpressLibrary expectedExpressLibrary = new ExpressLibrary(actualModel.getExpressLibrary());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedExpressLibrary, actualModel.getExpressLibrary());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given
     * {@code targetIndex} in the
     * {@code model}'s express library.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the book at the given
     * {@code targetIndex} in the
     * {@code model}'s express library.
     */
    public static void showBookAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBookList().size());

        Book book = model.getFilteredBookList().get(targetIndex.getZeroBased());
        final String[] splitTitle = book.getTitle().title.split("\\s+");
        model.updateFilteredBookList(new TitleContainsKeywordsPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredBookList().size());
    }
}
