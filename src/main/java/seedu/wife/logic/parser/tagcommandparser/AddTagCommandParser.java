package seedu.wife.logic.parser.tagcommandparser;

import seedu.wife.logic.commands.foodcommands.AddCommand;
import seedu.wife.logic.commands.tagcommands.AddTagCommand;
import seedu.wife.logic.parser.*;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.model.tag.Tag;
import seedu.wife.model.tag.TagName;

import java.util.Set;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_TAG;

public class AddTagCommandParser implements Parser<AddTagCommand> {
    @Override
    public AddTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

        TagName tagName = ParserUtil.parseTagName(argMultimap.getValue(PREFIX_NAME).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Tag tag = new Tag(tagName.toString());

        return new AddTagCommand(tag);
    }
}
