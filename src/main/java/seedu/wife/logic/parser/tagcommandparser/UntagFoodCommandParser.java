package seedu.wife.logic.parser.tagcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.wife.commons.core.index.Index;
import seedu.wife.commons.util.StringUtil;
import seedu.wife.logic.commands.tagcommands.UntagFoodCommand;
import seedu.wife.logic.parser.ArgumentMultimap;
import seedu.wife.logic.parser.ArgumentTokenizer;
import seedu.wife.logic.parser.CliSyntax;
import seedu.wife.logic.parser.Parser;
import seedu.wife.logic.parser.ParserUtil;
import seedu.wife.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UntagFoodCommand object
 */
public class UntagFoodCommandParser implements Parser<UntagFoodCommand> {
    @Override
    public UntagFoodCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        Index idx = StringUtil.getIndexFromCommand(argMultimap.getPreamble().trim(), UntagFoodCommand.MESSAGE_USAGE);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntagFoodCommand.MESSAGE_USAGE));
        }

        String tagName = ParserUtil.parseTagName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()).toString();

        return new UntagFoodCommand(tagName, idx);
    }
}
