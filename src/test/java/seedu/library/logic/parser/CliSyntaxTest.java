package seedu.library.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.library.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.library.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.library.logic.parser.CliSyntax.PREFIX_PROGRESS;
import static seedu.library.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.library.logic.parser.CliSyntax.PREFIX_URL;

import org.junit.jupiter.api.Test;

public class CliSyntaxTest {
    @Test
    public void prefixAuthor_success() {
        assertEquals(new Prefix("a/"), PREFIX_AUTHOR);
    }

    @Test
    public void prefixGenre_success() {
        assertEquals(new Prefix("g/"), PREFIX_GENRE);
    }

    @Test
    public void prefixProgress_success() {
        assertEquals(new Prefix("p/"), PREFIX_PROGRESS);
    }

    @Test
    public void prefixTag_success() {
        assertEquals(new Prefix("t/"), PREFIX_TAG);
    }

    @Test
    public void prefixTitle_success() {
        assertEquals(new Prefix("n/"), PREFIX_TITLE);
    }

    @Test
    public void prefixRating_success() {
        assertEquals(new Prefix("r/"), PREFIX_RATING);
    }

    @Test
    public void prefixUrl_success() {
        assertEquals(new Prefix("u/"), PREFIX_URL);
    }
}
