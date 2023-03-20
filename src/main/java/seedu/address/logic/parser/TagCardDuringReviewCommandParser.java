package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.TagCardDuringReviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The TagCardDuringReviewCommandParser class is responsible for parsing user input and
 * creating a new TagCardDuringReviewCommand object.
 */
public class TagCardDuringReviewCommandParser implements Parser<TagCardDuringReviewCommand> {

    /**
     * The Difficulty enum represents the difficulty levels that can be assigned to a card during review.
     */
    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    /**
     * Parses the user input and creates a new TagCardDuringReviewCommand object.
     *
     * @param args the user input as a string
     * @return a new TagCardDuringReviewCommand object with the specified tag name
     * @throws ParseException if the user input is invalid or the tag name is missing
     */
    public TagCardDuringReviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String tagName = ParserUtil.parseTagDuringReview(args);
        return new TagCardDuringReviewCommand(tagName);
    }
}
