package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GROUP;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.FindTimeCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FindTimeCommandParserTest {

    private FindTimeCommandParser parser = new FindTimeCommandParser();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTimeCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_allFieldsPresent_success() throws ParseException, CommandException {
        FindTimeCommand expectedFindTimeCommand =
                new FindTimeCommand(INDEX_FIRST_GROUP, LocalDate.now());
        FindTimeCommand userFindTimeCommand = parser.parse("1");
        CommandResult expectedCommandResult = expectedFindTimeCommand.execute(expectedModel);
        CommandResult commandResult = userFindTimeCommand.execute(model);
        assertEquals(expectedCommandResult, commandResult);
    }
}
