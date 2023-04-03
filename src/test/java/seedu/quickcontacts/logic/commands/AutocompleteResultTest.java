package seedu.quickcontacts.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.logic.parser.Prefix;

public class AutocompleteResultTest {
    @Test
    public void getPrefix() {
        Prefix prefix = new Prefix("m/");
        assertEquals(new AutocompleteResult(prefix, false).getResult(), Optional.of(prefix.getPrefix()));
        assertEquals(new AutocompleteResult().getResult(), Optional.empty());
    }

    @Test
    public void isReplaceCurrent() {
        assertFalse(new AutocompleteResult().isReplaceCurrent());

        Prefix prefix = new Prefix("m/");
        assertFalse(new AutocompleteResult(prefix, false).isReplaceCurrent());
        assertTrue(new AutocompleteResult(prefix, true).isReplaceCurrent());
    }
}
