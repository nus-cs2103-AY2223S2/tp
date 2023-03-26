package taa.logic.commands;

import taa.logic.commands.exceptions.CommandException;
import taa.model.Alarm;

import taa.model.Model;


import static java.util.Objects.requireNonNull;


public class AddAlarmCommand extends Command {

    private final Alarm alarm;
    public static final String COMMAND_WORD = "add_alarm";

    public AddAlarmCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);

        model.addAlarm(alarm);
        return new CommandResult("Alarm has been successfully set to +"
                + Integer.toString(alarm.getTime()) + " minutes later.");
    }
}
