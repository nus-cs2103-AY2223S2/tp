package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EASY_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HARD_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDIUM_TAG;
import static seedu.address.model.tag.Tag.TagName.EASY;
import static seedu.address.model.tag.Tag.TagName.HARD;
import static seedu.address.model.tag.Tag.TagName.MEDIUM;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.reviewcommands.ReviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag.TagName;

/**
 * Parses input arguments and creates a new ReviewCommand object
 */
public class ReviewCommandParser implements Parser<ReviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReviewCommand
     * and returns an ReviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EASY_TAG, PREFIX_MEDIUM_TAG, PREFIX_HARD_TAG);

        List<TagName> difficulties = new ArrayList<>();
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ReviewCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_EASY_TAG).isPresent()) {
            difficulties.add(EASY);
        }

        if (argMultimap.getValue(PREFIX_MEDIUM_TAG).isPresent()) {
            difficulties.add(MEDIUM);
        }

        if (argMultimap.getValue(PREFIX_HARD_TAG).isPresent()) {
            difficulties.add(HARD);
        }

        return new ReviewCommand(index, difficulties);
    }
}
