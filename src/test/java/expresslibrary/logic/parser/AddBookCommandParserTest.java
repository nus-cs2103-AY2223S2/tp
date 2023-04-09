package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static expresslibrary.logic.commands.CommandTestUtil.AUTHOR_DESC_ROWLING;
import static expresslibrary.logic.commands.CommandTestUtil.INVALID_AUTHOR_DESC;
import static expresslibrary.logic.commands.CommandTestUtil.INVALID_ISBN_DESC;
import static expresslibrary.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static expresslibrary.logic.commands.CommandTestUtil.ISBN_DESC_HARRY;
import static expresslibrary.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static expresslibrary.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static expresslibrary.logic.commands.CommandTestUtil.TITLE_DESC_HARRY;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_AUTHOR_ROWLING;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_ISBN_HARRY;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_TITLE_HARRY;
import static expresslibrary.logic.parser.CommandParserTestUtil.assertParseFailure;
import static expresslibrary.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static expresslibrary.testutil.TypicalBooks.BELOVED;

import org.junit.jupiter.api.Test;

import expresslibrary.logic.commands.AddBookCommand;
import expresslibrary.model.book.Author;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;
import expresslibrary.testutil.BookBuilder;

public class AddBookCommandParserTest {
    private AddBookCommandParser parser = new AddBookCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Book expectedBook = new BookBuilder(BELOVED).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_HARRY + AUTHOR_DESC_ROWLING
                + ISBN_DESC_HARRY, new AddBookCommand(expectedBook));

        // multiple titles - last title accepted
        assertParseSuccess(parser, TITLE_DESC_HARRY + TITLE_DESC_HARRY + AUTHOR_DESC_ROWLING + ISBN_DESC_HARRY,
                new AddBookCommand(expectedBook));

        // multiple authors - last author accepted
        assertParseSuccess(parser, TITLE_DESC_HARRY + AUTHOR_DESC_ROWLING + AUTHOR_DESC_ROWLING
                + ISBN_DESC_HARRY, new AddBookCommand(expectedBook));

        // multiple isbns - last isbn accepted
        assertParseSuccess(parser, TITLE_DESC_HARRY + AUTHOR_DESC_ROWLING + ISBN_DESC_HARRY + ISBN_DESC_HARRY,
                new AddBookCommand(expectedBook));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_HARRY + AUTHOR_DESC_ROWLING + ISBN_DESC_HARRY, expectedMessage);

        // missing author prefix
        assertParseFailure(parser, TITLE_DESC_HARRY + VALID_AUTHOR_ROWLING + ISBN_DESC_HARRY, expectedMessage);

        // missing isbn prefix
        assertParseFailure(parser, TITLE_DESC_HARRY + AUTHOR_DESC_ROWLING + VALID_ISBN_HARRY, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + AUTHOR_DESC_ROWLING + ISBN_DESC_HARRY,
                Title.MESSAGE_CONSTRAINTS);

        // invalid author
        assertParseFailure(parser, TITLE_DESC_HARRY + INVALID_AUTHOR_DESC + ISBN_DESC_HARRY,
                Author.MESSAGE_CONSTRAINTS);

        // invalid isbn
        assertParseFailure(parser, TITLE_DESC_HARRY + AUTHOR_DESC_ROWLING + INVALID_ISBN_DESC,
                Isbn.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + INVALID_AUTHOR_DESC + ISBN_DESC_HARRY,
                Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_HARRY + AUTHOR_DESC_ROWLING
                + ISBN_DESC_HARRY, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookCommand.MESSAGE_USAGE));
    }
}
