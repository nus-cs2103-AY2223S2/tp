package seedu.library.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TITLE;

import org.junit.jupiter.api.Test;

public class CliSyntaxTest {
    @Test
    public void prefixTitle_success() {
        assertEquals(new Prefix("n/"), PREFIX_TITLE);
    }
}
