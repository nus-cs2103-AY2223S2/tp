package seedu.modtrek.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Locale;
import java.util.Set;

import seedu.modtrek.logic.commands.TagCommand;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.tag.Tag;

/**
 * The type Tag command parser.
 */
public class TagCommandParser implements Parser<TagCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public TagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        boolean isInclude;

        String preamble = argMultimap.getPreamble();
        String[] preambleParts = preamble.split(" ");
        if (preambleParts.length > 1 && preambleParts[1].toLowerCase(Locale.ROOT).equals("include")) {
            isInclude = true;
        } else if (preambleParts.length > 1 && preambleParts[1].toLowerCase(Locale.ROOT).equals("remove")) {
            isInclude = false;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        }
        Code code = ParserUtil.parseCode(preambleParts[0]);

        boolean isTagPresent = argMultimap.getValue(PREFIX_TAG).isPresent();
        if (!isTagPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_MISSING_PREFIX));
        }
        if (argMultimap.getAllValues(PREFIX_TAG).contains("")) {
            throw new ParseException(Tag.MESSAGE_MISSING_DETAIL);
        }
        Set<Tag> tag = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        return new TagCommand(code, isInclude, tag);
    }
}
