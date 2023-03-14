package seedu.vms.logic.parser;

import java.util.AbstractMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.vms.commons.core.Messages;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.basic.HelpCommand;
import seedu.vms.logic.parser.appointment.AppointmentParser;
import seedu.vms.logic.parser.basic.BasicParser;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.logic.parser.patient.PatientParser;
import seedu.vms.logic.parser.vaccination.VaccinationParser;


/** Parsers user input.  */
public class VmsParser {
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<featureName>\\S+)(?<arguments>.*)");
    public static final Map<String, String> ALTERNATIVE_FEATURE_NAME_MAPPINGS = Map.ofEntries(
            new AbstractMap.SimpleEntry<String, String>("p", "patient"),
            new AbstractMap.SimpleEntry<String, String>("pa", "patient"),
            new AbstractMap.SimpleEntry<String, String>("pat", "patient"),
            new AbstractMap.SimpleEntry<String, String>("a", "appointment"),
            new AbstractMap.SimpleEntry<String, String>("app", "appointment"),
            new AbstractMap.SimpleEntry<String, String>("appo", "appointment"),
            new AbstractMap.SimpleEntry<String, String>("v", "vaccination"),
            new AbstractMap.SimpleEntry<String, String>("vac", "vaccination"),
            new AbstractMap.SimpleEntry<String, String>("vacc", "vaccination")
    );

    private final BasicParser basicParser = new BasicParser();
    private final PatientParser patientParser = new PatientParser();
    private final AppointmentParser appointmentParser = new AppointmentParser();
    private final VaccinationParser vaccinationParser = new VaccinationParser();

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
        switch (ALTERNATIVE_FEATURE_NAME_MAPPINGS.getOrDefault(featureName, featureName)) {

        case PatientParser.FEATURE_NAME:
            return patientParser.parse(arguments);

        case AppointmentParser.FEATURE_NAME:
            return appointmentParser.parse(arguments);

        case VaccinationParser.FEATURE_NAME:
            return vaccinationParser.parse(arguments);

        default:
            return basicParser.parse(userInput);
        }
    }
}
