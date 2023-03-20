package seedu.address.logic.parser;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The type Create command parser.
 */
public class CreateCommandParser implements Parser<CreateCommand> {

    @Override
    public CreateCommand parse(String userInput) throws ParseException {
        try {
            userInput.trim();
            //System.out.println(userInput);
            String[] tokens = userInput.split("-");
            //System.out.println(tokens[0]);
            //System.out.println(tokens[2]);
            Index index = ParserUtil.parseIndex(tokens[0]);
            int days = ParserUtil.parseDay(tokens[1]);
            return new CreateCommand(index, days);
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    CreateCommand.MESSAGE_USAGE), pe);
        }
    }
}
