package seedu.vms.logic.parser.vaccination;

import seedu.vms.commons.core.Messages;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.parser.ArgumentMultimap;
import seedu.vms.logic.parser.FeatureParser;
import seedu.vms.logic.parser.exceptions.ParseException;


/**
 * Parser of vaccination feature commands.
 */
public class VaccinationParser extends FeatureParser {
    public static final String FEATURE_NAME = "vaccination";


    @Override
    public Command parseCommand(String commandWord, ArgumentMultimap arguments) throws ParseException {
        switch (commandWord) {

        case AddVaxTypeParser.COMMAND_WORD:
            return new AddVaxTypeParser().parse(arguments);

        case DeleteVaxTypeParser.COMMAND_WORD:
            return new DeleteVaxTypeParser().parse(arguments);

        case EditVaxTypeParser.COMMAND_WORD:
            return new EditVaxTypeParser().parse(arguments);

        case FilterVaxTypeParser.COMMAND_WORD:
            return new FilterVaxTypeParser().parse(arguments);

        case ListVaxTypeParser.COMMAND_WORD:
            return new ListVaxTypeParser().parse(arguments);

        case DetailVaxTypeParser.COMMAND_WORD:
            return new DetailVaxTypeParser().parse(arguments);

        case ClearVaxTypeParser.COMMAND_WORD:
            return new ClearVaxTypeParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
