package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GroupAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class GroupAddCommandParser implements Parser<GroupAddCommand> {

    String MESSAGE_TAG_DOES_NOT_EXIST_PARSE_FAILURE = "Did not specify tag field! \n%1$s";

    /**
     * Parses the given {@code String} of arguments in the context of the GroupAddCommand
     * and returns an specific case of EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GroupAddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupAddCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getAllValues(PREFIX_TAG).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_TAG_DOES_NOT_EXIST_PARSE_FAILURE, GroupAddCommand.MESSAGE_USAGE));
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        return new GroupAddCommand(index, tagList);
    }
}
