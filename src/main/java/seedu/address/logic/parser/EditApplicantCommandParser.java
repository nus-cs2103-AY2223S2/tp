package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT_WITH_ID;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditApplicantCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Applicant;

/**
 * Parses input arguments and creates a new AddApplicantCommand object
 */
public class EditApplicantCommandParser implements Parser<EditApplicantCommand> {

    public static final String HASHCODE_MESSAGE_CONSTRAINTS = "The id must be a 4-digit integer";
    /**
     * Parses the given {@code String} of arguments in the context of the AddApplicantCommand
     * and returns an AddApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditApplicantCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPLICANT, PREFIX_APPLICANT_WITH_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_APPLICANT, PREFIX_APPLICANT_WITH_ID)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditApplicantCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditApplicantCommand.MESSAGE_USAGE), pe);
        }

        String oldApplicantId = ParserUtil.parseApplicantWithId(argMultimap.getValue(
                PREFIX_APPLICANT_WITH_ID).get());

        Applicant newApplicant = ParserUtil.parseApplicant(argMultimap.getValue(PREFIX_APPLICANT).get());

        return new EditApplicantCommand(index, oldApplicantId, newApplicant);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
