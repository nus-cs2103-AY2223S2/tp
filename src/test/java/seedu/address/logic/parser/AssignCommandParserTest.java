package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.util.TypicalPersons.INVALID_PERSON_INDEX;
import static seedu.address.model.util.TypicalPersons.INVALID_TASK_INDEX;
import static seedu.address.model.util.TypicalPersons.VALID_PERSON_INDEX;
import static seedu.address.model.util.TypicalPersons.VALID_TASK_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AssignCommand;

public class AssignCommandParserTest {
    private final AssignCommandParser parser = new AssignCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE);

        // missing person index
        assertParseFailure(parser, VALID_TASK_INDEX, expectedMessage);

        // missing task index
        assertParseFailure(parser, VALID_PERSON_INDEX, expectedMessage);
    }

    @Test
    public void parse_validArgs_returnsAssignCommand() {
        assertParseSuccess(parser, VALID_PERSON_INDEX + VALID_TASK_INDEX,
                new AssignCommand(INDEX_FIRST, INDEX_SECOND));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //invalid task index
        assertParseFailure(parser, VALID_PERSON_INDEX + INVALID_TASK_INDEX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));

        //invalid person index
        assertParseFailure(parser, INVALID_PERSON_INDEX + VALID_TASK_INDEX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
    }

}
