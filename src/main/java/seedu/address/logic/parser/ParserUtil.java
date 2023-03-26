package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Question;
import seedu.address.model.deck.Deck;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_N_INPUT = "Input must be an integer between 1 and 2147483647"
            + " inclusive or the String 'all'";
    public static final String MESSAGE_INVALID_INTEGER = "Input must be an integer between 1 and 2147483647 inclusive";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String question} into a {@code Question}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Question parseQuestion(String question) throws ParseException {
        requireNonNull(question);
        String trimmedQuestion = question.trim();
        if (!Question.isValidQuestion(trimmedQuestion)) {
            throw new ParseException(Question.MESSAGE_CONSTRAINTS);
        }
        return new Question(trimmedQuestion);
    }

    /**
     * Parses a {@code String answer} into an {@code Answer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Answer parseAnswer(String answer) throws ParseException {
        requireNonNull(answer);
        String trimmedAnswer = answer.trim();
        if (!Answer.isValidAnswer(trimmedAnswer)) {
            throw new ParseException(Answer.MESSAGE_CONSTRAINTS);
        }
        return new Answer(trimmedAnswer);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        // user can't type untagged
        if (!Tag.isValidTagName(trimmedTag) || trimmedTag.toLowerCase().equals("untagged")) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code String deckName} into a {@code Deck}
     */
    public static Deck parseDeck(String deckName) throws ParseException {
        requireNonNull(deckName);
        String trimmedDeckName = deckName.trim();
        if (!Deck.isValidDeckName(trimmedDeckName)) {
            throw new ParseException(Deck.MESSAGE_CONSTRAINTS);
        }
        return new Deck(trimmedDeckName);
    }

    /**
     * Parses a String {@code userInput} into an {@code Integer} and returns it. Leading and trailing whitespaces
     * will be trimmed.
     * @throws ParseException if the specified String is invalid (not non-zero unsigned integer or
     *      not the String "all").
     */
    public static int parseNumCardsPerReview(String userInput) throws ParseException {
        String trimmedUserInput = userInput.trim();
        if (trimmedUserInput.equalsIgnoreCase("all")) {
            return -1;
        } else if (trimmedUserInput.matches("\\d+")) {
            if (!StringUtil.isNonZeroUnsignedInteger(trimmedUserInput)) {
                throw new ParseException(MESSAGE_INVALID_INTEGER);
            }
            return Integer.parseInt(trimmedUserInput);
        } else {
            throw new ParseException(MESSAGE_INVALID_N_INPUT);
        }
    }

}
