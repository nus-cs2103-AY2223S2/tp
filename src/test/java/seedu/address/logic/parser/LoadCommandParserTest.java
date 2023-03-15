package seedu.address.logic.parser;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LoadCommand;

public class LoadCommandParserTest {

    @Test
    public void constructor_noIndex_throwsCommandException() {
        assertThrows(NullPointerException.class, () -> new LoadCommand(null));

    }
}
