package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.alarm.AlarmList;


/**
 * Deletes an alarm identified using its displayed index.
 */
public class DeleteAlarmCommand extends Command {

    public static final String COMMAND_WORD = "delete_alarm";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the alarm identified by the index number used in the displayed alarm list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ALARM_SUCCESS = "Successfully deleted the alarm";

    private final int targetIndex;

    /**
     * Construct a DeleteAlarmCommand instance with the specified target index
     * @param targetIndex the target index to delete
     */
    public DeleteAlarmCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (targetIndex > AlarmList.getAlarmCount()) {
            throw new CommandException("Alarm not found");
        }
        model.deleteAlarm(this.targetIndex);
        return new CommandResult(MESSAGE_DELETE_ALARM_SUCCESS);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeleteAlarmCommand) {
            return ((DeleteAlarmCommand) o).targetIndex == this.targetIndex;
        } else {
            return false;
        }
    }
}
