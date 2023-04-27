package seedu.address.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HelpEventCommand;
import seedu.address.logic.commands.HelpOrganisationCommand;
import seedu.address.logic.commands.HelpStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand, HelpTutorialCommand,
 * HelpLabCommand or HelpConsultationCommand object
 */
public class HelpCommandParser {

    private static final Pattern BASIC_HELP_FORMAT = Pattern.compile("(?<helpCategory>\\S+)(?<arguments>.*)");
    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns an HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public HelpCommand parse(String args) throws ParseException {
        final Matcher matcher = BASIC_HELP_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new HelpCommand();
        }

        final String helpCategory = matcher.group("helpCategory");
        final String arguments = matcher.group("arguments");
        switch (helpCategory) {

        case HelpEventCommand.COMMAND_WORD:
            return new HelpEventCommand().parse(arguments);

        case HelpStudentCommand.COMMAND_WORD:
            assert arguments.equals("") : "Additional Input Detected";
            return new HelpStudentCommand();

        case HelpOrganisationCommand.COMMAND_WORD:
            assert arguments.equals("") : "Additional Input Detected";
            return new HelpOrganisationCommand();

        default:
            assert false : "Incorrect Category";
            return new HelpCommand();
        }
    }
}
