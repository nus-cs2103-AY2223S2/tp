package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Displays the different Organisation helps available
 */
public class HelpOrganisationCommand extends HelpCommand {

    public static final String COMMAND_WORD = "organisation";

    public static final String ORGANISATION_SYNTAX = "alphabetica? chronlogical? Update coming soon~";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(ORGANISATION_SYNTAX);
    }
}
