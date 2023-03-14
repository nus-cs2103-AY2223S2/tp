package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new TagCommand object
 */
public class TagCommandParser implements Parser<TagCommand> {
    private static final int INPUT_INDEX = 0;
    private static final int TAG_INDEX = 1;

    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns a TagCommand object for execution.
     *
     * @param args The arguments to the TagCommand
     * @return The parsed TagCommand
     * @throws ParseException if {@code args} does not conform the expected format
     */
    public TagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split(" ");
        Index index = ParserUtil.parseIndex(nameKeywords[INPUT_INDEX]);
        Tag tag = ParserUtil.parseTag(nameKeywords[TAG_INDEX]);

        return new TagCommand(index, tag);
    }

}
