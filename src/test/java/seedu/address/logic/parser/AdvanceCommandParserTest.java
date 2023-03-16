package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AdvanceCommand;
import seedu.address.logic.commands.RejectCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.NamePhoneNumberPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

public class AdvanceCommandParserTest {
    private AdvanceCommandParser parser = new AdvanceCommandParser();

    @Test
    public void parse_validArgs_returnsAdvanceCommand() {
        // no leading and trailing whitespaces
        Person amy = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY).build();
        NamePhoneNumberPredicate predicate = new NamePhoneNumberPredicate(amy.getName(), amy.getPhone());
        AdvanceCommand expectedAdvanceCommand = new AdvanceCommand(predicate, Optional.empty());

        // standard preamble
        assertParseSuccess(parser, " n/Amy Bee p/11111111", expectedAdvanceCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Amy Bee \n \t p/11111111  \t", expectedAdvanceCommand);

        // multiple persons - last person accepted (Bob)
        assertParseSuccess(parser, NAME_DESC_BOB + NAME_DESC_AMY + PHONE_DESC_AMY, expectedAdvanceCommand);

        // one person, multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_BOB + PHONE_DESC_AMY, expectedAdvanceCommand);

        // one person, multiple names - last names accepted
        assertParseSuccess(parser, NAME_DESC_BOB + NAME_DESC_AMY + PHONE_DESC_AMY, expectedAdvanceCommand);

    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AdvanceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AdvanceCommand.MESSAGE_USAGE));
    }
}
