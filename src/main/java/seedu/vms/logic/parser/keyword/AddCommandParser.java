package seedu.vms.logic.parser.keyword;

import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_KEYWORD_MAIN;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_KEYWORD_SUB;

import java.util.stream.Stream;

import seedu.vms.logic.commands.keyword.AddCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.CommandParser;
import seedu.vms.logic.parser.ParserUtil;
import seedu.vms.logic.parser.Prefix;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.keyword.Keyword;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements CommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddCommand parse(ArgumentMultimap argsMap) throws ParseException {
        if (!arePrefixesPresent(argsMap, PREFIX_KEYWORD_MAIN, PREFIX_KEYWORD_SUB)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        String keywordMain = ParserUtil.parseMainKeyword(argsMap.getValue(PREFIX_KEYWORD_MAIN).get());
        String keywordSub = ParserUtil.parseKeyword(argsMap.getValue(PREFIX_KEYWORD_SUB).get());
        if (keywordSub.matches(".*[\\s(--)]+.*")) {
            throw new ParseException("Subkeyword must be 1 word.");
        }

        Keyword keyword = new Keyword(keywordMain, keywordSub);
        return new AddCommand(keyword);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
