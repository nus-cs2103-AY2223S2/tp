package taa.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import taa.commons.core.Messages;
import taa.commons.core.index.Index;
import taa.logic.commands.exceptions.CommandException;
import taa.model.Model;
import taa.model.alarm.AlarmList;
import taa.model.student.Student;

/**
 * Deletes a student identified using it's displayed index from the address book.
 */
public class DeleteAlarm extends Command {

    public static final String COMMAND_WORD = "delete_alarm";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ALARM_SUCCESS = "Deleted Alarm: %1$s";

    private final int targetIndex;

    public DeleteAlarm(int targetIndex) {
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
}
