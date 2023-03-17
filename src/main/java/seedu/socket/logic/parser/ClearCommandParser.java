package seedu.socket.logic.parser;

import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.socket.commons.util.StringUtil;
import seedu.socket.logic.commands.ClearCommand;
import seedu.socket.logic.parser.exceptions.ParseException;
import seedu.socket.model.person.predicate.TagContainsKeywordsPredicate;

/**
 * Parses the clear command entered by user
 */
public class ClearCommandParser implements Parser<ClearCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommand
     * and returns an ClearCommand object for execution.
     */
    public ClearCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }

        List<String> tagArguments = StringUtil.convertArgumentsIntoList(
                argMultimap.getValue(PREFIX_TAG).orElse(""));

        return new ClearCommand(new TagContainsKeywordsPredicate(tagArguments));
    }
}
