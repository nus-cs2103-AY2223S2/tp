package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.alarm.Alarm;

/**
 * Defines the add alarm command
 */
public class AddAlarmCommand extends Command {

    public static final String MESSAGE_ALARM_ACKNOWLEDGEMENT = "Added a new alarm!";
    public static final String COMMAND_WORD = "add_alarm";
    public static final String MESSAGE_USAGE = "Format: add_alarm t/{time} c/{comment}";
    private final Alarm alarm;

    public AddAlarmCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addAlarm(alarm);
        return new CommandResult(MESSAGE_ALARM_ACKNOWLEDGEMENT, false, false, true);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AddAlarmCommand) {
            return ((AddAlarmCommand) o).alarm.equals(this.alarm);
        } else {
            return false;
        }
    }
}

