package taa.logic.commands;

import taa.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Defines the command class for listing of alarms.
 */
public class ListAlarmsCommand extends Command {

    public static final String COMMAND_WORD = "list_alarms";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(model.listAlarms());
    }
}


