package seedu.powercards.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.reviewcommands.ReviewCommand;
import seedu.powercards.logic.parser.exceptions.ParseException;
import seedu.powercards.model.tag.Tag.TagName;



public class ReviewCommandParserTest {

    private final ReviewCommandParser parser = new ReviewCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("a"));
    }

    @Test
    public void parse_validArgs_returnsReviewCommand() throws ParseException {
        String args = "1 -e -m -h";
        ReviewCommand expectedCommand = new ReviewCommand(Index.fromOneBased(1),
                List.of(new TagName[]{TagName.EASY, TagName.MEDIUM, TagName.HARD}));
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_validArgsWithCapitalLetters_returnsReviewCommand() throws ParseException {
        String args = "1 -E -M -H";
        ReviewCommand expectedCommand = new ReviewCommand(Index.fromOneBased(1),
                List.of(new TagName[]{TagName.EASY, TagName.MEDIUM, TagName.HARD}));
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_validArgsWithMixedCaseLetters_returnsReviewCommand() throws ParseException {
        String args = "1 -e -M -h";
        ReviewCommand expectedCommand = new ReviewCommand(Index.fromOneBased(1),
                List.of(new TagName[]{TagName.EASY, TagName.MEDIUM, TagName.HARD}));
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        String invalidInput = " " + "-e";
        assertThrows(ParseException.class, () -> parser.parse(invalidInput));
    }
}
