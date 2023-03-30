package seedu.address.logic.parser.documents;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVER_LETTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESUME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.documents.AddDocumentsCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.documents.CoverLetterLink;
import seedu.address.model.documents.Documents;
import seedu.address.model.documents.ResumeLink;

/**
 * Parses input arguments and creates a new AddContactCommand object
 */
public class AddDocumentsCommandParser implements Parser<AddDocumentsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddContactCommand
     * and returns an AddContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDocumentsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RESUME, PREFIX_COVER_LETTER);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDocumentsCommand.MESSAGE_USAGE),
                    pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_RESUME, PREFIX_COVER_LETTER)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDocumentsCommand.MESSAGE_USAGE));
        }

        ResumeLink resumeLink = ParserUtil.parseResumeLink(argMultimap.getValue(PREFIX_RESUME).get());
        CoverLetterLink coverLetterLink = ParserUtil.parseCoverLetterLink(argMultimap.getValue(PREFIX_COVER_LETTER)
                .get());

        Documents documents = new Documents(resumeLink, coverLetterLink);

        return new AddDocumentsCommand(index, documents);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
