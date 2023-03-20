package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT_WITH_ID;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteApplicantCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteApplicantCommand object
 */
public class DeleteApplicantCommandParser implements Parser<DeleteApplicantCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteApplicantCommand
     * and returns a DeleteApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteApplicantCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPLICANT_WITH_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_APPLICANT_WITH_ID)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteApplicantCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteApplicantCommand.MESSAGE_USAGE), pe);
        }

        String applicantNameToDelete = ParserUtil.parseApplicantWithId(argMultimap.getValue(
                PREFIX_APPLICANT_WITH_ID).get());

        return new DeleteApplicantCommand(index, applicantNameToDelete);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
