package seedu.vms.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.vms.commons.core.Messages;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.HelpCommand;
import seedu.vms.logic.parser.exceptions.ParseException;


/** Parsers user input.  */
public class VmsParser {
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<featureName>\\S+)(?<arguments>.*)");

    private final BasicParser basicParser = new BasicParser();
    private final PatientParser patientParser = new PatientParser();

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String featureName = matcher.group("featureName");
        final String arguments = matcher.group("arguments");
        switch (featureName) {

        case PatientParser.FEATURE_NAME:
            return patientParser.parse(arguments);

        default:
            return basicParser.parse(userInput);
        }
    }
}
