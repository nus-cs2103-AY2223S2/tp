package seedu.address.logic.commands.delete;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;

/**
 * Deletes a module, lecture or video specified in command from le Tracker
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ":\n"
            + DeleteModuleCommand.MESSAGE_USAGE + "\n"
            + DeleteLectureCommand.MESSAGE_USAGE + "\n"
            + DeleteVideoCommand.MESSAGE_USAGE + "\n"
            + Messages.MESSAGE_CAN_DO_MULTIPLE;
}
