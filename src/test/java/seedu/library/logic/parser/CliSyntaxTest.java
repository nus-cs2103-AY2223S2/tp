package seedu.library.logic.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TITLE;

public class CliSyntaxTest {
    @Test
    public void prefixTitle_success() {
        assertEquals(new Prefix("n/"), PREFIX_TITLE);
    }
}
