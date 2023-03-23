package seedu.address.logic.parser.contact;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.contact.AddContactCommand;
import seedu.address.logic.commands.contact.DeleteContactCommand;
import seedu.address.logic.commands.contact.EditContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input related to task package.
 */
public class ContactParser {

    /**
     * Parses user input into task related command for execution.
     *
     * @param commandWord command word in user input
     * @param arguments command argument related to specified parameter types
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseContactCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddContactCommand.COMMAND_WORD:
            return new AddContactCommandParser().parse(arguments);

        case EditContactCommand.COMMAND_WORD:
            return new EditContactCommandParser().parse(arguments);

        case DeleteContactCommand.COMMAND_WORD:
            return new DeleteContactCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
