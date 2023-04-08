package seedu.address.logic.commands;

import seedu.address.model.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Displays the different Event helps available and
 * returns the desired Event category helps
 */
public class HelpEventCommand extends HelpCommand {

    private static final Pattern BASIC_HELP_FORMAT = Pattern.compile("(?<eventCategory>\\S+)(?<arguments>.*)");

    public static final String COMMAND_WORD = "event";

    public static final String EVENT_CATEGORIES = "Event categories:\n"
            + "help event tutorial\n"
            + "help event lab\n"
            + "help event consultation";

    /**
     * Checks the argument and return the desired event help command
     */
    public HelpCommand parse(String args) {
        final Matcher matcher = BASIC_HELP_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new HelpEventCommand();
        }

        final String eventCategory = matcher.group("eventCategory");
        final String arguments = matcher.group("arguments");
        switch (eventCategory) {

        case HelpTutorialCommand.COMMAND_WORD:
            assert arguments.equals("") : "Additional Input Detected";
            return new HelpTutorialCommand();

        case HelpLabCommand.COMMAND_WORD:
            assert arguments.equals("") : "Additional Input Detected";
            return new HelpLabCommand();

        case HelpConsultationCommand.COMMAND_WORD:
            assert arguments.equals("") : "Additional Input Detected";
            return new HelpConsultationCommand();

        default:
            assert false : "Incorrect Category";
            return new HelpEventCommand();
        }
        /*
        if (args.equals(" tutorial")) {
            return new HelpTutorialCommand();
        } else if (args.equals(" lab")) {
            return new HelpLabCommand();
        } else if (args.equals(" consultation")) {
            return new HelpConsultationCommand();
        } else {
            return new HelpEventCommand();
        }*/
    }
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(EVENT_CATEGORIES);
    }
}
