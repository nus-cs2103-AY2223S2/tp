package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.BackupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class BackupCommandParserTest {

    @Test
    public void parser_indexOutOfRange_throwsParseException() {
        String invalidArgs11 = "backup 11";
        String invalidArgs0 = "backup 0";
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        BackupCommand.MESSAGE_USAGE), () -> new BackupCommandParser().parse(invalidArgs11));
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        BackupCommand.MESSAGE_USAGE), () -> new BackupCommandParser().parse(invalidArgs0));
    }


}
