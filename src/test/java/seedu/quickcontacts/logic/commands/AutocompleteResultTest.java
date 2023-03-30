package seedu.quickcontacts.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.quickcontacts.logic.parser.Prefix;

public class AutocompleteResultTest {
    @Test
    public void getPrefix() {
        Prefix prefix = new Prefix("m/");
        Assertions.assertEquals(new AutocompleteResult(prefix, false).getPrefix(), Optional.of(prefix));
        assertEquals(new AutocompleteResult(null, false).getPrefix(), Optional.empty());
    }

    @Test
    public void isReplaceCurrent() {
        assertTrue(new AutocompleteResult(null, true).isReplaceCurrent());
        assertFalse(new AutocompleteResult(new Prefix("m/"), false).isReplaceCurrent());
    }
}
