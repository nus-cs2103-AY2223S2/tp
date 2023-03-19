package seedu.wife.logic.parser.tagcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.wife.logic.commands.tagcommands.CreateTagCommand;
import seedu.wife.logic.parser.ArgumentMultimap;
import seedu.wife.logic.parser.ArgumentTokenizer;
import seedu.wife.logic.parser.Parser;
import seedu.wife.logic.parser.ParserUtil;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.model.tag.Tag;
import seedu.wife.model.tag.TagName;

/**
 * Parses input arguments and create a new CreateTagCommand object
 */
public class CreateTagCommandParser implements Parser<CreateTagCommand> {
    @Override
    public CreateTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateTagCommand.MESSAGE_USAGE));
        }

        TagName tagName = ParserUtil.parseTagName(argMultimap.getValue(PREFIX_NAME).get());

        Tag tag = new Tag(tagName.toString());

        return new CreateTagCommand(tag);
    }
}
