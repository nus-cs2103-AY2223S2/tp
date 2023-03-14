package seedu.vms.logic.parser.vaccination;

import seedu.vms.commons.core.Messages;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.parser.FeatureParser;
import seedu.vms.logic.parser.exceptions.ParseException;


/**
 * Parser of vaccination feature commands.
 */
public class VaccinationParser extends FeatureParser {
    public static final String FEATURE_NAME = "vaccination";


    @Override
    public Command parseCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

        case AddVaxTypeParser.COMMAND_WORD:
            return new AddVaxTypeParser().parse(arguments);

        case DeleteVaxTypeParser.COMMAND_WORD:
            return new DeleteVaxTypeParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
