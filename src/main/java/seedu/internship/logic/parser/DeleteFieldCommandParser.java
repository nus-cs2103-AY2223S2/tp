package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.internship.logic.parser.ParserUtil.parseCompanyNamesToString;
import static seedu.internship.logic.parser.ParserUtil.parseDatesToString;
import static seedu.internship.logic.parser.ParserUtil.parseRolesToString;
import static seedu.internship.logic.parser.ParserUtil.parseStatusesToString;
import static seedu.internship.logic.parser.ParserUtil.parseTagsToString;

import java.util.List;

import seedu.internship.logic.commands.DeleteFieldCommand;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.internship.InternshipContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteFieldCommandParser implements Parser<DeleteFieldCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteFieldCommand
     * and returns a DeleteFieldCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteFieldCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_ROLE, PREFIX_STATUS, PREFIX_DATE,
                        PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteFieldCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isEmpty()
                && argMultimap.getValue(PREFIX_ROLE).isEmpty()
                && argMultimap.getValue(PREFIX_STATUS).isEmpty()
                && argMultimap.getValue(PREFIX_DATE).isEmpty()
                && argMultimap.getValue(PREFIX_TAG).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_MISSING_ARGUMENTS, DeleteFieldCommand.MESSAGE_USAGE));
        }

        List<String> nameList = parseCompanyNamesToString(argMultimap.getAllValues(PREFIX_COMPANY_NAME));
        List<String> roleList = parseRolesToString(argMultimap.getAllValues(PREFIX_ROLE));
        List<String> statusList = parseStatusesToString(argMultimap.getAllValues(PREFIX_STATUS));
        List<String> dateList = parseDatesToString(argMultimap.getAllValues(PREFIX_DATE));
        List<String> tagList = parseTagsToString(argMultimap.getAllValues(PREFIX_TAG));

        InternshipContainsKeywordsPredicate newPredicate = new InternshipContainsKeywordsPredicate(nameList,
                roleList, statusList, dateList, tagList);


        return new DeleteFieldCommand(newPredicate);
    }



}
