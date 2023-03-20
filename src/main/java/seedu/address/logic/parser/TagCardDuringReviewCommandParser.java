package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.TagCardDuringReviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class TagCardDuringReviewCommandParser implements Parser<TagCardDuringReviewCommand> {

    enum Difficulty {
        EASY, MEDIUM, HARD
    }

    private static boolean isValidDifficulty(String input) {
        try {
            Difficulty.valueOf(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public TagCardDuringReviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String userInput = args.trim();
        String tagName;
        if (isValidDifficulty(userInput.toUpperCase())) {
            tagName = userInput.toLowerCase();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TagCardDuringReviewCommand.MESSAGE_USAGE));
        }

        return new TagCardDuringReviewCommand(tagName);
    }
}
