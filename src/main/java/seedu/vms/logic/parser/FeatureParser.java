package seedu.vms.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.vms.commons.core.Messages;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.HelpCommand;
import seedu.vms.logic.parser.exceptions.ParseException;


public class FeatureParser {
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<featureName>\\S+)(?<arguments>.*)");

    private final BasicParser basicParser = new BasicParser();
    private final PatientParser patientParser = new PatientParser();


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
