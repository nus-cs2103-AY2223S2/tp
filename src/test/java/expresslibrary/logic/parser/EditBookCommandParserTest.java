package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static expresslibrary.logic.commands.CommandTestUtil.AUTHOR_DESC_ROWLING;
import static expresslibrary.logic.commands.CommandTestUtil.BORROW_DATE_DESC;
import static expresslibrary.logic.commands.CommandTestUtil.DUE_DATE_DESC;
import static expresslibrary.logic.commands.CommandTestUtil.INVALID_AUTHOR_DESC;
import static expresslibrary.logic.commands.CommandTestUtil.INVALID_ISBN_DESC;
import static expresslibrary.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static expresslibrary.logic.commands.CommandTestUtil.ISBN_DESC_HARRY;
import static expresslibrary.logic.commands.CommandTestUtil.TITLE_DESC_HARRY;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_AUTHOR_ROWLING;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_BORROW_DATE;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_DUE_DATE;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_ISBN_HARRY;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_TITLE_HARRY;
import static expresslibrary.logic.parser.CommandParserTestUtil.assertParseFailure;
import static expresslibrary.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static expresslibrary.testutil.TypicalIndexes.INDEX_FIRST;
import static expresslibrary.testutil.TypicalIndexes.INDEX_SECOND;
import static expresslibrary.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import expresslibrary.commons.core.index.Index;
import expresslibrary.logic.commands.EditBookCommand;
import expresslibrary.logic.commands.EditBookCommand.EditBookDescriptor;
import expresslibrary.model.book.Author;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;
import expresslibrary.testutil.EditBookDescriptorBuilder;

public class EditBookCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditBookCommand.MESSAGE_USAGE);

    private EditBookCommandParser parser = new EditBookCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE_HARRY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditBookCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + AUTHOR_DESC_ROWLING, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + AUTHOR_DESC_ROWLING, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS); // invalid title
        assertParseFailure(parser, "1" + INVALID_AUTHOR_DESC, Author.MESSAGE_CONSTRAINTS); // invalid author
        assertParseFailure(parser, "1" + INVALID_ISBN_DESC, Isbn.MESSAGE_CONSTRAINTS); // invalid isbn
        // assertParseFailure(parser, "1" + INVALID_BORROW_DATE, .MESSAGE_CONSTRAINTS); // invalid borrow date
        // assertParseFailure(parser, "1" + INVALID_DUE_DATE, .MESSAGE_CONSTRAINTS); // invalid due date

        // invalid title followed by valid author
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + AUTHOR_DESC_ROWLING, Title.MESSAGE_CONSTRAINTS);

        // valid author followed by invalid author. The test case for invalid author
        // followed by valid author
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + AUTHOR_DESC_ROWLING + INVALID_AUTHOR_DESC,
                Author.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_AUTHOR_DESC + VALID_ISBN_HARRY,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_HARRY + AUTHOR_DESC_ROWLING + ISBN_DESC_HARRY
                + BORROW_DATE_DESC + DUE_DATE_DESC;

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withTitle(VALID_TITLE_HARRY)
                .withAuthor(VALID_AUTHOR_ROWLING).withIsbn(VALID_ISBN_HARRY).withBorrowDate(VALID_BORROW_DATE)
                .withDueDate(VALID_DUE_DATE).build();
        EditBookCommand expectedCommand = new EditBookCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_HARRY + AUTHOR_DESC_ROWLING;

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withTitle(VALID_TITLE_HARRY)
                .withAuthor(VALID_AUTHOR_ROWLING).build();
        EditBookCommand expectedCommand = new EditBookCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_HARRY;
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withTitle(VALID_TITLE_HARRY).build();
        EditBookCommand expectedCommand = new EditBookCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // author
        userInput = targetIndex.getOneBased() + AUTHOR_DESC_ROWLING;
        descriptor = new EditBookDescriptorBuilder().withAuthor(VALID_AUTHOR_ROWLING).build();
        expectedCommand = new EditBookCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // isbn
        userInput = targetIndex.getOneBased() + ISBN_DESC_HARRY;
        descriptor = new EditBookDescriptorBuilder().withIsbn(VALID_ISBN_HARRY).build();
        expectedCommand = new EditBookCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // borrow date
        userInput = targetIndex.getOneBased() + BORROW_DATE_DESC;
        descriptor = new EditBookDescriptorBuilder().withBorrowDate(VALID_BORROW_DATE).build();
        expectedCommand = new EditBookCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // due date
        userInput = targetIndex.getOneBased() + DUE_DATE_DESC;
        descriptor = new EditBookDescriptorBuilder().withDueDate(VALID_DUE_DATE).build();
        expectedCommand = new EditBookCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_HARRY + AUTHOR_DESC_ROWLING + ISBN_DESC_HARRY
                + BORROW_DATE_DESC + DUE_DATE_DESC + TITLE_DESC_HARRY + AUTHOR_DESC_ROWLING + ISBN_DESC_HARRY
                + BORROW_DATE_DESC + DUE_DATE_DESC;

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withTitle(VALID_TITLE_HARRY)
                .withAuthor(VALID_AUTHOR_ROWLING).withIsbn(VALID_ISBN_HARRY).withBorrowDate(VALID_BORROW_DATE)
                .withDueDate(VALID_DUE_DATE).build();
        EditBookCommand expectedCommand = new EditBookCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_TITLE_DESC + AUTHOR_DESC_ROWLING;
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withAuthor(VALID_AUTHOR_ROWLING).build();
        EditBookCommand expectedCommand = new EditBookCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_TITLE_DESC + AUTHOR_DESC_ROWLING + ISBN_DESC_HARRY;
        descriptor = new EditBookDescriptorBuilder().withTitle(VALID_TITLE_HARRY).withAuthor(VALID_AUTHOR_ROWLING)
                .withIsbn(VALID_ISBN_HARRY).build();
        expectedCommand = new EditBookCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
