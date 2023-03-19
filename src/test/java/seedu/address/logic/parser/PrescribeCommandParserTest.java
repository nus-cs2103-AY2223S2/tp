package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PrescribeCommand;
import seedu.address.model.person.Medication;

class PrescribeCommandParserTest {

    private PrescribeCommandParser parser = new PrescribeCommandParser();

    @Test
    public void parse_validArgs_returnsPrescribeCommand() {
        assertParseSuccess(parser, "1 m/", new PrescribeCommand(INDEX_FIRST_PERSON, new Medication("")));
        assertParseSuccess(parser, "1 m/1 a", new PrescribeCommand(INDEX_FIRST_PERSON, new Medication("1 a")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a m/1 a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrescribeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 m/a", Medication.MESSAGE_CONSTRAINTS);
    }
}
