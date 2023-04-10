package seedu.powercards.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.powercards.logic.parser.CliSyntax.REVIEW_EASY_FLAG;
import static seedu.powercards.logic.parser.CliSyntax.REVIEW_HARD_FLAG;
import static seedu.powercards.logic.parser.CliSyntax.REVIEW_MEDIUM_FLAG;
import static seedu.powercards.logic.parser.CliSyntax.REVIEW_UPPER_EASY_FLAG;
import static seedu.powercards.logic.parser.CliSyntax.REVIEW_UPPER_HARD_FLAG;
import static seedu.powercards.logic.parser.CliSyntax.REVIEW_UPPER_MEDIUM_FLAG;
import static seedu.powercards.model.tag.Tag.TagName.EASY;
import static seedu.powercards.model.tag.Tag.TagName.HARD;
import static seedu.powercards.model.tag.Tag.TagName.MEDIUM;

import java.util.ArrayList;
import java.util.List;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.reviewcommands.ReviewCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;
import seedu.powercards.model.tag.Tag.TagName;

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
                ArgumentTokenizer.tokenize(args, REVIEW_EASY_FLAG, REVIEW_MEDIUM_FLAG, REVIEW_HARD_FLAG,
                        REVIEW_UPPER_EASY_FLAG, REVIEW_UPPER_MEDIUM_FLAG, REVIEW_UPPER_HARD_FLAG);

        List<TagName> difficulties = new ArrayList<>();
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ReviewCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(REVIEW_EASY_FLAG).isPresent()) {
            difficulties.add(EASY);
            try {
                ParserUtil.parseEmptyInput(argMultimap.getValue(REVIEW_EASY_FLAG).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReviewCommand.MESSAGE_USAGE), pe);
            }
        } else if (argMultimap.getValue(REVIEW_UPPER_EASY_FLAG).isPresent()) {
            difficulties.add(EASY);
            try {
                ParserUtil.parseEmptyInput(argMultimap.getValue(REVIEW_UPPER_EASY_FLAG).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReviewCommand.MESSAGE_USAGE), pe);
            }
        }

        if (argMultimap.getValue(REVIEW_MEDIUM_FLAG).isPresent()) {
            difficulties.add(MEDIUM);
            try {
                ParserUtil.parseEmptyInput(argMultimap.getValue(REVIEW_MEDIUM_FLAG).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReviewCommand.MESSAGE_USAGE), pe);
            }
        } else if (argMultimap.getValue(REVIEW_UPPER_MEDIUM_FLAG).isPresent()) {
            difficulties.add(MEDIUM);
            try {
                ParserUtil.parseEmptyInput(argMultimap.getValue(REVIEW_UPPER_MEDIUM_FLAG).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReviewCommand.MESSAGE_USAGE), pe);
            }
        }

        if (argMultimap.getValue(REVIEW_HARD_FLAG).isPresent()) {
            difficulties.add(HARD);
            try {
                ParserUtil.parseEmptyInput(argMultimap.getValue(REVIEW_HARD_FLAG).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReviewCommand.MESSAGE_USAGE), pe);
            }
        } else if (argMultimap.getValue(REVIEW_UPPER_HARD_FLAG).isPresent()) {
            difficulties.add(HARD);
            try {
                ParserUtil.parseEmptyInput(argMultimap.getValue(REVIEW_UPPER_HARD_FLAG).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReviewCommand.MESSAGE_USAGE), pe);
            }
        }

        return new ReviewCommand(index, difficulties);
    }
}
