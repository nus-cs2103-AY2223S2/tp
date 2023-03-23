package seedu.vms.logic.parser.keyword;

import seedu.vms.commons.core.Messages;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.keyword.AddCommand;
import seedu.vms.logic.commands.keyword.DeleteCommand;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.FeatureParser;
import seedu.vms.logic.parser.exceptions.ParseException;


/**
 * Parser of vaccination feature commands.
 */
public class KeywordParser extends FeatureParser {
    public static final String FEATURE_NAME = "keyword";


    @Override
    public Command parseCommand(String commandWord, ArgumentMultimap arguments) throws ParseException {
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
