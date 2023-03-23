package seedu.address.logic.parser.documents;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVER_LETTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESUME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.documents.EditDocumentsCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditDocumentsCommand object
 */
public class EditDocumentsCommandParser implements Parser<EditDocumentsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditDocumentsCommand
     * and returns an EditDocumentsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditDocumentsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RESUME, PREFIX_COVER_LETTER);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDocumentsCommand.MESSAGE_USAGE),
                    pe);
        }

        EditDocumentsCommand.EditDocumentsDescriptor editDocumentsDescriptor =
                new EditDocumentsCommand.EditDocumentsDescriptor();
        if (argMultimap.getValue(PREFIX_RESUME).isPresent()) {
            editDocumentsDescriptor.setResumeLink(
                    ParserUtil.parseResumeLink(argMultimap.getValue(PREFIX_RESUME).get()));
        }
        if (argMultimap.getValue(PREFIX_COVER_LETTER).isPresent()) {
            editDocumentsDescriptor.setCoverLetterLink(
                    ParserUtil.parseCoverLetterLink(argMultimap.getValue(PREFIX_COVER_LETTER).get()));
        }

        if (!editDocumentsDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditDocumentsCommand.MESSAGE_NOT_EDITED);
        }

        return new EditDocumentsCommand(index, editDocumentsDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
