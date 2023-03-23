package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Displays the different Event helps available and
 * returns the desired Event category helps
 */
public class HelpEventCommand extends HelpCommand {

    public static final String COMMAND_WORD = "event";

    public static final String EVENT_CATEGORIES = "Event categories:\n"
            + "help event tutorial\n"
            + "help event lab\n"
            + "help event consultation";

    /**
     * Checks the argument and return the desired event help command
     */
    public HelpCommand parse(String args) {
        if (args.equals(" tutorial")) {
            return new HelpTutorialCommand();
        } else if (args.equals(" lab")) {
            return new HelpLabCommand();
        } else if (args.equals(" consultation")) {
            return new HelpConsultationCommand();
        } else {
            return new HelpEventCommand();
        }
    }
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(EVENT_CATEGORIES);
    }
}
