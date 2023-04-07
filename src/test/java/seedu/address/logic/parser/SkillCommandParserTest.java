package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;


import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.SkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NoteContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;



public class SkillCommandParserTest {

    private SkillCommandParser parser = new SkillCommandParser();

    @Test
    public void parse_validArg_returnSkillCommand() throws ParseException {
        Person amy = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withNotes(VALID_NOTE_FRIEND)
                .build();

        NoteContainsKeywordsPredicate predicate = new NoteContainsKeywordsPredicate("friends");
        SkillCommand expectedSkillCommand = new SkillCommand(predicate);
        Command command = parser.parse("friends");


        assertParseSuccess(parser, "friends", expectedSkillCommand);
        //assertParseSuccess(parser, " \n friends \n \t ", expectedSkillCommand);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "       ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SkillCommand.MESSAGE_USAGE));
    }
}
