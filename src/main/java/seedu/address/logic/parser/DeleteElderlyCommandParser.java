package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.person.information.Nric.VALIDATION_REGEX;

import seedu.address.logic.commands.DeleteElderlyCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteElderlyCommandParser implements Parser<DeleteElderlyCommand> {

    public DeleteElderlyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || (!trimmedArgs.matches(VALIDATION_REGEX))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return new DeleteElderlyCommand(trimmedArgs);
    }
}
