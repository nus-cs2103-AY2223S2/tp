package seedu.internship.logic.parser;

import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
import java.util.logging.Logger;

import seedu.internship.commons.core.LogsCenter;
import seedu.internship.logic.commands.FindCommand;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.internship.InternshipContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final Logger logger = LogsCenter.getLogger(FindCommandParser.class);


    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_ROLE, PREFIX_STATUS, PREFIX_DATE,
                        PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            FindCommandParser.logger.info("User inputted find command with non-empty preamble.");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()
                && !argMultimap.getValue(PREFIX_ROLE).isPresent()
                && !argMultimap.getValue(PREFIX_STATUS).isPresent()
                && !argMultimap.getValue(PREFIX_DATE).isPresent()
                && !argMultimap.getValue(PREFIX_TAG).isPresent()) {
            FindCommandParser.logger.info("User inputted find command with no fields");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        //@@author kohkaixun
        List<String> nameList = parseCompanyNamesToString(argMultimap.getAllValues(PREFIX_COMPANY_NAME));
        List<String> roleList = parseRolesToString(argMultimap.getAllValues(PREFIX_ROLE));
        List<String> statusList = parseStatusesToString(argMultimap.getAllValues(PREFIX_STATUS));
        List<String> dateList = parseDatesToString(argMultimap.getAllValues(PREFIX_DATE));
        List<String> tagList = parseTagsToString(argMultimap.getAllValues(PREFIX_TAG));
        //@@author
        return new FindCommand(new InternshipContainsKeywordsPredicate(nameList, roleList, statusList, dateList,
                tagList));
    }
}
