package seedu.address.logic.commands.delete;

import static seedu.address.commons.core.Messages.MESSAGE_CONTEXT_USAGE;

import seedu.address.logic.commands.Command;

/**
 * Deletes a module, lecture or video specified in command from le Tracker
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD
            + ":\n"
            + DeleteModuleCommand.MESSAGE_USAGE + "\n\n"
            + DeleteLectureCommand.MESSAGE_USAGE + "\n\n"
            + DeleteVideoCommand.MESSAGE_USAGE + "\n\n"
            + MESSAGE_CONTEXT_USAGE;
}
