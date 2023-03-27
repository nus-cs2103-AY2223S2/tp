package seedu.careflow.logic.parser;
import static seedu.careflow.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.careflow.commons.core.Messages.MESSAGE_UNKNOWN_OPERATION_TYPE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.generalcommand.HelpCommand;
import seedu.careflow.logic.parser.drugparser.DrugParser;
import seedu.careflow.logic.parser.exceptions.ParseException;
import seedu.careflow.logic.parser.generalparser.GeneralParser;
import seedu.careflow.logic.parser.patientparser.PatientParser;

/**
 * Represents a Parser that is able to parse user input.
 * CareFlow Parser will distribute commands to DrugParser, PatientParser and GeneralParser based on the
 * operation type. ie. operation type can be "p", "d" or "g"
 */
public class CareFlowParser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<opType>[pdg])\\s(?<commandWord>\\S+)"
            + "(?<arguments>.*)");

    /**
     * Parses user input and distribute different operations to specific parsers.
     * @param userInput full user input string
     * @return the command based on user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        // "d" for drug related operations, "p" for patient related operations and "g" for general operations
        final String opType = matcher.group("opType");
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (opType) {
        case DrugParser.OPERATION_TYPE:
            return new DrugParser().parse(commandWord, arguments);
        case PatientParser.OPERATION_TYPE:
            return new PatientParser().parse(commandWord, arguments);
        case GeneralParser.OPERATION_TYPE:
            return new GeneralParser().parse(commandWord);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_OPERATION_TYPE);
        }
    }
}
